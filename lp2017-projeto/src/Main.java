import static javafx.geometry.HPos.RIGHT;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

	final int APP_WIDTH = 333;
	final int APP_HEIGHT = 600;
	
	public static void main(String[] args) {

		launch(args);
	}

	@Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("LP Clash Royale");
        
        // Grid 1 for scene 1
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
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

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 6);
        GridPane.setColumnSpan(actiontarget, 2);
        GridPane.setHalignment(actiontarget, RIGHT);
        actiontarget.setId("actiontarget");

        Scene scene = new Scene(grid, APP_WIDTH, APP_HEIGHT);
        
        // Grid 2 for scene 2
        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.CENTER);
        grid2.setHgap(10);
        grid2.setVgap(10);
        grid2.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle2 = new Text("Screen2");
        scenetitle2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid2.add(scenetitle2, 0, 0, 2, 1);

        Label userName2 = new Label("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        grid2.add(userName2, 0, 1);

        Button btn2 = new Button("Return to scene1");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(btn2);
        grid2.add(hbBtn2, 1, 4);
        
        Scene scene2 = new Scene(grid2, APP_WIDTH, APP_HEIGHT);
        primaryStage.setScene(scene);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	if(Utils.validateUser(userTextField.getText(), pwBox.getText())){
            		actiontarget.setText("Correct!");
            		primaryStage.setScene(scene2);
            	}
            	else{
            		actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Wrong credentials" + userName.getText() + "|" + pw.getText());
            	}
            }
        });
        
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	if (e.getSource()==btn)
            		primaryStage.setScene(scene2);
                else
                	primaryStage.setScene(scene);
            }
        });
        
        scene.getStylesheets().add("resource/login.css");
        scene2.getStylesheets().add("resource/style.css");
        primaryStage.show();
    }

}
