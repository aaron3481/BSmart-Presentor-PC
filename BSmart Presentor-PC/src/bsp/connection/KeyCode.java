package bsp.connection;

public class KeyCode {
	
	//Arrow keys
	public static final int KEY_UP_ARROW=0x26;
	public static final int KEY_DOWN_ARROW=0x28;
	
	//Other keys
	public static final int KEY_ESC=0x1B;
	public static final int KEY_F5=0x74;
	public static final int KEY_SHIFT=0x10;
	public static final int KEY_CTRL=0x11; 
	public static final int KEY_SPACEBAR=0x20;
	public static final int KEY_ALT=0x12; 
	public static final int KEY_TAB=0x9;
	public static final int KEY_END=0x23;
	public static final int KEY_HOME=0x24;
	public static final int KEY_ENTER=0xa;
	
	//Require performance
	public static final int NEXT_SLIDE = 0x0000FFF0;
	public static final int PREVIOUS_SLIDE = 0x0000FFF1;
	public static final int RESTART = 0x0000FFF2;	//restart the presentation
	public static final int EXIT = 0x0000FFF3;
	public static final int SWITCH_WINDOW = 0x0000FFF4;
	public static final int GO_TO_SLIDE = 0x0000FFF5;	
	public static final int ENTER = 0x0000FFF6;
	
	
	public static void main(String[]args){
		System.out.println(NEXT_SLIDE);
		System.out.println(PREVIOUS_SLIDE);
		System.out.println(RESTART);
		System.out.println(EXIT);
		System.out.println(SWITCH_WINDOW);
		System.out.println(GO_TO_SLIDE);
		System.out.println(ENTER);
	}
}
