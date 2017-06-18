package ismt.application.main;

import ismt.application.scene.*;
import static javafx.geometry.HPos.RIGHT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application implements GameInterface{

	final int APP_WIDTH = 333;
	final int APP_HEIGHT = 600;
	final int BUTTON_SIZE = 100;
	final int GAP_SIZE = 10;
	Scene sceneLogin, sceneMain, scenePlay, 
		  sceneViewStats, sceneViewPlayers, 
		  sceneViewCards, sceneViewRules, sceneViewShop;

	static boolean variavelDeAjuda = false;
	static int commonCard = 2;
	static int rareCard = 20;
	static int epicCard = 1000;
	static int legendaryCard = 10000;
	
	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("LP Clash Royale");

		// Build individual screens
		buildLoginScene(primaryStage);
		buildMainScene(primaryStage);
		scenePlay = new PlayScene().buildPlayScene(primaryStage, sceneMain);
		sceneViewStats = new StatsScene().buildStatsScene(primaryStage, sceneMain);
		sceneViewCards = new CardsScene().buildCardsScene(primaryStage, sceneMain);
		sceneViewPlayers = new PlayersScene().buildPlayersScene(primaryStage, sceneMain);
		sceneViewShop = new SceneShop().buildShopScene(primaryStage, sceneMain);
		sceneViewRules = new RulesScene().buildRulesScene(primaryStage, sceneMain);
		
		// Set initial scene for login
		primaryStage.setScene(sceneViewShop);
		
		// Show application!
		primaryStage.show();
	}

	public void buildLoginScene(Stage primaryStage) {
		// Scene login
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(GAP_SIZE);
		grid.setVgap(GAP_SIZE);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Login page");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);

		Button buttonLogin = new Button("Sign in");
		HBox hbBtn = new HBox(BUTTON_SIZE);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(buttonLogin);
		grid.add(hbBtn, 1, 4);
		
		Button buttonExit = new Button("Quit");
		HBox hbBtnBottom = new HBox(BUTTON_SIZE);
		hbBtnBottom.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtnBottom.getChildren().add(buttonExit);
		grid.add(hbBtnBottom, 2, 35);

		final Text actiontarget = new Text();
		grid.add(actiontarget, 0, 6);
		GridPane.setColumnSpan(actiontarget, 2);
		GridPane.setHalignment(actiontarget, RIGHT);
		actiontarget.setId("actiontarget");

		sceneLogin = new Scene(grid, APP_WIDTH, APP_HEIGHT);
		sceneLogin.getStylesheets().add("resource/login.css");

		// Set button login action
		buttonLogin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// if(Utils.validateUser(userTextField.getText(), pwBox.getText())){ // FIXME
				if(true){
					actiontarget.setText("Correct!");
					primaryStage.setScene(sceneMain);
				}
				else{
					actiontarget.setFill(Color.FIREBRICK);
					actiontarget.setText("Wrong credentials: " + userName.getText() + "|" + pw.getText());
				}
			}
		});
		
		// Set exit button action
		buttonExit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) { 
				primaryStage.close();
			}
		});
	
	}

	public void buildMainScene(Stage primaryStage) {
		// Scene main menu
		GridPane grid2 = new GridPane();
		grid2.setAlignment(Pos.CENTER);
		grid2.setHgap(GAP_SIZE);

		Text scenetitle2 = new Text("Main menu");
		scenetitle2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid2.add(scenetitle2, 0, 0, 2, 1);

		Button buttonPlay = new Button("Play!");
		Button buttonStats = new Button("View stats");
		Button buttonPlayers = new Button("View players");
		Button buttonCards = new Button("View cards");
		Button buttonShop = new Button("View shop");
		Button buttonRules = new Button("View rules");
		Button buttonLogout = new Button("Logout");
		buttonPlay.setMaxWidth(BUTTON_SIZE);
		buttonCards.setMaxWidth(BUTTON_SIZE);
		buttonStats.setMaxWidth(BUTTON_SIZE);
		buttonPlayers.setMaxWidth(BUTTON_SIZE);
		buttonShop.setMaxWidth(BUTTON_SIZE);
		buttonRules.setMaxWidth(BUTTON_SIZE);
		buttonLogout.setMaxWidth(BUTTON_SIZE);

		VBox vbBtn = new VBox(BUTTON_SIZE);
		vbBtn.setAlignment(Pos.BASELINE_CENTER);
		vbBtn.setSpacing(GAP_SIZE);
		vbBtn.setPadding(new Insets(0, 20, 10, 20)); 
		vbBtn.getChildren().addAll(buttonPlay, buttonStats, buttonPlayers, buttonCards, buttonShop, buttonRules, buttonLogout);
		grid2.add(vbBtn, 1, 4);

		sceneMain = new Scene(grid2, APP_WIDTH, APP_HEIGHT);
		sceneMain.getStylesheets().add("resource/style.css");

		// Set buttons action
		EventHandler<ActionEvent> buttonClickhandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (e.getSource() == buttonLogout)
					primaryStage.setScene(sceneLogin);
				else if (e.getSource() == buttonPlay)
					primaryStage.setScene(scenePlay);
				else if (e.getSource() == buttonStats)
					primaryStage.setScene(sceneViewStats);
				else if (e.getSource() == buttonPlayers)
					primaryStage.setScene(sceneViewPlayers);
				else if (e.getSource() == buttonCards)
					primaryStage.setScene(sceneViewCards);
				else if (e.getSource() == buttonShop)
					primaryStage.setScene(sceneViewShop);
				else if (e.getSource() == buttonRules)
					primaryStage.setScene(sceneViewRules);
				else
					primaryStage.setScene(sceneMain);
			}
		};

		// Associate event handler to buttons
		for(Object obj : vbBtn.getChildren().toArray())
		{
			if (obj.getClass().getTypeName() == "javafx.scene.control.Button")
				((Button)obj).setOnAction(buttonClickhandler);
		}
	}

	@Override
	public ArrayList<Card> drawRandomCards(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game createGame(ArrayList<Player> players) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String simulateGameResult(Game game) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startGame(Game game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endGame(Game game, String result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Card> shopRandomCards() {
		ArrayList<Card> shopCards = new ArrayList<Card>();
		ArrayList<Card> temp = new ArrayList<Card>();
		Utils util = new Utils();
		int index[] = new int[6];
		
		Random randomGenerator;
		try {
			shopCards = util.getAllCards();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		randomGenerator = new Random();
			
		for(int j = 0; j<shopCards.size(); j++){
			if(shopCards.get(j).getRarity() == Rarity.Common)
				shopCards.get(j).setCoinCost(2);
			else if(shopCards.get(j).getRarity() == Rarity.Rare)
				shopCards.get(j).setCoinCost(20);
			else if(shopCards.get(j).getRarity() == Rarity.Epic)
				shopCards.get(j).setCoinCost(1000);
			else if(shopCards.get(j).getRarity() == Rarity.Legendary)
				shopCards.get(j).setCoinCost(10000);
		}
		
		
		for(int i = 0; i < 6 ;i++){
			index[i] = randomGenerator.nextInt(shopCards.size());
			Card tempCard = shopCards.get(index[i]);
			shopCards.remove(index[i]);
			temp.add(tempCard);
			System.out.println(tempCard.getName() + " | Cost:" + tempCard.getCost() + " | rarity: " + tempCard.getRarity());
		}
		return temp;
	}
		
	public static int buyCard(ArrayList<Card> temp,ArrayList<Card> playerCards, Player player,int contador) {
		
		Card selectedCard = temp.get(contador);	
		
		if(selectedCard.getRarity() == Rarity.Common && player.getMoney() >= selectedCard.getCoinCost()){
			for(int cont = 0; cont < playerCards.size(); cont++){
				if(playerCards.get(cont).getName().toLowerCase().equals(selectedCard.getName().toLowerCase())){
					variavelDeAjuda = true;
					player.getAllCards().get(contador).setQuantity(player.getAllCards().get(contador).getQuantity()+1);
				}
			}
			if(!variavelDeAjuda){
				player.getAllCards().add(selectedCard);
			}
			player.setMoney(player.getMoney()-selectedCard.getCoinCost());
			selectedCard.setCoinCost(selectedCard.getCoinCost()+2);
			System.out.println("Current money: "+player.getMoney()+" Antes: "+selectedCard.getCoinCost());
		}
		else if(selectedCard.getRarity() == Rarity.Rare && player.getMoney() >= selectedCard.getCoinCost()){
			for(int cont = 0; cont < playerCards.size(); cont++){
				if(playerCards.get(cont).getName().toLowerCase() == selectedCard.getName().toLowerCase()){
					variavelDeAjuda = true;
					player.getAllCards().get(contador).setQuantity(player.getAllCards().get(contador).getQuantity()+1);
				}
				
			}
			if(!variavelDeAjuda){
				player.getAllCards().add(selectedCard);
			}
			player.setMoney(player.getMoney()-selectedCard.getCoinCost());
			selectedCard.setCoinCost(selectedCard.getCoinCost()+20);
			System.out.println("Current money: "+player.getMoney()+" Antes: "+selectedCard.getCoinCost());
		
		}
		else if(selectedCard.getRarity() == Rarity.Epic && player.getMoney() >= selectedCard.getCoinCost()){
			for(int cont = 0; cont < playerCards.size(); cont++){
				if(playerCards.get(cont).getName().toLowerCase().equals(selectedCard.getName().toLowerCase())){
					variavelDeAjuda = true;
					player.getAllCards().get(contador).setQuantity(player.getAllCards().get(contador).getQuantity()+1);
				}
			}
			if(!variavelDeAjuda){
				player.getAllCards().add(selectedCard);
			}
			player.setMoney(player.getMoney()-selectedCard.getCoinCost());
			selectedCard.setCoinCost(selectedCard.getCoinCost() + epicCard);
			System.out.println("Current money: "+player.getMoney()+" Antes: "+selectedCard.getCoinCost());
		}
		else if(selectedCard.getRarity() == Rarity.Legendary && player.getMoney() >= selectedCard.getCoinCost()){
			for(int cont = 0; cont < playerCards.size(); cont++){
				if(playerCards.get(cont).getName().toLowerCase().equals(selectedCard.getName().toLowerCase())){
					variavelDeAjuda = true;
					player.getAllCards().get(contador).setQuantity(player.getAllCards().get(contador).getQuantity()+1);
				}
			}	
			if(!variavelDeAjuda){
				player.getAllCards().add(selectedCard);
			}
			player.setMoney(player.getMoney()-selectedCard.getCoinCost());
			selectedCard.setCoinCost(selectedCard.getCoinCost()*2);
			System.out.println("Current money: "+player.getMoney()+" Antes: "+selectedCard.getCoinCost());
		}
		else if(player.getMoney() < selectedCard.getCoinCost()){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setContentText("I'm sorry but you dont have enough money to buy that Card!");

			alert.showAndWait();
		}
		
		return player.getMoney();
	}
	
	private static void upgradeCard(Player player,ArrayList<Card> playerCards) {
		System.out.println(".---------------------------------------.");
		Utils.printAllCards(player.getAllCards());
		int quantity;
		
		Card selectedCard = playerCards.get(5);
		
		for(int i = 0; i < playerCards.size(); i++){
			if(selectedCard==playerCards.get(i)){
				quantity = playerCards.get(i).getQuantity();
				if(playerCards.get(i).getRarity() == Rarity.Common){
					upgradeCommon(player, playerCards, quantity, i);
				}
				else if(playerCards.get(i).getRarity() == Rarity.Rare){
					upgradeRare(player, playerCards, quantity, i);
				}
				else if(playerCards.get(i).getRarity() == Rarity.Epic){
					upgradeEpic(player, playerCards, quantity, i);
				}
				else if(playerCards.get(i).getRarity() == Rarity.Legendary){
					upgradeLengendary(player, playerCards, quantity, i);
				}
			}	
		}
	}

	private static void upgradeLengendary(Player player, ArrayList<Card> playerCards, int quantity, int i) {
		if(playerCards.get(i).getLevel() == 1 ){
			if(quantity>=2){
				player.getAllCards().get(i).setLevel(2);
				player.getAllCards().get(i).setQuantity(quantity-2);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 2){
			if(quantity>=4){
				player.getAllCards().get(i).setLevel(3);
				player.getAllCards().get(i).setQuantity(quantity-4);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 3){
			if(quantity>=10){
				player.getAllCards().get(i).setLevel(4);
				player.getAllCards().get(i).setQuantity(quantity-10);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 4){
			if(quantity>=20){
				player.getAllCards().get(i).setLevel(5);
				player.getAllCards().get(i).setQuantity(quantity-20);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
	}

	private static void upgradeEpic(Player player, ArrayList<Card> playerCards, int quantity, int i) {
		if(playerCards.get(i).getLevel() == 1){
			if(quantity>=2){
				player.getAllCards().get(i).setLevel(2);
				player.getAllCards().get(i).setQuantity(quantity-2);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 2){
			if(quantity>=4){
				player.getAllCards().get(i).setLevel(3);
				player.getAllCards().get(i).setQuantity(quantity-4);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 3){
			if(quantity>=10){
				player.getAllCards().get(i).setLevel(4);
				player.getAllCards().get(i).setQuantity(quantity-10);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 4){
			if(quantity>=20){
				player.getAllCards().get(i).setLevel(5);
				player.getAllCards().get(i).setQuantity(quantity-20);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 5){
			if(quantity>=50){
				player.getAllCards().get(i).setLevel(6);
				player.getAllCards().get(i).setQuantity(quantity-50);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 6){
			if(quantity>=100){
				player.getAllCards().get(i).setLevel(7);
				player.getAllCards().get(i).setQuantity(quantity-100);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 7){
			if(quantity>=200){
				player.getAllCards().get(i).setLevel(8);
				player.getAllCards().get(i).setQuantity(quantity-200);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
	}

	private static void upgradeRare(Player player, ArrayList<Card> playerCards, int quantity, int i) {
		if(playerCards.get(i).getLevel() == 1){
			if(quantity>=2){
				player.getAllCards().get(i).setLevel(2);
				player.getAllCards().get(i).setQuantity(quantity-2);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 2){
			if(quantity>=4){
				player.getAllCards().get(i).setLevel(3);
				player.getAllCards().get(i).setQuantity(quantity-4);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 3){
			if(quantity>=10){
				player.getAllCards().get(i).setLevel(4);
				player.getAllCards().get(i).setQuantity(quantity-10);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 4){
			if(quantity>=20){
				player.getAllCards().get(i).setLevel(5);
				player.getAllCards().get(i).setQuantity(quantity-20);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 5){
			if(quantity>=50){
				player.getAllCards().get(i).setLevel(6);
				player.getAllCards().get(i).setQuantity(quantity-50);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 6){
			if(quantity>=100){
				player.getAllCards().get(i).setLevel(7);
				player.getAllCards().get(i).setQuantity(quantity-100);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 7){
			if(quantity>=200){
				player.getAllCards().get(i).setLevel(8);
				player.getAllCards().get(i).setQuantity(quantity-200);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 8){
			if(quantity>=400){
				player.getAllCards().get(i).setLevel(9);
				player.getAllCards().get(i).setQuantity(quantity-400);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 9){
			if(quantity>=800){
				player.getAllCards().get(i).setLevel(10);
				player.getAllCards().get(i).setQuantity(quantity-800);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 10){
			if(quantity>=1000){
				player.getAllCards().get(i).setLevel(11);
				player.getAllCards().get(i).setQuantity(quantity-1000);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
	}

	private static void upgradeCommon(Player player, ArrayList<Card> playerCards, int quantity, int i) {
		if(playerCards.get(i).getLevel() == 1){
			if(quantity>=2){
				player.getAllCards().get(i).setLevel(2);
				player.getAllCards().get(i).setQuantity(quantity-2);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 2){
			if(quantity>=4){
				player.getAllCards().get(i).setLevel(3);
				player.getAllCards().get(i).setQuantity(quantity-4);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 3){
			if(quantity>=10){
				player.getAllCards().get(i).setLevel(4);
				player.getAllCards().get(i).setQuantity(quantity-10);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 4){
			if(quantity>=20){
				player.getAllCards().get(i).setLevel(5);
				player.getAllCards().get(i).setQuantity(quantity-20);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 5){
			if(quantity>=50){
				player.getAllCards().get(i).setLevel(6);
				player.getAllCards().get(i).setQuantity(quantity-50);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 6){
			if(quantity>=100){
				player.getAllCards().get(i).setLevel(7);
				player.getAllCards().get(i).setQuantity(quantity-100);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 7){
			if(quantity>=200){
				player.getAllCards().get(i).setLevel(8);
				player.getAllCards().get(i).setQuantity(quantity-200);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 8){
			if(quantity>=400){
				player.getAllCards().get(i).setLevel(9);
				player.getAllCards().get(i).setQuantity(quantity-400);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 9){
			if(quantity>=800){
				player.getAllCards().get(i).setLevel(10);
				player.getAllCards().get(i).setQuantity(quantity-800);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 10){
			if(quantity>=1000){
				player.getAllCards().get(i).setLevel(11);
				player.getAllCards().get(i).setQuantity(quantity-1000);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 11){
			if(quantity>=2000){
				player.getAllCards().get(i).setLevel(12);
				player.getAllCards().get(i).setQuantity(quantity-2000);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
		else if(playerCards.get(i).getLevel() == 12){
			if(quantity>=5000){
				player.getAllCards().get(i).setLevel(13);
				player.getAllCards().get(i).setQuantity(quantity-5000);
			}else
				System.out.println("You dont have sufficient cards to upgrade!");
		}
	}
	
	
	
	
	
	


}
