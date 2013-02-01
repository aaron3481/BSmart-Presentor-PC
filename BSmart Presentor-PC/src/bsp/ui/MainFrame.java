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
		devicePanel = new javax.swing.JPanel();
		dL_device = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jSeparator1 = new javax.swing.JSeparator();
		dB_Refresh = new javax.swing.JButton();
		dB_Connect = new javax.swing.JButton();
		mainPanel = new bsp.ui.MainPanel();
		mainMenu = new javax.swing.JMenuBar();
		mMFile = new javax.swing.JMenu();
		mMFile_Open = new javax.swing.JMenuItem();
		mMAbout = new javax.swing.JMenu();
		fileChooser.getAccessibleContext().setAccessibleParent(this);

		devicePanel.setPreferredSize(new java.awt.Dimension(317, 430));

		dL_device.setFont(new java.awt.Font("Segoe UI", 0, 14));
		dL_device.setText("Please choose a device to connect:");

		jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14));
		jTable1.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null }, { null }, { null }, { null },
						{ null }, { null }, { null }, { null }, { null },
						{ null }, { null }, { null }, { null }, { null },
						{ null }, { null }, { null } },
				new String[] { "Devices" }) {
			boolean[] canEdit = new boolean[] { false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jScrollPane1.setViewportView(jTable1);

		dB_Refresh.setText("Refresh ");
		dB_Refresh.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dB_RefreshActionPerformed(evt);
			}
		});

		dB_Connect.setText("Connect");

		javax.swing.GroupLayout devicePanelLayout = new javax.swing.GroupLayout(
				devicePanel);
		devicePanel.setLayout(devicePanelLayout);
		devicePanelLayout
				.setHorizontalGroup(devicePanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								devicePanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												devicePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																devicePanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				jScrollPane1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				292,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addContainerGap())
														.addGroup(
																devicePanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				dL_device)
																		.addContainerGap(
																				95,
																				Short.MAX_VALUE))
														.addGroup(
																devicePanelLayout
																		.createSequentialGroup()
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				69,
																				Short.MAX_VALUE)
																		.addComponent(
																				dB_Refresh)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				dB_Connect)
																		.addGap(77,
																				77,
																				77))))
						.addComponent(jSeparator1,
								javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, 317,
								Short.MAX_VALUE));
		devicePanelLayout
				.setVerticalGroup(devicePanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								devicePanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(dL_device)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												314,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(
												jSeparator1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												10,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												devicePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																dB_Refresh)
														.addComponent(
																dB_Connect))
										.addContainerGap(16, Short.MAX_VALUE)));

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

	private void dB_RefreshActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		this.remove(devicePanel);
		this.repaint();

		add(mainPanel);
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

	// Other initialisations that not in initComponets()
	private void myInitComponents() {
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		frameSize = this.getPreferredSize();
		windowSize = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds();

		this.setLocation((int) (screen.getWidth() - frameSize.getWidth() - 10),
				(int) (windowSize.height - frameSize.getHeight()));

		initFChooser();

		mainPanel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		devicePanel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

		add(devicePanel);

		pack();
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
	private javax.swing.JButton dB_Connect;
	private javax.swing.JButton dB_Refresh;
	private javax.swing.JLabel dL_device;
	private javax.swing.JPanel devicePanel;
	private javax.swing.JFileChooser fileChooser;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JTable jTable1;
	private javax.swing.JMenu mMAbout;
	private javax.swing.JMenu mMFile;
	private javax.swing.JMenuItem mMFile_Open;
	private javax.swing.JMenuBar mainMenu;
	private bsp.ui.MainPanel mainPanel;
	// End of variables declaration//GEN-END:variables

	// Screen properties and state keepers. i.e. size etc.
	private Dimension screen, frameSize, previousScreen;
	private Rectangle windowSize;
	//private javax.swing.JPanel preciousPanel;

	// Fix size variables for components.
	private final int PANEL_WIDTH = 317;
	private final int PANEL_HEIGHT = 430;
}