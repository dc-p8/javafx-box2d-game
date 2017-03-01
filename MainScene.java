import javafx.scene.Scene;
import javafx.stage.*;
import javafx.scene.layout.*;

public class MainScene extends Scene
{
	private int _state = -1;
	public MainScene()
	{
		super(new Pane());

		String[] defaultp1keys = {"Z", "S", "Q", "D"};
		String[] defaultp2keys = {"UP", "DOWN", "LEFT", "RIGHT"};
		PlayerParams p1 = new PlayerParams(5, "Joueur", defaultp1keys, false);
		PlayerParams p2 = new PlayerParams(5, "Bender", defaultp2keys, true);
		
		MenuPrincipal menuPrincipal = new MenuPrincipal();
		menuPrincipal.initModality(Modality.APPLICATION_MODAL);
		Reglages reglages = new Reglages(p1, p2);
   		reglages.initModality(Modality.APPLICATION_MODAL);

   		_state = 3;
   		
   		Boolean loop = true;
   		while(loop)
   		{
   			if(_state == 0)
   			{
   				Game game = new Game(28f, 40, 15f, p1, p2);
   				game.showAndWait();

   				_state = game.getState();

   				if(_state == 1)
   					_state = 3;

   			}
   			else if(_state == 1)
   			{
   				reglages.showAndWait();
   				_state = reglages.getState();
   				if(_state == 0)
   					_state = 3;
   			}
   			else if(_state == 2)
   			{
   				loop = false;
   			}
   			else if(_state == 3)
   			{
   				menuPrincipal.showAndWait();
   				_state = menuPrincipal.getState();
   			}
   		}
	}
}