package UI;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessLogic.UserHandler;

@WebServlet("/SearchByUsername")
public class SearchByUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchByUsername() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = "<html>";
		Hashtable t = UserHandler.searchByUsername("Peonsson");
		int size = (int) t.get("size");
		System.out.println("size: " + size);
		for (int i = 0; i < size; i++) {
			Hashtable item = (Hashtable) t.get("User" + i);
			str+="<tr><td>" + item.get("username") + "</td><td>" + item.get("password") + "</td><td>" + item.get("accessLevel") + "</td><td><button onClick=\"doStuff()\">Button</button></td></tr>";
		}
		response.getWriter().append(str + "</html>");	
	}
}
