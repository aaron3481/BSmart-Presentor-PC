package bsp.connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
//import java.io.File;
//import java.io.FileInputStream;
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
	private boolean isDiscoveryAble;
	private String lock = new String("WaitLock");
	
	public ConnectionManager() {
		try {
			localDev = LocalDevice.getLocalDevice();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Cannot initialnize local device. Program exiting...",
					"Connection Manager Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
			//e.printStackTrace();
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
		isDiscoveryAble= false;
	}

	/**
	 * Get the copy of the Record. This method will have no effect if previously
	 * called.
	 */
	public void updateRecord() {
		//if (record == null)
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
				if(!isDiscoveryAble){
					localDev.setDiscoverable(DiscoveryAgent.GIAC);
					isDiscoveryAble=true;
				}
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
			e.printStackTrace();
		}

	}	

	public boolean sendRecord() {
		if (devConn != null) {
			try {
				os.write(Packer.getRecordHeaderPak(record));
				
				for (int i = 0; i < record.getSlideCount(); i++) {
					os.write(Packer.getSlidePak(record.getSlide(i)));
				}
				
				String userDir = System.getProperty("user.dir") + "\\tempData";
				ByteBuffer lenBuf = ByteBuffer.allocate(4);
				ByteBuffer dataBuf = ByteBuffer.allocate(4096);
				lenBuf.order(ByteOrder.LITTLE_ENDIAN);
				dataBuf.order(ByteOrder.LITTLE_ENDIAN);
				
				for(int i=0;i<record.getSlideCount();i++){
					lenBuf.clear();
					
					String file = userDir + "\\s" + (i + 1) + ".png";
					File nfile = new File(file);
					int fileLen = (int)nfile.length();
					lenBuf.putInt(fileLen);
					os.write(lenBuf.array());
					
					int readLen=0;
					byte temp[] = new byte[4096];
					FileInputStream in = new FileInputStream(nfile);
					while((readLen=in.read(temp))!=-1){
						dataBuf.clear();
						dataBuf.put(temp,0,readLen);
						os.write(dataBuf.array(),0,readLen);
						synchronized(lock){
							lock.wait();
						}
					}
					in.close();
				}
				
				Thread.sleep(10000);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			} catch (InterruptedException e) {
				e.printStackTrace();
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
						isp = new ISProcThread();
					} catch (Exception e) {
						devConn = null;
						e.printStackTrace();
						continue;
					}
					isp.start();
					
					sendRecord();
					
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
		}

		private void decode(String command) {
			int action;
			int arg1 = 0;
			int index = command.indexOf("-");
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
			byte[] data = new byte[128];
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
				} else if(command.indexOf("Notify")!=-1){
					synchronized(lock){
						lock.notifyAll();
					}
				} 
				else {
					decode(command);
				}

			}
		}
	}

}
