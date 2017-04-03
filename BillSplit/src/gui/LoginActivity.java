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
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.sql.*;


public class LoginActivity extends JFrame{

	
	
	private JPanel Cards;
	private JPanel Login;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	private JMenuBar menuBar;
	private JMenuItem mntmUsername;
	
	public LoginActivity(JPanel newCards, JMenuBar mntmUsername){
		
		menuBar = mntmUsername;
	
		Cards = newCards;
		
	}
	
	public void startActivity(){
		
		Login = new JPanel();
		Cards.add(Login, "name_2140769625460");
		Login.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(157, 53, 186, 32);
		Login.add(txtUsername);
		txtUsername.setText("Username");
		txtUsername.setColumns(10);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(157, 96, 186, 32);
		Login.add(pwdPassword);
		pwdPassword.setText("Password");
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(157, 139, 186, 35);
		Login.add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(157, 182, 186, 35);
		Login.add(btnRegister);
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Login.setVisible(false);
				try{
					if(checkLogin()){
						HomeActivity home = new HomeActivity(Cards);
						home.startActivity();
					}else{
						JOptionPane.showMessageDialog(null, "Sorry, incorrect username and/or password");
					}
				}
				catch(SQLException e){
					JOptionPane.showMessageDialog(null, e.toString());
				}
				
				
			}
		});
		btnRegister.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				Login.setVisible(false);
				RegisterActivity register = new RegisterActivity(Cards, menuBar);
				register.startActivity();
			}
		});
		Login.setVisible(true);
	}
	
	public boolean checkLogin() throws SQLException{
		String username = txtUsername.getText();
		String password = pwdPassword.getText();
		
		PreparedStatement prepared = null;
		String query = "";
		try{
			 
		    String conn = "jdbc:sqlserver://cit337.database.windows.net:1433;" +
		    					"database=CSE337;user=afdanaj@cit337;password=Temp12345;"+
		    					"encrypt=true;trustServerCertificate=false;" +
		    					"hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		    
		    Connection connection = DriverManager.getConnection(conn);
		   
		    query = "SELECT * FROM Users WHERE USER_USERNAME=? AND USER_PASSWORD=?";
		    prepared = connection.prepareStatement(query);		
		    prepared.setString(1, username);
		    prepared.setString(2, password);
		 
		    ResultSet rs = prepared.executeQuery();
		    while(rs.next()){
		    	Roommate roommate = Roommate.getInstance();
		    	roommate.setID(rs.getInt("USER_ID"));
		    	roommate.setFirstName(rs.getString("USER_FNAME"));
		    	roommate.setLastName(rs.getString("USER_LASTNAME"));
		    	roommate.setGroupID(rs.getInt("USER_GROUP_ID"));
		    	mntmUsername = new JMenuItem(rs.getString("USER_FNAME") + " " + rs.getString("USER_LASTNAME"));
				menuBar.add(mntmUsername);
		    
		    	return true;
		    }
		    return false;
		    
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