package bsp.ui.animation;

import java.awt.Dimension;
import javax.swing.JFrame;

public class WindowAnimation {

	//Horizontal Style
	static final int HORSTYLE_SLIP = 0;
	//Vertical Style
	static final int VERSTYLE_SLIP = 0;
	
	public static void action(JFrame frame, int horStyle, int horTimems,
			int verStyle, int verTimems) {
		
		Dimension frameD = frame.getPreferredSize();
		double width = frameD.getWidth();
		double height = frameD.getHeight();
		
		
	}
}
