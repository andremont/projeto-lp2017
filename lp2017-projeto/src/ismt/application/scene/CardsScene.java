package ismt.application.scene;

import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.TextField;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JList;


import ismt.application.main.Card;
import ismt.application.main.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
	
	public CardsScene() {
		// TODO Auto-generated constructor stub
	}

	public Scene buildCardsScene(Stage primaryStage, Scene sceneMain) {
		// TODO

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
				try {
					cards=Utils.getAllCards();
				} catch (IOException e1) {
					e1.printStackTrace();
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
		grid.setConstraints(table, 1, 3, 7, 1);
		grid.getChildren().add(table);
		
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
