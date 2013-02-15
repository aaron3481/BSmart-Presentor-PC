package bsp.test;

import java.io.*;
import javax.bluetooth.*;
import javax.microedition.io.*;

public class EchoServer {
    public final UUID uuid = new UUID(                              //the uid of the service, it has to be unique,
			"27012f0c68af4fbf8dbe6bbaf7aa432a", false); //it can be generated randomly
    public final String name = "Echo Server";                       //the name of the service
    public final String url  =  "btspp://localhost:" + uuid         //the service url
                                + ";name=" + name 
                                + ";authenticate=false;encrypt=false;";
    LocalDevice local = null;
    StreamConnectionNotifier server = null;
    StreamConnection conn = null;

    public EchoServer() {
    	
    	System.out.println(uuid.toString());
    	
        try {
            System.out.println("Setting device to be discoverable...");
            local = LocalDevice.getLocalDevice();
            local.setDiscoverable(DiscoveryAgent.GIAC);
            System.out.println("Start advertising service...");
            server = (StreamConnectionNotifier)Connector.open(url);
            System.out.println("Waiting for incoming connection...");
            conn = server.acceptAndOpen();
            System.out.println("Client Connected...");
            DataInputStream din   = new DataInputStream(conn.openInputStream());
            while(true){
                String cmd = "";
                char c;
                while (((c = din.readChar()) > 0) && (c!='\n') ){
                    cmd = cmd + c;
                }
                System.out.println("Received " + cmd);
            }
            
        } catch (Exception  e) {System.out.println("Exception Occured: " + e.toString());}
    }
    
    public static void main (String args[]){
        EchoServer echoserver = new EchoServer(); 
    }
    
}