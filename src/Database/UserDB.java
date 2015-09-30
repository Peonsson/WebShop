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
			String query = "SELECT Item.Name, Item.Price, Cart.Quantity FROM Item JOIN Cart ON Cart.ItemId = Item.ItemId WHERE Cart.UserId =" + userId;
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {

				String name = rs.getString("Name");
				float price = rs.getFloat("Price");
				int quantity = rs.getInt("Quantity");
				myList.add(new Item(name, price, quantity));
			}
			return myList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void addItemToCart(int userId, int itemId, int quantity) {
		Connection conn = DBManager.getConnection();
		try {

			Statement stmt = conn.createStatement();
			String query = "INSERT INTO Cart (UserId, ItemId, Quantity) VALUES (" + userId + ", " + itemId + "," + quantity + ");";
			stmt.executeQuery(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}
}
