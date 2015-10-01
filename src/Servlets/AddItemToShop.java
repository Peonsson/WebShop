package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessLogic.ItemHandler;

@WebServlet("/AddItemToShop")
public class AddItemToShop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddItemToShop() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userId = Integer.parseInt(request.getParameter("loggedInUser"));
		ItemHandler.addItemToShop(null, userId, userId, null);
		response.getWriter().append("Added item to shop!"); //TODO: change this
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
