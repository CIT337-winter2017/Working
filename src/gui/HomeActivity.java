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
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;


public class HomeActivity extends JFrame {
	
	
	private JPanel Cards;
	private CardLayout layout;
	private JPanel Home;
	private JLabel lblBillsplit;
	private JButton btnCalendar;
	private JButton btnAddBill;
	private JButton btnSettings;
	private JMenuBar menuBar;
	private JMenuItem mntmHome;
	private JMenuItem mntmSignOut;
	private JMenuItem mntmUsername;	
	private boolean created = false;
	
	private static HomeActivity sharedInstance = null;
	
	protected HomeActivity(){
		
	}
	public static HomeActivity getInstance(){
		if(sharedInstance == null){
			sharedInstance = new HomeActivity();
		}
		return sharedInstance;
	}
	public void setCards(JPanel newCards){
		Cards = newCards;
	}
	public void setLayout(CardLayout newLayout){
		layout = newLayout;
	}	
	public void passMenuBar(JMenuBar newMenuBar){
		menuBar = newMenuBar;
	}
	public void startActivity(String username){
		
		if(created){
			menuBar.show();
			layout.show(Cards, "home");
			return;
		}
	
		
		Home = new JPanel();
		Cards.add(Home, "home");
		Home.setLayout(null);
		
		mntmHome = new JMenuItem("Home"); 
		menuBar.add(mntmHome);
		mntmSignOut = new JMenuItem("Sign Out");
		menuBar.add(mntmSignOut);
		mntmUsername = new JMenuItem("" + username);
		menuBar.add(mntmUsername);
		
		
		lblBillsplit = new JLabel("BillSplit");
		lblBillsplit.setHorizontalAlignment(SwingConstants.CENTER);
		lblBillsplit.setBounds(0, 0, 528, 61);
		lblBillsplit.setFont(new Font("Tahoma", Font.BOLD, 50));
		Home.add(lblBillsplit);
		
		btnCalendar = new JButton("Calendar");
		btnCalendar.setBounds(191, 69, 141, 35);
		Home.add(btnCalendar);
		
		btnAddBill = new JButton("Add Bill");
		btnAddBill.setBounds(191, 112, 141, 35);
		Home.add(btnAddBill);
		
		btnSettings = new JButton("Settings");
		btnSettings.setBounds(191, 154, 141, 35);
		Home.add(btnSettings);
		
		btnCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				CalendarActivity calendar = new CalendarActivity(Cards, layout);
				calendar.startActivity();
			
			}
		});
		btnAddBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				BillActivity bill = new BillActivity(Cards, layout);
				bill.startActivity();
				
			}
		});
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SettingsActivity settings = new SettingsActivity(Cards, layout);
				settings.startActivity();
				
			}
		});
		mntmHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				layout.show(Cards, "home");
			}
			
		});
		mntmSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuBar.hide();
				layout.show(Cards, "login");
			}
			
		});
				
		created = true;
		layout.show(Cards, "home");
		
	}
}
