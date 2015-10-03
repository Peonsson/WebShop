package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BusinessLogic.ItemHandler;

/**
 * This servlet dispatches a order, that is a warehouse worker sends it.
 *
 * @author Johan Pettersson, Robin Veteläinen, TIDAA3
 */

@WebServlet("/DispatchOrder")
public class DispatchOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public DispatchOrder() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		int userDispatching = (int) session.getAttribute("accessLevel");
		ItemHandler.dispatchOrder(orderId, userDispatching);
		response.sendRedirect("/WebShop/Administration");
	}
}
