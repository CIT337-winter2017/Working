package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import object.Roommate;
import other.SQLConnection;

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
	private CardLayout layout;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	private JPasswordField pwdConfirmPass;
	private JMenuItem mntmUsername;
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblConfirmPass;

	private JMenuBar menuBar;
	
public RegisterActivity(JPanel newCards, CardLayout newLayout){
		
		Cards = newCards;
		layout = newLayout;
		
	}
	
	public void startActivity(){
		
		JPanel SignUp = new JPanel();
		Cards.add(SignUp, "register");
		SignUp.setLayout(null);
		
				//first name textbox
		txtFirstName = new JTextField();
		txtFirstName.setBounds(155, 21, 186, 32);
		SignUp.add(txtFirstName);
		txtFirstName.setColumns(10);
		//first name label
		lblFirstName = new JLabel();
		lblFirstName.setText("Enter First Name:");
		lblFirstName.setBounds(40,21,176,26);
		SignUp.add(lblFirstName);
		
		//last name textbox
		txtLastName = new JTextField();
		txtLastName.setBounds(155, 60, 186, 32);
		SignUp.add(txtLastName);
		txtLastName.setColumns(10);
		
		//last name label
		lblLastName = new JLabel();
		lblLastName.setText("Enter Last Name:");
		lblLastName.setBounds(40,60,176,26);
		SignUp.add(lblLastName);
		
		//username textbob
		txtUsername = new JTextField();
		txtUsername.setBounds(155, 98, 186, 32);
		SignUp.add(txtUsername);
		txtUsername.setColumns(10);
		//username label
		lblUsername = new JLabel();
		lblUsername.setText("Enter Username:");
		lblUsername.setBounds(40,98,176,26);
		SignUp.add(lblUsername);
		
		//add password validation
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(155, 136, 186, 32);
		SignUp.add(pwdPassword);
		pwdPassword.setColumns(10);
		
		lblPassword = new JLabel();
		lblPassword.setText("Enter Password:");
		lblPassword.setBounds(40,136,176,26);
		SignUp.add(lblPassword);
		
		//add password validation
		pwdConfirmPass= new JPasswordField();
		pwdConfirmPass.setBounds(155, 175, 186, 32);
		SignUp.add(pwdConfirmPass);
		pwdConfirmPass.setColumns(10);
		
		lblConfirmPass = new JLabel();
		lblConfirmPass.setText("Confirm Password:");
		lblConfirmPass.setBounds(40,175,176,26);
		SignUp.add(lblConfirmPass);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(155, 220, 186, 35);
		SignUp.add(btnSubmit);
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					if(registerUser()){
						layout.show(Cards, "login");
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
		layout.show(Cards, "register");
	}
	public boolean registerUser() throws SQLException{
		//gets passwords as char array and converst to string
		char [] pass = pwdPassword.getPassword();
		String passString = new String(pass);
		char [] confirmPass = pwdConfirmPass.getPassword();
		String confirmPassString = new String(confirmPass);
		PreparedStatement prepared = null;
		String query = "";
		try{
			 
			Connection connection = SQLConnection.getInstance().getConn();
		   
		    query = "INSERT INTO Users (USER_USERNAME, USER_PASSWORD, USER_FNAME, USER_LASTNAME, USER_GROUP_ID)" +
							"VALUES (?, ?, ?, ?, ?)";
		    prepared = connection.prepareStatement(query);	
		   
		    prepared.setString(1, txtUsername.getText());
		    prepared.setString(2, passString);
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
