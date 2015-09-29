package BusinessLogic;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

public class UserHandler {
	public static Hashtable searchByUsername(String username) {
		
		Collection c = User.searchByUsername(username);
		Hashtable t = new Hashtable();
		t.put("size", c.size());
		Iterator it = c.iterator();
		for (int i = 0; it.hasNext(); i++) {
			Hashtable user = new Hashtable();
			User nextItem = (User) it.next();
			user.put("username", nextItem.getUsername());
			user.put("password", nextItem.getPassword());
			user.put("accessLevel", nextItem.getAccessLevel());
			t.put("User" + i, user);
		}		
		return t;
	}
}
