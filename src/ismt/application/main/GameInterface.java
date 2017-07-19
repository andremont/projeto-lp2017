package ismt.application.main;

import java.util.ArrayList;
import javafx.stage.Stage;

public interface GameInterface {
	public void buildLoginScene(Stage primaryStage);
	public void buildMainScene(Stage primaryStage);
	public ArrayList<Card> drawRandomCards(Player player);
	public ArrayList<Card> shopRandomCards();
	public void start(Stage primaryStage);
	public Game createGame(ArrayList<Player> players);
	public String simulateGameResult(Game game);
	public void startGame(Game game);
	public void endGame(Game game, String result);
}
