package bsp.connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

public class BluetoothServer {
	private final static UUID SERVER_UUID = new UUID("00001101CADFFFDC194DCCAB3816FFBE",false);
	private final String URL;
	private StreamConnectionNotifier notifier;
	private LocalDevice localDev = null;
	private ServiceRecord serviceRecord;
	private final String paringCode;
	private String currentDeviceMAC;
	private ServerThread serverT;
	private boolean isOpen;
	private boolean isOn;
	
	
	
	public BluetoothServer(){
		paringCode = generateCode();
		currentDeviceMAC = null;
		isOpen = false;
		isOn = false;
		
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
	}
	
	public String getParingCode() {
		return paringCode;
	}
	
	public void startService(){
		try {
			isOn=localDev.setDiscoverable(DiscoveryAgent.GIAC);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		if(isOn&&!isOpen){
			serverT = new ServerThread();
			isOpen=true;
			java.awt.EventQueue.invokeLater(serverT);
		}else{
			System.out.println("Either the Bluetooth device is not on; or" +
					" there is already a server thread is running");
			System.exit(1);
		}
	}
	
	private String generateCode(){
		java.util.Random ra = new java.util.Random(1358);
		int temp=0;
		for(int i=0;i<6;i++){
			temp+=(ra.nextInt(9)+1)*10^i;
		}
		
		return String.valueOf(temp);
	}
	
	
	class ServerThread implements Runnable{
		//private boolean isPaired=false;
		
		@Override
		public void run() {
			// TODO this is the first version of the server
			// and may need to add some thread-safe components here
			try{
				notifier=(StreamConnectionNotifier)Connector.open(URL);
				serviceRecord = localDev.getRecord(notifier);
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
				
				//Check if there is an existing paring device
				if(currentDeviceMAC==null){
					
				}
			}
		}
		
	}

}
