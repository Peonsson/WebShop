package Database;

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

	public static Collection<ItemDB> listItems() {
		
		Vector<ItemDB> v = new Vector<>();
		Connection conn = DBManager.getConnection();
		try {
			
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM Item";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int ItemId = rs.getInt("ItemId");
				String Name = rs.getString("Name");
				int Quantity = rs.getInt("Quantity");
				float Price = rs.getFloat("Price");
				v.add(new ItemDB(ItemId, Name, Price, Quantity));
			}
			return v;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
