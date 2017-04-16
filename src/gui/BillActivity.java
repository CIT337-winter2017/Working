package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JDateChooser;

import object.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSlider;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;


public class BillActivity extends JFrame {
	
	private JPanel Cards;//Nicole
	private JPanel Home;//Nicole
	private CardLayout layout;//Nicole
	private BillManager billManager = null;
	
	private JTextField txtBillname;//Nicole
	private JTextField txtTotalbillamount;//Nicole
	//private JTextField txtDate;
	private JDateChooser dateChooser;//Nicole
	private SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");  //Nick
	private String formatted; //Nick
	
public BillActivity(JPanel newCards, CardLayout newLayout){
		
		Cards = newCards;//Nicole
		layout = newLayout;//Nicole
		billManager = BillManager.getInstance();
		
	}
	
	public void startActivity(){
		
		JPanel AddBill = new JPanel();//Nicole
		Cards.add(AddBill, "addBill");//Nicole
		AddBill.setLayout(null);//Nicole
		
		JLabel lblBillName = new JLabel("Bill Name:");//Nicole
		lblBillName.setBounds(21, 21, 112, 26);//Nicole
		AddBill.add(lblBillName);//Nicole
		
		JLabel lblTotalBillAmount = new JLabel("Total Bill Amount:");//Nicole
		lblTotalBillAmount.setBounds(21, 64, 176, 26);//Nicole
		AddBill.add(lblTotalBillAmount);//Nicole
		
		JLabel lblDate = new JLabel("Date:");//Nicole
		lblDate.setBounds(21, 111, 233, 26);//Nicole
		AddBill.add(lblDate);//Nicole
		
		JLabel lblSplit = new JLabel("Split Percent:");//Nicole
		lblSplit.setBounds(21, 164, 233, 26);//Nicole
		AddBill.add(lblSplit);//Nicole
		
		JLabel lblPercent = new JLabel("50%");//Nicole
		lblPercent.setBounds(294, 164, 233, 26);//Nicole
		AddBill.add(lblPercent);//Nicole
		
		//date selection
		dateChooser = new JDateChooser();//Nicole
		dateChooser.setBounds(126, 108, 186, 32);//Nicole
		AddBill.add(dateChooser);//Nicole
		
		txtBillname = new JTextField();//Nicole
		txtBillname.setBounds(126, 18, 186, 32);//Nicole
		AddBill.add(txtBillname);//Nicole
		txtBillname.setColumns(10);//Nicole
		
		txtTotalbillamount = new JTextField();//Nicole
		txtTotalbillamount.setBounds(126, 61, 186, 32);//Nicole
		AddBill.add(txtTotalbillamount);//Nicole
		txtTotalbillamount.setColumns(10);//Nicole
		
		 ChangeListener changeListener = new ChangeListener() {
		      public void stateChanged(ChangeEvent changeEvent) {
		    	  JSlider slider = (JSlider) changeEvent.getSource();
		    	  int value = slider.getValue();
		    	  int stepSize = 5;
		    	  value = (value-value % 5);
		    	  slider.setValue(value);
		    	  lblPercent.setText("" + slider.getValue() + "%");
		      }
		    };
		    
		JSlider percentAdjust = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		percentAdjust.setMinorTickSpacing(5);	
		percentAdjust.setPaintTicks(true);
		percentAdjust.setPaintLabels(true);
		percentAdjust.setMajorTickSpacing(25);
		percentAdjust.addChangeListener(changeListener);
		percentAdjust.setBounds(122, 170, 170, 45);
		AddBill.add(percentAdjust);
	
		
		JButton btnAddBill = new JButton("Add Bill");//Nicole
		btnAddBill.setBounds(181, 230, 141, 35);//Nicole
		AddBill.add(btnAddBill);//Nicole
		
		btnAddBill.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				formatted = format1.format(dateChooser.getDate()); //this was done to get the date as a string in the correct format - Nick
				if(billManager != null){
					billManager.addBill(txtBillname.getText(), txtTotalbillamount.getText(), formatted);				
				}				
				
				layout.show(Cards, "home");
				JOptionPane.showMessageDialog(null, "Your bill was successfully added!");
				
			}
		});
		layout.show(Cards, "addBill");
	}
	
	public void setBillManager(BillManager newBillManager){
		billManager = newBillManager;
	}
	
}
