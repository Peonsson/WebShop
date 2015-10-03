package BusinessLogic;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import Database.ItemDB;

public class ItemHandler {
	public static Hashtable searchByName(String name) {
		
		Collection c = Item.searchByName(name);
		Hashtable t = new Hashtable();
		t.put("size", c.size());
		Iterator it = c.iterator();
		for (int i = 0; it.hasNext(); i++) {
			Hashtable item = new Hashtable();
			Item nextItem = (Item) it.next();
			item.put("name", nextItem.getName());
			item.put("price", nextItem.getPrice());
			t.put("Item" + i, item);
		}		
		return t;
	}
	
	public static ArrayList<Item> listItems() {
		Collection<ItemDB> c = Item.listItems();
		ArrayList<Item> items = new ArrayList<>();
		
		for (Item item : c) {
			int itemId = item.getItemId();
			String name = item.getName();
			float price = item.getPrice();
			int quantity = item.getQuantity();
			String category = item.getCategory();
			
			items.add(new Item(itemId, name, price, quantity, category));
		}
		
		return items;
	}
	
	public static int addItemToShop(int userId, String name, float price, int quantity, String category) {
		
		if(UserHandler.getUser(userId).getAccessLevel() > 1) { //If I am allowed to do this then continue; else return -1
			Item item = new Item(name, price, quantity, category);
			ItemDB.addItemToShop(item);
			return 0;
		}
		return -1;
	}
	
	public static Item getItem(String name) {
		return ItemDB.getItem(name);
	}
	
	public static Item getItem(int itemId) {
		return ItemDB.getItem(itemId);
	}
	
	public static int modifyItem(int userId, int itemId, String name, int quantity, float price, String category) {
		
		if(UserHandler.getUser(userId).getAccessLevel() > 1) {	//If I am allowed to do this then continue; else return -1
			
			Item item = new Item(itemId, name, price, quantity, category);
			return ItemDB.modifyItem(item);
		}
		return -1;
	}
	
	public static void createOrder(int myUserId) {
		
		ResultSet rs = ItemDB.getCart(myUserId);
		ItemDB.createOrder(myUserId, rs);
		return;
	}
	
	public static int removeItemFromShop(int userId, int itemId) {
	
		if(UserHandler.getUser(userId).getAccessLevel() > 1) {	//If I am allowed to do this then continue; else return -1
			return ItemDB.removeItemFromShop(itemId);
		}
		return -1;
	}
}
