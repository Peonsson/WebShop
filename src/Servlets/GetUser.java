package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessLogic.User;
import BusinessLogic.UserDTO;
import BusinessLogic.UserHandler;

/**
 * This servlet gets user information for the user editor jsp file.
 *
 * @author Johan Pettersson, Robin Veteläinen, TIDAA3
 */

@WebServlet("/UserEditor")
public class GetUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public GetUser() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
 		UserDTO user = UserHandler.getUser(userId);
 		request.setAttribute("user", user);
 		request.getRequestDispatcher("/Pages/UserEditor.jsp").forward(request, response);
	}
}
