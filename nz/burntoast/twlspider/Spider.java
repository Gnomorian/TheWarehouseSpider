package nz.burntoast.twlspider;

import java.io.IOException;

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
}
