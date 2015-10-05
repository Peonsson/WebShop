package BusinessLogic;

import java.util.ArrayList;
import BusinessLogic.ItemDTO;
import Database.UserDB;

public class UserHandler {
	
	public static UserDTO getUser(String username) {
		
		User user = UserDB.getUser(username);
		
		int userId = user.getUserId();
		String usernameDTO = user.getUsername();
		String password = user.getPassword();
		int accessLevel = user.getAccessLevel();
		ArrayList<ItemDTO> cart = new ArrayList<ItemDTO>();
		
		for(int i = 0; i < user.getMyCart().size(); i++) {
			
			Item item = user.getMyCart().get(i);
			
			int itemId = item.getItemId();
			String name = item.getName();
			float price = item.getPrice();
			int quantity = item.getQuantity();
			String category = item.getCategory();
			
			cart.add(new ItemDTO(itemId, name, price, quantity, category));
		}
		return new UserDTO(userId, username, password, accessLevel, cart);
	}
	
	public static UserDTO getUser(int userId) {
		
		User user = UserDB.getUser(userId);
		
		int userIdDTO = user.getUserId();
		String username = user.getUsername();
		String password = user.getPassword();
		int accessLevel = user.getAccessLevel();
		ArrayList<ItemDTO> cart = new ArrayList<ItemDTO>();
		
		if(user.getMyCart() != null)
			for(int i = 0; i < user.getMyCart().size(); i++) {
				
				Item item = user.getMyCart().get(i);
				
				int itemId = item.getItemId();
				String name = item.getName();
				float price = item.getPrice();
				int quantity = item.getQuantity();
				String category = item.getCategory();
				
				cart.add(new ItemDTO(itemId, name, price, quantity, category));
			}
		return new UserDTO(userIdDTO, username, password, accessLevel, cart);
	}
	
	public static int changePassword(int userId, String password) {
		return UserDB.changePassword(userId, password);
	}
	
	public static UserDTO loginUser(String username, String password) {
		
		User user = UserDB.getUser(username);
		
		if(user == null)
			return null;
		
		int userId = user.getUserId();
		String usernameDTO = user.getUsername();
		String passwordDTO = user.getPassword();
		int accessLevel = user.getAccessLevel();
		
		UserDTO userDTO = new UserDTO(userId, usernameDTO, passwordDTO, accessLevel);
		
		username = username.toLowerCase();
		if(username.equals(user.getUsername().toLowerCase()) && password.equals(user.getPassword()))
			return userDTO;
		else
			return null;
	}
	
	public static ArrayList<ItemDTO> listCartByUserId(int userId) {
		
		ArrayList<Item> myList = UserDB.listCartByUserId(userId);
		ArrayList<ItemDTO> listDTO = new ArrayList<ItemDTO>();
		
		for(int i = 0; i < myList.size(); i++) {
			
			int itemId = myList.get(i).getItemId();
			String name = myList.get(i).getName();
			float price = myList.get(i).getPrice();
			int quantity = myList.get(i).getQuantity();
			String category = myList.get(i).getCategory();
			
			listDTO.add(new ItemDTO(itemId, name, price, quantity, category));
		}
		
		return listDTO;
	}
	
	public static void addItemToCart(int userId, int itemId, int quantity) {
		UserDB.addItemToCart(userId, itemId, quantity);
		return;
	}
	
	public static void removeItemFromCart(int userId, int itemId) {
		UserDB.removeItemFromCart(userId, itemId);
		return;
	}
	
	public static int modifyUserAccesslevel(int myUserId, int targetUserId, int targetAccesslevel) {
		
		if(UserHandler.getUser(myUserId).getAccessLevel() > 2) { //If I am allowed to do this then continue; else return -1
			UserDB.getUser(targetUserId).setAccessLevel(targetAccesslevel);
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
					return -2; //if name isn't unique return -1;
			}
			return UserDB.addUser(username, password, accessLevel); //If I got here continue; else return -1
		}
		return -3;
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
	
	public static ArrayList<UserDTO> getUsers(int myUserId) {
		if(UserHandler.getUser(myUserId).getAccessLevel() > 2) { //If I am allowed to do this then continue; else return -1
			
			ArrayList<User> users =  UserDB.getUsers();
			ArrayList<UserDTO> usersDTO = new ArrayList<UserDTO>();

			
			for(int i = 0; i < users.size(); i++) {
				
				User user = users.get(i);
				ArrayList<ItemDTO> cartDTO = new ArrayList<ItemDTO>();

				int userId = user.getUserId();
				String username = user.getUsername();
				String password = user.getPassword();
				int accessLevel = user.getAccessLevel();
				
				
				if(user.getMyCart() != null)
					for(int j = 0; j < user.getMyCart().size(); j++) {
						
						Item item = user.getMyCart().get(j);
						
						int itemId = item.getItemId();
						String name = item.getName();
						float price = item.getPrice();
						int quantity = item.getQuantity();
						String category = item.getCategory();
						
						cartDTO.add(new ItemDTO(itemId, name, price, quantity, category));
					}
				
				usersDTO.add(new UserDTO(userId, username, password, accessLevel, cartDTO));
			}
			return usersDTO;
		}
		return null;
	}

	public static int modifyUser(int myUserId, int targetUserId, String username, String password,	int accessLevel) {
		if(UserHandler.getUser(myUserId).getAccessLevel() > 2) { //If I am allowed to do this then continue; else return -1
			return UserDB.modifyUser(targetUserId, username, password, accessLevel); 		
		}
		return -1;
	}
}
