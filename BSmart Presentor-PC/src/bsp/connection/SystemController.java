package bsp.connection;

import bsp.connection.KeyCode;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class SystemController {
	private Robot robot;
	
	public SystemController() throws AWTException{
		robot = new Robot();
	}
	
	public boolean perform(int action, int arg1){
		
		switch(action){
		case KeyCode.NEXT_SLIDE:
			for(int i=0;i<arg1;i++){
				robot.keyPress(KeyCode.KEY_DOWN_ARROW);
				robot.keyRelease(KeyCode.KEY_DOWN_ARROW);
				robot.delay(550);
				/*try{
					Thread.sleep(500);
				}catch(Exception e){
					e.printStackTrace();
				}*/
			}
			return true;
		case KeyCode.PREVIOUS_SLIDE:
			for(int i=0;i<arg1;i++){
				robot.keyPress(KeyCode.KEY_UP_ARROW);
				robot.keyRelease(KeyCode.KEY_UP_ARROW);
				robot.delay(150);
			}
			return true;
		case KeyCode.EXIT:
			robot.keyPress(KeyCode.KEY_ESC);
			return true;
		case KeyCode.SWITCH_WINDOW:
			System.out.println("Switch");			
			robot.keyPress(java.awt.event.KeyEvent.VK_ALT);
			robot.keyPress(java.awt.event.KeyEvent.VK_TAB);
			//robot.delay(100);
			robot.keyRelease(java.awt.event.KeyEvent.VK_TAB);
			//robot.delay(100);
			robot.keyRelease(java.awt.event.KeyEvent.VK_ALT);
			//robot.delay(100);
			return true;
		}			
		
		return false;
	}
}
