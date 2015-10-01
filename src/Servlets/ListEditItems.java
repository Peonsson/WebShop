package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessLogic.Item;
import BusinessLogic.ItemHandler;

//@WebServlet("/edititems")
public class ListEditItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListEditItems() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Item> items = ItemHandler.listItems();
		System.out.println("ListEditItems AL: " + items.toString());
		
		request.setAttribute("items", items);
		request.getRequestDispatcher("/Pages/ItemAdministrator.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
