package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessLogic.ItemHandler;
import BusinessLogic.Order;

@WebServlet("/Orders")
public class ListOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListOrders() {
        super();
    }

 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		int userId = Integer.parseInt(request.getParameter("userId"));
 		ArrayList<Order> orders = ItemHandler.getUserOrders(userId);
 		System.out.println("orders: " + orders);
 		request.setAttribute("orders", orders);
 		request.getRequestDispatcher("/Pages/Orders.jsp").forward(request, response);
	}
 	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: Waiting for BO logic.
		response.getWriter().append("No BO logic to list orders.");
	}
}
