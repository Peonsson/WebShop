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

@WebServlet("/ActionServlet")
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ActionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String str = "<html>";
		Hashtable t = ItemHandler.listItems();
		int size = (int) t.get("size");
		System.out.println("size: " + size);
		str += "<table>";
		for (int i = 0; i < size; i++) {
			Hashtable item = (Hashtable) t.get("Item" + i);
			str+="<tr><td>" + item.get("name") + "</td><td>" + item.get("quantity") + "</td><td>" + item.get("price") + "</td><td><button id=" + item.get("itemId") + " onClick=\"doStuff()\">Button</button></td></tr>";
		}
		str += "</table>";
		response.getWriter().append(str + "</html>");		
	}
}
