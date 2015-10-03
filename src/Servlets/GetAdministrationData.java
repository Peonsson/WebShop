package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BusinessLogic.Item;
import BusinessLogic.ItemHandler;
import BusinessLogic.Order;
import BusinessLogic.User;
import BusinessLogic.UserHandler;

//@WebServlet("/edititems")
public class GetAdministrationData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetAdministrationData() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("loggedInUser");
		int accessLevel = (int) session.getAttribute("accessLevel");
		
		// Get item list
		List<Item> items = ItemHandler.listItems();
		request.setAttribute("items", items);
		
		if (accessLevel > 2) {
			// Get user list (for admins)
			List<User> users = UserHandler.getUsers(userId);
			request.setAttribute("users", users);
		}
		else if (accessLevel == 2) {
			// Get order list (for warehouse workers)
			List<Order> orders = ItemHandler.getAllUnhandledOrders();
			request.setAttribute("orders", orders);
		}

		request.getRequestDispatcher("/Pages/Administrator.jsp").forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
