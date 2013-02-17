package bsp.connection;

import bsp.connection.KeyCode;

import java.awt.AWTException;
import java.awt.Robot;

public class SystemController {
	private Robot robot;
	
	public SystemController() throws AWTException{
		robot = new Robot();
	}
	
	public boolean perform(int action, int arg1){
		
		switch(action){
		case KeyCode.NEXT_SLIDE:
			for(int i=0;i<arg1;i++)
				robot.keyPress(KeyCode.KEY_DOWN_ARROW);
			return true;
		case KeyCode.PREVIOUS_SLIDE:
			for(int i=0;i<arg1;i++)
				robot.keyPress(KeyCode.KEY_UP_ARROW);
			return true;
		case KeyCode.EXIT:
			robot.keyPress(KeyCode.KEY_ESC);
			return true;
		case KeyCode.RESTART:
			robot.keyPress(KeyCode.KEY_ALT);
			robot.keyPress(KeyCode.KEY_TAB);
			return true;
		}			
		
		return false;
	}
}
