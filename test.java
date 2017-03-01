import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;

public class test extends Application
{
	//public static int mod = 0;
	public void start(Stage stage)
	{

		VBox vBox = new VBox();
		Pane pane1 = new Pane();
		Pane pane2 = new Pane();
		pane1.setPrefHeight(100);
		pane1.setPrefWidth(100);
		pane2.setPrefHeight(100);
		pane2.setPrefWidth(100);
		
		vBox.getChildren().addAll(pane1, pane2);
		Scene scene = new Scene(vBox);
		stage.setScene(scene);
		stage.show();
		Rectangle rec = new Rectangle(30, 30);
		pane1.getChildren().addAll(rec);
		pane2.getChildren().addAll(rec);
		//pane2.getChildren().setAll(pane1.getChildren());
	}

	public static void main(String[] args) 
	{
		launch(args);
	}
}