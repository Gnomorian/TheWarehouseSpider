package nz.burntoast.twlspider;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.select.Elements;

public class Main {
	
	/* Categories of the shoe department to search for barcodes */
	public final String MENS = "http://www.thewarehouse.co.nz/c/clothing-jewellery/shoes/mens-shoes";
	public final String WOMENS = "http://www.thewarehouse.co.nz/c/clothing-jewellery/shoes/womens-shoes";
	public final String KIDS = "http://www.thewarehouse.co.nz/c/clothing-jewellery/shoes/kids-shoes";
	public final String CARE = "http://www.thewarehouse.co.nz/c/clothing-jewellery/shoes/shoe-care-";
	
	private Spider spider;
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		spider = new Spider();
		searchCategories();
	}
	
	/**
	 * go through all the footwear categories on the website searching for all the products
	 */
	public void searchCategories() {
		// gets all the raw tiles from the categories search page
		Elements tiles = spider.getTilesInCategory(MENS);
		System.out.println("DONE | Mens");
		tiles.addAll(spider.getTilesInCategory(WOMENS));
		System.out.println("DONE | Womens");
		tiles.addAll(spider.getTilesInCategory(KIDS));
		System.out.println("DONE | Kids");
		tiles.addAll(spider.getTilesInCategory(CARE));
		System.out.println("DONE | Care");
		
		// Condense the information down to what we need to find the barcodes
		ArrayList<Product> products = spider.generateProducts(tiles);
		
		// get objects containing all the shoes including barcodes
		ArrayList<Shoe> shoes;
		try {
			// get the whole list of shoes including their barcodes
			//TODO: bad name concidering it does everything acording to the main class
			shoes = spider.getBarcodes(products);
			System.out.println(shoes.size());
			Shoe shoe = shoes.get(0);
			
			System.out.println("Name: " + shoe.getName());
			System.out.println("Brand: " + shoe.getBrand());
			System.out.println("Colour: " + shoe.getColour());
			System.out.println("Size: " + shoe.getSize());
			System.out.println("Price: " + shoe.getPrice());
			System.out.println("Barcode: " + shoe.getBarcode());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
