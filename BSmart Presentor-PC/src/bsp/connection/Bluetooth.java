package bsp.connection;

import javax.bluetooth.*;
import java.util.Vector;

public class Bluetooth implements DiscoveryListener {

	// Defined variables:
	private Object lock;
	private Vector<RemoteDevice> devices;

	private LocalDevice loc_Device;
	private DiscoveryAgent dAgent;

	public Bluetooth() throws Exception {
		lock = new Object();

		devices = new Vector<RemoteDevice>();
		loc_Device = LocalDevice.getLocalDevice();
		System.out.print("Local Device Name: " + loc_Device.getFriendlyName());
		System.out.println("\tLocal Device MAC: "
				+ loc_Device.getBluetoothAddress());
		setdAgent(loc_Device.getDiscoveryAgent());
	}

	// This function start Bluetooth devices inquiry and automatically
	// fill up the passed in JTable.
	// IOException may occur.
	public void startInq(Bluetooth instance) {
		// Strat inquiry
		try {
			dAgent.startInquiry(DiscoveryAgent.LIAC, instance);
		} catch (BluetoothStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			synchronized (lock) {
				lock.wait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deviceDiscovered(RemoteDevice arg0, DeviceClass arg1) {
		// System.out.println("Device discovered: " +
		// arg0.getBluetoothAddress());
		// System.out.println("Discover");
		if (!devices.contains(arg0)) {
			devices.addElement(arg0);
		}

	}

	@Override
	public void inquiryCompleted(int arg0) {
		synchronized (lock) {
			lock.notify();
		}
		switch (arg0) {
		case DiscoveryListener.INQUIRY_COMPLETED:
			System.out.println(devices.size());
			System.out.println("INQUIRY_COMPLETED");
			break;
		case DiscoveryListener.INQUIRY_TERMINATED:
			System.out.println("INQUIRY_TERMINATED");
			break;
		case DiscoveryListener.INQUIRY_ERROR:
			System.out.println("INQUIRY_ERROR");
			break;
		default:
			System.out.println("Unknown Response Code");
			break;
		}

	}

	@Override
	public void serviceSearchCompleted(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void servicesDiscovered(int arg0, ServiceRecord[] arg1) {
		// TODO Auto-generated method stub

	}

	public DiscoveryAgent getdAgent() {
		return dAgent;
	}

	public void setdAgent(DiscoveryAgent dAgent) {
		this.dAgent = dAgent;
	}

	public Vector<RemoteDevice> getDevices() {
		return devices;
	}

}
