package nz.burntoast.twlspider;

import java.util.ArrayList;

import org.json.JSONObject;
import org.jsoup.nodes.Document;
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
		
		// Condense the information down to what we need
		ArrayList<Product> products = spider.generateProducts(tiles);
		System.out.println(products.size());
		products.get(0).toString();
	}
}
