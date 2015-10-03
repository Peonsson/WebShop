package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessLogic.UserHandler;

/**
 * This servlet changes a users private settings.
 *
 * @author Johan Pettersson, Robin Veteläinen, TIDAA3
 */

@WebServlet("/ChangeSettings")
public class ChangeSettings extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ChangeSettings() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("loggedInUser"));
		String password = request.getParameter("password_1");
		String verifyPassword = request.getParameter("password_2");
		
		// Make sure passwords entered match
		if (password.equals(verifyPassword)) {
			UserHandler.changePassword(userId, password);
			response.sendRedirect("/WebShop/Settings");
		}
		else {
			// TODO: Send attribute so view can show a message when passwords don't match
			System.out.println("Changing settings: Passwords don't match");
			response.sendRedirect("/WebShop/Settings");
		}	
	}
}
