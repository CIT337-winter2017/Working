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
import other.SQLConnection;

import java.sql.*;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

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
	
	private JPanel Cards;
	private JPanel Home;
	private int groupCount = 0;
	private JComboBox comboBox;
	private CardLayout layout;
	private BillManager billManager = null;
	private Roommate roommate = Roommate.getInstance();
	private String items;
	private JTextField txtBillname;
	private JTextField txtTotalbillamount;
	//private JTextField txtDate;
	private JDateChooser dateChooser;
	private SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");  //Nick
	private String formatted; //Nick
	
public BillActivity(JPanel newCards, CardLayout newLayout){
		
		Cards = newCards;
		layout = newLayout;
		billManager = BillManager.getInstance();
		
	}
	
	public void startActivity(){
		
		JPanel AddBill = new JPanel();
		Cards.add(AddBill, "addBill");
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
		
		JLabel lblSplit = new JLabel("Split Percent:");
		lblSplit.setBounds(21, 164, 233, 26);
		AddBill.add(lblSplit);
		
	
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
		
		
		comboBox = new JComboBox();
		comboBox.setBounds(122, 170, 186, 45);
		AddBill.add(comboBox);
		
		try{
			fillNames();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	
		
		//txtDate = new JTextField();
		//txtDate.setBounds(126, 108, 186, 32);
		//AddBill.add(txtDate);
		//txtDate.setColumns(10);
		
		JButton btnAddBill = new JButton("Add Bill");
		btnAddBill.setBounds(181, 230, 141, 35);
		AddBill.add(btnAddBill);
		
		btnAddBill.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				formatted = format1.format(dateChooser.getDate()); //this was done to get the date as a string in the correct format - Nick
				if(billManager != null){
					try{
						if(comboBox.getSelectedIndex() == comboBox.getItemCount() - 1){
							if(splitGroup()){
								layout.show(Cards, "home");
								JOptionPane.showMessageDialog(null, "Your bills was successfully added!");
								
							}
						}else{
							if(splitRoommate()){
								layout.show(Cards, "home");
								JOptionPane.showMessageDialog(null, "Your bills was successfully added!");
								
							}
						}
					}catch (SQLException ex){
						ex.printStackTrace();
					}
									
				}				
				
				
			}
		});
		layout.show(Cards, "addBill");
	}
	
	public void setBillManager(BillManager newBillManager){
		billManager = newBillManager;
	}
	public boolean splitGroup() throws SQLException{
		PreparedStatement prepared = null;
		String query = "";
		
		try{
			int id = 0;
		
			
			float value = Float.valueOf(txtTotalbillamount.getText());
    		value = value / groupCount;
		  
    	    String [] temp = items.split(",");
	    	for(int i = 0; i < groupCount; i++){
	    		id = Integer.valueOf(temp[i]);
	    		billManager.addBill(txtBillname.getText(), String.valueOf(value), formatted, id);
	    	}
		    	
		  
		  return true;
		    //return true;
		}catch(Exception ex){
			ex.printStackTrace();
		  return false;
		}finally{
			if(prepared != null){
				prepared.close();
			}
			
		}
	}
	public boolean splitRoommate() throws SQLException{
		PreparedStatement prepared = null;
		String query = "";
		
		try{
			 
		  String text = comboBox.getSelectedItem().toString();
		  int id = Integer.valueOf(text.substring(4, text.indexOf(" - ")));
		  float value = Float.valueOf(txtTotalbillamount.getText());
		  value = value / 2;
		  billManager.addBill(txtBillname.getText(), String.valueOf(value), formatted, id);
		  billManager.addBill(txtBillname.getText(), String.valueOf(value), formatted, billManager.getID());

		    return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}finally{
			if(prepared != null){
				prepared.close();
			}
			
		}
	}
	public void fillNames() throws SQLException{
		PreparedStatement prepared = null;
		String query = "";
		
		try{
			 groupCount=0;
			 items = "";
			Connection connection = SQLConnection.getInstance().getConn();
		    
		    query = "SELECT * FROM Users WHERE USER_GROUP_ID=?";
		    prepared = connection.prepareStatement(query);	
		    prepared.setInt(1, roommate.getGID());
		    ResultSet rs = prepared.executeQuery();
		    while(rs.next()){
		    	groupCount++;
		    	items += String.valueOf(rs.getInt("USER_ID")) + ",";
		    	
		    	comboBox.addItem("ID: " + rs.getInt("USER_ID") + " - " + rs.getString("USER_FNAME") + " " + rs.getString("USER_LASTNAME"));
		    }
		    prepared.close();
		    rs.close();
		    
		    query = "SELECT * FROM Groups WHERE GROUPS_ID=?;";
		    prepared = connection.prepareStatement(query);
		    prepared.setInt(1, roommate.getGID());
		    
		    rs = prepared.executeQuery();
		    rs.next();
		    comboBox.addItem("Group: " + rs.getString("GROUPS_NAME"));
		    prepared.close();
		    rs.close();
		    
		  

		    //return true;
		}catch(SQLException ex){
			ex.printStackTrace();
		//	return false;
		}finally{
			if(prepared != null){
				prepared.close();
			}
			
		}
	}
	
}
