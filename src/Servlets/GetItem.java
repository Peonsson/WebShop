package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessLogic.ItemHandler;

@WebServlet("/GetItem")
public class GetItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetItem() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	request.getParameter("itemId"); //TODO: in production
//    	System.out.println("ItemHandler.getItem(1).getName() == " + ItemHandler.getItem(1).getName()); //TODO: testcode remove if u want
    	System.out.println("ItemHandler.getItem(1).getName() == " + ItemHandler.getItem("Ficklampa").getName()); //TODO: testcode remove if u want
//    	request.getParameter("name");//TODO: in production
	}
}
