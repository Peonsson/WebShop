package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BusinessLogic.ItemHandler;

/**
 * This servlet adds items to the shop.
 *
 * @author Johan Pettersson, Robin Veteläinen, TIDAA3
 */

@WebServlet("/AddItemToShop")
public class AddItemToShop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public AddItemToShop() {
   	 super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("loggedInUser");
		
		String name = request.getParameter("name");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		float price = Float.valueOf(request.getParameter("price"));
		String category = request.getParameter("category");
		
		ItemHandler.addItemToShop(userId, name, price, quantity, category);
		response.sendRedirect("/WebShop/Administration");
	}
}
