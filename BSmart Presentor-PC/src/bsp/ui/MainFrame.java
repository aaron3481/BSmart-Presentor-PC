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

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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

		fileChooser = new javax.swing.JFileChooser();
		mainPanel = new javax.swing.JPanel();
		mL_File = new javax.swing.JLabel();
		mTF_Path = new javax.swing.JTextField();
		mB_Open = new javax.swing.JButton();
		mSP_Up = new javax.swing.JSeparator();
		mainMenu = new javax.swing.JMenuBar();
		mMFile = new javax.swing.JMenu();
		mMFile_Open = new javax.swing.JMenuItem();
		mMAbout = new javax.swing.JMenu();
		fileChooser.getAccessibleContext().setAccessibleParent(this);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("BSmart Presentor - PC");
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);
		addWindowStateListener(new java.awt.event.WindowStateListener() {
			public void windowStateChanged(java.awt.event.WindowEvent evt) {
				formWindowStateChanged(evt);
			}
		});

		mL_File.setFont(new java.awt.Font("Segoe UI", 0, 14));
		mL_File.setText("File: ");

		mTF_Path.setEditable(false);

		mB_Open.setText("Open..");
		mB_Open.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				mB_OpenActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(
				mainPanel);
		mainPanel.setLayout(mainPanelLayout);
		mainPanelLayout
				.setHorizontalGroup(mainPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								mainPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(mL_File)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												mTF_Path,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												178,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(mB_Open)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
						.addComponent(mSP_Up,
								javax.swing.GroupLayout.DEFAULT_SIZE, 322,
								Short.MAX_VALUE));
		mainPanelLayout
				.setVerticalGroup(mainPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								mainPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												mainPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(mL_File)
														.addComponent(
																mTF_Path,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(mB_Open))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												mSP_Up,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												10,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(355, Short.MAX_VALUE)));

		mMFile.setText("File");

		mMFile_Open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_O, 0));
		mMFile_Open.setText("Open");
		mMFile.add(mMFile_Open);

		mainMenu.add(mMFile);

		mMAbout.setText("About");
		mainMenu.add(mMAbout);

		setJMenuBar(mainMenu);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void formWindowStateChanged(java.awt.event.WindowEvent evt) {
		// TODO add your handling code here:
		if (this.getState() == Frame.NORMAL) {
			WindowAnimation.windowNormal(this, 0, previousScreen.getWidth(),
					previousScreen.getHeight());
		} else if (this.getState() == Frame.ICONIFIED) {
			this.previousScreen = this.getPreferredSize();
		}
	}

	private void mB_OpenActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		//previousScreen = this.getPreferredSize();
		//WindowAnimation.windowMinimize(this, 0);
		int reval = fileChooser.showOpenDialog(this);
		if (reval == JFileChooser.APPROVE_OPTION) {
			mTF_Path.setText(fileChooser.getSelectedFile().getPath());
			System.out.println(mTF_Path.getText());
		}

	}

	// Other initialisations that not in initComponets()
	private void myInitComponents() {
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		window = this.getPreferredSize();
		winSize = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds();

		this.setLocation((int) (screen.getWidth() - window.getWidth() - 10),
				(int) (winSize.height - window.getHeight()));

		initFChooser();
	}

	// Initialise the file chooser. Only the .ppt or .pdf files
	// can be shown
	private void initFChooser() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"ppt or pdf", "ppt", "pdf");
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(filter);
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
	private javax.swing.JFileChooser fileChooser;
	private javax.swing.JButton mB_Open;
	private javax.swing.JLabel mL_File;
	private javax.swing.JMenu mMAbout;
	private javax.swing.JMenu mMFile;
	private javax.swing.JMenuItem mMFile_Open;
	private javax.swing.JSeparator mSP_Up;
	private javax.swing.JTextField mTF_Path;
	private javax.swing.JMenuBar mainMenu;
	private javax.swing.JPanel mainPanel;
	// End of variables declaration//GEN-END:variables

	private Dimension screen, window, previousScreen;
	private Rectangle winSize;

}