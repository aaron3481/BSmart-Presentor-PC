/*
 * MainFrame.java
 *
 * Created on __DATE__, __TIME__
 */

package bsp.ui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;

import bsp.ui.animation.WindowAnimation;

/**
 * 
 * @author __USER__
 */
public class MainFrame extends javax.swing.JFrame {

	/** Creates new form MainFrame */
	public MainFrame() {
		initComponents();
		myInitComponents();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		mainPanel = new bsp.ui.MainPanel();
		devicePanel = new bsp.ui.DevicePanel();
		mainMenu = new javax.swing.JMenuBar();
		mMFile = new javax.swing.JMenu();
		mMFile_Open = new javax.swing.JMenuItem();
		mMAbout = new javax.swing.JMenu();
		devicePanel.getAccessibleContext().setAccessibleParent(this);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("BSmart Presentor - PC");
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);
		addWindowStateListener(new java.awt.event.WindowStateListener() {
			public void windowStateChanged(java.awt.event.WindowEvent evt) {
				formWindowStateChanged(evt);
			}
		});

		mMFile.setText("File");

		mMFile_Open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_O, 0));
		mMFile_Open.setText("Open");
		mMFile_Open.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				mMFile_OpenActionPerformed(evt);
			}
		});
		mMFile.add(mMFile_Open);

		mainMenu.add(mMFile);

		mMAbout.setText("About");
		mainMenu.add(mMAbout);

		setJMenuBar(mainMenu);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 315,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 427,
				Short.MAX_VALUE));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void mMFile_OpenActionPerformed(java.awt.event.ActionEvent evt) {
		add(mainPanel);
		repaint();
		pack();
	}

	private void formWindowStateChanged(java.awt.event.WindowEvent evt) {
		// TODO add your handling code here:
		if (this.getState() == Frame.NORMAL) {
			WindowAnimation.windowNormal(this, 0, previousScreen.getWidth(),
					previousScreen.getHeight());
		} else if (this.getState() == Frame.ICONIFIED) {
			this.previousScreen = this.getPreferredSize();
		}
	}
	
	private void formComponentRemoved(java.awt.event.ContainerEvent evt){
		//TODO Editing later;
	}

	// Other initialisations that not in initComponets()
	private void myInitComponents() {
		
		this.getContentPane().addContainerListener(new java.awt.event.ContainerAdapter() {
			public void componentRemoved(java.awt.event.ContainerEvent evt) {
				formComponentRemoved(evt);
			}
		});
		
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		frameSize = this.getPreferredSize();
		windowSize = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds();

		this.setLocation((int) (screen.getWidth() - frameSize.getWidth() - 10),
				(int) (windowSize.height - frameSize.getHeight()));

		mainPanel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		devicePanel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

		add(devicePanel);

		pack();

	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	protected bsp.ui.DevicePanel devicePanel;
	private javax.swing.JMenu mMAbout;
	private javax.swing.JMenu mMFile;
	private javax.swing.JMenuItem mMFile_Open;
	private javax.swing.JMenuBar mainMenu;
	protected bsp.ui.MainPanel mainPanel;
	// End of variables declaration//GEN-END:variables

	// Screen properties and state keepers. i.e. size etc.
	private Dimension screen, frameSize, previousScreen;
	private Rectangle windowSize;
	//private javax.swing.JPanel preciousPanel;

	// Fix size variables for components.
	private final int PANEL_WIDTH = 317;
	private final int PANEL_HEIGHT = 430;
}