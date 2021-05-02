package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.CredAccount;

public class JDBCHelpAuth {

	Connection con;
	Statement stmt;
	PreparedStatement pStmt;
	CallableStatement cStmt;

	// 1. Load the Driver
	public JDBCHelpAuth() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("=== Driver Loaded ===");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 2. Create the Connection
	public void createConnection() {
		try {

			String url = "jdbc:mysql://localhost/edureka";
			String user = "root";
			String password = "NO";

			con = DriverManager.getConnection(url, user, password);
			System.out.println("=== Connection Created ===");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 3. Write SQL Statement
	// 4. Execute SQL Statement
	public boolean loginUser(CredAccount user) {
		boolean result = false;
		try {
			createConnection();
			// records must be manually added to the table before this statement will work
			String sql = "select * from credit_card where Name = ? and Card_Number = ? and Code = ?"; // ?

			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, user.name);
			pStmt.setLong(2, user.CardNum);
			pStmt.setInt(3, user.code);

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				result = true;
				System.out.println("Authorization successful!!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 5. Close the Connection
	public void closeConnection() {
		try {
			con.close();
			System.out.println("=== Connection Closed ===");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}