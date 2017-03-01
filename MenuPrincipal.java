import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.event.*;
import javafx.stage.*;

public class MenuPrincipal extends Stage
{
	private int _state = -1;
	public static String[] texts = {"Jouer", "Reglages", "Quitter"};
	public static Menu myMenu = new Menu(30, 30, 300, texts);
	public MenuPrincipal()
	{
		setTitle("Menu principal");
		initStyle(StageStyle.UNDECORATED);
		setResizable(false);
		Scene scene = new Scene(myMenu);
		setScene(scene);
		
		scene.setOnKeyPressed(
   			new EventHandler<KeyEvent>()
   			{
   			    public void handle(KeyEvent e)
   			    {
   			    	String code = e.getCode().toString();
   			    	if(code == "UP")
   			    	{

   			        	myMenu.up();
   			    	}
   			    	else if(code == "DOWN")
   			    	{
   			        	myMenu.down();
   			    	}
   			    	else if(code == "ENTER")
   			    	{
   			        	_state = myMenu.select();
   			        	close();
   			    	}
   			    }
   			});

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