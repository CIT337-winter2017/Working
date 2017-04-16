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
	
	
	private JPanel Cards;//Nicole
	private CardLayout layout;//Nicole
	private JTextField txtFirstName;//Nicole
	private JTextField txtLastName;//Nicole
	private JTextField txtUsername;//Nicole
	private JPasswordField pwdPassword;//Nicole
	private JPasswordField pwdConfirmPass;//Nicole
	private JMenuItem mntmUsername;
	private JLabel lblFirstName;//Nicole
	private JLabel lblLastName;//Nicole
	private JLabel lblUsername;//Nicole
	private JLabel lblPassword;//Nicole
	private JLabel lblConfirmPass;//Nicole

	private JMenuBar menuBar;//Nicole
	
public RegisterActivity(JPanel newCards, CardLayout newLayout){
		
		Cards = newCards;//Nicole
		layout = newLayout;//Nicole
		
	}
	
	public void startActivity(){
		
		JPanel SignUp = new JPanel();//Nicole
		Cards.add(SignUp, "register");//Nicole
		SignUp.setLayout(null);//Nicole
		
				//first name textbox
		txtFirstName = new JTextField();//Nicole
		txtFirstName.setBounds(155, 21, 186, 32);//Nicole
		SignUp.add(txtFirstName);//Nicole
		txtFirstName.setColumns(10);//Nicole
		//first name label
		lblFirstName = new JLabel();//Nicole
		lblFirstName.setText("Enter First Name:");//Nicole
		lblFirstName.setBounds(40,21,176,26);//Nicole
		SignUp.add(lblFirstName);//Nicole
		
		//last name textbox
		txtLastName = new JTextField();//Nicole
		txtLastName.setBounds(155, 60, 186, 32);//Nicole
		SignUp.add(txtLastName);//Nicole
		txtLastName.setColumns(10);//Nicole
		
		//last name label
		lblLastName = new JLabel();//Nicole
		lblLastName.setText("Enter Last Name:");//Nicole
		lblLastName.setBounds(40,60,176,26);//Nicole
		SignUp.add(lblLastName);//Nicole
		
		//username textbob
		txtUsername = new JTextField();//Nicole
		txtUsername.setBounds(155, 98, 186, 32);//Nicole
		SignUp.add(txtUsername);//Nicole
		txtUsername.setColumns(10);//Nicole
		//username label
		lblUsername = new JLabel();//Nicole
		lblUsername.setText("Enter Username:");//Nicole
		lblUsername.setBounds(40,98,176,26);//Nicole
		SignUp.add(lblUsername);//Nicole
		
		//add password validation
		pwdPassword = new JPasswordField();//Nicole
		pwdPassword.setBounds(155, 136, 186, 32);//Nicole
		SignUp.add(pwdPassword);//Nicole
		pwdPassword.setColumns(10);//Nicole
		
		lblPassword = new JLabel();//Nicole
		lblPassword.setText("Enter Password:");//Nicole
		lblPassword.setBounds(40,136,176,26);//Nicole
		SignUp.add(lblPassword);//Nicole
		
		//add password validation
		pwdConfirmPass= new JPasswordField();//Nicole
		pwdConfirmPass.setBounds(155, 175, 186, 32);//Nicole
		SignUp.add(pwdConfirmPass);//Nicole
		pwdConfirmPass.setColumns(10);//Nicole
		
		lblConfirmPass = new JLabel();//Nicole
		lblConfirmPass.setText("Confirm Password:");//Nicole
		lblConfirmPass.setBounds(40,175,176,26);//Nicole
		SignUp.add(lblConfirmPass);//Nicole
		
		JButton btnSubmit = new JButton("Submit");//Nicole
		btnSubmit.setBounds(155, 220, 186, 35);//Nicole
		SignUp.add(btnSubmit);//Nicole
		
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
		char [] pass = pwdPassword.getPassword();//Nicole
		String passString = new String(pass);//Nicole
		char [] confirmPass = pwdConfirmPass.getPassword();//Nicole
		String confirmPassString = new String(confirmPass);//Nicole
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
