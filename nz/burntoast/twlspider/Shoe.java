package nz.burntoast.twlspider;

public class Shoe extends Product {
	
	private String colour;
	// the barcode of the different sizes of this product
	private String barcode;
	// the size of this product
	private String size;
	// the link to the specific page to get this shoe
	private String url;
	
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Shoe(Product product) {
		this.setBrand(product.getBrand());
		this.setCategory(product.getCategory());
		this.setId(product.getId());
		this.setName(product.getName());
		this.setPrice(product.getPrice());
	}
}
