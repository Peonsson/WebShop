package BusinessLogic;

import java.util.ArrayList;

public class Order {

	private int orderId;
	private int userId;
	private ArrayList<Item> items;
	private int sent;
	
	public Order(int orderId, int userId, ArrayList<Item> items, int sent) {
		this.orderId = orderId;
		this.userId = userId;
		this.items = items;
		this.sent = sent;
	}
	
	public Order(int userId, ArrayList<Item> items, int sent) {
		this.userId = userId;
		this.items = items;
		this.sent = sent;
	}

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public int getSent() {
		return sent;
	}

	public void setSent(int sent) {
		this.sent = sent;
	}
	
	public String toString() {
		String string = "";
		
		for (int i = 0; i < items.size(); i++) {
			string += items.get(i).toString() + " ";
		}
		
		string += "Sent: " + sent + "\n";
		
		return string;
	}
}
