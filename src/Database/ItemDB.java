package Database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BusinessLogic.Item;
import BusinessLogic.Order;
import BusinessLogic.User;

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
	
	public static Order createOrder(int myUserId, ArrayList<Item> items) {
		
		Connection conn = DBManager.getConnection();
		int sent = 0; //not sent yet
		
		try {
			
			conn.setAutoCommit(false);
			
			for(int i = 0; i < items.size(); i++) {
				
				Statement stmt = conn.createStatement();
				
				String query = "UPDATE Item SET " +
						"Quantity = Quantity - " + items.get(i).getQuantity() +
						" WHERE ItemId = " + items.get(i).getItemId();
				
				stmt.execute(query);
				
				String query2 =
						"INSER INTO Orders (UserId, ItemId, Sent, Quantity) VALUES (" + myUserId + ", " + items.get(i).getItemId() + ", " + sent + ", " + items.get(i).getQuantity() + ")"; 
				
				stmt.execute(query2);
			}
			
			conn.commit();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
		return new Order(myUserId, items, sent);
	}
}
