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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ViewBillActivity {
	private CardLayout layout;
	private JPanel Cards;
	private JPanel Home;
	private int billID;
	private String billTitle;
	private Date billDate;
	private float billAmount;
	private int billManagerID;
	
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

	int intID=1;
    
	
	
	String conn = "jdbc:sqlserver://cit337.database.windows.net:1433;" +
			"database=CSE337;user=afdanaj@cit337;password=Temp12345;"+
			"encrypt=true;trustServerCertificate=false;" +
			"hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
   layout.show(Cards, "view");
try {
	String query="SELECT * FROM Bills WHERE BILL_OWNER_ID = ?";
	Connection connection = DriverManager.getConnection(conn);
	//Statement stmt = connection.createStatement();
	
   
    Object[] rowData = new Object[3];

    //rs = stmt.executeQuery("SELECT (BILL_ID,BILL_NAME, BILL_DUE_DATE, BILL_AMOUNT, BILL_OWNER_ID) FROM dbo.Bills");
    
    
	 prepared = connection.prepareStatement(query);
	 prepared.setInt(1, 0);
     ResultSet rs=prepared.executeQuery();
    
    
    
    
		while(rs.next())
	   {

		   rowData[0]=rs.getString("BILL_NAME");
		   rowData[1]=rs.getDate("BILL_DUE_DATE");
		   rowData[2]=rs.getFloat("BILL_AMOUNT");
		   model.addRow(rowData);
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