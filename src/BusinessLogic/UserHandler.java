package BusinessLogic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import Database.UserDB;

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
	//TODO use a Cart class instead!
	public static ArrayList<Item> listCartByUserId(int userId) {
		ArrayList<Item> myList = UserDB.listCartByUserId(userId);
		return myList;
	}
	
	public static ArrayList<Item> addItemToCartItems() {
		ArrayList<Item> myList = new ArrayList<Item>();
		return myList;
	}
}
