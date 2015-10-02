package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessLogic.UserHandler;

@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddUser() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int myUserId = Integer.parseInt(request.getParameter("loggedInUser"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int accessLevel = Integer.parseInt(request.getParameter("targetAccesslevel"));
		
		int returnMessage = UserHandler.addUser(myUserId, username, password, accessLevel); //for testing only
		
		System.out.println("addUser: " + returnMessage); //for testing only
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
