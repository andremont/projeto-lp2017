package ismt.application.scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.Image;
import java.awt.Label;
import java.io.IOException;
import java.util.ArrayList;
import javax.tools.Tool;
import ismt.application.main.Player;
import ismt.application.main.Utils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayersScene {

	final static int APP_WIDTH = 333;
	final static int APP_HEIGHT = 600;
	final static int BUTTON_SIZE = 100;
	final static int GAP_SIZE = 10;
	ArrayList<Player> playersData = new ArrayList<Player>();

	public PlayersScene() {
		// TODO Auto-generated constructor stub
	}

	public Scene buildPlayersScene(Stage primaryStage, Scene sceneMain) {
		// TODO

		//

		try {

			playersData = Utils.getAllPlayers();

		} catch (IOException e1) {

			// TODO Auto-generated catch block

			e1.printStackTrace();

		}

		// Tabela
		TableView<Player> tablePlayer = new TableView<Player>();
		TableColumn nameCol = new TableColumn("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn moneyCol = new TableColumn("Money");
		moneyCol.setCellValueFactory(new PropertyValueFactory<>("money"));
		TableColumn levelCol = new TableColumn("Level");
		levelCol.setCellValueFactory(new PropertyValueFactory<>("level"));
		TableColumn pointsCol = new TableColumn("Points");
		pointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));

		// Carregar Dados
		ObservableList<Player> items = FXCollections.observableArrayList(playersData);
		tablePlayer.setItems(items);
		tablePlayer.getColumns().addAll(nameCol, moneyCol, levelCol, pointsCol);

		// Interface

		// Set button back action
		EventHandler<ActionEvent> buttonBackhandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				primaryStage.setScene(sceneMain);
			}
		};

		// All other scenes
		Button btnBack = new Button("Back");
		btnBack.setOnAction(buttonBackhandler);
		VBox vbBtnBack = new VBox(BUTTON_SIZE);
		vbBtnBack.setSpacing(5);
		vbBtnBack.setAlignment(Pos.CENTER);
		vbBtnBack.setTranslateY(5);

		// vbBtnBack.getChidlren().add(buttonBack);
		GridPane grid = new GridPane();
		grid.add(vbBtnBack, 1, 4);
		Scene tempScene = new Scene(grid, APP_WIDTH, APP_HEIGHT);
		tempScene.getStylesheets().add("resource/style.css");

		Button btnListPlayers = new Button("Visualizar Jogadores");
		btnListPlayers.setTooltip(new Tooltip("Listar todos os resultados na tabela resumo dos jogadores."));

		Button btnSortNamePlayers = new Button("Ordenar por Nome");
		btnSortNamePlayers.setTooltip(new Tooltip("Ordenar por Nome."));

		Button btnPlayersPointsSort = new Button("Ordenar por Pontos");
		btnPlayersPointsSort.setTooltip(new Tooltip("Ordenar jogadores com por pontuação."));

		Button btnCleanPlayer = new Button("Limpar Pesquisa");
		btnCleanPlayer.setTooltip(new Tooltip("Limpar Pesquisa"));

		Button btnFindPlayers = new Button("Pesquisar Jogadores");
		btnFindPlayers.setTooltip(new Tooltip("Utilize a caixe de texto para pesquisar jogadores por nome."));
		// btnFindPlayers.setDisable(true);

		Button btnViewPlayer = new Button(
				"Clique na linha correspondente a de cada jogador para visualizar as suas informações individuais.");
		btnViewPlayer.setTooltip(new Tooltip(
				"Clique na linha correspondente a de cada jogador para visualizar as suas informações individuais."));
		btnViewPlayer.setDisable(true);

		btnBack.setTooltip(new Tooltip("Voltar ao Menu Principal."));
		tablePlayer.setTooltip(new Tooltip("Tabela resumo dos jogadores"));

		TextField txtFindPlayers = new TextField();
		txtFindPlayers.setPromptText("Pesquisar...");
		txtFindPlayers.setPrefColumnCount(10);
		txtFindPlayers.getText();
		txtFindPlayers.setTooltip(new Tooltip("Pesquisar jogadores por nome ou por pontuação."));

		TextField txtName = new TextField();
		txtName.setPromptText("Name");
		TextField txtMoney = new TextField();
		txtMoney.setPromptText("Money");
		TextField txtLevel = new TextField();
		txtLevel.setPromptText("Level");
		TextField txtPoints = new TextField();
		txtPoints.setPromptText("Points");

		// btn Pesquisa
		btnFindPlayers.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
					tablePlayer.refresh();
					btnFindPlayers.setDisable(false);
					vbBtnBack.getChildren().addAll(btnCleanPlayer);
					ArrayList<Player> pesquisa = new ArrayList<Player>();
					for (Player p : playersData)
						if (p.getName().toLowerCase().contains(txtFindPlayers.getText()))
							pesquisa.add(p);
					// btnFindPlayers.setDisable(true);
					ObservableList<Player> items = FXCollections.observableArrayList(pesquisa);
					tablePlayer.setItems(items);
					txtFindPlayers.requestFocus();
				}
			}
		});

		// btn Ordenar por nome
		btnSortNamePlayers.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
					ObservableList<Player> items = FXCollections.observableArrayList(playersData);
					tablePlayer.getColumns().removeAll(nameCol, moneyCol, levelCol, pointsCol);
					tablePlayer.setItems(items);
					tablePlayer.getColumns().addAll(nameCol, moneyCol, levelCol, pointsCol);
					tablePlayer.getSortOrder().add(nameCol);
				}
			}
		});

		// btn Ordenar por pontos
		btnPlayersPointsSort.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
					ObservableList<Player> items = FXCollections.observableArrayList(playersData);
					tablePlayer.getColumns().removeAll(nameCol, moneyCol, levelCol, pointsCol);
					tablePlayer.setItems(items);
					tablePlayer.getColumns().addAll(nameCol, moneyCol, levelCol, pointsCol);
					tablePlayer.getSortOrder().add(pointsCol);
				}
			}
		});

		// btn Limpar
		btnCleanPlayer.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
					ObservableList<Player> items = FXCollections.observableArrayList(playersData);
					tablePlayer.getColumns().removeAll(nameCol, moneyCol, levelCol, pointsCol);
					tablePlayer.setItems(items);
					tablePlayer.getColumns().addAll(nameCol, moneyCol, levelCol, pointsCol);
					tablePlayer.getSortOrder().add(nameCol);
					vbBtnBack.getChildren().removeAll(btnCleanPlayer);
					txtFindPlayers.setText(null);
				}
			}
		});

		// View individual tabela
		tablePlayer.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
					txtName.setText("Name: " + tablePlayer.getSelectionModel().getSelectedItem().getName());
					txtName.setTooltip(new Tooltip("Name: " + tablePlayer.getSelectionModel().getSelectedItem().getName()));
					txtMoney.setText("Money: " + tablePlayer.getSelectionModel().getSelectedItem().getMoney());
					txtMoney.setTooltip(new Tooltip("Money: " + tablePlayer.getSelectionModel().getSelectedItem().getMoney()));
					txtLevel.setText("Level: " + tablePlayer.getSelectionModel().getSelectedItem().getLevel());
					txtLevel.setTooltip(new Tooltip("Level: " + tablePlayer.getSelectionModel().getSelectedItem().getLevel()));
					txtPoints.setText("Points: " + tablePlayer.getSelectionModel().getSelectedItem().getPoints());
					txtPoints.setTooltip(new Tooltip("Points: " + tablePlayer.getSelectionModel().getSelectedItem().getPoints()));
					tablePlayer.setItems(items);
				}
			}
		});

		// Adicionar elementos interface
		vbBtnBack.getChildren().addAll(tablePlayer, txtFindPlayers);
		vbBtnBack.getChildren().addAll(btnFindPlayers, btnSortNamePlayers, btnPlayersPointsSort, btnViewPlayer, txtName,
		txtMoney, txtPoints, btnBack);

		return tempScene;

	}
}