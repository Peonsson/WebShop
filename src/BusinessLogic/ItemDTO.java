package BusinessLogic;

public class ItemDTO {
	
	private int itemId;
	private String name;
	private float price;
	private int quantity;
	private String category;
	
	public ItemDTO(int itemId, String name, float price, int quantity, String category) {
		this.itemId = itemId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
	}

	public ItemDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getItemId() {
		return itemId;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getCategory() {
		return category;
	}
}
