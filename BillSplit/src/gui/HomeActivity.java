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


public class HomeActivity extends JFrame {
	
	//Nicole
	private JPanel Cards;
	
	private JPanel Home;
	private JLabel lblBillsplit;
	private JButton btnCalendar;
	private JButton btnAddBill;
	private JButton btnSettings;
		
	

	public HomeActivity(JPanel newCards){
		//Nicole
		Cards = newCards;
	}
	
	public void startActivity(){
		
		
	
		//Nicole
		Home = new JPanel();
		Cards.add(Home, "name_2393664671946");
		Home.setLayout(null);
		
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
				Home.setVisible(false);	
				CalendarActivity calendar = new CalendarActivity(Cards);
				calendar.startActivity();
			
			}
		});
		btnAddBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.setVisible(false);
				BillActivity bill = new BillActivity(Cards, Home);
				bill.startActivity();
				
			}
		});
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.setVisible(false);
				SettingsActivity settings = new SettingsActivity(Cards);
				settings.startActivity();
				
			}
		});
		Home.setVisible(true);
		
	}
}
