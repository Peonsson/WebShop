package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BusinessLogic.UserHandler;

/**
 * This servlet removes items from a user's cart.
 *
 * @author Johan Pettersson, Robin Veteläinen, TIDAA3
 */

@WebServlet("/RemoveItemFromCart")
public class RemoveItemFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public RemoveItemFromCart() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("loggedInUser");
		int itemToRemove = Integer.parseInt(request.getParameter("itemId"));
		UserHandler.removeItemFromCart(userId, itemToRemove);
		response.sendRedirect("/WebShop/cart");
	}
}
