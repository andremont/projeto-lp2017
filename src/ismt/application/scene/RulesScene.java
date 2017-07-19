package ismt.application.scene;

import java.awt.Label;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import javax.json.stream.JsonParser.Event;
import javax.swing.text.TableView.TableRow;

import org.omg.PortableServer.ServantRetentionPolicyValue;

import ismt.application.main.Rules;
import ismt.application.main.RulesCategory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class RulesScene {

	final static int APP_WIDTH = 333;
	final static int APP_HEIGHT = 600;
	final static int BUTTON_SIZE = 100;
	final static int GAP_SIZE = 10;

	public RulesScene() {

	}

	public Scene buildRulesScene(Stage primaryStage, Scene sceneMain) {
		TableView table = new TableView();
		TableView table1 = new TableView();
		EventHandler<ActionEvent> buttonBackhandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				primaryStage.setScene(sceneMain);
			}
		};
		EventHandler<ActionEvent> buttonListar = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				table.setVisible(true);
			}
		};
		
		
		EventHandler<ActionEvent> buttonPesquisa = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				TextField txtFindRules = new TextField();
				txtFindRules.setText("Pesquisar...");
				//Label label1 = new Label("Pesquisa:");
				//TextField textField = new TextField();
				fazerPesquisa(lerRegras(), txtFindRules.getText());
				txtFindRules.setVisible(true);
			}

		};
		
		EventHandler<ActionEvent> buttonOrdenar = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				OrdenarRules(null);
			}
		};
		
		
		
		Button buttonBack = new Button("Back");
		buttonBack.setOnAction(buttonBackhandler);
		VBox vbBtnBack = new VBox(BUTTON_SIZE);
		vbBtnBack.setAlignment(Pos.BOTTOM_RIGHT);
		vbBtnBack.getChildren().addAll(buttonBack);
		GridPane grid = new GridPane();
		grid.add(vbBtnBack, 1, 4);
		Scene tempScene = new Scene(grid, APP_WIDTH, APP_HEIGHT);
		tempScene.getStylesheets().add("resource/style.css");

		Button rules1 = new Button("Listar regras");
		rules1.setOnAction(buttonListar);

				
		
		Button rules2 = new Button("Pesquisar regras");
		rules2.setOnAction(buttonPesquisa);
		
		Button rules3 = new Button("Ordenar regras");
		rules3.setOnAction(buttonOrdenar);

		ArrayList<Rules> rules = new ArrayList<Rules>();
		try {
			rules = lerRegras();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		TableColumn numRegra = new TableColumn("Numero");
		numRegra.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("number"));
		TableColumn titulo = new TableColumn("Titulo");
		titulo.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("name"));
		TableColumn descricao = new TableColumn("Descrição");
		descricao.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("summary"));

		table.getColumns().addAll(numRegra, titulo, descricao);
		javafx.collections.ObservableList<Rules> items = javafx.collections.FXCollections.observableArrayList(rules);
		table.setItems(items);
		table.setVisible(false);
		VBox vbBtnRules = new VBox(BUTTON_SIZE);
		vbBtnRules.setAlignment(Pos.TOP_CENTER);
		vbBtnRules.getChildren().addAll(rules1, rules2, rules3, table);
		grid.add(vbBtnRules, 2, 5);
		tempScene.getStylesheets().add("resource/style.css");
		rules1.onMouseClickedProperty();
		
		table1.getColumns().add(numRegra);
		
		return tempScene;
	}

	public ArrayList<Rules> lerRegras() {

		ArrayList<Rules> rules = new ArrayList<Rules>();

		for (int i = 1; i < 5; i++) {
			Rules rule1 = new Rules();
			rule1.setNumber("" + i);
			if (i == 1) {
				rule1.setName("The Battle in Clash Royale");
			} else if (i == 2) {
				rule1.setName("The Battle Deck");
			} else if (i == 3) {
				rule1.setName("Winning the Battle in Clash Royale");
			} else if (i == 4) {
				rule1.setName("Clash Royale Cards");
			}
			rule1.setSummary("");
			if (i == 1) {
				rule1.setSummary("Players square off against live competition on tiny maps dotted by six buildings; three for each player: two Arena Towers and one King’s Tower.\nThe object of the game is to destroy the other player’s King’s Tower (middle building) while protecting your own.\nYou’ll do this by deploying troops from a hand of cards (drawn from a Battle Deck that you’ve built) anywhere within an allowable area on the field.\nEach card requires a certain amount of Elixir to deploy, but your Elixir regenerates quickly, so you’re only ever moments away from deploying your next troop or spell.\nMore cards are collected by unlocking chests won in battle or bought in the shop, which in turn will unlock new cards that you can add to your Battle Deck and level up the cards you already have.\nOn the event of taking down an Arena Tower, a new region on the opponent’s side is opened, available for you to deploy your Troop Cards to have an upper hand on the opponent.");
			} else if (i == 2) {
				rule1.setSummary("Your battle deck is the rotation of cards you wish to have in battle.\nTo change these you must tap the card you wish to add into your battle deck and tap use then tap the card that you would like remove from your battle deck.\nNext to the Battle Deck heading it will display the average elixir cost of all your troops, sometimes this can come in handy when deciding if your deck is suitable. For example a deck with an average of \n3.5 elixir that is semi-powerful is better than a deck with an average of 4.7 that is slightly more powerful as you will not be able to deploy troops as often to counter defences and proceed with your offensive strategy.\nThe battle deck consists of only 8 cards, so you want all your cards to be ones that you will be using, otherwise that is a waste of a valuable cardspace.");
			} else if (i == 3) {
				rule1.setSummary("If you destroy the other player’s King’s Tower, you win immediately.\nIf no one is able to take down the King’s Tower within the first 2 minutes, you and your opponent’s Elixir regeneration speed is doubled.\nIf no one is able to take down the King’s Tower or have more towers destroyed than the opponent within the first 3 minutes, sudden death is enabled, a mode in which the next player to destroy a tower wins.\nIf that is not able to happen within another minute, the game will end as a tie and no one will receive or lose any of their Trophies.");
			} else if (i == 4) {
				rule1.setSummary("Everything about Clash Royale Cards is here!\nIn Clash Royale, using Cards are the only method for deploying troops.\nClash Royale Cards can be obtained by buying with Gold or found in Chests.\nDuplicate cards obtained will fill up an upgrade meter, once the meter is full, you can upgrade the card with coins.\nThere are 4 levels of rarity, Common signified by blue, Rare signified by orange, Epic signified by purple and Legendary signified by rainbow.\nCurrently there are 64 Clash Royale Cards in total.\nThere are 3 types of cards: Troop, Spell and Building.");
			}
			rules.add(rule1);
		}
		return rules;
	}

	static ArrayList<List<String>> lerFicheiro() throws IOException {
		ArrayList<List<String>> rules = new ArrayList<List<String>>();
		String titulo, descricao;

		int linhas = 0;
		String read = "";
		File nomeFicheiro = new File("C:\\Users\\Alda\\git\\projeto-lp2017\\lp2017-projeto\\src\\resource\\regras.txt");

		try (FileReader fr = new FileReader(nomeFicheiro)) {

			BufferedReader br = new BufferedReader(new FileReader(nomeFicheiro));

			do {
				read += br.readLine();
				linhas++;
			} while (read != null);

			br.close();

			String[] lines = read.split("\n\r"); // System.lineSeparator()

			for (int i = 0; i < lines.length; i += 2) {
				rules.add(Arrays.asList(lines));// lines[i], lines[i + 1]));

			}
		}
		return rules;
	}

	public static String fazerPesquisa(ArrayList<Rules> temp, String inputPesquisa) {

		
		int i = 0, k = -1;

		int contador = 0;
		String index = "";

		for (int n = 0; n < temp.size(); n++) {
			if (temp.get(n).getName().contains(inputPesquisa))
				n = i;
			else
				i = i + 1;

		}
	
		/*Scanner teclado = new Scanner(System.in);

		System.out.print("Pesquisa:");
		inputPesquisa = teclado.nextLine();*/

		return (index);

	}
	
	
	public static String[][] OrdenarRules(String[][] a){
		for ( int i = 1; i < a.length; i++)
		{
			String tmp = a[i][1];
			int j = i;
			
			for( ; j>0 && tmp.compareTo(a[j-1][1]) <0; j--)
			{
				a[j][0]=a[j-1][0];
				a[j-1][0]=tmp;
				
				a[j][1]=a[j-1][1];
				a[j-1][1]=tmp;
			}
		}
		return a;
	}
	
	

}
