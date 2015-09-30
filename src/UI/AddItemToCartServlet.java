package UI;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessLogic.UserHandler;

@WebServlet("/AddItemToCartServlet")
public class AddItemToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddItemToCartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userId = (int) request.getAttribute("userId");
		int itemId = (int) request.getAttribute("itemId");
		int quantity = (int) request.getAttribute("quantity");
		
		UserHandler.addItemToCart(userId, itemId, quantity);
		
		PrintWriter out = response.getWriter();
		out.println("entered: addItemToCartServlet!");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
