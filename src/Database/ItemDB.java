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

	public static int addItemToShop(Item item) {

		if (item == null)
			return -1;

		String name = item.getName();
		float price = item.getPrice();
		int quantity = item.getQuantity();
		String category = item.getCategory();

		Connection conn = DBManager.getConnection();
		try {
			Statement stmt = conn.createStatement();
			int categoryId = -1;

			String getOrSetCategoryQuery = "SELECT * FROM Category WHERE Name = '"
				+ category + "'";
			ResultSet rs = stmt.executeQuery(getOrSetCategoryQuery);

			if (rs.next()) { // if categoryId exists - get it; else create it
			categoryId = rs.getInt("CategoryId");

			} else {

			String createCategory = "INSERT INTO Category (Name) VALUES ('"
					+ category + "')";
			stmt.execute(createCategory); // create the category

			String getOrSetCategoryQuery2 = "SELECT * FROM Category WHERE Name = '"
					+ category + "'";
			ResultSet rs3 = stmt.executeQuery(getOrSetCategoryQuery2); // get
																		// new
																		// categoryId

			if (rs3.next()) {
				categoryId = rs3.getInt("CategoryId");
			}
			}

			String query = "INSERT INTO Item (Name, Price, Quantity, Category) VALUES ('"
				+ name + "', " + price + ", " + quantity + ", " + categoryId
				+ ")";
			stmt.execute(query);

			return 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static int removeItemFromShop(int itemId) {

		Connection conn = DBManager.getConnection();

		try {
			Statement stmt = conn.createStatement();
			String query = "DELETE FROM Item WHERE ItemId = " + itemId;
			stmt.execute(query);
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
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

		if (item == null)
			return -1;

		int itemId = item.getItemId();
		String category = item.getCategory();
		String name = item.getName();
		float price = item.getPrice();
		int quantity = item.getQuantity();

		Connection conn = DBManager.getConnection();
		try {
			// TRANSACTION START
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			int categoryId = -1;

			String getOrSetCategoryQuery = "SELECT * FROM Category WHERE Name = '"
				+ category + "'";
			ResultSet rs = stmt.executeQuery(getOrSetCategoryQuery);

			if (rs.next()) { // if categoryId exists - get it; else create it
				categoryId = rs.getInt("CategoryId");

			}
			else {
				String createCategory = "INSERT INTO Category (Name) VALUES ('"
						+ category + "')";
				stmt.execute(createCategory); // create the category
	
				String getOrSetCategoryQuery2 = "SELECT * FROM Category WHERE Name = '"
						+ category + "'";
				ResultSet rs3 = stmt.executeQuery(getOrSetCategoryQuery2); // get
																			// new
																			// categoryId
				if (rs3.next())
					categoryId = rs3.getInt("CategoryId");
			}

			String query = "UPDATE Item SET " + "Name = '" + name + "', "
				+ "Price = " + price + ", " + "Quantity = " + quantity + ", "
				+ "Category = " + categoryId + " WHERE ItemId = " + itemId;

			stmt.execute(query);
			conn.commit();
			// TRANSACTION DONE
			conn.setAutoCommit(true);
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static void createOrder(int myUserId, ResultSet myResultSet) {

		Connection conn = DBManager.getConnection();
		int sent = 0; // not sent yet

		try {
			if (myResultSet.next() == false) {
				System.out.println("Nothing in cart");
				return;
			}

			// Reset cursor if there are results
			myResultSet.beforeFirst();

			// TRANSACTION START
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();

			// Create new order
			String query1 = "INSERT INTO UserOrders (UserId, Sent) VALUES ("
				+ myUserId + ", " + sent + ")";
			stmt.execute(query1);

			// Get last added order
			String query2 = "SELECT UserOrderId FROM UserOrders ORDER BY UserOrderId DESC LIMIT 1";
			ResultSet rs = stmt.executeQuery(query2);
			int userOrderId = 0;
			
			if (rs.next()) {
				userOrderId = rs.getInt("UserOrderId");
			}

			while (myResultSet.next()) {
				String query3 = "UPDATE Item SET Quantity = Quantity - "
						+ myResultSet.getInt("Quantity") + " WHERE ItemId = "
						+ myResultSet.getInt("ItemId");
				stmt.execute(query3);
	
				// Add order items
				String query4 = "INSERT INTO OrderItem (UserOrderId, ItemId, Quantity) VALUES ("
						+ userOrderId + ", "
						+ myResultSet.getInt("ItemId") + ", "
						+ myResultSet.getInt("Quantity") + ")";
				stmt.execute(query4);
	
				// Delete from cart
				String query5 = "DELETE FROM Cart WHERE UserId = " + myUserId;
				stmt.execute(query5);
			}

			conn.commit();
			// TRANSACTION END
		}
		catch (SQLException e) {
			try {
				conn.rollback();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return;
		}
		finally {
			try {
				conn.setAutoCommit(true);
			}
			catch (SQLException e) {
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
			PreparedStatement query = (PreparedStatement) conn
				.prepareStatement("SELECT * FROM UserOrders WHERE UserId = ?");
			query.setInt(1, userId);
			ResultSet rs = query.executeQuery();

			// Add order ids belonging to a user into list
			while (rs.next()) {
				userOrderIds.add(rs.getInt("UserOrderId"));
				sendStatusOrder.add(rs.getInt("Sent"));
			}

			// Make query for each order item belonging to order
			query = (PreparedStatement) conn.prepareStatement("SELECT * FROM OrderItem WHERE UserOrderId = ?");
			for (int i = 0; i < userOrderIds.size(); i++) {
				ArrayList<Item> items = new ArrayList<>();

				query.setInt(1, userOrderIds.get(i));
				rs = query.executeQuery();
	
				while (rs.next()) {
					// Get the item in the order
					PreparedStatement query1 = (PreparedStatement) conn
							.prepareStatement(
									"SELECT ItemId, Name, Price, Category FROM Item WHERE ItemId = ?");
					query1.setInt(1, rs.getInt("ItemId"));
					ResultSet rs1 = query1.executeQuery();
					rs1.next();
	
					int itemId = rs1.getInt("ItemId");
					String name = rs1.getString("Name");
					float price = rs1.getFloat("Price");
					int quantity = rs.getInt("Quantity");
					int category = rs1.getInt("Category");
	
					// Get the category name string
					PreparedStatement query2 = (PreparedStatement) conn
							.prepareStatement(
									"SELECT Name FROM Category WHERE CategoryId = ?");
					query2.setInt(1, category);
					ResultSet rs2 = query2.executeQuery();
					rs2.next();
	
					items.add(new Item(itemId, name, price, quantity, rs2.getString("Name")));
				}

				orders.add(new Order(userOrderIds.get(i), userId, items,sendStatusOrder.get(i)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orders;
	}

	public static ArrayList<Order> getAllUnhandledOrders() {
		ArrayList<Order> orders = new ArrayList<>();
		ArrayList<Integer> userOrderIds = new ArrayList<>();
		ArrayList<Integer> userIds = new ArrayList<>();
		ArrayList<Integer> sendStatusOrder = new ArrayList<>();
		
		Connection conn = DBManager.getConnection();
		
		try {
			// Find orders where sent status = 0 (not sent)
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("SELECT * FROM UserOrders WHERE Sent = ?");
			query.setInt(1, 0);
			ResultSet rs = query.executeQuery();

			// Add order ids that haven't been sent to a list
			while (rs.next()) {
				userOrderIds.add(rs.getInt("UserOrderId"));
				userIds.add(rs.getInt("UserId"));
				sendStatusOrder.add(rs.getInt("Sent"));
			}
			
			
			// Make query for each order item belonging to non-sent order
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
					PreparedStatement query2 = (PreparedStatement) conn
							.prepareStatement(
									"SELECT Name FROM Category WHERE CategoryId = ?");
					query2.setInt(1, category);
					ResultSet rs2 = query2.executeQuery();
					rs2.next();
		
					items.add(new Item(itemId, name, price, quantity, rs2.getString("Name")));
				}
		
				orders.add(new Order(userOrderIds.get(i), userIds.get(i), items, sendStatusOrder.get(i)));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}
	
	public static void changeOrderStatus(int orderId) {
		Connection conn = DBManager.getConnection();
		
		try {
			// Set to 1 (sent)
			PreparedStatement update = (PreparedStatement) conn.prepareStatement("UPDATE UserOrders SET Sent = 1 WHERE UserOrderId = ?");
			update.setInt(1, orderId);
			update.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
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
