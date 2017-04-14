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
	private CardLayout layout;
	
public SettingsActivity(JPanel newCards, CardLayout newLayout){
		
		Cards = newCards;
		layout = newLayout;
	}
	
	public void startActivity(){
		
		JPanel Settings = new JPanel();
		Cards.add(Settings, "settings");
		Settings.setLayout(null);
		
		JButton btnManageGroups = new JButton("Manage Group(s)");
		btnManageGroups.setBounds(160, 32, 195, 35);
		Settings.add(btnManageGroups);
		
		btnManageGroups.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				GroupActivity Group = new GroupActivity(Cards, layout);
				Group.startActivity();
				
			}
		});
		
		JButton btnUpdateInfo = new JButton("Update Info");
		btnUpdateInfo.setBounds(160, 93, 195, 35);
		Settings.add(btnUpdateInfo);
		
		layout.show(Cards, "settings");
	}
}

