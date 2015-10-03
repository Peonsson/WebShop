package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessLogic.UserHandler;

/**
 * This servlet user handler to edit usr settings.
 *
 * @author Johan Pettersson, Robin Veteläinen, TIDAA3
 */

@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public EditUser() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String remove = request.getParameter("remove");
		int userToChangeId = Integer.parseInt(request.getParameter("userId"));
		int loggedInUserId = Integer.parseInt(request.getParameter("loggedInUser"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int accessLevel = Integer.parseInt(request.getParameter("accessLevel"));
		
		// If remove checkbox wasn't checked, edit user
		if (remove == null) {
			UserHandler.modifyUser(loggedInUserId, userToChangeId, username, password, accessLevel);
		}
		// If checkbox was checked, delete user
		else {
			UserHandler.removeUser(loggedInUserId, userToChangeId);
		}
		
		response.sendRedirect("/WebShop/Administration");
	}
}
