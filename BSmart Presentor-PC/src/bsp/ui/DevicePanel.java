/*
 * DevicePanel.java
 *
 * Created on __DATE__, __TIME__
 */

package bsp.ui;

import bsp.connection.Bluetooth;
import java.util.Vector;

import javax.bluetooth.RemoteDevice;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author __USER__
 */
public class DevicePanel extends javax.swing.JPanel {

	/** Creates new form DevicePanel */
	public DevicePanel() {
		initComponents();
		myInitComponents();
	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		l_device = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		t_Device = new javax.swing.JTable();
		jSeparator1 = new javax.swing.JSeparator();
		dB_Refresh = new javax.swing.JButton();
		dB_Connect = new javax.swing.JButton();

		setPreferredSize(new java.awt.Dimension(317, 430));

		l_device.setFont(new java.awt.Font("Segoe UI", 0, 14));
		l_device.setText("Please choose a device to connect:");

		t_Device.setFont(new java.awt.Font("Segoe UI", 0, 14));
		t_Device.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null }, { null, null },
						{ null, null }, { null, null }, { null, null },
						{ null, null }, { null, null }, { null, null },
						{ null, null }, { null, null }, { null, null },
						{ null, null }, { null, null }, { null, null },
						{ null, null }, { null, null }, { null, null } },
				new String[] { "Device's Name", "MAC" }) {
			boolean[] canEdit = new boolean[] { false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		t_Device.getTableHeader().setReorderingAllowed(false);
		jScrollPane1.setViewportView(t_Device);

		dB_Refresh.setText("Refresh ");
		dB_Refresh.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dB_RefreshActionPerformed(evt);
			}
		});

		dB_Connect.setText("Connect");
		dB_Connect.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dB_ConnectActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup().addContainerGap()
								.addComponent(l_device)
								.addContainerGap(95, Short.MAX_VALUE))
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(13, Short.MAX_VALUE)
								.addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										292,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
				.addComponent(jSeparator1,
						javax.swing.GroupLayout.DEFAULT_SIZE, 317,
						Short.MAX_VALUE)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(81, Short.MAX_VALUE)
								.addComponent(dB_Refresh)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(dB_Connect).addGap(77, 77, 77)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(l_device)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										314,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jSeparator1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										10,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(dB_Connect)
												.addComponent(dB_Refresh))
								.addContainerGap(27, Short.MAX_VALUE)));
	}// </editor-fold>
		// GEN-END:initComponents

	private void dB_ConnectActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		javax.swing.JFrame frame = (javax.swing.JFrame) this.getRootPane()
				.getParent();
		;
		frame.remove(this);
		frame.repaint();

		// (javax.swing.JFrame)parent.remove(this);
		// System.out.println(this.accessibleContext.getAccessibleParent().getClass());
		// this.removeAll();
		// this.repaint();
	}

	private void dB_RefreshActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		dB_Refresh.setEnabled(false);
		bt.startInq(bt);
		devices = bt.getDevices();
		dB_Refresh.setEnabled(true);

		int tableRowCount = t_Model.getRowCount();
		if (tableRowCount < devices.size()) {
			// If current decive's table row < number of devices found,
			// add extra row before filling the table;
			for (int i = 0; i < devices.size() - tableRowCount; i++) {
				t_Model.addRow(new Object[] { "", "" });
			}
		}

		tableRowCount = 0; // temp use

		for (RemoteDevice de : devices) {
			try {
				t_Model.setValueAt(de.getFriendlyName(false), tableRowCount, 0);
				t_Model.setValueAt(de.getBluetoothAddress(), tableRowCount++, 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//System.out.println(devices.size());
	}

	private void myInitComponents() {
		try {
			bt = new Bluetooth();
		} catch (Exception e) {
			e.printStackTrace();
		}

		t_Model = (DefaultTableModel) t_Device.getModel();
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton dB_Connect;
	private javax.swing.JButton dB_Refresh;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JLabel l_device;
	private javax.swing.JTable t_Device;
	// End of variables declaration//GEN-END:variables

	private Bluetooth bt;
	private Vector<RemoteDevice> devices;
	private DefaultTableModel t_Model;
}