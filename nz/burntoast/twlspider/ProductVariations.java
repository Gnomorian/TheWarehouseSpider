package nz.burntoast.twlspider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ProductVariations {
	/**
	 * finds the page of each size of shoe of each colour variation
	 * @throws IOException 
	 */
	public ArrayList<Shoe> getProductVariations(ArrayList<Product> products) throws IOException {
		ArrayList<Shoe> shoes = new ArrayList<Shoe>();
		Iterator<Product> i = products.iterator();
		// get the barcodes of all products in the array
		while(i.hasNext()) {
			Product product = i.next();
			System.out.println("Getting variations of " + product.getName());
			String url = "https://www.thewarehouse.co.nz/p/basics-brand-womens-sea-jandals/" + product.getId() + ".html";
			String[] variationUrls;
			

			// get the pages of all the different colours (variations)
			variationUrls = getVariations(url);
			for(String variationUrl : variationUrls) {
				// get the pages of all the sizes of these colours (variations)\
				String[] sizeUrls = getSizes(variationUrl);
				// create the shoe class of all the urls
				for(String link : sizeUrls) {
					Shoe shoe = new Shoe(product);
					shoe.setUrl(link);
				}
			}

		}
		return shoes;
	}
	/**
	 * gets the urls to the page for each size of this variation
	 * @param link - the url to the "demandware details page"
	 * @throws IOException 
	 */
	private String[] getSizes(String url) throws IOException {
		Document doc = null;
		// first get the <option>s that have the links
		doc = Jsoup.connect(url).get();
		Elements sizeelements = doc.getElementsByClass("variation-select");
		sizeelements = sizeelements.get(0).children();
		// the first element is a "select size" option
		sizeelements.remove(0);
		String[] urls = new String[sizeelements.size()];
		for(int i = 0; i < sizeelements.size(); i++) {
			urls[i] = sizeelements.get(i).attr("value");
		}
		return urls;
	}
	
	/**
	 * gets the urls to each product variation
	 * @throws IOException 
	 */
	private String[] getVariations(String url) throws IOException {
		Document doc = null;
		doc = Jsoup.connect(url).get();
		Elements variations = doc.getElementsByClass("swatchanchor");
		String[] variationLinks = new String[variations.size()];
		// get the href attribute of each colour of this product
		for(int j = 0; j < variations.size(); j++) {
			variationLinks[j] = variations.get(j).attr("href");
		}
		return variationLinks;
	}
}
