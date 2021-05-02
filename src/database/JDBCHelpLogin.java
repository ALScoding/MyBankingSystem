package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCHelpLogin {
	public static boolean checkUser(String username, String password) {
		boolean st = false;
		Connection con; // added

		try {

			// loading drivers for MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// creating connection with the database
			con = DriverManager.getConnection("jdbc:mysql://localhost/edureka", "root", "NO");
			// records must be manually added to the table before this statement will work
			PreparedStatement ps = con.prepareStatement("select * from user_pass where name = ? and password = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			st = rs.next();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return st;

	}

}