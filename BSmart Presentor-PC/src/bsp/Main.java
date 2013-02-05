package bsp;

import java.awt.EventQueue;

//import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[]args){
		Runnable r = new Runnable(){
			public void run(){
				bsp.ui.MainFrame frame = new bsp.ui.MainFrame();
				frame.setVisible(true);
			}
		};
		EventQueue.invokeLater(r);
		//SwingUtilities.invokeLater(r);
	}
}
