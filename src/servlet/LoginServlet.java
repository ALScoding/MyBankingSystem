package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.JDBCHelpLogin;

@WebServlet({ "/LoginServlet", "/Login" })
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// if the inputed name and password exist in user_pass, the main
		// menu will appear instead of the login screen when Submit is clicked
		if (JDBCHelpLogin.checkUser(username, password)) {
			RequestDispatcher rs = request.getRequestDispatcher("Main_Menu.html");
			rs.forward(request, response);
		} else {
			RequestDispatcher rs = request.getRequestDispatcher("index.html");
			rs.include(request, response);
			out.println("<center><h3>Sorry, your login failed!</h3></center>");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}