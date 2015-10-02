package BusinessLogic;

import java.util.ArrayList;

import Database.UserDB;

public class UserHandler {
	
	public static User getUser(String username) {
		return UserDB.getUser(username);
	}
	
	public static User getUser(int userId) {
		return UserDB.getUser(userId);
	}
	
//	public static int changePassword(int userId) {
//		return UserDB.changePassword(int userId);
//	}
	
	public static User loginUser(String username, String password) {
		
		User user = UserDB.getUser(username);
		username = username.toLowerCase();
		if(username.equals(user.getUsername().toLowerCase()) && password.equals(user.getPassword()))
			return user;
		else
			return null;
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
	
	public static int modifyUserAccesslevel(int myUserId, int targetUserId, int targetAccesslevel) {
		
		if(UserHandler.getUser(myUserId).getAccessLevel() > 2) { //If I am allowed to do this then continue; else return -1
			UserHandler.getUser(targetUserId).setAccessLevel(targetAccesslevel);
			return 0;
		}
		return -1;
	}
	
	public static int addUser(int myUserId, String username, String password, int accessLevel) {
		
		if(UserHandler.getUser(myUserId).getAccessLevel() > 2) { //If I am allowed to do this then continue; else return -1
			
			String tempUsername = username.toLowerCase(); //Compare usernames in lowercase
			ArrayList<User> myUsers = UserDB.getUsers(); 
			
			for(int i = 0; i < myUsers.size(); i++) { //Check if new user has unique username
				String tempDatabaseUsername = myUsers.get(i).getUsername().toLowerCase();  //Compare usernames in lowercase
				if(tempUsername.equals(tempDatabaseUsername))
					return -1; //if name isn't unique return -1;
			}
			
			return UserDB.addUser(username, password, accessLevel); //If I got here continue; else return -1
			
		}
		return -1;
	}
	
	public static int removeUser(int myUserId, int targetUserId) {
		
		if(UserHandler.getUser(myUserId).getAccessLevel() > 2) { //If I am allowed to do this then continue; else return -1
			
			ArrayList<User> myUsers = UserDB.getUsers(); 

			for(int i = 0; i < myUsers.size(); i++) {
				if(myUsers.get(i).getUserId() == targetUserId)
					return UserDB.removeUser(targetUserId); //if userId exists continue; else return -1
			}		
		}
		return -1;
	}
}
