package ismt.application.scene;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;
import ismt.application.main.Card;
import ismt.application.main.GameMain;
//import ismt.application.main.GameMain.GameListener;
import ismt.application.main.Main;
import ismt.application.main.Utils;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlayScene{

	final static int APP_WIDTH = 333;
	final static int APP_HEIGHT = 600;
	final static int BUTTON_SIZE = 100;
	final static int GAP_SIZE = 10;
	
	
	private static final Integer STARTTIME = 15;
    private Timeline timeline;
    private Label timerLabel = new Label();
    private Integer timeSeconds = STARTTIME;
	ArrayList<Card> deck = new ArrayList<Card>();
	private GameMain game;
	
	public PlayScene() {
		// TODO Auto-generated constructor stub
	}
	
	public Scene buildPlayScene(Stage primaryStage, Scene sceneMain) {
		
		// All other scenes
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		
		Button buttonBack = new Button("Back");
		grid.setConstraints(buttonBack, 0, 0, 2, 1);
		grid.getChildren().add(buttonBack);
		
		
		Scene tempScene = new Scene(grid, APP_WIDTH, APP_HEIGHT);
		tempScene.getStylesheets().add("resource/play.css");
		
		
		timerLabel.setText(timeSeconds.toString());
        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 4em;");
        
        
        Button carta1 = new Button();
        grid.setConstraints(carta1, 3, 20, 2, 2);
        grid.getChildren().add(carta1);
        
        Button carta2 = new Button();
        grid.setConstraints(carta2, 5, 20, 2, 2);
        grid.getChildren().add(carta2);
        Button carta3 = new Button();
        grid.setConstraints(carta3, 7, 20, 2, 2);
        grid.getChildren().add(carta3);
        Button carta4 = new Button();
        grid.setConstraints(carta4, 9, 20, 2, 2);
        grid.getChildren().add(carta4);
        
        Button comingCard = new Button();
        grid.setConstraints(comingCard, 1, 22, 1, 1);
        grid.getChildren().add(comingCard);
        
        carta1.getStyleClass().add("carta1Style");
        carta2.getStyleClass().add("carta2Style");
        carta3.getStyleClass().add("carta3Style");
        carta4.getStyleClass().add("carta4Style");
		
		return tempScene;
	}

}

