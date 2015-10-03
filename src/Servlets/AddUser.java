package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BusinessLogic.UserHandler;

/**
 * This servlet adds items from index to cart.
 *
 * @author Johan Pettersson, Robin Veteläinen, TIDAA3
 */

@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public AddUser() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int loggedInUser = 0;
		String username = null;
		String password = null;
		int accessLevel = 0;
		
		try {
			loggedInUser = (int) session.getAttribute("loggedInUser");
			username = request.getParameter("username");
			password = request.getParameter("password");
			accessLevel = Integer.parseInt(request.getParameter("accessLevel"));
		}
		catch (NumberFormatException nfe) {
			response.sendRedirect("/WebShop/Administration");
			return;
		}
		
		if (loggedInUser == 0 || username.isEmpty() || username == null || password.isEmpty() || password == null || accessLevel == 0) {
			// One or more fields left empty. Redirect back.
			response.sendRedirect("/WebShop/Administration");
			return;
		}
		
		int returnMessage = UserHandler.addUser(loggedInUser, username, password, accessLevel); //for testing only
		System.out.println("addUser: " + returnMessage); //for testing only
		
		response.sendRedirect("/WebShop/Administration");
	}
}
