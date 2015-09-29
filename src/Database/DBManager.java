package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	
	public static Connection getConnection() {
      try {
      	Class.forName("com.mysql.jdbc.Driver").newInstance();	
      	return DriverManager.getConnection("jdbc:mysql://localhost:3307/webshop?user=root&password=password");
      }
      catch (SQLException sqle) {
      	// handle any errors
         System.out.println("SQLException: " + sqle.getMessage());
         System.out.println("SQLState: " + sqle.getSQLState());
         System.out.println("VendorError: " + sqle.getErrorCode());
      }
      catch (Exception ex) {
      	// handle the error
      }
      
      return null;
	}
}
