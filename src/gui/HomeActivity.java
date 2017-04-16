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
	
	
	private JPanel Cards;//Nicole
	private CardLayout layout;//Nicole
	private JPanel Home;//Nicole
	private JLabel lblBillsplit;//Nicole
	//private JButton btnCalendar;
	private JButton btnAddBill;//Nicole
	private JButton btnView;//Nicole
	private JButton btnPay;//Nicole
	private JButton btnSettings;//Nicole
	private JMenuBar menuBar;//Nicole
	private JMenuItem mntmHome;//Nicole
	private JMenuItem mntmSignOut;//Nicole
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
	
		
		Home = new JPanel();//Nicole
		Cards.add(Home, "home");//Nicole
		Home.setLayout(null);//Nicole
		
		mntmHome = new JMenuItem("Home"); //Nicole
		menuBar.add(mntmHome);//Nicole
		mntmSignOut = new JMenuItem("Sign Out");//Nicole
		menuBar.add(mntmSignOut);//Nicole
		mntmUsername = new JMenuItem("" + username);
		menuBar.add(mntmUsername);
		
		
		lblBillsplit = new JLabel("BillSplit");//Nicole
		lblBillsplit.setHorizontalAlignment(SwingConstants.CENTER);
		lblBillsplit.setBounds(0, 0, 528, 61);//Nicole
		lblBillsplit.setFont(new Font("Tahoma", Font.BOLD, 50));//Nicole
		Home.add(lblBillsplit);//Nicole
		
		//btnCalendar = new JButton("Calendar");
		//btnCalendar.setBounds(191, 69, 141, 35);
		//Home.add(btnCalendar);
		
		btnPay = new JButton("Pay Bill"); //Nicole
		btnPay.setBounds(191,69,141,35); //Nicole
		Home.add(btnPay);//Nicole
		
		btnAddBill = new JButton("Add Bill");//Nicole
		btnAddBill.setBounds(191, 112, 141, 35);//Nicole
		Home.add(btnAddBill);//Nicole
		
		btnView = new JButton("View Bills");
		btnView.setBounds(191, 155, 141, 35);
		Home.add(btnView);
		
		btnSettings = new JButton("Settings");//Nicole
		btnSettings.setBounds(191, 198, 141, 35);//Nicole
		Home.add(btnSettings);//Nicole
		
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				ViewBillActivity view = new ViewBillActivity(Cards, layout);
				view.startActivity();
			
			}
		});
		
		//btnCalendar.addActionListener(new ActionListener() {
			//public void actionPerformed(ActionEvent e) {
			
				//CalendarActivity calendar = new CalendarActivity(Cards, layout);
				//calendar.startActivity();
			
			//}
		//});
		
		btnPay.addActionListener(new ActionListener() {//Nicole
			public void actionPerformed(ActionEvent e) {//Nicole
			
				PayBillActivity pay = new PayBillActivity(Cards, layout);//Nicole
				pay.startActivity();//Nicole
			
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
