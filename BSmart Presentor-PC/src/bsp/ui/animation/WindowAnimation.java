package bsp.ui.animation;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JFrame;

public class WindowAnimation {

	// Style
	public static final int STYLE_SLIP_ONE = 0;
	public static final int STYLE_SLIP_BOTH = 1;
	
	//Private variables
	private static final int DEFAULT_TIGGER = 120;
	private static final double ACCLARATION = 0.65;
	
	public static void windowMinimize(JFrame frame, int style) {
		
		Dimension frameD = frame.getPreferredSize();
		double width = frameD.getWidth();
		double height = frameD.getHeight();
		
		double temp;
		
		switch(style){
		case STYLE_SLIP_ONE:
			temp = DEFAULT_TIGGER;
			while(height>0){
				height-=8;
				frameD.setSize(width, height);
				frame.setPreferredSize(frameD);
				frame.pack();
				
				try {
					Thread.sleep((long)temp);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(temp>=50) temp*=ACCLARATION;
			}
			break;
		}
		frame.setState(Frame.ICONIFIED);
	}
	
	public static void windowNormal(JFrame frame, int style, double pWidth, double pHeight){
		Dimension frameD = frame.getPreferredSize();
		double width = frameD.getWidth();
		double height = frameD.getHeight();
		
		double temp;
		
		switch(style){
		case STYLE_SLIP_ONE:
			temp = DEFAULT_TIGGER;
			while(height<pHeight){
				height+=8;
				frameD.setSize(width, height);
				frame.setPreferredSize(frameD);
				frame.pack();
				
				try {
					Thread.sleep((long)temp);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(temp>=50) temp*=ACCLARATION;
			}
			frameD.setSize(pWidth, pHeight);
			frame.setPreferredSize(frameD);
			frame.pack();
			break;
		}
	}

}
