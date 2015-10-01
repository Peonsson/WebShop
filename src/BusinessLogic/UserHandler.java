package BusinessLogic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import Database.UserDB;

public class UserHandler {
	
	public static User getUser(String username) {
		return UserDB.getUser(username);
	}
	
	public static int loginUser(String username, String password) {
		
		User user = UserDB.getUser(username);
		username = username.toLowerCase();
		if(username.equals(user.getUsername().toLowerCase()) && password.equals(user.getPassword()))
			return user.getUserId();
		else
			return -1;
	}
	
	public static ArrayList<Item> listCartByUserId(int userId) {
		System.out.println("UserHandler userId : " + userId);
		ArrayList<Item> myList = UserDB.listCartByUserId(userId);
		System.out.println("UserHandler ArrayList : " + myList.toString());
		return myList;
	}
	
	public static void addItemToCart(int userId, int itemId, int quantity) {
		UserDB.addItemToCart(userId, itemId, quantity);
		return;
	}
}
