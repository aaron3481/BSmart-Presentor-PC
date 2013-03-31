/*
 * MainFrame.java
 *
 * Created on __DATE__, __TIME__
 */

package bsp.ui;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 * 
 * @author Jiannan Cai
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
		mMFile_FL = new javax.swing.JMenuItem();
		mMFile_BL = new javax.swing.JMenuItem();
		mMAbout = new javax.swing.JMenu();
		devicePanel.getAccessibleContext().setAccessibleParent(this);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("BSmart Presentor - PC");
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		mMFile.setText("File");

		mMFile_FL.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_F, 0));
		mMFile_FL.setText("File Loader");
		mMFile_FL.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				mMFile_FLActionPerformed(evt);
			}
		});
		mMFile.add(mMFile_FL);

		mMFile_BL.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_B, 0));
		mMFile_BL.setText("Bluetooth Server");
		mMFile_BL.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				mMFile_BLActionPerformed(evt);
			}
		});
		mMFile.add(mMFile_BL);

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

	private void mMFile_BLActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		mMFile_FL.setEnabled(true);
		mMFile_BL.setEnabled(false);
		devicePanel.updateRecord();
		remove(mainPanel);
		repaint();
		add(devicePanel);
		pack();
	}

	private void mMFile_FLActionPerformed(java.awt.event.ActionEvent evt) {
		mMFile_FL.setEnabled(false);
		mMFile_BL.setEnabled(true);
		remove(devicePanel);
		repaint();
		add(mainPanel);
		pack();

	}

	/*private void formComponentRemoved(java.awt.event.ContainerEvent evt) {
		//TODO Editing later;
	}*/

	// Other initialisations that not in initComponets()
	private void myInitComponents() {

		//System.out.println(System.getProperty("os.name"));

		/*this.getContentPane().addContainerListener(
				new java.awt.event.ContainerAdapter() {
					public void componentRemoved(
							java.awt.event.ContainerEvent evt) {
						formComponentRemoved(evt);
					}
				});*/

		screen = Toolkit.getDefaultToolkit().getScreenSize();
		frameSize = this.getPreferredSize();
		windowSize = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds();

		this.setLocation((int) (screen.getWidth() - frameSize.getWidth() - 10),
				(int) (windowSize.height - frameSize.getHeight()));

		mainPanel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		devicePanel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

		//add(devicePanel);

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
	private javax.swing.JMenuItem mMFile_BL;
	private javax.swing.JMenuItem mMFile_FL;
	private javax.swing.JMenuBar mainMenu;
	protected bsp.ui.MainPanel mainPanel;
	// End of variables declaration//GEN-END:variables

	// Screen properties and state keepers. i.e. size etc.
	private Dimension screen, frameSize;
	private Rectangle windowSize;
	//private javax.swing.JPanel preciousPanel;

	// Fix size variables for components.
	private final int PANEL_WIDTH = 317;
	private final int PANEL_HEIGHT = 430;
}