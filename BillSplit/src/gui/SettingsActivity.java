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

public class SettingsActivity extends JFrame{
	
	
	private JPanel Cards;
	
	
public SettingsActivity(JPanel newCards){
		
		Cards = newCards;
		
	}
	
	public void startActivity(){
		
		JPanel Settings = new JPanel();
		Cards.add(Settings, "name_2866472175132");
		Settings.setLayout(null);
		
		JButton btnManageGroups = new JButton("Manage Group(s)");
		btnManageGroups.setBounds(160, 32, 195, 35);
		Settings.add(btnManageGroups);
		
		btnManageGroups.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Settings.setVisible(false);
				GroupActivity Group = new GroupActivity(Cards);
				Group.startActivity();
				
			}
		});
		
		JButton btnUpdateInfo = new JButton("Update Info");
		btnUpdateInfo.setBounds(160, 93, 195, 35);
		Settings.add(btnUpdateInfo);
		
		Settings.setVisible(true);
	}
}

