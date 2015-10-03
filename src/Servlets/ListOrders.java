package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BusinessLogic.ItemHandler;
import BusinessLogic.Order;

@WebServlet("/Orders")
public class ListOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListOrders() {
        super();
    }

 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		HttpSession session = request.getSession();
 		int userId = (int) session.getAttribute("loggedInUser");
 		
 		ArrayList<Order> orders = ItemHandler.getUserOrders(userId);
 		request.setAttribute("orders", orders);
 		request.getRequestDispatcher("/Pages/Orders.jsp").forward(request, response);
	}
}
