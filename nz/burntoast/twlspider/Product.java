package nz.burntoast.twlspider;

public class Product {
	//used to find the products page on the website
	private String id;
	// full name of the product
	private String name;
	// current price of the product
	private float price;
	// who made the product
	private String brand;
	// category of the product, mens hiking shoes?
	private String category;
	// variant of the product


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
