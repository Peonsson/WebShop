package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This servlet logs a user out by invalidating the session.
 *
 * @author Johan Pettersson, Robin Veteläinen, TIDAA3
 */

@WebServlet("/Logout")
public class LogoutService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public LogoutService() {
		super();
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.invalidate();
		response.sendRedirect("/WebShop/");
	}
}
