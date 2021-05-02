package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.NumAccount;

@WebServlet({ "/TransactionServlet", "/Transaction" })
public class TransactionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// both input fields must contain numbers for the transaction to be approved
		if (!request.getParameter("txtAmount").isEmpty() && !request.getParameter("txtAccNum").isEmpty()) {

			int amount = Integer.parseInt(request.getParameter("txtAmount"));
			int AccNum = Integer.parseInt(request.getParameter("txtAccNum"));

			NumAccount num = new NumAccount();
			num.amount = amount;
			num.AccNum = AccNum;
			// Clicking on Debit From and Credit To result in different transactions
			if (request.getParameter("reqType1") != null) {
				System.out.println(request.getParameter("reqType1") + " was selected\n");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Transactions.html");
				dispatcher.include(request, response);
				out.println("<center><h3>Success! You have debited " + amount + " from account #" + AccNum
						+ " to account #" + num.getAccNum() + "</h3></center>");
			} else if (request.getParameter("reqType2") != null) {
				System.out.print(request.getParameter("reqType2") + " was selected\n");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Transactions.html");
				dispatcher.include(request, response);
				out.println("<center><h3>Success! You have transferred " + amount + " from account #" + num.getAccNum()
						+ " to account #" + AccNum + "</h3></center>");

			}
		} else {
			RequestDispatcher rs = request.getRequestDispatcher("Transactions.html");
			rs.include(request, response);
			out.println("<center><h3>Sorry, your transaction has been declined!</h3></center>");
		}
		out.println("</center></body></html>");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}