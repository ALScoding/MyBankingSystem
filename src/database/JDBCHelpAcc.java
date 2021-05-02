package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import model.UserAccount;

public class JDBCHelpAcc {

	Connection con;
	Statement stmt;
	PreparedStatement pStmt;
	CallableStatement cStmt;

	// 1. Load the Driver
	public JDBCHelpAcc() {
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
	public int registerUser(UserAccount user) {

		int i = 0;

		try {
			// a record will be inserted into the table only when all input fields are not
			// empty, there will be no blank entries
			if (!user.name.isEmpty() && !user.DOB.isEmpty() && !user.email.isEmpty() && !user.address.isEmpty()
					&& !user.type.equals("None")) {
				String sql = "insert into account_details values(null,?,?,?,?,?)"; // ? -> wild card

				pStmt = con.prepareStatement(sql);
				pStmt.setString(1, user.name);
				pStmt.setString(2, user.DOB);
				pStmt.setString(3, user.email);
				pStmt.setString(4, user.address);
				pStmt.setString(5, user.type);

				i = pStmt.executeUpdate();
			}
			if (i > 0) {
				System.out.println(user.name + " account created!");
			} else {
				System.out.println("Some error while inserting...");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return i;
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