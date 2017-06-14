package ismt.application.scene;

import java.io.File;
import java.util.ArrayList;
import ismt.application.main.Card;
import ismt.application.main.Main;
import ismt.application.main.Player;
import ismt.application.main.Utils;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;	
public class SceneShop{

	final static int APP_WIDTH = 333;
	final static int APP_HEIGHT = 600;
	final static int BUTTON_SIZE = 100;
	final static int GAP_SIZE = 10;
	
	ArrayList<Card> temp = new ArrayList<Card>();
	ArrayList<Card> playerCards = new ArrayList<Card>();
	Main main = new Main();
	Player player = Utils.GetUser("root");
	String label[] = new String[6];
	ImageView pages[] = new ImageView[6];
	
	public SceneShop() {
		// TODO Auto-generated constructor stub
	}

	public Scene buildShopScene(Stage primaryStage, Scene sceneMain) {
		// TODO
		
		temp = main.shopRandomCards();
		String money = Integer.toString(player.getMoney());
		playerCards = player.getAllCards();
		
		GridPane grid = new GridPane();
		Scene tempScene = new Scene(grid, APP_WIDTH, APP_HEIGHT);
		
		grid.setHgap(20); 
		grid.setVgap(20);
		

	    for (int i=0; i<temp.size(); i++) {
	    	String imagemurl = temp.get(i).getImage();
	        pages[i] = new ImageView(
	            new Image(new File(imagemurl).toURI().toString()));
	        label[i] = Integer.toString(temp.get(i).getCoinCost());
	        pages[i].setFitHeight(80);
	    	pages[i].setFitWidth(80);
	    	if(i<3){
	    		grid.setConstraints(pages[i], 1+i, 2);
	    	}
	    	else{
	    		grid.setConstraints(pages[i], i-2, 6);
	    	}	
	        grid.getChildren().add(pages[i]);
	    
	       
	    }
	    
	
		Button button1 = new Button("      Card 1      ");
		Button button2 = new Button("      Card 2      ");
		Button button3 = new Button("      Card 3      ");
		Button button4 = new Button("      Card 4      ");
		Button button5 = new Button("      Card 5      ");
		Button button6 = new Button("      Card 6      ");
		
		Button buttonBack = new Button("Back");

		
		Label labelMiguel = new Label("Money available: "+money);
		Label finishLabel = new Label("The Card has been bought.");
		finishLabel.setVisible(false);
		Label labelM1 = new Label("Custo: "+label[0]);
		Label labelM2 = new Label("Custo: "+label[1]);
		Label labelM3 = new Label("Custo: "+label[2]);
		Label labelM4 = new Label("Custo: "+label[3]);
		Label labelM5 = new Label("Custo: "+label[4]);
		Label labelM6 = new Label("Custo: "+label[5]);
		
		
		grid.setConstraints(buttonBack, 0, 0, 2,1);
		grid.setConstraints(button1, 1, 4);
		grid.setConstraints(button2, 2, 4);
		grid.setConstraints(button3, 3, 4);
		grid.setConstraints(button4, 1, 8);
		grid.setConstraints(button5, 2, 8);
		grid.setConstraints(button6, 3, 8);
		
		
		
		grid.setConstraints(labelMiguel, 1, 10, 2, 2);
		grid.setConstraints(finishLabel, 1, 12, 2, 1);
		grid.setConstraints(labelM1, 1, 3);
		grid.setConstraints(labelM2, 2, 3);
		grid.setConstraints(labelM3, 3, 3);
		grid.setConstraints(labelM4, 1, 7);
		grid.setConstraints(labelM5, 2, 7);
		grid.setConstraints(labelM6, 3, 7);
		
		grid.getChildren().addAll(labelMiguel,finishLabel,labelM1,labelM2,labelM3,labelM4,labelM5,labelM6);
	    grid.getChildren().addAll(buttonBack,button1,button2,button3,button4,button5,button6);
	    
		tempScene.getStylesheets().add("resource/style.css");
		
		
		
		
		// Set button back action
		EventHandler<ActionEvent> buttonBackhandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				primaryStage.setScene(sceneMain);
			}
		};
		EventHandler<ActionEvent> button1handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				PauseTransition visiblePause = new PauseTransition(
				        Duration.seconds(0)
				);
				visiblePause.setOnFinished(
				        event -> finishLabel.setVisible(true)
				);
				visiblePause.play();
				PauseTransition visiblePause1 = new PauseTransition(
				        Duration.seconds(1)
				);
				visiblePause1.setOnFinished(
				        event -> finishLabel.setVisible(false)
				);
				visiblePause1.play();
				
				int tempMoney = Main.buyCard(temp, playerCards, player, 0);
				for(int i = 0; i< temp.size(); i++){
					label[i] = Integer.toString(temp.get(i).getCoinCost());
				}
				labelMiguel.setText("Money available: "+tempMoney);
				labelM1.setText("Custo: "+label[0]);
				
				
				
				
			}
		};
		EventHandler<ActionEvent> button2handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				PauseTransition visiblePause = new PauseTransition(
				        Duration.seconds(0)
				);
				visiblePause.setOnFinished(
				        event -> finishLabel.setVisible(true)
				);
				visiblePause.play();
				PauseTransition visiblePause1 = new PauseTransition(
				        Duration.seconds(1)
				);
				visiblePause1.setOnFinished(
				        event -> finishLabel.setVisible(false)
				);
				visiblePause1.play();
				
				int tempMoney = Main.buyCard(temp, playerCards, player, 1);
				for(int i = 0; i< temp.size(); i++){
					label[i] = Integer.toString(temp.get(i).getCoinCost());
				}
				labelMiguel.setText("Money available: "+tempMoney);
				labelM2.setText("Custo: "+label[1]);
				
			}
		};
		EventHandler<ActionEvent> button3handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				PauseTransition visiblePause = new PauseTransition(
				        Duration.seconds(0)
				);
				visiblePause.setOnFinished(
				        event -> finishLabel.setVisible(true)
				);
				visiblePause.play();
				PauseTransition visiblePause1 = new PauseTransition(
				        Duration.seconds(1)
				);
				visiblePause1.setOnFinished(
				        event -> finishLabel.setVisible(false)
				);
				visiblePause1.play();
				
				int tempMoney = Main.buyCard(temp, playerCards, player, 2);
				for(int i = 0; i< temp.size(); i++){
					label[i] = Integer.toString(temp.get(i).getCoinCost());
				}
				labelMiguel.setText("Money available: "+tempMoney);
				labelM3.setText("Custo: "+label[2]);
				
				
			}
		};
		EventHandler<ActionEvent> button4handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				PauseTransition visiblePause = new PauseTransition(
				        Duration.seconds(0)
				);
				visiblePause.setOnFinished(
				        event -> finishLabel.setVisible(true)
				);
				visiblePause.play();
				PauseTransition visiblePause1 = new PauseTransition(
				        Duration.seconds(1)
				);
				visiblePause1.setOnFinished(
				        event -> finishLabel.setVisible(false)
				);
				visiblePause1.play();
				
				int tempMoney = Main.buyCard(temp, playerCards, player, 3);
				for(int i = 0; i< temp.size(); i++){
					label[i] = Integer.toString(temp.get(i).getCoinCost());
				}
				labelMiguel.setText("Money available: "+tempMoney);
				labelM4.setText("Custo: "+label[3]);
				
			}
		};
		EventHandler<ActionEvent> button5handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				PauseTransition visiblePause = new PauseTransition(
				        Duration.seconds(0)
				);
				visiblePause.setOnFinished(
				        event -> finishLabel.setVisible(true)
				);
				visiblePause.play();
				PauseTransition visiblePause1 = new PauseTransition(
				        Duration.seconds(1)
				);
				visiblePause1.setOnFinished(
				        event -> finishLabel.setVisible(false)
				);
				visiblePause1.play();
				
				int tempMoney = Main.buyCard(temp, playerCards, player, 4);
				for(int i = 0; i< temp.size(); i++){
					label[i] = Integer.toString(temp.get(i).getCoinCost());
				}
				labelMiguel.setText("Money available: "+tempMoney);
				labelM5.setText("Custo: "+label[4]);
				
			}
		};
		EventHandler<ActionEvent> button6handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				PauseTransition visiblePause = new PauseTransition(
				        Duration.seconds(0)
				);
				visiblePause.setOnFinished(
				        event -> finishLabel.setVisible(true)
				);
				visiblePause.play();
				PauseTransition visiblePause1 = new PauseTransition(
				        Duration.seconds(1)
				);
				visiblePause1.setOnFinished(
				        event -> finishLabel.setVisible(false)
				);
				visiblePause1.play();
				
				int tempMoney = Main.buyCard(temp, playerCards, player, 5);
				for(int i = 0; i< temp.size(); i++){
					label[i] = Integer.toString(temp.get(i).getCoinCost());
				}
				labelMiguel.setText("Money available: "+tempMoney);
				labelM6.setText("Custo: "+label[5]);
				
			}
		};
		
		
		buttonBack.setOnAction(buttonBackhandler);
		button1.setOnAction(button1handler);
		button2.setOnAction(button2handler);
		button3.setOnAction(button3handler);
		button4.setOnAction(button4handler);
		button5.setOnAction(button5handler);
		button6.setOnAction(button6handler);
		
		
		
		return tempScene;
	
	}
	
}
	
	
	
	
	



