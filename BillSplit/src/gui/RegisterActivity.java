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



public class RegisterActivity extends JFrame {
	
	
	private JPanel Cards;
	
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtUsername_1;
	private JTextField txtPassword;
	
	
public RegisterActivity(JPanel newCards){
		
		Cards = newCards;
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
		
		txtUsername_1 = new JTextField();
		txtUsername_1.setText("Username");
		txtUsername_1.setBounds(155, 98, 186, 32);
		SignUp.add(txtUsername_1);
		txtUsername_1.setColumns(10);
		
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
				SignUp.setVisible(false);
				HomeActivity home = new HomeActivity(Cards);
				home.startActivity();
			
			}
		});
		SignUp.setVisible(true);
	}
	
	
}
