package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import object.Roommate;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import java.sql.*;

public class RegisterActivity extends JFrame {
	
	
	private JPanel Cards;
	
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JMenuItem mntmUsername;

	private JMenuBar menuBar;
	
public RegisterActivity(JPanel newCards, JMenuBar menuBar){
		
		Cards = newCards;
		this.menuBar = menuBar;
		
	}
	
	public void startActivity(){
		
		JPanel SignUp = new JPanel();
		Cards.add(SignUp, "name_2175269678062");
		SignUp.setLayout(null);
		
		txtFirstName = new JTextField();
		txtFirstName.setText("First Name");
		txtFirstName.setBounds(155, 21, 186, 32);
		SignUp.add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setText("Last Name");
		txtLastName.setBounds(155, 60, 186, 32);
		SignUp.add(txtLastName);
		txtLastName.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setText("Username");
		txtUsername.setBounds(155, 98, 186, 32);
		SignUp.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setText("Password");
		txtPassword.setBounds(155, 136, 186, 32);
		SignUp.add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(155, 189, 186, 35);
		SignUp.add(btnSubmit);
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					if(registerUser()){
						SignUp.setVisible(false);
						JOptionPane.showMessageDialog(null, "Account created! Please log in");
					}else{
						JOptionPane.showMessageDialog(null, "Sorry, username already exists, please try again!");
					}
					
				}
				catch(SQLException ex){
					JOptionPane.showConfirmDialog(null, ex.toString());
				}
				
			
			}
		});
		SignUp.setVisible(true);
	}
	public boolean registerUser() throws SQLException{
		PreparedStatement prepared = null;
		String query = "";
		try{
			 
		    String conn = "jdbc:sqlserver://cit337.database.windows.net:1433;" +
		    					"database=CSE337;user=afdanaj@cit337;password=Temp12345;"+
		    					"encrypt=true;trustServerCertificate=false;" +
		    					"hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		    
		    Connection connection = DriverManager.getConnection(conn);
		   
		    query = "INSERT INTO Users (USER_USERNAME, USER_PASSWORD, USER_FNAME, USER_LASTNAME, USER_GROUP_ID)" +
							"VALUES (?, ?, ?, ?, ?)";
		    prepared = connection.prepareStatement(query);	
		   
		    prepared.setString(1, txtUsername.getText());
		    prepared.setString(2, txtPassword.getText());
		    prepared.setString(3, txtFirstName.getText());
		    prepared.setString(4, txtLastName.getText());
		    prepared.setInt(5, 0);
		    prepared.execute();
			
		    return true;
		}catch(SQLException ex){
			ex.printStackTrace();
			return false;
		}finally{
			if(prepared != null){
				prepared.close();
			}
			
		}
	}
	
	
	
}
