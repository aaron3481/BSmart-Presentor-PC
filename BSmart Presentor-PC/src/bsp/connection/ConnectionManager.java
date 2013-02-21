package bsp.connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import bsp.fileloader.Record;
import java.beans.PropertyChangeSupport;
import javax.swing.JOptionPane;

public class ConnectionManager {
	private boolean isOn; // whether the Bluetooth device is on.
	private boolean serverStatus;
	private boolean isEndByUser;
	private LocalDevice localDev;
	private final String code;
	private ServerThread server;
	private ISProcThread isp;
	private Record record;
	private PropertyChangeSupport changeSupport;
	private StreamConnection devConn;
	private StreamConnectionNotifier notifier;
	private DataInputStream is;
	private DataOutputStream os;

	// Public methods
	public ConnectionManager() {
		try {
			localDev = LocalDevice.getLocalDevice();
			localDev.setDiscoverable(DiscoveryAgent.GIAC);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Cannot initialnize local device. Program exiting...",
					"Connection Manager Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		isOn = LocalDevice.isPowerOn();
		serverStatus = false;
		isEndByUser = false;
		code = generateCode();
		server = null;
		isp = null;
		record = null;
		devConn = null;
		notifier = null;
		is = null;
		os = null;
		changeSupport = new PropertyChangeSupport(this);
	}

	/**
	 * Get the copy of the Record. This method will have no effect if previously
	 * called.
	 */
	public void updateRecord() {
		if (record == null)
			record = Record.getInstance();
	}

	public boolean getServerStatus() {
		return serverStatus;
	}

	public String getCode() {
		return code;
	}

	public boolean getOnStatus() {
		return isOn;
	}

	public String getlocName() {
		if (localDev == null)
			return null;
		else
			return localDev.getFriendlyName();
	}

	public String getlocAddr() {
		if (localDev == null)
			return null;
		else
			return localDev.getBluetoothAddress();
	}

	public synchronized void addPropertyChangeListener(
			java.beans.PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	public synchronized void removePropertyChangeListener(
			java.beans.PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	public void startServer() {
		if (!serverStatus) {
			try {
				server = new ServerThread();
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			serverStatus = true;
			changeSupport.firePropertyChange("SERVER_STATUS", new Boolean(
					!serverStatus), new Boolean(serverStatus));
			server.start();
		}
	}

	public void close() {
		try {
			isEndByUser = true;
			notifier.close();
		} catch (IOException e) {
			// System.out.println(123123123);
		}

	}

	public boolean sendRecord() {
		if (devConn != null) {
			try {
				os.write(Packer.getRecordHeaderPak(record));
				os.flush();
				for (int i = 0; i < record.getSlideCount(); i++) {
					os.write(Packer.getSlidePak(record.getSlide(i)));
					os.flush();
				}
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	// Private methods
	private String generateCode() {
		java.util.Random ra = new java.util.Random();
		int temp = 0;
		for (int i = 0; i < 6; i++) {
			temp += (ra.nextInt(9) + 1) * (Math.pow(10, i));
		}

		return String.valueOf(temp);
	}

	class ServerThread extends Thread {
		private UUID SERVER_UUID = new UUID("00001101CADFFFDC194DCCAB3816FFBE",
				false);
		private String URL;

		public ServerThread() throws Exception {
			super();
			URL = "btspp://localhost:" + SERVER_UUID.toString()
					+ ";name=BSPServer" + ";authorize=false";
			System.out.println(URL);
		}

		@Override
		public void run() {
			ServerThread thisT = (ServerThread) Thread.currentThread();
			try {
				notifier = (StreamConnectionNotifier) Connector.open(URL);
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Cannot Open the server. Please try again later",
						"Connection Manager - ServerThread Error",
						JOptionPane.ERROR_MESSAGE);
				serverStatus = false;
				changeSupport.firePropertyChange("SERVER_STATUS", new Boolean(
						!serverStatus), new Boolean(serverStatus));
				return;
			}
			while (server == thisT) {
				StreamConnection conn = null;
				try {
					conn = notifier.acceptAndOpen();
					System.out.println("Has Incoming Connection");
				} catch (Exception e) {
					if (!isEndByUser) {
						System.err
								.println("Notifier accepting error. Drop this connection"
										+ "Connection Manager - ServerThread Error");
					} else {
						isEndByUser = false;
						server = null;
						serverStatus = false;
						devConn = null;
						changeSupport.firePropertyChange("SERVER_STATUS",
								new Boolean(!serverStatus), new Boolean(
										serverStatus));
					}
					continue;
				}

				if (devConn == null) {
					devConn = conn;
					try {
						is = devConn.openDataInputStream();
						os = devConn.openDataOutputStream();
					} catch (Exception e) {
						System.err
								.println("Cannot open data input stream. Drop this connection"
										+ "Connection Manager - ServerThread Error");
						devConn = null;
						continue;
					}
					try {

						byte[] data = new byte[6];
						is.read(data);

						// Since C/C++ packing byte[] in little_endian, so it
						// need to convert.
						ByteBuffer buff = ByteBuffer.wrap(data);
						buff.order(ByteOrder.LITTLE_ENDIAN);

						String pc = new String(buff.array());
						System.out.println(pc.length());
						if (!pc.equals(code)) {
							System.out.println(pc.length() + "eeee");
							devConn = null;
							continue;
						}
					} catch (Exception e) {
						System.err.println("Paring Error."
								+ "Connection Manager - ServerThread Error");
						devConn = null;
						continue;
					}

					try {
						isp = new ISProcThread();
					} catch (Exception e) {
						devConn = null;
						e.printStackTrace();
						continue;
					}
					isp.start();
				} else
					continue; // ignore other incoming connection once has
								// paired.

			}
		}
	}

	class ISProcThread extends Thread {
		private bsp.connection.SystemController col;

		public ISProcThread() throws Exception {
			super();
			col = new bsp.connection.SystemController();
			changeSupport.firePropertyChange("ISP_STATUS", new Boolean(false),
					new Boolean(true));
			System.out.println("Init. ISP");
		}

		private void decode(String command) {
			int action;
			int arg1 = 0;
			int index = command.indexOf("-");
			System.out.println(command);
			action = Integer.parseInt(command.substring(0, index));
			if (index != command.length() - 1)
				arg1 = Integer.parseInt(command.substring(index + 1));
			if (record != null)
				col.perform(record.getType(), action, arg1,
						record.getSlideCount());
			else {
				col.perform(null, action, arg1, 0);
			}
		}

		@Override
		public void run() {
			ISProcThread thisT = (ISProcThread) Thread.currentThread();
			byte[] data = new byte[256];
			ByteBuffer buff;

			while (isp == thisT) {
				String command = "";
				try {
					int readlen = is.read(data);
					byte[] copy = new byte[readlen];
					System.arraycopy(data, 0, copy, 0, readlen);
					buff = ByteBuffer.wrap(copy);
					buff.order(ByteOrder.LITTLE_ENDIAN);
					command = new String(buff.array());

				} catch (Exception e) {
					// Connection error
					e.printStackTrace();
					devConn = null;
					isp = null;
					changeSupport.firePropertyChange("ISP_STATUS", new Boolean(
							true), new Boolean(false));
					continue;
				}

				if (command.equals("CONN_CLOSE")) {
					try {
						notifier.close(); // this cause the ServerThread close;
					} catch (IOException e) {
						e.printStackTrace();
					}
					isp = null;
					changeSupport.firePropertyChange("ISP_STATUS", new Boolean(
							true), new Boolean(false));
					continue;
				} else {
					decode(command);
				}

			}
		}
	}

}
