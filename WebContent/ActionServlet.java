package UI;

// TODO: FIX THIS
import BusinessLogic.ItemHandler;

import java.io.IOException;
import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ActionServlet
 */
@WebServlet("/ActionServlet")
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String str = "<html>";
		Hashtable t = ItemHandler.listItems();
		int size = (int) t.get("size");
		System.out.println("size: " + size);
		str += "<table>";
		for (int i = 0; i < size; i++) {
			Hashtable item = (Hashtable) t.get("Item" + i);
			str+="<tr><td>" + item.get("name") + "</td><td>" + item.get("quantity") + "</td><td>" + item.get("price") + "</td><td><button onClick=\"doStuff()\">Button</button></td></tr>";
		}
		str += "</table>";
		response.getWriter().append(str + "</html>");		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
