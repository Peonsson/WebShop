package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessLogic.ItemHandler;
import BusinessLogic.UserHandler;

/**
 * Servlet implementation class EditUser
 */
@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditUser() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userToChangeId = Integer.parseInt(request.getParameter("userId"));
		int loggedInUserId = Integer.parseInt(request.getParameter("loggedInUser"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int accessLevel = Integer.parseInt(request.getParameter("accessLevel"));
				
//		TODO: Waiting for back end BO implementation
//		UserHandler.modifyUser(loggedInUserId, userToChangeId, username, password, accessLevel);
		
		response.sendRedirect("/WebShop/Administration");
	}

}
