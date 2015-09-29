package BusinessLogic;

import java.util.Collection;
import java.util.Iterator;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class Test {

	public static void main(String[] args) {
		Collection c = Item.searchItems("Ficklampa");
		Hashtable t = new Hashtable();
		
		t.put("size", c.size());
		
		Iterator it = c.iterator();
		
		for (int i = 0; it.hasNext(); i++) {
			Hashtable item = new Hashtable();
			Item nextItem = (Item) it.next();
			item.put("name", nextItem.getName());
			item.put("price", nextItem.getPrice());
			t.put("Item"+ i, item);
		}
		
		System.out.println(t.toString());
	}
}
