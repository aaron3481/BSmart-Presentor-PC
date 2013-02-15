/*
 * MainPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package bsp.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import bsp.fileloader.Loader;

/**
 * 
 * @author Jiannan Cai
 */
public class MainPanel extends javax.swing.JPanel {

	/** Creates new form MainPanel */
	public MainPanel() {
		// thisPanel = this;
		initComponents();
		initMyComponents();
	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		fileChooser = new javax.swing.JFileChooser();
		loadFile = new javax.swing.JProgressBar();
		propertyP = new javax.swing.JPanel();
		pTypeL = new javax.swing.JLabel();
		pType = new javax.swing.JLabel();
		pNameL = new javax.swing.JLabel();
		pName = new javax.swing.JLabel();
		pNumCL = new javax.swing.JLabel();
		pNumC = new javax.swing.JLabel();
		pCumL = new javax.swing.JLabel();
		pCum = new javax.swing.JLabel();
		pPathL = new javax.swing.JLabel();
		pPath = new javax.swing.JLabel();
		ll_Path = new javax.swing.JLabel();
		TF_Path = new javax.swing.JTextField();
		b_Open = new javax.swing.JButton();
		jSeparator1 = new javax.swing.JSeparator();
		b_Load = new javax.swing.JButton();
		b_TimeFrame = new javax.swing.JButton();
		b_Send = new javax.swing.JButton();
		jSeparator2 = new javax.swing.JSeparator();

		propertyP.setPreferredSize(new java.awt.Dimension(315, 330));

		pTypeL.setText("File Type: ");

		pType.setText(" ");

		pNameL.setText("File Nmae: ");

		pName.setText(" ");

		pNumCL.setText("Number of Slides: ");

		pNumC.setText(" ");

		pCumL.setText("Number of Notes: ");

		pCum.setText(" ");

		pPathL.setText("File Path: ");

		pPath.setText(" ");

		javax.swing.GroupLayout propertyPLayout = new javax.swing.GroupLayout(
				propertyP);
		propertyP.setLayout(propertyPLayout);
		propertyPLayout
				.setHorizontalGroup(propertyPLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								propertyPLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												propertyPLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																pPath,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																287,
																Short.MAX_VALUE)
														.addGroup(
																propertyPLayout
																		.createSequentialGroup()
																		.addComponent(
																				pTypeL)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				pType,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				213,
																				Short.MAX_VALUE))
														.addGroup(
																propertyPLayout
																		.createSequentialGroup()
																		.addComponent(
																				pNameL)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				pName,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				205,
																				Short.MAX_VALUE))
														.addGroup(
																propertyPLayout
																		.createSequentialGroup()
																		.addComponent(
																				pNumCL)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				pNumC,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				157,
																				Short.MAX_VALUE))
														.addGroup(
																propertyPLayout
																		.createSequentialGroup()
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				pCumL)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				pCum,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				157,
																				Short.MAX_VALUE))
														.addComponent(pPathL))
										.addContainerGap()));
		propertyPLayout
				.setVerticalGroup(propertyPLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								propertyPLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												propertyPLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(pTypeL)
														.addComponent(pType))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												propertyPLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(pNameL)
														.addComponent(pName))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												propertyPLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(pNumCL)
														.addComponent(pNumC))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												propertyPLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(pCumL)
														.addComponent(pCum))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(pPathL)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(pPath)
										.addContainerGap(137, Short.MAX_VALUE)));

		setPreferredSize(new java.awt.Dimension(315, 430));
		setRequestFocusEnabled(false);

		ll_Path.setFont(new java.awt.Font("Segoe UI", 0, 14));
		ll_Path.setText("File: ");

		TF_Path.setEditable(false);
		TF_Path.setText("Aaron Cai");

		b_Open.setText("Open..");
		b_Open.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				b_OpenActionPerformed(evt);
			}
		});

		b_Load.setText("Load");
		b_Load.setPreferredSize(new java.awt.Dimension(95, 25));
		b_Load.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				b_LoadActionPerformed(evt);
			}
		});

		b_TimeFrame.setText("Time Frame");
		b_TimeFrame.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				b_TimeFrameActionPerformed(evt);
			}
		});

		b_Send.setText("Send");
		b_Send.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				b_SendActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(ll_Path)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(TF_Path,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										191, Short.MAX_VALUE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(b_Open).addContainerGap())
				.addComponent(jSeparator1,
						javax.swing.GroupLayout.DEFAULT_SIZE, 335,
						Short.MAX_VALUE)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(b_Load,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										103,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(b_Send,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										77,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(b_TimeFrame)
								.addContainerGap(14, Short.MAX_VALUE))
				.addComponent(jSeparator2,
						javax.swing.GroupLayout.DEFAULT_SIZE, 335,
						Short.MAX_VALUE));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(ll_Path)
												.addComponent(b_Open)
												.addComponent(
														TF_Path,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jSeparator1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										10,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										298, Short.MAX_VALUE)
								.addComponent(jSeparator2,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										10,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														b_Load,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(b_TimeFrame)
												.addComponent(b_Send))
								.addContainerGap()));
	}// </editor-fold>
		// GEN-END:initComponents

	private void b_TimeFrameActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:

	}

	private void b_LoadActionPerformed(java.awt.event.ActionEvent evt) {
		String path = TF_Path.getText();
		String extension = path.substring(path.lastIndexOf('.'));
		loadFile.setValue(0);
		add(loadFile);
		repaint();

		if (extension.equals(".pptx")) {
			loader = new bsp.fileloader.LoadPPTX(path);
		} else if (extension.equals(".ppt")) {
			pptGuide = new PPTGuide((javax.swing.JFrame) this.getRootPane()
					.getParent(), true);
			// pptGuide
			pptGuide.setVisible(true);

			if (pptGuide.getReturnStatus() == PPTGuide.RET_OK)
				loader = new bsp.fileloader.LoadPPT(path);
			else {
				b_Load.setEnabled(false);
				TF_Path.setText("");
				remove(loadFile);
				repaint();

				return;
			}
		} else if (extension.equals(".pdf")) {

			pdfGuide = new PDFGuide((javax.swing.JFrame) this.getRootPane()
					.getParent(), true);
			pdfGuide.setVisible(true);

			if (pdfGuide.getReturnStatus() == PDFGuide.RET_OK)
				loader = new bsp.fileloader.LoadPDF(path, null);
			else if (pdfGuide.getReturnStatus() == PDFGuide.RET_OPEN) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"pdf file", "pdf");
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.setFileFilter(filter);

				int reval = fileChooser.showOpenDialog(this);
				String notePath = null;
				if (reval == JFileChooser.APPROVE_OPTION)
					notePath = fileChooser.getSelectedFile().getPath();

				loader = new bsp.fileloader.LoadPDF(path, notePath);
				this.initFChooser();
			} else {
				b_Load.setEnabled(false);
				TF_Path.setText("");
				remove(loadFile);
				repaint();
				return;
			}

		}

		PrepareTask task = new PrepareTask();
		task.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				int oldV = loadFile.getValue();
				if (evt.getPropertyName().equals("progress")) {
					for (int i = oldV; i <= (Integer) evt.getNewValue(); i++)
						loadFile.setValue(i);
				}
			}
		});

		task.execute();
	}

	private void b_SendActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		javax.swing.JFrame parent = (javax.swing.JFrame) this.getRootPane()
				.getParent();
		parent.remove(this);
		parent.repaint();
	}

	private void b_OpenActionPerformed(java.awt.event.ActionEvent evt) {
		int reval = fileChooser.showOpenDialog(this);
		if (reval == JFileChooser.APPROVE_OPTION) {
			TF_Path.setText(fileChooser.getSelectedFile().getPath());
			TF_Path.setToolTipText(TF_Path.getText());
		}
		if (!previousPath.equals(TF_Path.getText())) {
			previousPath = TF_Path.getText();
			b_Load.setEnabled(true);
		}
	}

	private void initMyComponents() {
		initFChooser();

		previousPath = "";

		b_Load.setEnabled(false);
		// b_Send.setEnabled(false);
		// b_TimeFrame.setEnabled(false);

		loadFile.setBounds(84, 210, 146, 14);
		loadFile.setStringPainted(true);
		loadFile.setMaximum(100);
		loadFile.setValue(0);

		propertyP.setBounds(0, 45, 315, 330);
		// add(propertyP);
		// loadFile.setValue(5);

		// this.add(loadFile);
		// this.repaint();

		// System.out.println(p_Body.getPreferredSize());
		// javax.swing.JButton test = new javax.swing.JButton("Test");
		// test.setBounds(0, 45, 315, 330);
		// this.add(test);
		// this.repaint();
	}

	// Initialise the file chooser. Only the .ppt or .pdf files
	// can be shown
	private void initFChooser() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"ppt,pptx or pdf", "ppt", "pptx", "pdf");
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(filter);

	}

	// Task classes
	public class PrepareTask extends javax.swing.SwingWorker<Void, Integer> {

		public void setProg(int val) {
			this.setProgress(val);
		}

		@Override
		protected Void doInBackground() throws Exception {
			// TODO Auto-generated method stub
			loader.prepare(this);
			return null;
		}

		@Override
		protected void done() {
			remove(loadFile);

			String[] prop = loader.getProperty();
			pType.setText(prop[0]);
			pName.setText(prop[1]);
			pNumC.setText(prop[3]);
			pCum.setText(prop[4]);
			pPath.setText(prop[2]);

			add(propertyP);
			// propertyP.setVisible(true);
			b_Load.setEnabled(false);
			repaint();
			revalidate();
		}

	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JTextField TF_Path;
	private javax.swing.JButton b_Load;
	private javax.swing.JButton b_Open;
	private javax.swing.JButton b_Send;
	private javax.swing.JButton b_TimeFrame;
	private javax.swing.JFileChooser fileChooser;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSeparator jSeparator2;
	private javax.swing.JLabel ll_Path;
	private javax.swing.JProgressBar loadFile;
	private javax.swing.JLabel pCum;
	private javax.swing.JLabel pCumL;
	private javax.swing.JLabel pName;
	private javax.swing.JLabel pNameL;
	private javax.swing.JLabel pNumC;
	private javax.swing.JLabel pNumCL;
	private javax.swing.JLabel pPath;
	private javax.swing.JLabel pPathL;
	private javax.swing.JLabel pType;
	private javax.swing.JLabel pTypeL;
	private javax.swing.JPanel propertyP;
	// End of variables declaration//GEN-END:variables

	private Loader loader;
	private String previousPath;
	private bsp.ui.PPTGuide pptGuide;
	private bsp.ui.PDFGuide pdfGuide;
	// private final javax.swing.JPanel thisPanel;

}