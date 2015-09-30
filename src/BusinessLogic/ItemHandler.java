package BusinessLogic;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

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
	
	public static Hashtable listItems() {
		
		Collection c = Item.listItems();
		Hashtable t = new Hashtable();
		t.put("size", c.size());
		Iterator it = c.iterator();
		for (int i = 0; it.hasNext(); i++) {
			Hashtable item = new Hashtable();
			Item nextItem = (Item) it.next();
			item.put("name", nextItem.getName());
			item.put("price", nextItem.getPrice());
			item.put("quantity",nextItem.getQuantity());
			item.put("itemId", nextItem.getItemId());
			t.put("Item"+ i, item);
		}		
		return t;
	}
}
