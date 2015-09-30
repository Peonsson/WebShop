package UI;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessLogic.Item;
import BusinessLogic.UserHandler;

@WebServlet("/listCartByUserIdServlet")
public class ListCartByUserIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListCartByUserIdServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String returnValue = "";
		ArrayList<Item> myList = UserHandler.listCartByUserId(2);
		
		for (int i = 0; i < myList.size(); i++) {
			returnValue += "name: " + myList.get(i).getName() + " price: " + myList.get(i).getPrice() + " quantity: " + myList.get(i).getQuantity() + "\n";
		}
		
		out.println(returnValue);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
