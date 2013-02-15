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
	private final static UUID SERVER_UUID = new UUID("CAD00C83466FFFDC194DCCAB3816FFBE",false);
	
	private StreamConnectionNotifier notifier;
	
	private LocalDevice localDev = null;
	
	public static void main(String[]args){
		System.out.println(SERVER_UUID.toString());
	}
}
