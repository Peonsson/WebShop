package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessLogic.UserHandler;

/**
 * This servlet adds items from browse page to the users cart.
 *
 * @author Johan Pettersson, Robin Veteläinen, TIDAA3
 */

@WebServlet("/AddItemToCart")
public class AddItemToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public AddItemToCart() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("loggedInUser"));
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		int quantity;
		
		try {
			quantity = Integer.parseInt(request.getParameter("quantity"));
			if (quantity < 1)
				quantity = 1;
		}
		// If user doesn't choose quantity when adding
		catch (NumberFormatException nfe) {
			quantity = 1;
		}
		
		UserHandler.addItemToCart(userId, itemId, quantity);
		response.sendRedirect("/WebShop/");
	}
}
