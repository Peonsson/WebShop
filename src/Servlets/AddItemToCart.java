package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessLogic.UserHandler;

//@WebServlet("/AddItemToCart")
public class AddItemToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddItemToCart() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("loggedInUser"));
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		int quantity;
		try {
			quantity = Integer.parseInt(request.getParameter("quantity"));
		}
		catch (NumberFormatException nfe) {
			quantity = 1;
		}

		
		UserHandler.addItemToCart(userId, itemId, quantity);
		response.sendRedirect("/WebShop/");
	}
}
