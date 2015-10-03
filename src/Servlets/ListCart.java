package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessLogic.Item;
import BusinessLogic.UserHandler;

@WebServlet("/listCartByUserIdServlet")
public class ListCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListCart() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		List<Item> items = UserHandler.listCartByUserId(userId);		
		request.setAttribute("items", items);
      request.getRequestDispatcher("/Pages/cart.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
