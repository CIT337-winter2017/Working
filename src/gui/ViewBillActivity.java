package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import object.Roommate;

public class ViewBillActivity {
	private CardLayout layout;
	private JPanel Cards;
	private JPanel Home;
	private int billID;
	private String billTitle;
	private Date billDate;
	private float billAmount;
	private int billManagerID;
	private int rowCount,elementCount=0;
	ArrayList groupList=new ArrayList();
	ArrayList billList=new ArrayList();
	private Object[] groupIDArray;
	private Object[] BillIDArray;
	private int groupIntIdArray[];
	private int BillIntIdArray[];
	private Roommate roommate = Roommate.getInstance();
	public ViewBillActivity(JPanel newCards, CardLayout newLayout){
		Cards = newCards;
		layout = newLayout;
	}
	

@SuppressWarnings("resource")
public void startActivity(){
	
	JPanel ViewBill = new JPanel();
	Cards.add(ViewBill, "view");
	ViewBill.setLayout(new BorderLayout());
	
	PreparedStatement prepared = null;
	String[] ColumNames={"Bill Name","Bill Due Date","Bill Amount"};
	DefaultTableModel model = new DefaultTableModel(ColumNames,0);
	String conn = "jdbc:sqlserver://cit337.database.windows.net:1433;" +
			"database=CSE337;user=afdanaj@cit337;password=Temp12345;"+
			"encrypt=true;trustServerCertificate=false;" +
			"hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
   layout.show(Cards, "view");
try {
	Connection connection = DriverManager.getConnection(conn);
  		String query="SELECT * FROM Bills";
    Object[] rowData = new Object[3];
	 prepared = connection.prepareStatement(query);
	 Connection connection2 = DriverManager.getConnection(conn);
		Connection connection3 = DriverManager.getConnection(conn);

		 prepared = connection.prepareStatement(query);
		 //prepared.setInt(1, roommate.getGID());
	     ResultSet rs=prepared.executeQuery();
	     
		PreparedStatement prepared2 = null;
		 prepared2 = connection2.prepareStatement(query);
		// prepared2.setInt(1, roommate.getGID());
	     ResultSet rsc=prepared2.executeQuery();
			String query5 = "SELECT * FROM Users WHERE USER_GROUP_ID=?";
			PreparedStatement prepared5 = null;
			Connection connection5 = DriverManager.getConnection(conn);
			prepared5 = connection5.prepareStatement(query5);
	    	 prepared5.setInt(1, roommate.getGID());
				ResultSet rsgetGroup=prepared5.executeQuery();
				while(rsgetGroup.next())
				{
					//System.out.println(rsgetGroup.getInt("USER_ID"));
					groupList.add(rsgetGroup.getInt("USER_ID"));
					elementCount++;
				}
				groupIDArray=new Object[elementCount];
				groupIDArray=groupList.toArray();
				groupIntIdArray=new int[elementCount];
				for(int i=0;i<elementCount;i++)
		    	 {
					
					groupIntIdArray[i]=(int)groupIDArray[i];
					
		    		 
		    	 }
	     while(rsc.next()) {
	    	 for(int i=0;i<elementCount;i++)
	    	 {
	    		 if(rsc.getInt("BILL_OWNER_ID")==groupIntIdArray[i])
	    		 {
	    			 billList.add(rsc.getInt("BILL_ID"));
	    			 rowCount++;
	    		 }
	    	 }
	    	 
	    	}
	     connection2.close();
			BillIDArray=new Object[elementCount];
			BillIDArray=billList.toArray();
			BillIntIdArray=new int[elementCount];
			for(int i=0;i<rowCount;i++)
	    	 {

				BillIntIdArray[i]=(int)BillIDArray[i];
	    	 }
		     int count=0;
		while(rs.next())
	   {
			if(rowCount>count)
			{
			if(BillIntIdArray[count]==rs.getInt("BILL_ID"))
			{
		   rowData[0]=rs.getString("BILL_NAME");
		   rowData[1]=rs.getDate("BILL_DUE_DATE");
		   rowData[2]=rs.getFloat("BILL_AMOUNT");
		   model.addRow(rowData);
		   count++;
			}
			}
  else{
	   break;
  }
		   //intID++;
		   //prepared.setInt(1, intID);
		   //rs=prepared.executeQuery();
	   }
			
		 JTable jtable = new JTable(model);
	     JScrollPane scrollPane = new JScrollPane(jtable);
	     jtable.setFillsViewportHeight(true);
		ViewBill.add(scrollPane, BorderLayout.CENTER);
		

} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();

}
		
}
}