package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.JDBCHelpAuth;
import model.CredAccount;

@WebServlet({ "/AuthServlet", "/Authorize" })
public class AuthServlet extends HttpServlet {

	private JDBCHelpAuth databaseConnection;

	public AuthServlet() {
		databaseConnection = new JDBCHelpAuth();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("txtName");

		// input fields must be all filled in for authorization
		if (!request.getParameter("txtName").isEmpty() && !request.getParameter("txtCardNum").isEmpty()
				&& !request.getParameter("txtCode").isEmpty() && !request.getParameter("txtAmount").isEmpty()) {

			long CardNum = Long.parseLong(request.getParameter("txtCardNum"));
			int code = Integer.parseInt(request.getParameter("txtCode"));
			float amount = Float.parseFloat(request.getParameter("txtAmount"));

			CredAccount user = new CredAccount();
			user.name = name;
			user.CardNum = CardNum;
			user.code = code;
			user.amount = amount;

			// negative numbers and amounts over 1000 will not be accepted
			if (!name.isEmpty() && CardNum > 0 && code > 0 && amount > 0 && amount <= 1000) {
				boolean isCardInfoValid = databaseConnection.loginUser(user);

				if (isCardInfoValid) {
					RequestDispatcher rs = request.getRequestDispatcher("Authorize.html");
					rs.include(request, response);
					out.println("<center><h3>Your transaction has been approved!</h3></center>");
				} else {
					RequestDispatcher rs = request.getRequestDispatcher("Authorize.html");
					rs.include(request, response);
					out.println("<center><h3>Sorry, your transaction has been declined!</h3></center>");
				}
			}
		} else {
			RequestDispatcher rs = request.getRequestDispatcher("Authorize.html");
			rs.include(request, response);
			out.println("<center><h3>Sorry, your transaction has been declined!</h3></center>");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}