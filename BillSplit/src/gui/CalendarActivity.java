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



public class CalendarActivity extends JFrame {
	
	private JPanel Cards;
	private JPanel Calendar;
		
	
public CalendarActivity(JPanel newCards){
		
		Cards = newCards;
	}
	
	public void startActivity(){
		
		Calendar = new JPanel();
		Cards.add(Calendar, "name_2614559085932");
		Calendar.setLayout(null);
		Calendar.setVisible(true);
	}
	
	
}
