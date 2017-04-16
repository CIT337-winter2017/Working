package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import object.Group;
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
	
	private CardLayout layout;//Nicole
	private JPanel Cards;//Nicole
	private JTextField txtCreateNewGroup;//Nicole
	private JTextField txtEnterGroupName;//Nicole
	private JLabel lblGroupName;  						// Created
	private Roommate roommate = Roommate.getInstance();	// by
	private Group group = Group.getInstance();			// Nick
	
	
public GroupActivity(JPanel newCards, CardLayout newLayout){
		
		Cards = newCards;//Nicole
		layout = newLayout;//Nicole
		
	}
	
	public void startActivity(){
	
		
	  	JPanel Group = new JPanel();//Nicole
		Cards.add(Group, "group");//Nicole
		Group.setLayout(null);//Nicole
		
		txtEnterGroupName = new JTextField();//Nicole
		txtEnterGroupName.setText("Enter Group Name");//Nicole
		txtEnterGroupName.setBounds(21, 124, 186, 32);//Nicole
		Group.add(txtEnterGroupName);//Nicole
		txtEnterGroupName.setColumns(10);//Nicole
		
		JButton btnJoinGroup = new JButton("Join Group");//Nicole
		btnJoinGroup.setBounds(228, 123, 141, 35);//Nicole
		Group.add(btnJoinGroup);//Nicole
		
		txtCreateNewGroup = new JTextField();//Nicole
		txtCreateNewGroup.setText("Create New Group");//Nicole
		txtCreateNewGroup.setBounds(21, 54, 186, 32);//Nicole
		Group.add(txtCreateNewGroup);//Nicole
		txtCreateNewGroup.setColumns(10);//Nicole
		
		JLabel label1 = new JLabel("Current Group:", SwingConstants.CENTER);	// Start Nick
		label1.setBounds(21,194,186,16);										
		Group.add(label1);														
																				
		lblGroupName = new JLabel("", SwingConstants.CENTER);
		lblGroupName.setText(group.getName());;
		lblGroupName.setBounds(21, 210, 186, 16);
		Group.add(lblGroupName);												// End Nick
		
		JButton btnCreateGroup = new JButton("Create Group");//Nicole
		btnCreateGroup.setBounds(228, 53, 167, 35);//Nicole
		Group.add(btnCreateGroup);//Nicole
		
		JButton btnLeaveGroup = new JButton("Leave Group");//Nick
		btnLeaveGroup.setBounds(228, 193, 141, 35);	//Nick
		Group.add(btnLeaveGroup);			//Nick
		
		layout.show(Cards, "group");//Nicole
		
		btnCreateGroup.addActionListener(new ActionListener() {					//Everything below was created by Nick
			public void actionPerformed(ActionEvent e) {
				
				try{
					if(createGroup()){
						//Group.setVisible(false);
						updateRoommate();
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
					if(roommate.getGID() == 0){
						if(joinGroup()){
							//Group.setVisible(false);
							JOptionPane.showMessageDialog(null, "Group joined!");
						}else{
							JOptionPane.showMessageDialog(null, "Fatal Error.");
						}
					}else{
						JOptionPane.showMessageDialog(null, "You are already in a group!");
					}
					
				}
				catch(SQLException ex){
					JOptionPane.showConfirmDialog(null, ex.toString());
				}
				
			
			}
		});
		
		btnLeaveGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					if(leaveGroup()){
						//Group.setVisible(false);
						JOptionPane.showMessageDialog(null, "Group left!");
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
	//Begin Austin
	public void updateRoommate() throws SQLException{
		PreparedStatement prepared = null;
		String query = "";
		
		try{
			 
			Connection connection = SQLConnection.getInstance().getConn();
		    
		    query = "SELECT * FROM Groups WHERE GROUPS_OWNER_ID=?";
		    prepared = connection.prepareStatement(query);	
		    prepared.setInt(1, roommate.getID());
		    ResultSet rs = prepared.executeQuery();
		    rs.next();
		    int groupID = rs.getInt("GROUPS_ID");
		    roommate.setGroupID(groupID);
		    group.setID(groupID);
		    group.setName(rs.getString("GROUPS_NAME"));
		    group.setOwnerID(rs.getInt("GROUPS_OWNER_ID"));
		    lblGroupName.setText(rs.getString("GROUPS_NAME"));
		    prepared.close();
		    
		    query = "UPDATE Users SET USER_GROUP_ID=? WHERE USER_ID=?;";
		    prepared = connection.prepareStatement(query);
		    prepared.setInt(1, roommate.getGID());
		    prepared.setInt(2, roommate.getID());
		    prepared.execute();
		    

		    //return true;
		}catch(SQLException ex){
			ex.printStackTrace();
		//	return false;
		}finally{
			if(prepared != null){
				prepared.close();
			}
			
		}
	} //end Austin
	public boolean createGroup() throws SQLException{
	PreparedStatement prepared = null;
	String query = "";
	
	try{
		 
		Connection connection = SQLConnection.getInstance().getConn();
	   
	    query = "INSERT INTO Groups (GROUPS_NAME, GROUPS_OWNER_ID)" +
						"VALUES (?, ?)";
	    prepared = connection.prepareStatement(query);	
	    prepared.setString(1, txtCreateNewGroup.getText());
	    prepared.setInt(2, roommate.getID());
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
		    group.setID(groupID);
		    group.setName(rs.getString("GROUPS_NAME"));
		    group.setOwnerID(rs.getInt("GROUPS_OWNER_ID"));
		    lblGroupName.setText(rs.getString("GROUPS_NAME"));
		    prepared.close();
		    
		    query = "UPDATE Users SET USER_GROUP_ID = ? WHERE USER_ID=?;";
		    prepared = connection.prepareStatement(query);
		    prepared.setInt(1, groupID);
		    prepared.setInt(2, roommate.getID());
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
	
	public boolean leaveGroup() throws SQLException{
		PreparedStatement prepared = null;
		String query = "";
		
		try{
			 
		    Connection connection = SQLConnection.getInstance().getConn();
		   
		    roommate.setGroupID(0);
		    group.setID(0);
		    group.setName("You do not belong to a group");
		    group.setOwnerID(roommate.getID());
		    lblGroupName.setText(group.getName());
		    
		    query = "UPDATE Users SET USER_GROUP_ID = 0 WHERE USER_ID=?;";
		    prepared = connection.prepareStatement(query);
		    prepared.setInt(1, roommate.getID());
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
