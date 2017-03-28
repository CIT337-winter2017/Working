package gui;

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



public class LoginActivity extends JFrame{

	
	
	private JPanel Cards;
	private JPanel Login;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;

	
	public LoginActivity(JPanel newCards){
		
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
				HomeActivity home = new HomeActivity(Cards);
				home.startActivity();
				
			}
		});
		btnRegister.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				Login.setVisible(false);
				RegisterActivity register = new RegisterActivity(Cards);
				register.startActivity();
			}
		});
		Login.setVisible(true);
	}
	
	
	
}
