package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;


public class MainActivity extends JFrame{
	//Nicole
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenuItem mntmHome;
	private JMenuItem mntmSignOut;
	private JMenuItem mntmUsername;
	private JPanel Cards;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainActivity main = new MainActivity();
					main.startActivity();		
					main.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});				
		
	}
	public MainActivity() {
		
		
	}
	
	public void startActivity(){
		//Nicole
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 400);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mntmHome = new JMenuItem("Home"); 
		menuBar.add(mntmHome);
		mntmSignOut = new JMenuItem("Sign Out");
		menuBar.add(mntmSignOut);
	
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		Cards = new JPanel();
		contentPane.add(Cards, "name_2146934950434");
		Cards.setLayout(new CardLayout(0, 0));				
					
		LoginActivity login = new LoginActivity(Cards, menuBar);
		login.startActivity();
			
		
		
	}
	
	

}
