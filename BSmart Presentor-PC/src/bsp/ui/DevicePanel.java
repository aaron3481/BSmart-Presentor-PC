/*
 * DevicePanel.java
 *
 * Created on __DATE__, __TIME__
 */

package bsp.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/*import java.util.Vector;

 import bsp.connection.Bluetooth;

 import javax.bluetooth.RemoteDevice;
 import javax.swing.table.DefaultTableModel;*/

/**
 * 
 * @author Jiannan Cai
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

		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jSeparator1 = new javax.swing.JSeparator();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		jSeparator2 = new javax.swing.JSeparator();

		setPreferredSize(new java.awt.Dimension(317, 430));

		jButton1.setText("Start Server");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("Close Server");
		jButton2.setEnabled(false);
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jLabel1.setBackground(new java.awt.Color(51, 153, 255));
		jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14));
		jLabel1.setForeground(new java.awt.Color(255, 255, 255));
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("Bluetooth Information");
		jLabel1.setOpaque(true);

		jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14));
		jLabel2.setText("Local Device Name: ");

		jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14));
		jLabel3.setText(" ");

		jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14));
		jLabel4.setText("Local Device Addr: ");

		jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14));
		jLabel5.setText(" ");

		jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14));
		jLabel6.setText("Server Status:");

		jLabel7.setBackground(new java.awt.Color(255, 102, 102));
		jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14));
		jLabel7.setForeground(new java.awt.Color(255, 255, 255));
		jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel7.setText("Off");
		jLabel7.setOpaque(true);

		jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14));
		jLabel8.setText("Connection Status: ");

		jLabel9.setBackground(new java.awt.Color(255, 102, 102));
		jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14));
		jLabel9.setForeground(new java.awt.Color(255, 255, 255));
		jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel9.setText("Disconnected");
		jLabel9.setOpaque(true);

		jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14));
		jLabel10.setText("Paring Code: ");

		jLabel11.setBackground(new java.awt.Color(255, 255, 255));
		jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 48));
		jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel11.setText(" ");
		jLabel11.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(51, 153, 255)));
		jLabel11.setOpaque(true);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jSeparator1,
						javax.swing.GroupLayout.DEFAULT_SIZE, 317,
						Short.MAX_VALUE)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(72, Short.MAX_VALUE)
								.addComponent(jButton1)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jButton2).addGap(50, 50, 50))
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel1,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										293, Short.MAX_VALUE).addContainerGap())
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel2)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel3,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										164, Short.MAX_VALUE).addContainerGap())
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel4)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel5,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										170, Short.MAX_VALUE).addContainerGap())
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel6)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel7,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										56,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(159, Short.MAX_VALUE))
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel8)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel9,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										103,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(76, Short.MAX_VALUE))
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel10)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		173,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(48, 48,
																		48)
																.addComponent(
																		jLabel11,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		207,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(50, Short.MAX_VALUE))
				.addComponent(jSeparator2,
						javax.swing.GroupLayout.DEFAULT_SIZE, 317,
						Short.MAX_VALUE));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel1)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel2)
												.addComponent(jLabel3))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel4)
												.addComponent(jLabel5))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(jLabel6)
												.addComponent(jLabel7))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel8)
												.addComponent(jLabel9))
								.addGap(18, 18, 18)
								.addComponent(jSeparator2,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										10,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(31, 31, 31)
								.addComponent(jLabel10)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel11,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										84,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										64, Short.MAX_VALUE)
								.addComponent(jSeparator1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										10,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jButton1)
												.addComponent(jButton2))
								.addContainerGap()));
	}// </editor-fold>
		// GEN-END:initComponents
	
	public void updateRecord(){
		cm.updateRecord();
	}
	
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		cm.startServer();
		jButton1.setEnabled(false);
		jButton2.setEnabled(true);
		//((javax.swing.JFrame)this.getRootPane().getParent()).setState(java.awt.Frame.ICONIFIED);
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		jButton1.setEnabled(true);
		jButton2.setEnabled(false);
		cm.close();
	}

	private void CMPropChange(PropertyChangeEvent evt) {
		String event = evt.getPropertyName();
		boolean newStatus = (Boolean) evt.getNewValue();
		
		System.out.println(event);
		
		if (event.equals("SERVER_STATUS")) {
			if (newStatus) {
				jLabel7.setText("On");
				jLabel7.setBackground(new java.awt.Color(153, 255, 153));
			} else {
				jLabel7.setText("Off");
				jLabel7.setBackground(new java.awt.Color(255, 102, 102));
			}
		} else {
			// ISP Status
			if (newStatus) {
				jLabel9.setText("Connected");
				jLabel9.setBackground(new java.awt.Color(153, 255, 153));
				jButton2.setEnabled(false);
			} else {
				jLabel9.setText("Disconnected");
				jLabel9.setBackground(new java.awt.Color(255, 102, 102));
				jButton2.setEnabled(true);
			}
		}
	}

	private void myInitComponents() {
		cm = new bsp.connection.ConnectionManager();
		cm.updateRecord();
		cm.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				CMPropChange(evt);
			}
		});
		jLabel3.setText(cm.getlocName());
		jLabel5.setText(cm.getlocAddr());
		jLabel11.setText(cm.getCode());
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSeparator jSeparator2;
	// End of variables declaration//GEN-END:variables

	private bsp.connection.ConnectionManager cm;
}