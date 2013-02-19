package bsp.connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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

	// Public methods
	public ConnectionManager() {
		try {
			localDev = LocalDevice.getLocalDevice();
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
	
	public boolean getOnStatus(){
		return isOn;
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
		private StreamConnectionNotifier notifier;
		private DataInputStream is;

		public ServerThread() throws Exception {
			super();
			URL = "btspp://localhost:" + SERVER_UUID.toString()
					+ ";name=BSPServer" + ";authorize=false";
			localDev.setDiscoverable(DiscoveryAgent.GIAC);
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
				} catch (Exception e) {
					if (!isEndByUser) {
						System.err
								.println("Notifier accepting error. Drop this connection"
										+ "Connection Manager - ServerThread Error");
					} else {
						server = null;
						serverStatus = false;
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
					} catch (Exception e) {
						System.err
								.println("Cannot open data input stream. Drop this connection"
										+ "Connection Manager - ServerThread Error");
						devConn = null;
						continue;
					}
					try {
						String pc = is.readUTF();
						if (!pc.equals(code)) {
							devConn = null;
							continue;
						}
						is.close();
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
		private DataInputStream is;
		private bsp.connection.SystemController col;
		public ISProcThread() throws Exception{
			super();
			is = devConn.openDataInputStream();
		}
	}

}
