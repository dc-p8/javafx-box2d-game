import javafx.scene.Scene;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.event.*;

public class Reglages extends Stage
{
	private int _state = -1;
	public Reglages(PlayerParams p1, PlayerParams p2)
	{
		setTitle("Réglages");
		initStyle(StageStyle.UNDECORATED);
		setResizable(false);

		VBox vBox = new VBox(50);
		vBox.setStyle("-fx-border-color:rgb(90,123,94);-fx-border-width:10;-fx-background-color:rgb(150,172,135)");
		vBox.setPadding(new Insets(10));

		Button btn = new Button("OK");
		BorderPane bp = new BorderPane();
		bp.setCenter(btn);
		btn.setOnAction(new EventHandler<ActionEvent>()
		{
            @Override
            public void handle(ActionEvent event)
            {
            	_state = 0;
            	p1.update();
            	p2.update();
                close();
            }
        });

		vBox.getChildren().addAll(p1, p2, bp);

		Scene scene = new Scene(vBox);
		setScene(scene);

		setOnCloseRequest(new EventHandler<WindowEvent>()
		{
			public void handle(WindowEvent we)
			{
				we.consume();
			}
      	});
	}

	public int getState(){return _state;}
}