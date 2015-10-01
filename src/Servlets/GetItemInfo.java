package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessLogic.Item;
import BusinessLogic.ItemHandler;

/**
 * Servlet implementation class ItemEdit
 */
//@WebServlet("/ItemEdit")
public class GetItemInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetItemInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("getting");
		
		// REMOVE LATER
		Item item = new Item(1, "Ficklampa", 25f, 100);
		request.setAttribute("item", item);
		request.getRequestDispatcher("/Pages/itemedit.jsp").forward(request, response);;
	}
}
