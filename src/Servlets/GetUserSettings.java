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

@WebServlet("/Settings")
public class GetUserSettings extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetUserSettings() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("loggedInUser");
		
		User user = UserHandler.getUser(userId);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/Pages/Settings.jsp").forward(request, response);
	}
}
