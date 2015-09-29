package BusinessLogic;

import java.util.Collection;

import Database.UserDB;

public class User {
	private int userId;
	private String username;
	private String password;
	private int accessLevel;
	
	public User(int userId, String username, String password, int accessLevel) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.accessLevel = accessLevel;
	}
	
	public static Collection<UserDB> searchByUsername(String name) {
		return UserDB.searchByUsername(name);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}
}
