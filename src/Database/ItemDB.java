package Database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BusinessLogic.Item;
import BusinessLogic.Order;

public class ItemDB extends Item {
	protected ItemDB(int itemId, String name, float price, int quantity) {
		super(itemId, name, price, quantity);
	}

	protected ItemDB(int itemId, String name, float price, int quantity,
			String category) {
		super(itemId, name, price, quantity, category);
	}

	public static Collection<ItemDB> searchByName(String string) {

		Vector<ItemDB> v = new Vector<>();
		Connection conn = DBManager.getConnection();
		try {

			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM Item WHERE Name = '" + string + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {

			int ItemId = rs.getInt("ItemId");
			String Name = rs.getString("Name");
			float Price = rs.getFloat("Price");
			int Quantity = rs.getInt("Quantity");
			v.add(new ItemDB(ItemId, Name, Price, Quantity));
			}
			return v;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Collection listItems() {

		ArrayList<ItemDB> v = new ArrayList<>();
		Connection conn = DBManager.getConnection();
		try {

			Statement stmt = conn.createStatement();
			String query = "SELECT Item.ItemId, Item.Category, Item.Name, Item.Price, Item.Quantity, Category.Name FROM Item JOIN Category ON Category.CategoryId = Item.Category";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
			int ItemId = rs.getInt("ItemId");
			String Name = rs.getString("Item.Name");
			int Quantity = rs.getInt("Quantity");
			float Price = rs.getFloat("Price");
			String Category = rs.getString("Category.Name");
			v.add(new ItemDB(ItemId, Name, Price, Quantity, Category));
			}

			return v;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void addItemToShop(Item item) {

		Connection conn = DBManager.getConnection();
		String name = item.getName();
		float price = item.getPrice();
		int quantity = item.getQuantity();
		String category = item.getCategory();

		try {
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO Item (Name, Price, Quantity, Category) VALUES ("
				+ name + ", " + price + "," + quantity + ", " + category + ");";
			stmt.executeQuery(query);
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	public static void removeItemFromShop(int itemId) {

		Connection conn = DBManager.getConnection();

		try {
			Statement stmt = conn.createStatement();
			String query = "DELETE FROM Item WHERE ItemId = " + itemId;
			stmt.executeQuery(query);
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	public static void removeItemFromShop(String name) {

		Connection conn = DBManager.getConnection();

		try {
			Statement stmt = conn.createStatement();
			String query = "DELETE FROM Item WHERE Name = " + name;
			stmt.executeQuery(query);
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	public static Item getItem(String name) {

		Connection conn = DBManager.getConnection();
		Item item = new Item();
		try {

			Statement stmt = conn.createStatement();
			String query = "SELECT Item.ItemId, Item.Category, Item.Name, Item.Price, Item.Quantity, Category.Name FROM Item JOIN Category ON Category.CategoryId = Item.Category WHERE Item.Name = "
				+ "'" + name + "'";
			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {

			int itemId = rs.getInt("ItemId");
			String Name = rs.getString("Item.Name");
			float price = rs.getFloat("Price");
			int quantity = rs.getInt("Quantity");
			String category = rs.getString("Category.Name");

			item.setItemId(itemId);
			item.setName(Name);
			item.setPrice(price);
			item.setQuantity(quantity);
			item.setCategory(category);

			return item;
			} else
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Item getItem(int ItemId) {

		Connection conn = DBManager.getConnection();
		Item item = new Item();
		try {

			Statement stmt = conn.createStatement();
			String query = "SELECT Item.ItemId, Item.Category, Item.Name, Item.Price, Item.Quantity, Category.Name FROM Item JOIN Category ON Category.CategoryId = Item.Category WHERE ItemId = "
				+ ItemId;
			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {

			int itemId = rs.getInt("ItemId");
			String Name = rs.getString("Name");
			float price = rs.getFloat("Price");
			int quantity = rs.getInt("Quantity");
			String category = rs.getString("Category.Name");

			item.setItemId(itemId);
			item.setName(Name);
			item.setPrice(price);
			item.setQuantity(quantity);
			item.setCategory(category);

			return item;
			} else
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int modifyItem(Item item) {
		
		if(item == null)
			return -1;
		
		int itemId = item.getItemId();
		String category = item.getCategory();
		String name = item.getName();
		float price = item.getPrice();
		int quantity = item.getQuantity();
		
		System.out.println("itemId = " + itemId);

		Connection conn = DBManager.getConnection();
		try {			
			Statement stmt = conn.createStatement();
			String query = "UPDATE Item SET " +
					"Name = '" + name + "', " +
					"Price = " + price + ", " +
					"Quantity = " + quantity +
					" WHERE ItemId = " + itemId;
			
			stmt.execute(query);
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static void createOrder(int myUserId, ResultSet myResultSet) {
		
		System.out.println("got here!");
		Connection conn = DBManager.getConnection();
		int sent = 0; //not sent yet
		
		try {
			
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			
			if(myResultSet == null)
				System.out.println("myResultSet = NULL");
			
			while(myResultSet.next()) {
				String query = "UPDATE Item SET Quantity = Quantity - " + myResultSet.getInt("Quantity") + " WHERE ItemId = " + myResultSet.getInt("ItemId");
				String query2 = "INSERT INTO Orders (UserId, ItemId, Sent, Quantity) VALUES (" + myUserId + ", " + myResultSet.getInt("ItemId") + ", " + sent + ", " + myResultSet.getInt("Quantity") + ")"; 
				String query3 = "DELETE FROM Cart WHERE UserId = " + myUserId;
				stmt.execute(query);
				stmt.execute(query2);
				stmt.execute(query3);
			}
			
			conn.commit();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return;
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return;
	}
	
	public static ArrayList<Order> getUserOrders(int userId) {
		ArrayList<Integer> userOrderIds = new ArrayList<>();
		ArrayList<Integer> sendStatusOrder = new ArrayList<>();
		ArrayList<Order> orders = new ArrayList<>();
		
		Connection conn = DBManager.getConnection();
		
		try {
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("SELECT * FROM UserOrders WHERE UserId = ?");
			query.setInt(1, userId);
			ResultSet rs = query.executeQuery();
			
			// Add order ids belonging to a user into list
			while (rs.next()) {
				userOrderIds.add(rs.getInt("UserOrderId"));
				sendStatusOrder.add(rs.getInt("Sent"));
			}
			
			System.out.println("AL : " + userOrderIds.toString());
			
			// Make query for each order id belonging to user
			query = (PreparedStatement) conn.prepareStatement("SELECT * FROM OrderItem WHERE UserOrderId = ?");
			for (int i = 0; i < userOrderIds.size(); i++) {
				ArrayList<Item> items = new ArrayList<>();
				
				query.setInt(1, userOrderIds.get(i));
				rs = query.executeQuery();
				
				while (rs.next()) {
					// Get the item in the order
					PreparedStatement query1 = (PreparedStatement) conn.prepareStatement("SELECT ItemId, Name, Price, Category FROM Item WHERE ItemId = ?");
					query1.setInt(1, rs.getInt("ItemId"));
					ResultSet rs1 = query1.executeQuery();
					rs1.next();
					
					int itemId = rs1.getInt("ItemId");
					String name = rs1.getString("Name");
					float price = rs1.getFloat("Price");
					int quantity = rs.getInt("Quantity");
					int category = rs1.getInt("Category");
					
					// Get the category name string
					PreparedStatement query2 = (PreparedStatement) conn.prepareStatement("SELECT Name FROM Category WHERE CategoryId = ?");
					query2.setInt(1, category);
					ResultSet rs2 = query2.executeQuery();
					rs2.next();
					
					items.add(new Item(itemId, name, price, quantity, rs2.getString("Name")));
				}
				
				orders.add(new Order(userOrderIds.get(i), userId, items, sendStatusOrder.get(i)));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}

	public static ResultSet getCart(int myUserId) {
		Connection conn = DBManager.getConnection();
		try {

			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM Cart WHERE UserId = " + myUserId;
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
