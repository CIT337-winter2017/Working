package gui;


import object.*;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;


public class BillActivity extends JFrame {
	
	private JPanel Cards;
	private JPanel Home;
	
	
	private JTextField txtBillname;
	private JTextField txtTotalbillamount;
	private JTextField txtNumroommates;
		
	
public BillActivity(JPanel newCards, JPanel home){
		
		Cards = newCards;
		Home = home;
		
	}
	
	public void startActivity(){
		
		JPanel AddBill = new JPanel();
		Cards.add(AddBill, "name_2684231063061");
		AddBill.setLayout(null);
		
		JLabel lblBillName = new JLabel("Bill Name:");
		lblBillName.setBounds(21, 21, 112, 26);
		AddBill.add(lblBillName);
		
		JLabel lblTotalBillAmount = new JLabel("Total Bill Amount:");
		lblTotalBillAmount.setBounds(21, 64, 176, 26);
		AddBill.add(lblTotalBillAmount);
		
		JLabel lblNumberOfRoommates = new JLabel("Number of Roommates:");
		lblNumberOfRoommates.setBounds(21, 111, 233, 26);
		AddBill.add(lblNumberOfRoommates);
		
		txtBillname = new JTextField();
		txtBillname.setBounds(126, 18, 186, 32);
		AddBill.add(txtBillname);
		txtBillname.setColumns(10);
		
		txtTotalbillamount = new JTextField();
		txtTotalbillamount.setBounds(197, 61, 186, 32);
		AddBill.add(txtTotalbillamount);
		txtTotalbillamount.setColumns(10);
		
		txtNumroommates = new JTextField();
		txtNumroommates.setBounds(255, 108, 186, 32);
		AddBill.add(txtNumroommates);
		txtNumroommates.setColumns(10);
		
		JButton btnAddBill = new JButton("Add Bill");
		btnAddBill.setBounds(181, 180, 141, 35);
		AddBill.add(btnAddBill);
		
		btnAddBill.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				
				Bill bill = new Bill();
				bill.createBill(txtBillname.getText(), Float.valueOf(txtTotalbillamount.getText()), Date.valueOf(("2017-2-28") ));
				
				try {
					bill.saveBill();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				AddBill.setVisible(false);
				Home.setVisible(true);
			}
		});
		AddBill.setVisible(true);
	}
}
