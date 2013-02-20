package bsp.connection;

import java.io.DataOutputStream;
import java.nio.ByteOrder;
//import java.io.IOException;
import java.util.Vector;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.Connector;

public class TestClient implements DiscoveryListener {
	private final static UUID SERVER_UUID = new UUID(
			"00001101CADFFFDC194DCCAB3816FFBE", false);
	private Vector<ServiceRecord> records = new Vector<ServiceRecord>();
	private Vector<RemoteDevice> remoteD = new Vector<RemoteDevice>();
	private UUID[] uuidSet = new UUID[] { SERVER_UUID, new UUID(0x1101) };

	private DiscoveryAgent discoveryAgent = null;
	private DataOutputStream dos = null;

	private int[] transIDs;
	
	private boolean wait = true;

	public TestClient(String paringCode) {
		try {
			discoveryAgent = LocalDevice.getLocalDevice().getDiscoveryAgent();
			discoveryAgent.startInquiry(DiscoveryAgent.GIAC, this);
			while(wait){
				
			}
			//wait();
			wait=true;
			transIDs = new int[remoteD.size()];

			for (int i = 0; i < remoteD.size(); i++) {
				RemoteDevice rd = (RemoteDevice) remoteD.elementAt(i);
				try {

					transIDs[i] = discoveryAgent.searchServices(null, uuidSet,
							rd, this);
					//System.out.println(transIDs[i]);
				} catch (BluetoothStateException ex) {
					continue;
				}
			}
			//wait();
			while(wait)
			{}
			StreamConnection con = (StreamConnection) Connector.open(records
					.get(0).getConnectionURL(
							ServiceRecord.AUTHENTICATE_NOENCRYPT, false));
			
			dos = con.openDataOutputStream();
			
			byte[] data = paringCode.getBytes();
			java.nio.ByteBuffer buff = java.nio.ByteBuffer.wrap(data);
			buff.order(ByteOrder.LITTLE_ENDIAN);
			
			dos.write(buff.array());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendCommand(String com) throws Exception{
		byte[] data = com.getBytes();
		java.nio.ByteBuffer buff = java.nio.ByteBuffer.wrap(data);
		buff.order(ByteOrder.LITTLE_ENDIAN);
		dos.write(buff.array());
		dos.flush();
	}

	/*public synchronized void start() {
		
		 * System.out.println(new UUID(0x1101));
		 * System.out.println(uuidSet.length); String jj=null; try {
		 * jj=discoveryAgent.selectService(SERVER_UUID,
		 * ServiceRecord.AUTHENTICATE_NOENCRYPT, false); } catch
		 * (BluetoothStateException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * System.out.println(jj);
		 * 
		 * try { StreamConnection conn = (StreamConnection)Connector.open(jj);
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 

		try {
			discoveryAgent.startInquiry(DiscoveryAgent.GIAC, this);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return;
		}

		try {

			wait();
		} catch (InterruptedException ex) {
			System.out.println(ex.getMessage());
			// clientForm.appendInfo(ex.getMessage());
			return;
		}

		transIDs = new int[remoteD.size()];

		for (int i = 0; i < remoteD.size(); i++) {
			RemoteDevice rd = (RemoteDevice) remoteD.elementAt(i);
			try {

				transIDs[i] = discoveryAgent.searchServices(null, uuidSet, rd,
						this);
				System.out.println(transIDs[i]);
			} catch (BluetoothStateException ex) {
				continue;
			}
		}

		try {
			wait();
		} catch (InterruptedException ex) {
			System.out.println(ex.getMessage());
			return;
		}

		System.out.println(records.size());
		System.out.println(remoteD.size());

		try {
			StreamConnection con = (StreamConnection) Connector.open(records
					.get(0).getConnectionURL(
							ServiceRecord.AUTHENTICATE_NOENCRYPT, false));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/

	@Override
	public void deviceDiscovered(RemoteDevice arg0, DeviceClass arg1) {
		// TODO Auto-generated method stub
		if (remoteD.indexOf(arg0) == -1) {
			/*
			 * try { System.out.print(arg0.getFriendlyName(false)); } catch
			 * (IOException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */
			System.out.println("\t" + arg0.getBluetoothAddress());
			remoteD.addElement(arg0);
		}
	}

	@Override
	public void inquiryCompleted(int arg0) {
		wait=false;
	}

	@Override
	public void serviceSearchCompleted(int arg0, int respCode) {
		// TODO Auto-generated method stub
		switch (respCode) {
		case DiscoveryListener.SERVICE_SEARCH_COMPLETED:
			for (int i = 0; i < transIDs.length; i++) {
				if (transIDs[i] == arg0) {
					transIDs[i] = -1;
				}
			}
			boolean finished = false;
			for (int i = 0; i < transIDs.length; i++) {
				if (transIDs[i] == -1) {
					finished = true;
					break;
				}
			}
			
			wait=false;
			
/*
			if (finished) {
				synchronized (this) {
					notify();
				}
			}*/
			break;
		case DiscoveryListener.SERVICE_SEARCH_DEVICE_NOT_REACHABLE:
			// this.do_alert("Device not Reachable" , 4000);
			System.out.println("Device not Reachable");
			break;
		case DiscoveryListener.SERVICE_SEARCH_ERROR:
			// this.do_alert("Service serch error" , 4000);
			System.out.println("Service serch error");
			break;
		case DiscoveryListener.SERVICE_SEARCH_NO_RECORDS:
			// this.do_alert("No records returned" , 4000);
			System.out.println("No records returned");
			break;
		case DiscoveryListener.SERVICE_SEARCH_TERMINATED:
			// this.do_alert("Inqury Cancled" , 4000);
			break;
		}

	}

	@Override
	public void servicesDiscovered(int arg0, ServiceRecord[] arg1) {
		// TODO Auto-generated method stub
		System.out.println(123);
		for (int i = 0; i < arg1.length; i++)
			records.add(arg1[i]);
	}

	public static void main(String[] arg) {
		// TestClient client = new TestClient();
		// client.start();
		int a = 0x0000fff0;
		System.out.println(String.valueOf(a));
	}

}
