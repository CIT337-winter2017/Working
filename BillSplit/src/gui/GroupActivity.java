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
	private JTextField txtCreateNewGroup;
	private JTextField txtEnterGroupName;
	
	
public GroupActivity(JPanel newCards){
		
		Cards = newCards;
		
	}
	
	public void startActivity(){
	
		
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
		
		JButton btnCreateGroup = new JButton("Create Group");
		btnCreateGroup.setBounds(228, 53, 167, 35);
		Group.add(btnCreateGroup);
		
		Group.setVisible(true);
	}
	public boolean createGroup() throws SQLException{
	PreparedStatement prepared = null;
	String query = "";
	
	try{
		 
	    String conn = "jdbc:sqlserver://cit337.database.windows.net:1433;" +
	    					"database=CSE337;user=afdanaj@cit337;password=Temp12345;"+
	    					"encrypt=true;trustServerCertificate=false;" +
	    					"hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
	    
	    Connection connection = DriverManager.getConnection(conn);
	   
	    query = "INSERT INTO Groups (GROUPS_ID, GROUPS_NAME, GROUPS_OWNER_ID)" +
						"VALUES (?, ?, ?)";
	    prepared = connection.prepareStatement(query);	
	    prepared.setInt(1, 0);
	    prepared.setString(2, txtCreateNewGroup.getText());
	    prepared.setInt(3, 0);
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
