package object;


import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;


public class Bill {

	private int billID;
	private String billTitle;
	private Date billDate;
	private float billAmount;
	private int billManagerID;
	
	public Bill(){
		
	}
	public void createBill(String billTitle, float billAmount, Date billDate){
		this.billTitle = billTitle;
		this.billAmount = billAmount;
		this.billDate = billDate;
	}
	public void saveBill() throws SQLException, ClassNotFoundException{
		
		/*
		Statement stmt = null;
		String query = "";
		try{
			 
		    String conn = "jdbc:sqlserver://cit337.database.windows.net:1433;" +
		    					"database=CSE337;user=afdanaj@cit337;password=Temp12345;"+
		    					"encrypt=true;trustServerCertificate=false;" +
		    					"hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		    
		    Connection connection = DriverManager.getConnection(conn);
		    stmt = connection.createStatement();
		    query = "INSERT INTO Bill (BILL_ID, BILL_DESCRIP, BILL_DUE_DATE, BILL_AMOUNT, BILL_OWNER)" +
							"VALUES (?, ?, ?, ?, ?)";
		    PreparedStatement prepared = connection.prepareStatement(query);
		    prepared.setInt(1, billID);
		    prepared.setString(2, billTitle);
		    prepared.setDate(3, billDate);
		    prepared.setFloat(4, billAmount);
		    prepared.setInt(5, billManagerID);
		    prepared.execute();
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			if(stmt != null){
				stmt.close();
			}
			
		}
		*/
	}
	
}
