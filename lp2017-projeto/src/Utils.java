import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

public class Utils {

	/** Returns an array of all existing cards */
	public static ArrayList<Card> getAllCards() throws IOException
	{
		ArrayList<Card> cards = new ArrayList<Card>();
		InputStream fileReader = new FileInputStream("info.json");

		try{
			// Create Json reader to read the file in Json format
			JsonReader jsonReader = Json.createReader(fileReader);
			JsonObject cardsObject = jsonReader.readObject();
			fileReader.close();
			ArrayList<String> cardNames = new ArrayList<String>();
			cardNames.addAll(cardsObject.keySet()); 

			ArrayList<JsonValue> cardInfo = new ArrayList<JsonValue>();
			cardInfo.addAll(cardsObject.values());

			// Get each card info
			for(int i=0; i < cardInfo.size(); i++){
				JsonObject card = cardInfo.get(i).asJsonObject();
				Card tempCard = new Card(cardNames.get(i));

				if (card.containsKey("type"))
					tempCard.setType(card.getString("type"));
				if (card.containsKey("cost"))
					tempCard.setCost(card.getString("cost"));
				if (card.containsKey("rarity"))
					tempCard.setRarity(getRarity(card.getString("rarity")));
				if (card.containsKey("levels"))
				{
					// Set initial level to 1
					tempCard.setLevel(1);
					// Get levels array
					JsonArray subCardInfo = card.get("levels").asJsonArray();
					// Save to card
					tempCard.setLevels(subCardInfo);	
					// Get info about each individual level
					tempCard = setCardAttributes(tempCard, subCardInfo);
									
				}
				cards.add(tempCard);
			}
		}
		finally{
			fileReader.close();
		}
		return cards;
	}
	
	/** Gets all attributes for a given card */
	public static Card setCardAttributes(Card tempCard, JsonArray subCardInfo)
	{
		for(int j=0; j < subCardInfo.size(); j++)
		{
			JsonValue cardLevelDetails = subCardInfo.getValue("/"+j);

			try	{
				
				if (tempCard.getType().toLowerCase() == "troop")
				{
					TroopCard newCard = new TroopCard(tempCard.getName(), tempCard.getRarity(), tempCard.getCost(), tempCard.getLevels(), tempCard.getType());
					if (cardLevelDetails != JsonValue.NULL )
						if (!cardLevelDetails.asJsonObject().isNull("hitpoints"))
							if(cardLevelDetails.asJsonObject().containsKey("hitpoints"))
							{
								JsonObject obj = cardLevelDetails.asJsonObject();
								String attribute = obj.getString("hitpoints").replaceAll(",", "");
								newCard.setHitPoints(Integer.parseInt(attribute));
								newCard.setDamagePoints(obj.getInt("damage"));
								newCard.setDamagePerSecond(obj.getInt("damage_per_second"));
							}
					return newCard;
				}
				else if (tempCard.getType().toLowerCase() == "building")
				{
					BuildingCard newCard = new BuildingCard(tempCard.getName(), tempCard.getRarity(), tempCard.getCost(), tempCard.getLevels(), tempCard.getType());
					if (cardLevelDetails != JsonValue.NULL )
						if (!cardLevelDetails.asJsonObject().isNull("deploy_time"))
							if(cardLevelDetails.asJsonObject().containsKey("deploy_time"))
							{
								JsonObject obj = cardLevelDetails.asJsonObject();
								newCard.setDeploy_time(obj.getString("deploy_time"));
								newCard.setLifetime(obj.getString("lifetime"));
							}
					return newCard;
				}
				else if (tempCard.getType().toLowerCase() == "spell")
				{
					SpellCard newCard = new SpellCard(tempCard.getName(), tempCard.getRarity(), tempCard.getCost(), tempCard.getLevels(), tempCard.getType());
					if (cardLevelDetails != JsonValue.NULL )
						if (!cardLevelDetails.asJsonObject().isNull("radius"))
							if(cardLevelDetails.asJsonObject().containsKey("radius"))
							{
								JsonObject obj = cardLevelDetails.asJsonObject();
								newCard.setRadius(obj.getString("radius"));
								newCard.setDuration(obj.getString("duration"));
							}
					return newCard;
				}
				
			}
			catch(Exception e){
				//System.out.println(cardLevelDetails);
			}
		}	
		return tempCard;
	}
	
	/** Converts a String into the respective Enum */
	public static Rarity getRarity(String rarity)
	{
		for(Rarity enumRarity : Rarity.class.getEnumConstants())
			if (rarity.equals(enumRarity.toString()))
				return enumRarity;

		return Rarity.Common;
	}
}
