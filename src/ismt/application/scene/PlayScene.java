package ismt.application.scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayScene{

	final static int APP_WIDTH = 333;
	final static int APP_HEIGHT = 600;
	final static int BUTTON_SIZE = 100;
	final static int GAP_SIZE = 10;
	
	public PlayScene() {
		// TODO Auto-generated constructor stub
	}

	public Scene buildPlayScene(Stage primaryStage, Scene sceneMain) {
		// TODO

		// Set button back action
		EventHandler<ActionEvent> buttonBackhandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				primaryStage.setScene(sceneMain);
			}
		};

		// All other scenes
		Button buttonBack = new Button("Back");
		buttonBack.setOnAction(buttonBackhandler);
		VBox vbBtnBack = new VBox(BUTTON_SIZE);
		vbBtnBack.setAlignment(Pos.BOTTOM_RIGHT);
		vbBtnBack.getChildren().add(buttonBack);
		GridPane grid = new GridPane();
		grid.add(vbBtnBack, 1, 4);
		Scene tempScene = new Scene(grid, APP_WIDTH, APP_HEIGHT);
		tempScene.getStylesheets().add("resource/play.css");
		
		return tempScene;
	}
}

