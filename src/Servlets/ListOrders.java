package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Orders")
public class ListOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListOrders() {
        super();
    }

 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: Waiting for BO logic.
		response.getWriter().append("No BO logic to list orders.");
	}
 	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: Waiting for BO logic.
		response.getWriter().append("No BO logic to list orders.");
	}
}