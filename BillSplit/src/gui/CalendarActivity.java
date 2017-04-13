package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

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



public class CalendarActivity extends JFrame {
	//Nicole
	private JPanel Cards;
	private JPanel Calendar;
		
	
public CalendarActivity(JPanel newCards){
		//Nicole
		Cards = newCards;
	}
	
	public void startActivity(){
		//Nicole
		Calendar = new JPanel();
		Cards.add(Calendar, "name_2614559085932");
		Calendar.setLayout(null);
		Calendar.setVisible(true);
		
		//date chooser
		JDateChooser bills = new JDateChooser();
		bills.setBounds(193, 21, 147, 32);
		Calendar.add(bills);
		//select date label
		JLabel lblSelectADate = new JLabel("Select A Date:");
		lblSelectADate.setBounds(45, 21, 195, 26);
		Calendar.add(lblSelectADate);
		//date submit button
		JButton btnSubmitDate = new JButton("Submit Date");
		btnSubmitDate.setBounds(355, 18, 152, 35);
		Calendar.add(btnSubmitDate);
	}
	
	
}
