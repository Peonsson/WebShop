package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessLogic.ItemHandler;

/**
 * This servlet calls for item handler to edit items.
 *
 * @author Johan Pettersson, Robin Veteläinen, TIDAA3
 */

@WebServlet("/EditItem")
public class EditItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public EditItem() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		int userId = Integer.parseInt(request.getParameter("loggedInUser"));
		
		String name = request.getParameter("name");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		float price = Float.valueOf(request.getParameter("price"));
		String category = request.getParameter("category");
		
		String remove = request.getParameter("remove");
		
		// TODO: Check so user entered something in all fields
		// If remove checkbox wasn't checked, edit item
		if(remove == null) {
			ItemHandler.modifyItem(userId, itemId, name, quantity, price, category);
		}
		// If checkbox was checked, delete item
		else {
			ItemHandler.removeItemFromShop(userId, itemId);
		}
		
		response.sendRedirect("/WebShop/Administration");
	}
}
