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

public class GroupActivity extends JFrame{
	
	
	private JPanel Cards;
	
	
public GroupActivity(JPanel newCards){
		
		Cards = newCards;
		
	}
	
	public void startActivity(){
	
		
	  	JPanel Group = new JPanel();
		Cards.add(Group, "name_3024945128167");
		Group.setLayout(null);
		
		JTextField txtEnterGroupName = new JTextField();
		txtEnterGroupName.setText("Enter Group Name");
		txtEnterGroupName.setBounds(21, 124, 186, 32);
		Group.add(txtEnterGroupName);
		txtEnterGroupName.setColumns(10);
		
		JButton btnJoinGroup = new JButton("Join Group");
		btnJoinGroup.setBounds(228, 123, 141, 35);
		Group.add(btnJoinGroup);
		
		JTextField txtCreateNewGroup = new JTextField();
		txtCreateNewGroup.setText("Create New Group");
		txtCreateNewGroup.setBounds(21, 54, 186, 32);
		Group.add(txtCreateNewGroup);
		txtCreateNewGroup.setColumns(10);
		
		JButton btnCreateGroup = new JButton("Create Group");
		btnCreateGroup.setBounds(228, 53, 167, 35);
		Group.add(btnCreateGroup);
		
		Group.setVisible(true);
	}
}
