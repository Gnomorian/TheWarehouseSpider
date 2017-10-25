package nz.burntoast.twlspider;

import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {
	
	public static void main(String[] args)
	{
		Document doc = null;
		Spider spider = new Spider();

		Elements tiles = spider.getTilesInCategory("http://www.thewarehouse.co.nz/c/clothing-jewellery/shoes/womens-shoes");
		System.out.println(tiles.size());
		
		JSONObject obj;
		
		for(int i = 0; i < tiles.size(); i++) {
			String json = tiles.get(i).attr("data-line-item");
			obj = new JSONObject(json);
			String name = obj.getString("name");
			System.out.println(name);
		}
	}
}
