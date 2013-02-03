package bsp.test;
/*
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import javax.bluetooth.*;
import java.io.*;
public class EchoClient extends MIDlet 
   implements CommandListener,DiscoveryListener  {
    List main_list,dev_list;
    Command exit,ok;
    TextBox cmd;
    Display display;
    java.util.Vector devices,services;
    LocalDevice local;
    DiscoveryAgent agent;
    DataOutputStream dout;
    int currentDevice = 0;       //used as an indicator to the device queried for 
                                 //the echo server
    
public void startApp() {
    main_list = new List("Echo Server",Choice.IMPLICIT);   //the main menu
    dev_list  = new List("Select Device",Choice.IMPLICIT); //the list of devices
    cmd       = new TextBox("Text to echo","",120,TextField.ANY);
    exit      = new Command("Exit",Command.EXIT,1);
    ok        = new Command("Send",Command.OK,1);
    display   = Display.getDisplay(this);

    main_list.addCommand(exit);
    main_list.setCommandListener(this);
    dev_list.addCommand(exit);
    dev_list.setCommandListener(this);
    cmd.addCommand(ok);
    cmd.setCommandListener(this);
    
    main_list.append("Find Echo Server",null);
    display.setCurrent(main_list);
        
    }
public void commandAction(Command com, Displayable dis) {
    if (com == exit){                                              //exit triggered from the main form
        destroyApp(false);
        notifyDestroyed();
    }
    if (com == List.SELECT_COMMAND){
        if (dis == main_list){                                     //select triggered from the main from
            if (main_list.getSelectedIndex() >= 0){                //find devices
                FindDevices();
                do_alert("Searching for devices...", Alert.FOREVER);
            }
        }
        if (dis == dev_list){                                      //select triggered from the device list
            StreamConnection conn = null;
            ServiceRecord service = (ServiceRecord)
                            services.elementAt(dev_list.getSelectedIndex());
            String url = service.getConnectionURL(
                                   ServiceRecord.NOAUTHENTICATE_NOENCRYPT,
                                   false);
            try {
                conn = (StreamConnection) Connector.open(url);       //establish the connection
                dout = new DataOutputStream(conn.openOutputStream());//Get the output stream
                display.setCurrent(cmd);                             //Show the textbox
            } catch (Exception e) {this.do_alert("Error Connecting" , 4000);}
    
        }
     }
    if(com == ok){                                                  //the user is sending a command
        try{
            dout.writeChars(cmd.getString() + "\n");
            dout.flush();
            cmd.setString("");
        } catch (Exception e) {this.do_alert("Error sending data" , 4000);} 
    }
}

public void FindDevices(){
    try{
        devices              = new java.util.Vector();
        LocalDevice local    = LocalDevice.getLocalDevice();
        DiscoveryAgent agent = local.getDiscoveryAgent();
        agent.startInquiry(DiscoveryAgent.GIAC,this);
    }catch(Exception e){this.do_alert("Erron in initiating search" , 4000);}
 }
     
public void FindServices(RemoteDevice device){
    try{
        UUID[] uuids  = new UUID[1];
        uuids[0]      = new UUID("27012f0c68af4fbf8dbe6bbaf7aa432a",false);    //The UUID of the ech service
        local         = LocalDevice.getLocalDevice();
        agent         = local.getDiscoveryAgent();
        agent.searchServices(null,uuids,device,this);            
    }catch(Exception e){this.do_alert("Erron in initiating search" , 4000);}
}
    
public void deviceDiscovered(RemoteDevice remoteDevice,DeviceClass deviceClass) {
    devices.addElement(remoteDevice);
}

public void servicesDiscovered(int transID,ServiceRecord[] serviceRecord) {
    for (int x = 0; x < serviceRecord.length; x++ )
        services.addElement(serviceRecord[x]);
    try{
        dev_list.append(((RemoteDevice)devices.elementAt(currentDevice)).
                                            getFriendlyName(false),null);
    }catch(Exception e){this.do_alert("Erron in initiating search" , 4000);}
}
public void inquiryCompleted(int param){
    switch (param) {
        case DiscoveryListener.INQUIRY_COMPLETED:    //Inquiry completed normally
            if (devices.size() > 0){                 //Atleast one device has been found
                services = new java.util.Vector();
                this.FindServices((RemoteDevice)
                         devices.elementAt(0));     //Check if the first device offers the service
            }else
                do_alert("No device found in range",4000);
        break;
        case DiscoveryListener.INQUIRY_ERROR:       // Error during inquiry
            this.do_alert("Inqury error" , 4000);
        break;
        case DiscoveryListener.INQUIRY_TERMINATED:  // Inquiry terminated by agent.cancelInquiry()
             this.do_alert("Inqury Canceled" , 4000);
        break;
       }
}
    
public void serviceSearchCompleted(int transID, int respCode) {
    switch(respCode) {
        case DiscoveryListener.SERVICE_SEARCH_COMPLETED:
            if(currentDevice == devices.size() -1){ //all devices have been searched
                if(services.size() > 0){
                    display.setCurrent(dev_list);
                }else
                    do_alert("The service was not found",4000);
            }else{                               //search next device
                currentDevice++;
                this.FindServices((RemoteDevice)devices.elementAt(currentDevice));
            }
        break;
        case DiscoveryListener.SERVICE_SEARCH_DEVICE_NOT_REACHABLE:
             this.do_alert("Device not Reachable" , 4000);
        break;
        case DiscoveryListener.SERVICE_SEARCH_ERROR:
             this.do_alert("Service serch error" , 4000);
        break;
        case DiscoveryListener.SERVICE_SEARCH_NO_RECORDS:
            this.do_alert("No records returned" , 4000);
        break;
        case DiscoveryListener.SERVICE_SEARCH_TERMINATED:
            this.do_alert("Inqury Cancled" , 4000);
        break;
     }
}

public void do_alert(String msg,int time_out){
    if (display.getCurrent() instanceof Alert ){
        ((Alert)display.getCurrent()).setString(msg);
        ((Alert)display.getCurrent()).setTimeout(time_out);
    }else{
        Alert alert = new Alert("Bluetooth");
        alert.setString(msg);
        alert.setTimeout(time_out);
        display.setCurrent(alert);
    }
}

public void pauseApp() {}

public void destroyApp(boolean unconditional) {}

}
*/