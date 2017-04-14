package object;


import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;

import other.SQLConnection;


public class Bill {

	private int billID;
	private String billTitle;
	private Date billDate;
	private float billAmount;
	private int billManagerID;
	
	public Bill(){
		
	}
	public void createBill(String billTitle, float billAmount, Date billDate, int billManagerID){
		this.billTitle = billTitle;
		this.billAmount = billAmount;
		this.billDate = billDate;
		this.billManagerID = billManagerID;
	}
	public void saveBill() throws SQLException{
		
		
		PreparedStatement prepared = null;
		String query = "";
		try{
			 
			Connection connection = SQLConnection.getInstance().getConn(); //connection line simplified by Nick
		   
		    query = "INSERT INTO Bills (BILL_NAME, BILL_DUE_DATE, BILL_AMOUNT, BILL_OWNER_ID)" +
							"VALUES (?, ?, ?, ?)";
		    prepared = connection.prepareStatement(query);		
		    prepared.setString(1, billTitle);
		    prepared.setDate(2, billDate);
		    prepared.setFloat(3, billAmount);
		    prepared.setInt(4, billManagerID);
		    prepared.execute();
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			if(prepared != null){
				prepared.close();
			}
			
		}
		
	}
	
}
