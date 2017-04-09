package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import object.*;
import java.sql.*;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;


public class BillActivity extends JFrame {
	
	private JPanel Cards;
	private JPanel Home;
	
	private BillManager billManager = null;
	
	private JTextField txtBillname;
	private JTextField txtTotalbillamount;
	//private JTextField txtDate;
	private JDateChooser dateChooser;
	
public BillActivity(JPanel newCards, JPanel home){
		
		Cards = newCards;
		Home = home;
		billManager = BillManager.getInstance();
		
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
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(21, 111, 233, 26);
		AddBill.add(lblDate);
		
		//date selection
		dateChooser = new JDateChooser();
		dateChooser.setBounds(126, 108, 186, 32);
		AddBill.add(dateChooser);
		
		txtBillname = new JTextField();
		txtBillname.setBounds(126, 18, 186, 32);
		AddBill.add(txtBillname);
		txtBillname.setColumns(10);
		
		txtTotalbillamount = new JTextField();
		txtTotalbillamount.setBounds(126, 61, 186, 32);
		AddBill.add(txtTotalbillamount);
		txtTotalbillamount.setColumns(10);
		
		//txtDate = new JTextField();
		//txtDate.setBounds(126, 108, 186, 32);
		//AddBill.add(txtDate);
		//txtDate.setColumns(10);
		
		JButton btnAddBill = new JButton("Add Bill");
		btnAddBill.setBounds(181, 180, 141, 35);
		AddBill.add(btnAddBill);
		
		btnAddBill.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				
				if(billManager != null){
					billManager.addBill(txtBillname.getText(), txtTotalbillamount.getText(), dateChooser.getDateFormatString());				
				}				
				AddBill.setVisible(false);
				Home.setVisible(true);
				JOptionPane.showMessageDialog(null, "Your bill was successfully added!");
				
			}
		});
		AddBill.setVisible(true);
	}
	
	public void setBillManager(BillManager newBillManager){
		billManager = newBillManager;
	}
	
}
