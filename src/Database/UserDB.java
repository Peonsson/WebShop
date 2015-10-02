package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import BusinessLogic.Item;
import BusinessLogic.User;

public class UserDB extends User {

	public UserDB(int userId, String username, String password, int accessLevel) {
		super(userId, username, password, accessLevel);
	}
	
	public static Collection<UserDB> searchByUsername(String string) {
		
		Vector<UserDB> v = new Vector<>();
		Connection conn = DBManager.getConnection();
		try {

			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM User WHERE Username = '" + string + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {

				int userId = rs.getInt("UserId");
				String name = rs.getString("Username");
				String password = rs.getString("Password");
				int accessLevel = rs.getInt("AccessLevel");
				v.add(new UserDB(userId, name, password, accessLevel));
			}
			return v;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Item> listCartByUserId(int userId) {
		
		ArrayList<Item> myList = new ArrayList<Item>();
		Connection conn = DBManager.getConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT Item.ItemId, Item.Name, Item.Price, Cart.Quantity FROM Item JOIN Cart ON Cart.ItemId = Item.ItemId WHERE Cart.UserId =" + userId;
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int itemId = rs.getInt("ItemId");
				String name = rs.getString("Name");
				float price = rs.getFloat("Price");
				int quantity = rs.getInt("Quantity");
				myList.add(new Item(itemId, name, price, quantity));
			}
			
			System.out.println("UserDB ArrayList : " + myList.toString());
			
			return myList;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void addItemToCart(int userId, int itemId, int quantity) {
		Connection conn = DBManager.getConnection();
		try {

			Statement stmt = conn.createStatement();
			String query = "INSERT INTO Cart (UserId, ItemId, Quantity) VALUES (" + userId + ", " + itemId + "," + quantity + ");";
			stmt.execute(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	public static User getUser(String username) {
		Connection conn = DBManager.getConnection();
		try {

			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM User WHERE Username = '" + username + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int userId = rs.getInt("UserId");
				String Username = rs.getString("Username");
				String password = rs.getString("Password");
				int accessLevel = rs.getInt("AccessLevel");
				return new User(userId, Username, password, accessLevel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static User getUser(int UserId) {
		
		Connection conn = DBManager.getConnection();
		
		try {
			User user = new User();
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM User WHERE UserId = '" + UserId + "'";
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				user.setUserId(rs.getInt("UserId"));
				user.setUsername(rs.getString("Username"));
				user.setPassword(rs.getString("Password"));
				user.setAccessLevel(rs.getInt("AccessLevel"));
			}

			return user;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int addUser(String username, String password, int accessLevel) {
		
		Connection conn = DBManager.getConnection();
		
		try {

			Statement stmt = conn.createStatement();
			String query = "INSERT INTO User (Username, Password, AccessLevel) VALUES ('" + username + "', '" + password + "', " + accessLevel + ")";
			stmt.execute(query);
			
			return 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int removeUser(String username) {
		
		Connection conn = DBManager.getConnection();
		
		try {

			Statement stmt = conn.createStatement();
			String query = "";
			stmt.execute(query);
			
			return 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int removeUser(int userId) {
		
		Connection conn = DBManager.getConnection();
		
		try {

			Statement stmt = conn.createStatement();
			String query = "DELETE FROM User WHERE UserId = " + userId;
			stmt.execute(query);
			
			return 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static ArrayList<User> getUsers() {
		
		Connection conn = DBManager.getConnection();
		ArrayList<User> myList = new ArrayList<User>(1000);
		
		try {

			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM User";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int userId = rs.getInt("UserId");
				String username = rs.getString("Username");
				String password = rs.getString("Password");
				int accessLevel = rs.getInt("AccessLevel");
				myList.add(new User(userId, username, password, accessLevel));
			}
			
			return myList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int changePassword(int userId, String password) {
		
		Connection conn = DBManager.getConnection();
	
		try {

			Statement stmt = conn.createStatement();
			
			String query = "UPDATE User SET Password = '" + password + "' WHERE UserId = " + userId;
			stmt.execute(query);
			
			return 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static int modifyUser(int targetUserId, String username, String password, int accessLevel) {

		Connection conn = DBManager.getConnection();
		
		try {

			Statement stmt = conn.createStatement();
			String query = "UPDATE User SET Username = '" + username + "',  Password = '" + password + "', AccessLevel = " + accessLevel + " WHERE UserId = " + targetUserId;
			stmt.execute(query);
			
			return 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
