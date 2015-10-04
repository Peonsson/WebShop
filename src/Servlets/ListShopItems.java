package Servlets;

import BusinessLogic.Item;
import BusinessLogic.ItemHandler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet receives a list of all items in the shop and
 * dispatches them to index.jsp
 *
 * @author Johan Pettersson, Robin Veteläinen, TIDAA3
 */

@WebServlet("/")
public class ListShopItems extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListShopItems() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Item> items = ItemHandler.listItems();
		request.setAttribute("items", items);
      request.getRequestDispatcher("/Pages/index.jsp").forward(request, response);
	}
}
