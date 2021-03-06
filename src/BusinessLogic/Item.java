package BusinessLogic;

import java.util.Collection;
import Database.ItemDB;

public class Item {
	private int itemId;
	private String name;
	private float price;
	private int quantity;
	private String category;
	
	public Item(int itemId, String name, float price, int quantity, String category) {
		this.itemId = itemId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
	}

	public Item(int itemId, String name, float price, int quantity) {
		this.itemId = itemId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public Item(String name, float price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public Item(String name, float price, int quantity, String category) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
	}

	public Item() {
	}

	public Item(int itemId, int quantity) {
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public static Collection searchByName(String name) {
		return ItemDB.searchByName(name);
	}
	
	public static Collection<ItemDB> listItems() {
		return ItemDB.listItems();
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String toString() {
		return "(ItemId : " + itemId + ", Name: " + name + ", Price: " + price + ", Quantity: " + quantity + ")";
	}
}
