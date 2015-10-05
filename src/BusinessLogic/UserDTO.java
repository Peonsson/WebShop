package BusinessLogic;

import java.util.ArrayList;

public class UserDTO {
	
	private int userId;
	private String username;
	private String password;
	private int accessLevel;
	private ArrayList<ItemDTO> myCart;
	
	public UserDTO(int userId, String username, String password, int accessLevel, ArrayList<ItemDTO> myCart) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.accessLevel = accessLevel;
		this.myCart = myCart;
	}

	public UserDTO(int userId, String username, String password, int accessLevel) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.accessLevel = accessLevel;
	}

	public int getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public ArrayList<ItemDTO> getMyCart() {
		return myCart;
	}
}
