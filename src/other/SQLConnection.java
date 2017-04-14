package other;

import java.sql.Connection;

public class SQLConnection {		//all code in this class created by Nick
		
		private Connection connection;
		
		private static SQLConnection sharedInstance = null;
		
		protected SQLConnection(){
			
		}
		public static SQLConnection getInstance(){
			if(sharedInstance == null){
				sharedInstance = new SQLConnection();
			}
			return sharedInstance;
		}
		
		
		public void setConn (Connection conn){
			connection = conn;
		}

		
		public Connection getConn (){
			return connection;
		}

	
}
