package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BusinessLogic.User;
import BusinessLogic.UserHandler;

@WebServlet("/LoginService")
public class LoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginService() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (!username.isEmpty() && !password.isEmpty()) {
			User user = UserHandler.loginUser(username, password);

			if (user != null) {
				HttpSession session = request.getSession(true);
				session.setAttribute("loggedInUser", user.getUserId());
				response.sendRedirect("/WebShop/");
			} else
				response.sendRedirect("/WebShop/login");
		} else
			response.sendRedirect("/WebShop/login");
	}
}
