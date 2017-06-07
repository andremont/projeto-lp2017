package ismt.application.main;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

public class Utils {

	private static final String INFO_JSON = "info.json";
	private static final String USERS_JSON = "users.json";
	private static final String TOWERS_JSON = "towers.json";
	private static final String STATS_JSON = "stats.json";

	/** Returns an array of all existing cards in the game */
	public static ArrayList<Card> getAllCards() throws IOException
	{
		ArrayList<Card> cards = new ArrayList<Card>();
		InputStream fileReader = new FileInputStream(INFO_JSON);

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
				if (card.containsKey("quantity"))
					tempCard.setQuantity(card.getInt("quantity"));
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

	/** Gets and sets all attributes for a given card */
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

	/** Print all cards in deck */
	public static void printAllCards(ArrayList<Card> cards)
	{
		for(Card newCard : cards)
			System.out.println(newCard.getName() + " | Cost:" + newCard.getCost() + " | rarity: " + newCard.getRarity());
	}

	/** Saves all cards in deck to a String */
	public static String getAllCards(ArrayList<Card> cards)
	{
		String tempString = "";

		for(Card newCard : cards)
			tempString += newCard.getName() + " | Cost:" + newCard.getCost() + " | Rarity: " + newCard.getRarity() + "\n";

		return tempString;
	}

	/** Validates user credentials  */
	public static boolean validateUser(String username, String password) 
	{ 
		boolean valid = false;
		InputStream fileReader = null;

		try {
			fileReader = new FileInputStream(USERS_JSON);
			// Create Json reader to read the file in Json format
			JsonReader jsonReader = Json.createReader(fileReader);
			JsonObject usersObject = jsonReader.readObject();
			jsonReader.close();

			if(usersObject != null)
				if (usersObject.containsKey(username)){
					JsonValue user = usersObject.get(username);
					String originalPass = user.asJsonObject().getString("password");

					if (originalPass.equals(password)) 
						valid = true;
				}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return valid;
	}

	/**
	 * Get user records
	 * @param player name
	 * @return Player object
	 */
	public static Stats GetUserRecords(String player)
	{
		InputStream fileReader = null;
		Stats stats = new Stats();

		try {
			fileReader = new FileInputStream(STATS_JSON);

			// Create Json reader to read the file in Json format
			JsonReader jsonReader = Json.createReader(fileReader);
			JsonObject userObject = jsonReader.readObject().get(player).asJsonObject();
			jsonReader.close();
			fileReader.close();

			if(userObject != null)
				if(userObject != JsonArray.NULL)
				{
					stats.setVictories(Integer.parseInt(userObject.getString("victories")));
					stats.setDraws(Integer.parseInt(userObject.getString("draws")));
					stats.setLosses(Integer.parseInt(userObject.getString("losses")));
				}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stats;
	}

	/**
	 * 
	 * @param Player name
	 * @return Player details
	 */
	public static Player GetUser(String player)
	{
		InputStream fileReader = null;
		Player newPlayer = new Player();

		try {
			fileReader = new FileInputStream(USERS_JSON);

			// Create Json reader to read the file in Json format
			JsonReader jsonReader = Json.createReader(fileReader);
			JsonObject userObject = jsonReader.readObject().get(player).asJsonObject();
			jsonReader.close();
			fileReader.close();

			if(userObject != null)
				if(userObject != JsonArray.NULL)
				{
					newPlayer.setName(player);

					if (userObject.containsKey("level"))
						newPlayer.setLevel(userObject.getString("level"));
					if (userObject.containsKey("money"))
						newPlayer.setMoney(Integer.parseInt(userObject.getString("money")));
					if (userObject.containsKey("deck"))
						newPlayer.setDeck(buildCardArrayFromFile("deck", userObject));
					if (userObject.containsKey("allcards"))
						newPlayer.setAllCards(buildCardArrayFromFile("allcards", userObject));
					if (userObject.containsKey("level"))
						newPlayer.setTowers(getTowers(newPlayer.getLevel()));
				}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newPlayer;
	}

	/**
	 * Get all users
	 * @return Player array
	 */
	public static ArrayList<Player> getAllPlayers() throws IOException
	{
		ArrayList<Player> players = new ArrayList<Player>();
		InputStream fileReader = new FileInputStream(USERS_JSON);

		try{
			// Create Json reader to read the file in Json format
			JsonReader jsonReader = Json.createReader(fileReader);
			JsonObject playerObject = jsonReader.readObject();
			fileReader.close();
			ArrayList<String> playerNames = new ArrayList<String>();
			playerNames.addAll(playerObject.keySet()); 

			// Get each player info
			for(int i=0; i < playerNames.size(); i++){
				String playerName = playerNames.get(i);
				players.add(GetUser(playerName));
			}
		}
		finally{
			fileReader.close();
		}
		return players;
	}


	private static ArrayList<Tower> getTowers(String level) {
		ArrayList<Tower> towers = new ArrayList<Tower>(3);
		Tower leftTower = new Tower();
		Tower rightTower = new Tower();
		Tower kingTower = new Tower();
		InputStream fileReader = null;

		try {
			fileReader = new FileInputStream(TOWERS_JSON);
			// Create Json reader to read the file in Json format
			JsonReader jsonReader = Json.createReader(fileReader);
			JsonObject userObject = jsonReader.readObject();
			jsonReader.close();
			fileReader.close();

			// Get side towers info
			JsonObject object = userObject.get("left_right").asJsonObject();
			JsonObject value = object.get(level).asJsonArray().getJsonObject(0); 

			leftTower = new Tower("Left", Integer.parseInt(level), value.getInt("hitpoints"), value.getInt("damagepoints"), value.getInt("damagepersecond"));
			rightTower = new Tower("Right", Integer.parseInt(level), value.getInt("hitpoints"), value.getInt("damagepoints"), value.getInt("damagepersecond"));

			// Get king tower info
			object = userObject.get("king").asJsonObject();
			value = object.get(level).asJsonArray().getJsonObject(0); 
			kingTower = new Tower("King", Integer.parseInt(level), value.getInt("hitpoints"), value.getInt("damagepoints"), value.getInt("damagepersecond"));


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		towers.add(leftTower);
		towers.add(rightTower);
		towers.add(kingTower);

		return towers;
	}

	/**
	 * 
	 * @param type
	 * @param userObject
	 * @return Card array
	 */
	private static ArrayList<Card> buildCardArrayFromFile(String type, JsonObject userObject) {
		ArrayList<Card> deck = new ArrayList<Card>();
		JsonArray deckArray = userObject.getJsonArray(type);

		// Get each card info
		for(int i=0; i < deckArray.size(); i++){
			JsonObject card = deckArray.get(i).asJsonObject();
			Card tempCard = new Card(card.getString("name"));

			if (card.containsKey("type"))
				tempCard.setType(card.getString("type"));
			if (card.containsKey("cost"))
				tempCard.setCost(card.getString("cost"));
			if (card.containsKey("rarity"))
				tempCard.setRarity(getRarity(card.getString("rarity")));
			if (card.containsKey("level"))
				tempCard.setLevel(card.getInt("level"));
			deck.add(tempCard);
		}

		return deck;
	}

	/**
	 * Saves input user to file, including cards
	 * @param player
	 * @return success variable
	 */
	public static boolean SaveUser(Player player)
	{
		boolean success = false;
		InputStream fileReader = null;

		try {
			fileReader = new FileInputStream(USERS_JSON);

			// Create Json reader to read the file in Json format
			JsonReader jsonReader = Json.createReader(fileReader);
			JsonObject usersObject = jsonReader.readObject();
			jsonReader.close();
			fileReader.close();
			JsonObjectBuilder usersBuilder = Json.createObjectBuilder();

			if(usersObject != null)
				if(usersObject != JsonArray.NULL)
					usersObject.entrySet().forEach(e -> usersBuilder.add(e.getKey(), e.getValue()));

			// Remove if exists
			usersBuilder.remove(player.getName());

			// Add all player's deck cards
			JsonObjectBuilder userBuilder = Json.createObjectBuilder();
			userBuilder.add("deck", buildCardsArray(player.getDeck())); 
			// Add all other cards
			userBuilder.add("allcards", buildCardsArray(player.getAllCards())); 
			// Add all player's properties and attributes
			userBuilder.add("password", player.getPassword())
			.add("money", player.getMoney() + "")
			.add("level", player.getLevel());

			usersBuilder.add(player.getName(), userBuilder);

			// Write to file
			OutputStream os = new FileOutputStream(USERS_JSON);
			Map<String, Boolean> config = new HashMap<>();
			config.put(JsonGenerator.PRETTY_PRINTING, true);
			JsonWriterFactory jwf = Json.createWriterFactory(config);
			JsonWriter jsonWriter = jwf.createWriter(os);
			jsonWriter.writeObject(usersBuilder.build());
			jsonWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			success = true;
		}

		return success;
	}

	/** Builds an returns an array built in Json */
	private static JsonArrayBuilder buildCardsArray(ArrayList<Card> cards) {
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

		for(Card card : cards){
			JsonObjectBuilder jpb = Json.createObjectBuilder().
					add("name", card.getName()).
					add("type", card.getType()).
					add("cost", card.getCost()).
					add("rarity", card.getRarity().toString()).
					add("level", card.getLevel());
			arrayBuilder.add(jpb);
		}
		return arrayBuilder;
	}


}
