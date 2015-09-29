package BusinessLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class LoadDriver {
	public static void main(String[] args) {
        try {
        	
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = null;
            try {
            	
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/webshop", "root", "password");
                Statement stmt = null;
                ResultSet rs = null;
                try {
                    stmt = conn.createStatement();
                    
                    // or alternatively, if you don't know ahead of time that
                    // the query will be a SELECT...
                    
//                    stmt.execute("INSERT INTO `WebShop`.`Access` (`Name`) VALUES ('User')");
                    
//                    stmt.execute("INSERT INTO `WebShop`.`User`(`Username`,`Password`,`AccessLevel`)"
//                    		+ "VALUES ('Peonsson', 'password', 1)");
//                    
                    if (stmt.execute("SELECT Username FROM User")) {
                        rs = stmt.getResultSet();
                    }
                    
                    while(rs.next())
                    	System.out.println(rs.getString("Username"));
                    
                    // Now do something with the ResultSet ....
                    
                } catch (SQLException ex){
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                } finally {
                	
                    // it is a good idea to release
                    // resources in a finally{} block
                    // in reverse-order of their creation
                    // if they are no-longer needed
                	
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException sqlEx) { } // ignore
                        rs = null;
                    } if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (SQLException sqlEx) { } // ignore
                        stmt = null;
                    }
                }
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        } catch (Exception ex) {
        }
    }
}