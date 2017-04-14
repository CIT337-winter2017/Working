package object;

public class Roommate {
	
		private int id;
		private String firstName;
		private String lastName;
		private int groupID;
		private static Roommate sharedInstance = null;
		
		protected Roommate(){
			
		}
		public static Roommate getInstance(){
			if(sharedInstance == null){
				sharedInstance = new Roommate();
			}
			return sharedInstance;
		}
		
		
		public void setID (int newID){
			id = newID;
		}
		public void setFirstName(String newFirstName){
			firstName = newFirstName;
		}
		public void setLastName(String newLastName){
			lastName = newLastName;
		}
		public void setGroupID(int newGroupID){
			groupID = newGroupID;
		}
		
		
		public int getID (){
			return id;
		}
		public String getFName(){
			return firstName;
		}
		public String getLName(){
			return lastName;
		}
		public int getGID(){
			return groupID;
		}
		
}
