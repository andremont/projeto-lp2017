package ismt.application.scene;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Logger;

import javafx.scene.control.ScrollPane;
import javafx.stage.FileChooser;
import javafx.scene.control.TextArea;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;


public class RulesScene {

	final static int APP_WIDTH = 333;
	final static int APP_HEIGHT = 600;
	final static int BUTTON_SIZE = 100;
	final static int GAP_SIZE = 10;
	
	
	public RulesScene(String caminho_ficheiro)
	{
		//
	}
	
	 private String lerFicheiro(File file)
	 {
	        StringBuilder stringBuffer = new StringBuilder();
	        //Armazena caracteres na memoria para que possam ser manipulados com mais facilidade
	        BufferedReader bufferedReader = null;
	         
	        try {
	 
	            bufferedReader = new BufferedReader(new FileReader(file));
	             
	            String text;
	            while ((text = bufferedReader.readLine()) != null) {
	                stringBuffer.append(text + System.lineSeparator());
	            }
	 
	        } catch (FileNotFoundException ex) {
	            Logger.getLogger(RulesScene.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (IOException ex) {
	            Logger.getLogger(RulesScene.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                bufferedReader.close();
	            } catch (IOException ex) {
	                Logger.getLogger(RulesScene.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        } 
	         
	        return stringBuffer.toString();
	    }
	
	public RulesScene() 
	{
		//
	}

	public Scene buildRulesScene(Stage primaryStage, Scene sceneMain) {
		
		javafx.scene.control.TextArea textArea = new javafx.scene.control.TextArea();
		ScrollPane scrollPane = new ScrollPane(textArea); 
		textArea.setEditable(false);
		
		EventHandler<ActionEvent> buttonReadhandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) 
			{
				FileChooser fileChooser = new FileChooser();
				File file = fileChooser.showOpenDialog(primaryStage);
                
				if(file != null)
				{
                   textArea.setText(lerFicheiro(file));
                }
			
			}
		};
		
		Button buttonRead = new Button("Ler Ficheiro");
		buttonRead.setOnAction(buttonReadhandler);
		/*VBox vbBtnRead = new VBox(BUTTON_SIZE);
		vbBtnRead.setAlignment(Pos.BOTTOM_RIGHT);
		vbBtnRead.getChildren().add(buttonRead);
		GridPane grid = new GridPane();
		grid.add(vbBtnRead, 1, 4);
		Scene tempScene = new Scene(grid, APP_WIDTH, APP_HEIGHT);
		tempScene.getStylesheets().add("resource/style.css");*/
		

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
		vbBtnBack.getChildren().add(buttonRead);
		vbBtnBack.getChildren().add(textArea);
		vbBtnBack.getChildren().add(buttonBack);
		GridPane grid = new GridPane();
		grid.add(vbBtnBack, 1, 4);
		Scene tempScene = new Scene(grid, APP_WIDTH, APP_HEIGHT);
		tempScene.getStylesheets().add("resource/style.css");
		
		return tempScene;
	}
}
