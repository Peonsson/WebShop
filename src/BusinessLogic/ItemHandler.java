package BusinessLogic;

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
			
			items.add(new Item(itemId, name, price, quantity));
		}
		
		return items;
	}
}
