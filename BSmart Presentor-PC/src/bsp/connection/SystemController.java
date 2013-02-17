package bsp.connection;

import bsp.connection.KeyCode;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

public class SystemController {
	private Robot robot;
	private final String osName;
	private final String osVersion;
	
	public SystemController() throws AWTException{
		robot = new Robot();
		osName = System.getProperty("os.name");
		osVersion = System.getProperty("os.version");
	}
	
	public void perform(String fileType, int action, int arg1, int arg2){
		switch(action){
		case KeyCode.NEXT_SLIDE:
			if(fileType!=null){
				if(osName.contains("Windows")){
					robot.keyPress(KeyCode.KEY_DOWN_ARROW);
					robot.keyRelease(KeyCode.KEY_DOWN_ARROW);
				}
				
			}
			break;
		case KeyCode.PREVIOUS_SLIDE:
			if(fileType!=null){
				if(osName.contains("Windows")){
					robot.keyPress(KeyCode.KEY_UP_ARROW);
					robot.keyRelease(KeyCode.KEY_UP_ARROW);
				}
				
			}
			break;
		case KeyCode.GO_TO_SLIDE:
			if(fileType!=null){
				if(arg1<(arg2/2)){
					robot.keyPress(KeyCode.KEY_HOME);
					robot.keyRelease(KeyCode.KEY_HOME);
					robot.keyPress(KeyCode.KEY_ESC);
					robot.keyRelease(KeyCode.KEY_ESC);
					for(int i=0;i<arg1-1;i++){
						robot.keyPress(KeyCode.KEY_DOWN_ARROW);
						robot.keyRelease(KeyCode.KEY_DOWN_ARROW);
						robot.delay(50);
					}
				}else{
					robot.keyPress(KeyCode.KEY_END);
					robot.keyRelease(KeyCode.KEY_END);
					robot.keyPress(KeyCode.KEY_ESC);
					robot.keyRelease(KeyCode.KEY_ESC);
					for(int i=0;i<arg2-arg1;i++){
						robot.keyPress(KeyCode.KEY_UP_ARROW);
						robot.keyRelease(KeyCode.KEY_UP_ARROW);
						robot.delay(50);
					}
				}
				
				robot.keyPress(KeyCode.KEY_SHIFT);
				robot.keyPress(KeyCode.KEY_F5);
				robot.keyRelease(KeyCode.KEY_F5);
				robot.keyRelease(KeyCode.KEY_SHIFT);
				
			}
			break;
		case KeyCode.EXIT:
			robot.keyPress(KeyCode.KEY_ESC);
			break;
		case KeyCode.SWITCH_WINDOW:
			System.out.println("Switch");	
			
			try {
				String dd = "cmd /c "+System.getProperty("user.dir")+"\\explorer.lnk";
				
				Runtime.getRuntime().exec(dd);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			/*robot.keyPress(java.awt.event.KeyEvent.VK_ALT);
			robot.keyPress(java.awt.event.KeyEvent.VK_TAB);
			//robot.delay(100);
			robot.keyRelease(java.awt.event.KeyEvent.VK_TAB);
			//robot.delay(100);
			robot.keyRelease(java.awt.event.KeyEvent.VK_ALT);*/
			//robot.delay(100);
			break;
		}			
		
	}
	
	public static void main(String[]arg){
		System.out.println("cmd /c "+System.getProperty("user.dir")+"\\explorer.lnk");
	}
}
