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
	
	
	private JPanel Cards;//Nicole
	private CardLayout layout;//Nicole
	
public SettingsActivity(JPanel newCards, CardLayout newLayout){
		
		Cards = newCards;//Nicole
		layout = newLayout;//Nicole
	}
	
	public void startActivity(){
		
		JPanel Settings = new JPanel();//Nicole
		Cards.add(Settings, "settings");//Nicole
		Settings.setLayout(null);//Nicole
		
		JButton btnManageGroups = new JButton("Manage Group(s)");//Nicole
		btnManageGroups.setBounds(160, 32, 195, 35);//Nicole
		Settings.add(btnManageGroups);//Nicole
		
		btnManageGroups.addActionListener(new ActionListener(){//Nicole
			public void actionPerformed(ActionEvent e){//Nicole
				
				GroupActivity Group = new GroupActivity(Cards, layout);//Nicole
				Group.startActivity();//Nicole
				
			}
		});
		
		JButton btnUpdateInfo = new JButton("Update Info");//Nicole
		btnUpdateInfo.setBounds(160, 93, 195, 35);//Nicole
		Settings.add(btnUpdateInfo);//Nicole
		
		layout.show(Cards, "settings");//Nicole
	}
}

