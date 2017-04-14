package object;

public class Group {  // all code in this class created by Nick

	private int id;
	private String name;
	private int ownerID;
	private static Group sharedInstance = null;
	
	protected Group(){
		
	}
	public static Group getInstance(){
		if(sharedInstance == null){
			sharedInstance = new Group();
		}
		return sharedInstance;
	}
	
	
	public void setID (int newID){
		id = newID;
	}
	public void setName(String newName){
		name = newName;
	}
	public void setOwnerID(int newOwnerID){
		ownerID = newOwnerID;
	}
	
	
	public int getID (){
		return id;
	}
	public String getName(){
		return name;
	}
	public int getOwnerID(){
		return ownerID;
	}
}
