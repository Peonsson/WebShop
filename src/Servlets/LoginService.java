package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BusinessLogic.UserHandler;

/**
 * Servlet implementation class LoginService
 */
@WebServlet("/LoginService")
public class LoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginService() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ")
			.append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (username != null && password != null) {
			Hashtable t = UserHandler.searchByUsername(username);	
			int size = (int) t.get("size");
			
			if (size == 1) {
				Hashtable item = (Hashtable) t.get("User" + 0);
				
				if (password.equals(item.get("password"))) {
					System.out.println("Login succeeded.");
					System.out.println("user id : " + item.get("userId"));
					
					HttpSession session = request.getSession(true);
					session.setAttribute("loggedInUser", item.get("userId"));
					response.sendRedirect("/WebShop/");
				}
				else {
					response.sendRedirect("/WebShop/login");
				}
			}
			else {
				out.println("User doesn't exist.");
				response.sendRedirect("/WebShop/login");
			}
		}
		else {
			response.sendRedirect("/WebShop/login");
		}
	}
}
