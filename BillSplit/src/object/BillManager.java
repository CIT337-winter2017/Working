package object;

import java.util.*;

public class BillManager extends Roommate{

		
	
		public BillManager(){
			super();
		}
	
		public BillManager(int id, String firstName, String lastName, int groupID){
			super(id, firstName, lastName, groupID);
		}
		
		public Bill addBill(String billTitle, float billAmount, Date billDate){
			Bill bill = new Bill();
			//bill.createBill(billTitle, billAmount, billDate);
			//bill.saveBill();
			return bill;
		}
		public void removeBill(int newBillID){
			
		}
		
}
