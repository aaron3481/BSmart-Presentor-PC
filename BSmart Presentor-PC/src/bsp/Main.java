package bsp;

import java.awt.EventQueue;

public class Main {
	public static void main(String[]args){
		Runnable r = new Runnable(){
			public void run(){
				bsp.ui.MainFrame frame = new bsp.ui.MainFrame();
				frame.setVisible(true);
				System.out.println(1);
			}
		};
		EventQueue.invokeLater(r);
	}
}
