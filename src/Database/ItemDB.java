package Database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BusinessLogic.Item;

public class ItemDB extends Item {
	protected ItemDB(int itemId, String name, float price, int quantity) {
		super(itemId, name, price, quantity);
	}
	
	protected ItemDB(int itemId, String name, float price, int quantity, String category) {
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
			String query = "INSERT INTO Item (Name, Price, Quantity, Category) VALUES (" + name + ", " + price + "," + quantity + ", " + category + ");";
			stmt.executeQuery(query);
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}
}
