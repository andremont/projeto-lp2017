package ismt.application.scene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import ismt.application.main.Card;
import ismt.application.main.Main;
import ismt.application.main.Utils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayScene{

	final static int APP_WIDTH = 333;
	final static int APP_HEIGHT = 600;
	final static int BUTTON_SIZE = 100;
	final static int GAP_SIZE = 10;
	ArrayList<String> cartasMao = new ArrayList<>();
	
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
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		
		Button buttonBack = new Button("Back");
		buttonBack.setOnAction(buttonBackhandler);
		grid.setConstraints(buttonBack, 0, 0, 2, 1);
		grid.getChildren().add(buttonBack);
		
		
		Scene tempScene = new Scene(grid, APP_WIDTH, APP_HEIGHT);
		tempScene.getStylesheets().add("resource/play.css");
        
		
        Button carta1 = new Button();
        carta1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

			  	ArrayList<Card> deck = new ArrayList<>();
				deck = Main.getPlayerLogged().getDeck();
				cartasMao=gerarCarta(cartasMao, deck,0);	
				
				 Card card1 = new Card();
			        ArrayList<Card> cards = null;
					try {
						cards = Utils.getAllCards();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			      //IMAGEM 1
			        for(Card cd: cards){
			        	if(cd.getName().equals(cartasMao.get(0))){
			        		card1=cd;
			        	}
			        }
			        String imagel = card1.getImage();
			        ImageView image11 = new ImageView();
			        image11 = new ImageView(new Image(new File(imagel).toURI().toString()));
			        image11.setFitHeight(60);
			        image11.setFitWidth(60);
			        
			        grid.setConstraints(image11, 1, 18, 2, 2);
			        grid.getChildren().add(image11);
			        
			}	
		});
        
       

        Button carta2 = new Button();
        carta2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ArrayList<Card> deck = new ArrayList<>();
				deck = Main.getPlayerLogged().getDeck();
				cartasMao=gerarCarta(cartasMao, deck,1);	
				
				 Card card2 = new Card();
			        ArrayList<Card> cards = null;
					try {
						cards = Utils.getAllCards();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			      //IMAGEM 2
			        for(Card cd: cards){
			        	if(cd.getName().equals(cartasMao.get(1))){
			        		card2=cd;
			        	}
			        }
			        String image2 = card2.getImage();
			        ImageView image21 = new ImageView();
			        image21 = new ImageView(new Image(new File(image2).toURI().toString()));
			        image21.setFitHeight(60);
			        image21.setFitWidth(60);
			        
			        grid.setConstraints(image21, 3, 18, 2, 2);
			        grid.getChildren().add(image21);
			        
			
			} 	
		});
        
        
        
        Button carta3 = new Button();
        carta3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ArrayList<Card> deck = new ArrayList<>();
				deck = Main.getPlayerLogged().getDeck();
				cartasMao=gerarCarta(cartasMao, deck,2);	
				
				 Card card3 = new Card();
			        ArrayList<Card> cards = null;
					try {
						cards = Utils.getAllCards();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for(Card cd: cards){
			        	if(cd.getName().equals(cartasMao.get(2))){
			        		card3=cd;
			        	}
			        }
			        String image3 = card3.getImage();
			        ImageView image31 = new ImageView();
			        image31 = new ImageView(new Image(new File(image3).toURI().toString()));
			        image31.setFitHeight(60);
			        image31.setFitWidth(60);
			        
			        grid.setConstraints(image31, 5, 18, 2, 2);
			        grid.getChildren().add(image31);
			        
			} 	
		});
      
        
        
        Button carta4 = new Button();
        carta4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ArrayList<Card> deck = new ArrayList<>();
				deck = Main.getPlayerLogged().getDeck();
				cartasMao=gerarCarta(cartasMao, deck,3);	
				
				 Card card4 = new Card();
			        ArrayList<Card> cards = null;
					try {
						cards = Utils.getAllCards();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for(Card cd: cards){
			        	if(cd.getName().equals(cartasMao.get(3))){
			        		card4=cd;
			        	}
			        }
			        String image4 = card4.getImage();
			        ImageView image41 = new ImageView();
			        image41 = new ImageView(new Image(new File(image4).toURI().toString()));
			        image41.setFitHeight(60);
			        image41.setFitWidth(60);
			        
			        grid.setConstraints(image41, 7, 18, 2, 2);
			        grid.getChildren().add(image41);
			} 	
		});
        
        
        Button comingCard = new Button("PLAY");
        comingCard.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ArrayList<Card> deck = new ArrayList<>();
				deck = Main.getPlayerLogged().getDeck();
				cartasMao=gerarCarta(cartasMao, deck,0);	
				
				ArrayList<Card> cards = null;
				try {
					cards = Utils.getAllCards();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				grid.setConstraints(carta1, 1, 20, 2, 2);
		        grid.getChildren().add(carta1);
				//IMAGEM 1
				Card card1 = new Card();
		        for(Card cd: cards){
		        	if(cd.getName().equals(cartasMao.get(0))){
		        		card1=cd;
		        	}
		        }
		        String imagel = card1.getImage();
		        ImageView image11 = new ImageView();
		        image11 = new ImageView(new Image(new File(imagel).toURI().toString()));
		        image11.setFitHeight(60);
		        image11.setFitWidth(60);
		        
		        grid.setConstraints(image11, 1, 18, 2, 2);
		        grid.getChildren().add(image11);
		        
		      //IMAGEM 2
		        grid.setConstraints(carta2, 3, 20, 2, 2);
		        grid.getChildren().add(carta2);
				Card card2 = new Card();
		        for(Card cd: cards){
		        	if(cd.getName().equals(cartasMao.get(1))){
		        		card2=cd;
		        	}
		        }
		        String image2 = card2.getImage();
		        ImageView image21 = new ImageView();
		        image21 = new ImageView(new Image(new File(image2).toURI().toString()));
		        image21.setFitHeight(60);
		        image21.setFitWidth(60);
		        
		        grid.setConstraints(image21, 3, 18, 2, 2);
		        grid.getChildren().add(image21);
		        
		      //IMAGEM 3
		        grid.setConstraints(carta3, 5, 20, 2, 2);
		        grid.getChildren().add(carta3);
				Card card3 = new Card();
		        for(Card cd: cards){
		        	if(cd.getName().equals(cartasMao.get(2))){
		        		card3=cd;
		        	}
		        }
		        String image3 = card3.getImage();
		        ImageView image31 = new ImageView();
		        image31 = new ImageView(new Image(new File(image3).toURI().toString()));
		        image31.setFitHeight(60);
		        image31.setFitWidth(60);
		        
		        grid.setConstraints(image31, 5, 18, 2, 2);
		        grid.getChildren().add(image31);
		        
		      //IMAGEM 4
		        grid.setConstraints(carta4, 7, 20, 2, 2);
		        grid.getChildren().add(carta4);
				Card card4 = new Card();
		        for(Card cd: cards){
		        	if(cd.getName().equals(cartasMao.get(3))){
		        		card4=cd;
		        	}
		        }
		        String image4 = card4.getImage();
		        ImageView image41 = new ImageView();
		        image41 = new ImageView(new Image(new File(image4).toURI().toString()));
		        image41.setFitHeight(60);
		        image41.setFitWidth(60);
		        
		        grid.setConstraints(image41, 7, 18, 2, 2);
		        grid.getChildren().add(image41);
		
		
		        grid.getChildren().remove(comingCard);
			} 	
		});
        grid.setConstraints(comingCard, 1, 22, 1, 1);
        grid.getChildren().add(comingCard);
        
        carta1.getStyleClass().add("carta1Style");
        carta2.getStyleClass().add("carta2Style");
        carta3.getStyleClass().add("carta3Style");
        carta4.getStyleClass().add("carta4Style");
		
		return tempScene;
	}

	public static ArrayList<String> gerarCarta(ArrayList<String> cartasMao, ArrayList<Card> deck, int index){
		Random random = new Random();
		boolean existe=true;
		String tempCarta = null;
		while(existe){
			existe=false;
			int rd = random.nextInt(7);
			tempCarta = deck.get(rd).getName();
			if(cartasMao.size()==4){
				for(int i =0; i<cartasMao.size();i++){
					if(cartasMao.get(i).equals(tempCarta)){
						existe=true;
					}
				}
			}else{
				cartasMao.add(tempCarta);
				existe=true;
			}
		}
		cartasMao.remove(index);
		cartasMao.add(tempCarta);
		return cartasMao;
	}

}

