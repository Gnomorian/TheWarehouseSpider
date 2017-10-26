package nz.burntoast.twlspider;

import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Spider {
	/**
	 * loops through all the pages searching for tiles and adding them to the Elements array
	 * @param url e.g. "http://www.thewarehouse.co.nz/c/clothing-jewellery/shoes/womens-shoes"
	 */
	public Elements getTilesInCategory(String url) {
		Elements tiles = new Elements();
		
		// controls the products listed on the page
		int currentPosition = 0;
		int maxProductTileAmount = 24;
		boolean pageFlipping = true;
		
		// flick through all the pages of the category adding the product-tiles to the tile list, when there are none anymore break.
		while(pageFlipping) {
			Elements newTiles = getTilesOnPage(url + "?sz=" + maxProductTileAmount + "&start=" + currentPosition);
			currentPosition += maxProductTileAmount;
			if(newTiles.size() == 0)
				pageFlipping = false;
			tiles.addAll(newTiles);
		}
		return tiles;
	}
	/**
	 * gets all the tiles on the url
	 * @param url
	 * @return
	 */
	private Elements getTilesOnPage(String url) {
		Document doc = null;
		Elements tiles = null;
		try {
			doc = Jsoup.connect(url).get();
			tiles = doc.getElementsByClass("product-tile");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tiles;
	}
	/**
	 * converts the Elements array into a Product ArrayList to remove garbage.
	 * @param tiles
	 */
	public ArrayList<Product> generateProducts(Elements tiles) {
		ArrayList<Product> products = new ArrayList<Product>();
		for(int i = 0; i < tiles.size(); i++) {
			String json = tiles.get(i).attr("data-line-item");
			Product product = createProduct(json);
			products.add(product);
		}
		return products;
	}
	/**
	 * used to remove the garbage from the information we got from the website, 
	 * creates a Product class that has all the information we need about a shoe.
	 * @param json
	 * @return
	 */
	private Product createProduct(String json) {
		Product product = new Product();
		JSONObject obj;
		obj = new JSONObject(json);
		product.setName(obj.getString("name"));
		product.setId(obj.getString("id"));
		product.setPrice(obj.getFloat("price"));
		product.setBrand(obj.getString("brand"));
		product.setCategory(obj.getString("category"));
		
		return product;
	}
	
	/**
	 * gets all the barcodes of the various products
	 * @throws IOException 
	 */
	public ArrayList<Shoe> getBarcodes(ArrayList<Product> products) throws IOException {
		// get a list of all the shoes
		ProductVariations variations = new ProductVariations();
		ArrayList<Shoe> shoes = variations.getProductVariations(products);
		Document doc;
		// find the barcode for each shoe
		for(int i = 0; i < shoes.size(); i++) {
			Shoe shoe = shoes.get(i);
			System.out.println("Getting barcode of " + shoe.getName());
			// get the page containing the barcode
			doc = Jsoup.connect(shoes.get(i).getUrl()).get();
			// get the barcode
			Elements productIdElement = doc.getElementsByClass("product-id");
			// set the barcode
			shoe.setBarcode(productIdElement.get(0).html());
		}
		
		return shoes;
	}
}
