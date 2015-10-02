package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessLogic.Item;
import BusinessLogic.ItemHandler;
import BusinessLogic.User;
import BusinessLogic.UserHandler;

/**
 * Servlet implementation class GetUser
 */
@WebServlet("/UserEditor")
public class GetUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
 		User user = UserHandler.getUser(userId);
 		request.setAttribute("user", user);
 		request.getRequestDispatcher("/Pages/UserEditor.jsp").forward(request, response);
	}
}
