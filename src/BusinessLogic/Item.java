package BusinessLogic;

import java.util.Collection;
import Database.ItemDB;

public class Item {
	private int itemId;
	private String name;
	private float price;
	private static int quantity;
	
	protected Item(int itemId, String name, float price) {
		this.itemId = itemId;
		this.name = name;
		this.price = price;
	}
	
	public static Collection<ItemDB> searchItems(String name) {
		return ItemDB.searchItems(name);
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public static int getQuantity() {
		return quantity;
	}

	public static void setQuantity(int quantity) {
		Item.quantity = quantity;
	}
	
	
}
