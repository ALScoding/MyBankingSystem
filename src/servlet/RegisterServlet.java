package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.JDBCHelpAcc;
import model.NumAccount;
import model.UserAccount;

@WebServlet({ "/RegisterServlet", "/Register" })
public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("=== init executed ===");
	}

	public void destroy() {
		System.out.println("=== destroy executed ===");
	}

	// service is executed in the Servlet if for any request method
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("=== service executed ===");

		// Extract the data from Request
		UserAccount user = new UserAccount();
		user.name = request.getParameter("txtName");
		user.DOB = request.getParameter("txtDOB");
		user.address = request.getParameter("txtAddress");
		user.email = request.getParameter("txtEmail");
		user.type = request.getParameter("txtType");

		// Save the User Object in DB
		// We have to create a table in DB and use JDBC
		JDBCHelpAcc helper = new JDBCHelpAcc();
		helper.createConnection();
		int i = helper.registerUser(user);
		helper.closeConnection();

		// Sending Back the Response to the Client by Servlet
		// response.setContentType("text/html");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.print("<html><body>");

		// the account information will not be accepted unless all the input fields
		// contain something, there will be no blank entries in the database table
		if (!user.name.isEmpty() && !user.DOB.isEmpty() && !user.email.isEmpty() && !user.address.isEmpty()) {

			NumAccount num = new NumAccount(); //

			if (user.type.equals("Current")) {
				RequestDispatcher rs = request.getRequestDispatcher("Create_Account.html");
				rs.include(request, response);
				out.println("<h3>Welcome, " + user.name + "! Current account created successfully!</h3>");
				out.println("<h3>Your account # is: " + num.getAccNum() + "</h3>");
			} else if (user.type.equals("SB")) {
				RequestDispatcher rs = request.getRequestDispatcher("Create_Account.html");
				rs.include(request, response);
				out.println("<h3>Welcome, " + user.name + "! SB account created successfully!</h3>");
				out.println("<h3>Your account # is: " + num.getAccNum() + "</h3>");
			} else {
				RequestDispatcher rs = request.getRequestDispatcher("Create_Account.html");
				rs.include(request, response);
				out.println("<h3>Your account has not been created! </br> Please select an account type first!</h3>");
			}
		} else {
			RequestDispatcher rs = request.getRequestDispatcher("Create_Account.html");
			rs.include(request, response);
			out.println("<h3>Your account has not been created! </br> Please enter all the details!</h3>");
		}

		out.println("</center></body></html>");
	}

}