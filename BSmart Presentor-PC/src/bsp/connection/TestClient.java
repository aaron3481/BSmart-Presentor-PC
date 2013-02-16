package bsp.connection;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;


public class TestClient implements DiscoveryListener{
	private final static UUID SERVER_UUID = new UUID("00001101CADFFFDC194DCCAB3816FFBE",false);
	private Vector<ServiceRecord> records=new Vector<ServiceRecord>();
	private Vector<RemoteDevice> remoteD=new Vector<RemoteDevice>();
	private UUID[] uuidSet=new UUID[]{new UUID(0x1101),SERVER_UUID};
	
	private DiscoveryAgent discoveryAgent=null;
	private DataOutputStream dos=null;
	
	private int []transIDs;
	
	public TestClient(){
		try{
			discoveryAgent = LocalDevice.getLocalDevice().getDiscoveryAgent();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public synchronized void start(){
		try{
			discoveryAgent.startInquiry(DiscoveryAgent.GIAC, this);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());			
			return;
		}
		
		try{
			
			wait();
		} 
		catch(InterruptedException ex){
			System.out.println(ex.getMessage());
			//clientForm.appendInfo(ex.getMessage());
			return;
		}
		
		transIDs = new int[remoteD.size()];
		
		for(int i=0;i<remoteD.size(); i++ ){
			RemoteDevice rd=(RemoteDevice)remoteD.elementAt(i);
			try{
				
				transIDs[i]=discoveryAgent.searchServices(null, uuidSet, rd, this);
			}
			catch(BluetoothStateException ex){
				continue;
			}
		}
		
		try{
			wait();
		}
		catch(InterruptedException ex){
			System.out.println(ex.getMessage());
			return;
		}
		
		System.out.println(records.size());
		
		
	}
	
	@Override
	public void deviceDiscovered(RemoteDevice arg0, DeviceClass arg1) {
		// TODO Auto-generated method stub
		if(remoteD.indexOf(arg0)==-1){
			remoteD.addElement(arg0);
		}
	}
	@Override
	public void inquiryCompleted(int arg0) {
		// TODO Auto-generated method stub
		synchronized(this){
			notify();
		}
	}
	@Override
	public void serviceSearchCompleted(int arg0, int arg1) {
		// TODO Auto-generated method stub
		for(int i=0;i<transIDs.length;i++){
			if(transIDs[i]==arg0){
				transIDs[i]=-1;
			}
		}
		boolean finished=false;
		for(int i=0;i<transIDs.length;i++){
			if(transIDs[i]==-1){
				finished=true;
				break;
			}
		}
		
		if(finished){
			synchronized(this){
				notify();
			}
		}
	}
	@Override
	public void servicesDiscovered(int arg0, ServiceRecord[] arg1) {
		// TODO Auto-generated method stub
		for(int i=0; i<arg1.length;i++)
			records.add(arg1[i]);
	}
	
	
	public static void main(String[]arg){
		TestClient client = new TestClient();
		client.start();
	}
	
	
	
}
