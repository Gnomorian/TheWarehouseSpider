package nz.burntoast.twlspider;

import java.io.IOException;
import java.util.HashMap;

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
	 * converts the Elements array into a Product HashMap to remove garbage.
	 * HashMap key is the id of the product.
	 * @param tiles
	 */
	public HashMap<String, Product> generateProducts(Elements tiles) {
		HashMap<String, Product> products = new HashMap<String, Product>();
		for(int i = 0; i < tiles.size(); i++) {
			String json = tiles.get(i).attr("data-line-item");
			Product product = createProduct(json);
			products.put(product.getId(), product);
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
}
