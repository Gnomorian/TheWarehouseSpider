package nz.burntoast.twlspider;

import java.util.HashMap;

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
	private String colour;
	// the barcodes of the different sizes of this product
	private HashMap<Integer, String> barcodes;


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

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public HashMap<Integer, String> getBarcodes() {
		return barcodes;
	}

	public void setBarcodes(HashMap<Integer, String> barcodes) {
		this.barcodes = barcodes;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id : " + id + "\n");
		sb.append("name : " + name + "\n");
		sb.append("price : " + price + "\n");
		sb.append("brand : " + brand + "\n");
		sb.append("category : " + category + "\n");
		sb.append("colour : " + colour + "\n");
		return sb.toString();
	}
}
