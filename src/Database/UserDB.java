package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Vector;
import BusinessLogic.User;

public class UserDB extends User {

	public UserDB(int userId, String username, String password, int accessLevel) {
		super(userId, username, password, accessLevel);
	}
	
	public static Collection<UserDB> searchByUsername(String string) {
		
		Vector<UserDB> v = new Vector<>();
		Connection conn = DBManager.getConnection();
		try {

			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM User WHERE Username = '" + string + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {

				int userId = rs.getInt("UserId");
				String name = rs.getString("Username");
				String password = rs.getString("Password");
				int accessLevel = rs.getInt("AccessLevel");
				v.add(new UserDB(userId, name, password, accessLevel));
			}
			return v;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
