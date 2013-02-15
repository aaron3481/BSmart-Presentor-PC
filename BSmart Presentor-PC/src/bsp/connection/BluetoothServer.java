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
	private boolean isOpen;
	
	
	
	public BluetoothServer(){
		paringCode = generateCode();
		currentDeviceMAC = null;
		isOpen = false;
		
		URL = "btspp://localhost:" +
				SERVER_UUID.toString() +
				";name=BSPServer" +
				";authorize=false";
	}
	
	public String getParingCode() {
		return paringCode;
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
			
			
		}
		
	}

}
