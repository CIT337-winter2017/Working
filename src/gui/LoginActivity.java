package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import object.Group;
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
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.sql.*;


public class LoginActivity extends JFrame{

	
	private CardLayout layout;
	private JPanel Cards;
	private JPanel Login;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JMenuBar menuBar;
	private JMenuItem mntmUsername;
	private Roommate roommate = Roommate.getInstance();
	private String username;
	
	public LoginActivity(JPanel newCards, JMenuBar newMenuBar, CardLayout newLayout){
		
		menuBar = newMenuBar;
		layout = newLayout;
		Cards = newCards;
		
	}
	
	public void startActivity(){
		
		Login = new JPanel();
		Cards.add(Login, "login");
		Login.setLayout(null);
		
		//username textbox
		txtUsername = new JTextField();
		txtUsername.setBounds(157, 53, 186, 32);
		Login.add(txtUsername);
		txtUsername.setColumns(10);
		//username label
		lblUsername = new JLabel();
		lblUsername.setBounds(40,53,176,26);
		lblUsername.setText("Enter Username:");
		Login.add(lblUsername);
		
		//password textbox
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(157, 96, 186, 32);
		Login.add(pwdPassword);
		//password label
		lblPassword = new JLabel();
		lblPassword.setBounds(40,96,176,26);
		lblPassword.setText("Enter Password:");
		Login.add(lblPassword);
		
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
						HomeActivity home = HomeActivity.getInstance();
						home.setLayout(layout);
						home.setCards(Cards);
						home.passMenuBar(menuBar);
						home.startActivity(username);
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
				RegisterActivity register = new RegisterActivity(Cards, layout);
				register.startActivity();
			}
		});
		layout.show(Cards, "login");
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
		    
		    String url = "jdbc:mysql://localhost:3306/cse337?useSSL=false"; //local database connection set up by Nick
		    String user = "root";
		    String pw = "6425";
		    
		    Connection connection = DriverManager.getConnection(conn);
		    //Connection connection = DriverManager.getConnection(url, user, pw);
		    SQLConnection sqlconn = SQLConnection.getInstance(); //SQLConection instance set up by Nick
		    sqlconn.setConn(connection);
		   
		    query = "SELECT * FROM Users WHERE USER_USERNAME=? AND USER_PASSWORD=?";
		    prepared = connection.prepareStatement(query);		
		    prepared.setString(1, username);
		    prepared.setString(2, password);
		 
		    ResultSet rs = prepared.executeQuery();
		    int count = 0;
		    while(rs.next()){
		    	count++;
		    	roommate.setID(rs.getInt("USER_ID"));
		    	roommate.setFirstName(rs.getString("USER_FNAME"));
		    	roommate.setLastName(rs.getString("USER_LASTNAME"));
		    	roommate.setGroupID(rs.getInt("USER_GROUP_ID"));
		    	username = (rs.getString("USER_FNAME") + " " + rs.getString("USER_LASTNAME"));
			
		    }
		    if( count == 0){
		    	return false;
		    }
		    
		    if (roommate.getGID() != 0){		//This if/else statement created by Nick to get group info and
		    	prepared.close();				//apply it to the group singleton instance
		    	query = "SELECT * FROM Groups WHERE GROUPS_ID = ?";
		    	prepared = connection.prepareStatement(query);
		    	prepared.setInt(1, roommate.getGID());
		    	rs = prepared.executeQuery();
		    	rs.next();
		    	Group group = Group.getInstance();
		    	group.setID(rs.getInt("GROUPS_ID"));;
		    	group.setName(rs.getString("GROUPS_NAME"));
		    	group.setOwnerID(rs.getInt("GROUPS_OWNER_ID"));
		    }else{
		    	Group group = Group.getInstance();
		    	group.setID(0);
		    	group.setName("You do not belong to a group");
		    	group.setOwnerID(roommate.getID());
		    }
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
