package Servlets;

import BusinessLogic.Item;
import BusinessLogic.ItemHandler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/")
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
