package Servlets;

import BusinessLogic.Item;
// TODO: FIX THIS
import BusinessLogic.ItemHandler;
import Database.ItemDB;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
      request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);	
	}
}
