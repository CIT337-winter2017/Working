package object;

import java.sql.*;

import javax.swing.JOptionPane;

public class BillManager extends Roommate{

		private int managerID;
		private static BillManager sharedInstance = null;
				
		protected BillManager(){
			Roommate.getInstance();
			this.managerID = this.getID();
		}
		public static BillManager getInstance(){
			if(sharedInstance == null){
				sharedInstance = new BillManager();
			}
			return sharedInstance;
		}	
					
		public Bill addBill(String billTitle, String billAmount, String billDate, int id){
				
			
			Bill bill = new Bill();
			bill.createBill(billTitle, Float.valueOf(billAmount), Date.valueOf(billDate), id);
			
			try{
				bill.saveBill();
			}catch (SQLException ex){
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error: Please make sure date is in form of - yyyy-[m]m-[d]d");
			}
			return bill;
		}
		public void removeBill(int newBillID){
			
		}
		
}
