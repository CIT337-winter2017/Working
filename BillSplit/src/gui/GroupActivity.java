package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import object.Roommate;
import other.SQLConnection;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class GroupActivity extends JFrame{
	
	//Nicole
	private JPanel Cards;
	private JTextField txtCreateNewGroup;
	private JTextField txtEnterGroupName;
	private JLabel lblGroupName;
	private Roommate roommate = Roommate.getInstance();
	
	
public GroupActivity(JPanel newCards){
		//Nicole
		Cards = newCards;
		
	}
	
	public void startActivity(){
	
		//Nicole
	  	JPanel Group = new JPanel();
		Cards.add(Group, "name_3024945128167");
		Group.setLayout(null);
		
		txtEnterGroupName = new JTextField();
		txtEnterGroupName.setText("Enter Group Name");
		txtEnterGroupName.setBounds(21, 124, 186, 32);
		Group.add(txtEnterGroupName);
		txtEnterGroupName.setColumns(10);
		
		JButton btnJoinGroup = new JButton("Join Group");
		btnJoinGroup.setBounds(228, 123, 141, 35);
		Group.add(btnJoinGroup);
		
		txtCreateNewGroup = new JTextField();
		txtCreateNewGroup.setText("Create New Group");
		txtCreateNewGroup.setBounds(21, 54, 186, 32);
		Group.add(txtCreateNewGroup);
		txtCreateNewGroup.setColumns(10);
		
		JLabel label1 = new JLabel("Current Group:", SwingConstants.CENTER);
		label1.setBounds(21,194,186,16);
		Group.add(label1);
		
		lblGroupName = new JLabel("", SwingConstants.CENTER);
		lblGroupName.setText("test");;
		lblGroupName.setBounds(21, 210, 186, 16);
		Group.add(lblGroupName);
		
		JButton btnCreateGroup = new JButton("Create Group");
		btnCreateGroup.setBounds(228, 53, 167, 35);
		Group.add(btnCreateGroup);
		
		JButton btnLeaveGroup = new JButton("Leave Group");
		btnLeaveGroup.setBounds(228, 193, 141, 35);
		Group.add(btnLeaveGroup);
		
		Group.setVisible(true);
		
		btnCreateGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					if(createGroup()){
						Group.setVisible(false);
						JOptionPane.showMessageDialog(null, "Group created!");
					}else{
						JOptionPane.showMessageDialog(null, "Fatal Error.");
					}
					
				}
				catch(SQLException ex){
					JOptionPane.showConfirmDialog(null, ex.toString());
				}
				
			
			}
		});
		
		btnJoinGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					if(joinGroup()){
						Group.setVisible(false);
						JOptionPane.showMessageDialog(null, "Group joined!");
					}else{
						JOptionPane.showMessageDialog(null, "Fatal Error.");
					}
					
				}
				catch(SQLException ex){
					JOptionPane.showConfirmDialog(null, ex.toString());
				}
				
			
			}
		});
		
	}
	public boolean createGroup() throws SQLException{
	PreparedStatement prepared = null;
	String query = "";
	
	try{
		 
		Connection connection = SQLConnection.getInstance().getConn();
	   
	    query = "INSERT INTO Groups (GROUPS_ID, GROUPS_NAME, GROUPS_OWNER_ID)" +
						"VALUES (?, ?, ?)";
	    prepared = connection.prepareStatement(query);	
	    prepared.setInt(1, 0);
	    prepared.setString(2, txtCreateNewGroup.getText());
	    prepared.setInt(3, roommate.getID());
	    prepared.execute();

	    return true;
	}catch(SQLException ex){
		ex.printStackTrace();
		return false;
	}finally{
		if(prepared != null){
			prepared.close();
		}
		
	}


}
	
	public boolean joinGroup() throws SQLException{
		PreparedStatement prepared = null;
		String query = "";
		
		try{
			 
		    Connection connection = SQLConnection.getInstance().getConn();
		   
		    query = "SELECT * FROM Groups WHERE GROUPS_NAME=?";
		    prepared = connection.prepareStatement(query);	
		    prepared.setString(1, txtEnterGroupName.getText());
		    
		    ResultSet rs = prepared.executeQuery();
		    rs.next();
		    int groupID = rs.getInt("GROUPS_ID");
		    roommate.setGroupID(groupID);
		    prepared.close();
		    
		    query = "UPDATE Users SET USER_GROUP_ID = ? WHERE USER_ID=1;";
		    prepared = connection.prepareStatement(query);
		    prepared.setInt(1, groupID);
		    prepared.execute();

		    return true;
		}catch(SQLException ex){
			ex.printStackTrace();
			return false;
		}finally{
			if(prepared != null){
				prepared.close();
			}
			
		}


	}

}
