package ismt.application.scene;

import java.io.IOException;
import java.util.ArrayList;

import ismt.application.main.Main;
import ismt.application.main.Card;
import ismt.application.main.Utils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CardsScene{

	final static int APP_WIDTH = 333;
	final static int APP_HEIGHT = 600;
	final static int BUTTON_SIZE = 100;
	final static int GAP_SIZE = 10;
	
	javafx.scene.control.TextField text;
	TableView<Card> table;
	GridPane grid;
	CardsScene scene = this;
	ComboBox<String> comboBox;
	Scene sceneViewCards;
	
	public CardsScene() {
		
	}

	public Scene buildCardsScene(Stage primaryStage, Scene sceneMain) {
		
		// Set button back action
		EventHandler<ActionEvent> buttonBackhandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				primaryStage.setScene(sceneMain);
			}
		};
		
		EventHandler<ActionEvent> buttonPesqhandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String dadosPesquisa = text.getText();
				ArrayList<Card> cards = new ArrayList<>();
				if(comboBox.getSelectionModel().getSelectedItem().equals("Todas")){
					try {
						cards=Utils.getAllCards();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if(comboBox.getSelectionModel().getSelectedItem().equals("Cartas do Player")){
						cards=Main.getPlayerLogged().getAllCards();
			          }else{
							cards=Main.getPlayerLogged().getDeck();
			          }
				cards= pesquisa(cards, dadosPesquisa);
				updateList(cards);
				
			}
		};
		
		grid = new GridPane();
		grid.setHgap(18);
		grid.setVgap(18);
		
		Scene tempScene = new Scene(grid, APP_WIDTH, APP_HEIGHT);
		
	
		Button buttonBack = new Button("Back");
		buttonBack.setOnAction(buttonBackhandler);
		grid.setConstraints(buttonBack, 0, 0, 2, 1);
		
		
		text = new javafx.scene.control.TextField();
		Button buttonPesq = new Button("Pesquisar");
		buttonPesq.setOnAction(buttonPesqhandler);
		grid.setConstraints(text, 3, 1);
		grid.setConstraints(buttonPesq, 4, 1,3,1);
		
		grid.getChildren().addAll(buttonBack,text,buttonPesq);
		ArrayList<Card> cards = new ArrayList<>();
		try {
			cards=Utils.getAllCards();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		table = new TableView<Card>();
		table.setRowFactory( tv -> {
		    TableRow<Card> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Card rowData = row.getItem();
		            Scene sceneIndividualCard = new IndividualCardScene(rowData).buildCardsScene(primaryStage, sceneMain);
		            primaryStage.setScene(sceneIndividualCard);
		        }
		    });
		    return row ;
		});
		updateList(cards);
		grid.setConstraints(table, 1, 4, 7, 1);
		grid.getChildren().add(table);
		
		comboBox = new ComboBox<String>();
		comboBox.getItems().addAll("Todas","Cartas do Player","Deck");
		comboBox.getSelectionModel().selectFirst();
		comboBox.valueProperty().addListener(new ChangeListener<String>() {
			 @Override public void changed(ObservableValue ov, String t, String t1) {
		          if(t1.equals("Todas")){
		        	  	ArrayList<Card> cards = new ArrayList<>();
						try {
							cards=Utils.getAllCards();
							System.out.println("Todas Cartas");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						updateList(cards);
		          }else if(t1.equals("Cartas do Player")){
		        	  	ArrayList<Card> cards = new ArrayList<>();
						cards=Main.getPlayerLogged().getAllCards();
						System.out.println("All player Card");
						updateList(cards);
		          }else{
		        	  	ArrayList<Card> cards = new ArrayList<>();
						cards=Main.getPlayerLogged().getDeck();
						System.out.println("Deck of player");
						updateList(cards);
		          }
		        }
		});
		grid.setConstraints(comboBox, 2, 3, 6, 1);
		grid.getChildren().add(comboBox);
		
		Button buttonSelec = new Button("Ver Selecionada");
		buttonSelec.setOnAction(buttonPesqhandler);
		
		tempScene.getStylesheets().add("resource/style.css");
		return tempScene;
	}
	
	
	public void updateList(ArrayList<Card> cards){
		TableColumn nameCol = new TableColumn("Nome");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn costCol = new TableColumn("Custo");
		costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
		TableColumn rarityCol = new TableColumn("Raridade");
		rarityCol.setCellValueFactory(new PropertyValueFactory<>("rarity"));
		ObservableList<Card> items =FXCollections.observableArrayList(cards);
		table.getColumns().clear();
		table.setItems(items);
		table.getColumns().addAll(nameCol,costCol,rarityCol);
		
	}
	
	public ArrayList<Card> pesquisa(ArrayList<Card> cards, String text){
		ArrayList<Card> tempCards = new ArrayList<Card>();
		for(Card card: cards){
			if(card.getName().toLowerCase().contains(text.toLowerCase())){
				tempCards.add(card);
			}
		}
		return tempCards;
	}


}
