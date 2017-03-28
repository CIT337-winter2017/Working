package object;

public class Roommate {

		private int id;
		private String firstName;
		private String lastName;
		private int groupID;
		
		public Roommate(){
			
		}
		public Roommate(int id, String firstName, String lastName, int groupID){
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.groupID = groupID;
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
		
}
