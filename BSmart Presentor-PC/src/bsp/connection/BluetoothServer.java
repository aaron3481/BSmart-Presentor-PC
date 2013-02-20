package bsp.connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
//import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

public class BluetoothServer {
	private final static UUID SERVER_UUID = new UUID("00001101CADFFFDC194DCCAB3816FFBE",false);
	private final String URL;
	private StreamConnectionNotifier notifier;
	private LocalDevice localDev = null;
	//private ServiceRecord serviceRecord;
	private final String paringCode;
	//private String currentDeviceMAC;
	private boolean isOpen;
	private boolean isOn;
	private StreamConnection devConn;
	
	private DataInputStream is;
	private DataOutputStream os;
	private ServerThread serverT;
	private ISProcessThread isP;
	private bsp.fileloader.Record record;
	
	private Object streamLock;
	
	public BluetoothServer(){
		paringCode = generateCode();
		//currentDeviceMAC = null;
		isOpen = false;
		isOn = false;
		devConn = null;
		is=null;
		os=null;
		isP=null;
		streamLock = new Object();
		
		URL = "btspp://localhost:" +
				SERVER_UUID.toString() +
				";name=BSPServer" +
				";authorize=false";
		
		try{
			localDev = LocalDevice.getLocalDevice();
		}catch(Exception e){
			System.out.println("Cannot get the local Device.");
			System.exit(1);
		}
		
		System.out.println(paringCode);
		
	}
	
	public String getParingCode() {
		return paringCode;
	}
	
	public void updateRecord(){
		record = bsp.fileloader.Record.getInstance();
	}
	
	public void startService(){
		try {
			isOn=localDev.setDiscoverable(DiscoveryAgent.GIAC);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		if(isOn&&!isOpen){
			isOpen=true;
			serverT = new ServerThread();
			serverT.start();
		}else{
			System.out.println("Either the Bluetooth device is not on; or" +
					" there is already a server thread is running");
			System.exit(1);
		}
	}
	
	
	
	public static void main(String[]arg){
		BluetoothServer se = new BluetoothServer();
		se.startService();
		/*try {
			Thread.sleep(2000);
			se.notifier.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	private String generateCode(){
		java.util.Random ra = new java.util.Random();
		int temp=0;
		for(int i=0;i<6;i++){
			temp+=(ra.nextInt(9)+1)*(Math.pow(10, i));
		}
		
		return String.valueOf(temp);
	}
	
	//Once the connection lost, ISProcessThread and
	//OSProcessThread should return and stop and reset
	//to null
	private void closeConnection(){
		synchronized(streamLock){
			isP=null;
			try{
				if(is!=null){
					is.close();
					is=null;
				}
				if(os!=null){
					os.close();
					os=null;
				}
				if(devConn!=null){
					devConn.close();
					devConn=null;
				}
			}catch(Exception e){
				e.printStackTrace();
			}			
		}		
	}
	
	class ServerThread extends Thread{
		//private boolean isPaired=false;
		
		
		
		@Override
		public void run() {
			// TODO this is the first version of the server
			// and may need to add some thread-safe components here
			try{
				notifier=(StreamConnectionNotifier)Connector.open(URL);
				//serviceRecord = localDev.getRecord(notifier);				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			while(true){
				StreamConnection conn  = null;
				try{
					conn=notifier.acceptAndOpen();
				}catch(Exception e){
					e.printStackTrace();
					continue;
				}
				System.out.println("Has incoming connection...");
				
				if(devConn==null){
					devConn = conn;
					try {
						System.out.println("Openning data input and output streams");
						is = devConn.openDataInputStream();
						os = devConn.openDataOutputStream();
					} catch (IOException e) {
						e.printStackTrace();
						closeConnection();
						continue;
					}
					
					//waiting for pairing code
					String pairing;
					
					try {
						pairing = is.readUTF();
						System.out.println("Receiving pairing code: "+pairing);
						if(!pairing.equals(paringCode)){
							System.out.println("Pairing un-match!");
							closeConnection();
							continue;
						}						
					} catch (IOException e) {
						//TODO may need to add staff here.
						System.out.println("Connection Error");
						closeConnection();
						e.printStackTrace();
						continue;
					}
					
					System.out.println("Starting ISP");
					
					isP = new ISProcessThread();
					//java.awt.EventQueue.invokeLater(new ISProcessThread());
					isP.start();				
				}else
					continue;
			}
		}
		
	}
	
	
	class OSProcessThread extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class ISProcessThread extends Thread{
		private bsp.connection.SystemController col=null;
		
		public ISProcessThread(){
			super();
			try{
				col = new bsp.connection.SystemController();
			}catch(Exception e){
				e.printStackTrace();
				return;
			}
		}
		
		private synchronized void anyzier(String com){
			int first=-1;
			int last=-1;
			int index = com.indexOf("-");
			first = Integer.parseInt(com.substring(0,index));
			if(index!=com.length()-1)
				last = Integer.parseInt(com.substring(index+1));
			//col.perform(record.getType(),first, last,record.getSlideCount());
			col.perform("pdf", first, last, 28);
				
		}
		
		@Override
		public void run() {
			ISProcessThread thisThread = (ISProcessThread) Thread.currentThread();
			System.out.println("Opened");		
			
			while(true){
				while(isP==thisThread){
					System.out.println("Into Main Loop");
					String msg;
					try{
						msg = is.readUTF();
						System.out.println(msg);
					}catch(Exception e){
						//e.printStackTrace();
						System.out.println("ISProcessThread Lost");
						closeConnection();
						break;
					}
					
					if(col!=null)
						anyzier(msg);
					
				}
				break;
			}
			
			//If here, means neither connection lost or 
			//required to close
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			is = null;
		}
	}

}
