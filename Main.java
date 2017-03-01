import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;

import javafx.scene.paint.*;

import javafx.geometry.Insets;
import javafx.scene.text.TextAlignment;
import javafx.scene.shape.*;

import javafx.scene.input.KeyEvent;
import javafx.event.*;


public class Main extends Application
{
	//public static int mod = 0;
	
	public void start(Stage stage)
	{
		//
		//
		stage.setScene(new MainScene());




		//stage.show();
		//stage.close();
		//MenuPrincipal menuPrincipal = new MenuPrincipal();
		
		/*
		VBox vBox = new VBox();
		Scene root = new Scene(vBox);
		stage.setScene(root);
		stage.sizeToScene();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		*/


		/*
		Game game = new Game(28f, 40, 15f, false);
		Scene scene = new Scene(game);

		scene.setOnKeyPressed(
    	new EventHandler<KeyEvent>()
    	{
    	    public void handle(KeyEvent e)
    	    {
    	        String code = e.getCode().toString();
    	        game.addInput(code);
    	    }
    	});
    	scene.setOnKeyReleased(
    	new EventHandler<KeyEvent>()
    	{
    	    public void handle(KeyEvent e)
    	    {
    	        String code = e.getCode().toString();
    	        game.removeInput(code);
    	    }
    	});

		stage.setScene(scene);
		stage.show();
		*/

		
	}

	public static void main(String[] args) 
	{
		

		launch(args);
	}
}