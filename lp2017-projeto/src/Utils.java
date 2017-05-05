import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Utils {

	public static ArrayList<Card> getAllCards() throws IOException
	{
		final String urlWithInfo = "https://github.com/andremont/projeto-lp2017/blob/master/info.json";
		ArrayList<Card> cards = new ArrayList<Card>();
		URL url = new URL(urlWithInfo);

		try(InputStream is = url.openStream();

			JsonReader rdr = Json.createReader(is)) {

			JsonObject obj = rdr.readObject();
			JsonArray results = obj.asJsonArray(); //obj.getJsonArray("data");

			for (JsonObject result : results.getValuesAs(JsonObject.class)) {
				Card tempCard = new Card();
				String name = result.toString();
				String rarity = result.getJsonObject(name).getString("rarity");
				int cost = result.getJsonObject(name).getInt("cost");
				int level = result.getJsonObject(name).getInt("level");
				//int hitPoints = result.getJsonObject(name).getString("rarity");
				//int damagePoints = result.getJsonObject(name).getString("rarity");
				//int damagePerSecond = result.getJsonObject(name).getString("rarity");
			}
		}
		return cards;
	}
}
