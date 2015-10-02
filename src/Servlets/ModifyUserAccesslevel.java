package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessLogic.UserHandler;

@WebServlet("/modifyUserAccesslevel")
public class ModifyUserAccesslevel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModifyUserAccesslevel() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int myUserId = Integer.parseInt(request.getParameter("loggedInUser"));
		int targetUserId = Integer.parseInt(request.getParameter("targetUserId"));
		int targetAccesslevel = Integer.parseInt(request.getParameter("targetAccesslevel"));
		
		int returnMessage = UserHandler.modifyUserAccesslevel(myUserId, targetUserId, targetAccesslevel); //for testing only
		
		System.out.println("modifyUserAcesslevel: " + returnMessage); //for testing only
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
