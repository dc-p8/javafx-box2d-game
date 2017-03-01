import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import java.lang.*;
import org.jbox2d.dynamics.Body;
import javafx.scene.canvas.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.scene.text.TextAlignment;
import javafx.scene.paint.Color;
import java.util.EventListener;
import javafx.scene.input.MouseEvent;
import javafx.event.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import org.jbox2d.dynamics.BodyType;
import javafx.scene.shape.*;
import static java.lang.Math.random;
import java.util.ArrayList;
import javafx.scene.input.KeyEvent;
import javafx.stage.*;
import javafx.scene.Scene;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import java.io.File;
import java.io.FileInputStream;

public class Game extends Stage
{
	private int _state = -1;
	public ArrayList<Item> bodiesToRemove;

	public World world;

	public float SCALE;
	public float WIDTH;
	public float HEIGHT;

	static float wall_size = 0.3f;

	private GameLoop gameLoop;

	private ArrayList<String> input = new ArrayList<String>();
	
	private Pane layout;

	private float total = 0;
	private float ball_acumulator = 0;
	private float ball_tick = 0.05f;

	public Target target;
	public Player p1;
	public Player p2;
	public Wall left;
	public Wall right;
	public Wall top;
	public Wall bot;

	Rectangle pausefilter;
	private Boolean setPause = false;

	private static String[] texts = {"Reprendre", "Retour au menu principal", "Quitter"};
	private Menu myMenu;

	TerrainArea ta_left;
	TerrainArea ta_right;

	private Boolean reset_target = false;
	private Boolean finished = false;

	public void addShape(Shape shape)
	{
		layout.getChildren().add(shape);
	}
	public void removeItem(Item i)
	{
		world.destroyBody(i.getBody());
		layout.getChildren().remove(i.getShape());
	}
	public Game(float sc, float w, float h, PlayerParams param_p1, PlayerParams param_p2)
	{
		setTitle("Shoot'n Goal ! (en jeu)");

		//Media sound = new Media("/sounds/hit.wav");//(new File("/sounds/hit.wav").toURI().toString());
		//MediaPlayer mp = new MediaPlayer(sound);
		

		SCALE = sc;
		WIDTH = w;
		HEIGHT = h;

		setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we)
			{
				we.consume();
			}
		});

		pausefilter = new Rectangle(SCALE * WIDTH, SCALE * HEIGHT);
		pausefilter.setFill(Color.rgb(0, 0, 0, 0.5));
		myMenu = new Menu(30, 30, 300, texts);

		StackPane root = new StackPane();
		ta_left = new TerrainArea(SCALE * ((WIDTH / 2) - 0.5f), SCALE * HEIGHT, "#000064", param_p1._name, "5");
		ta_right = new TerrainArea(SCALE * ((WIDTH / 2) - 0.5f), SCALE * HEIGHT, "#640000", param_p2._name, "5");
		//ta_left.setPrefWidth(SCALE * ((WIDTH / 2) - 0.5));
		HBox hArea = new HBox(SCALE * 1);
		hArea.setMaxHeight(SCALE * HEIGHT);
		hArea.getChildren().addAll(ta_left, ta_right);

		root.getChildren().add(hArea);

		BorderPane bp = new BorderPane();
		bp.setMargin(myMenu, new Insets(200, ((WIDTH * sc) / 2) - 150, 200, ((WIDTH * sc) / 2) - 150));
    	bp.setCenter(myMenu);

		//root.setAlignment(myMenu,Pos.BASELINE_CENTER);
		Scene scene = new Scene(root);
		initStyle(StageStyle.UNDECORATED);
		setResizable(false);
		setScene(scene);
		sizeToScene();

		scene.setOnKeyPressed(
    	new EventHandler<KeyEvent>()
    	{
    	    public void handle(KeyEvent e)
    	    {
    	        String key = e.getCode().toString();
    	        if(key == "ESCAPE")
    	        {
    	        	if(setPause == true)
    	        		return;
    	        	
    	        	layout.getChildren().add(pausefilter);
    	        	root.getChildren().add(bp);
    	        
    	        	setPause = true;
    	        	gameLoop.stop();
    	        	setTitle("Shoot'n Goal ! (en pause)");
    	        }
    	        if(setPause)
    	    	{
    	    		if(key == "UP")
    	    		{
    	    			myMenu.up();
    	    		}
    	    		else if(key == "DOWN")
    	    		{
    	    			myMenu.down();
    	    		}
    	    		else if(key == "ENTER")
    	    		{
    	    			_state = myMenu.select();
   			        	if(_state != 0)
   			        	{
   			        		close();
   			        		return;
   			        	}
   			        	else
   			        	{
   			        		setTitle("Shoot'n Goal ! (en jeu)");
   			        		layout.getChildren().remove(pausefilter);
   			        		root.getChildren().remove(bp);
   			        		setPause = false;
   			        		if(!finished)
   			        			gameLoop.start();
   			        	}
    	    		}
    	    	}

    	        if(!input.contains(key))
    	    		input.add(key);
    	    }
    	});
    	scene.setOnKeyReleased(
    	new EventHandler<KeyEvent>()
    	{
    	    public void handle(KeyEvent e)
    	    {
    	        String key = e.getCode().toString();
				input.remove(key);
    	    }
    	});

		world = new World(new Vec2(0f, 0f));

		bodiesToRemove = new ArrayList<Item>();
		
		gameLoop = new GameLoop(this);

		layout = new Pane();
		layout.setPrefWidth(sc * w);
		layout.setPrefHeight(sc * h);
		root.getChildren().add(layout);

		String[] keys = {"Z", "S", "Q", "D"};
		String[] keys2 = {"UP", "DOWN", "LEFT", "RIGHT"};

		left = new Wall(this, wall_size / 2, HEIGHT / 2, wall_size, HEIGHT, 0.06f, Color.rgb(0, 0, 0, 0.25f), Color.rgb(0, 0, 0), 0);
		right = new Wall(this, WIDTH - wall_size / 2, HEIGHT / 2, wall_size, HEIGHT, 0.06f, Color.rgb(0, 0, 0, 0.25f), Color.rgb(0, 0, 0), 0);
		top = new Wall(this, WIDTH / 2, HEIGHT - wall_size / 2, WIDTH - (2 * wall_size), wall_size, 0.06f, Color.rgb(0, 0, 0, 0.25f), Color.rgb(0, 0, 0), 0);
		bot = new Wall(this, WIDTH / 2, wall_size / 2, WIDTH - (2 * wall_size), wall_size, 0.06f, Color.rgb(0, 0, 0, 0.25f), Color.rgb(0, 0, 0), 0);
		target = new Target(this, WIDTH / 2, HEIGHT / 2, 2f, 0.75f, 0.1f, Color.rgb(0, 255, 0, 1f), Color.rgb(0, 0, 0));
		target.body.setLinearVelocity(new Vec2(0, 5));
		target.body.setAngularVelocity(3);
		p1 = new Player(this, 1, 5.0f, 0.95f, param_p1);
		p2 = new Player(this, -1, 5.0f, 0.95f, param_p2);

		Separator sep = new Separator(this, WIDTH / 2, HEIGHT / 2, 1, WIDTH);

		MyCollider myCollider = new MyCollider(this);
		world.setContactListener(myCollider);

		gameLoop.start();
	}

	public void proceed(float secs)
	{
		if(reset_target)
		{
			target.resetPosition();
			reset_target = false;
		}
		total += secs;
		removeBodies();

		p1.move();
		p2.move();

		Vec2 vel = target.getBody().getLinearVelocity();
		target.getBody().setLinearVelocity(new Vec2(vel.x * 0.99f, vel.y * 0.99f));

		ball_acumulator += secs;

		if(ball_acumulator > ball_tick)
		{
			ball_acumulator -= ball_tick;

			Vec2 playerPos = p1._ship.body.getPosition();
			Ball ball = new Ball(this, 0.2f, playerPos.x, playerPos.y, 0.02f, Color.rgb(0, 0, 255), Color.rgb(255, 255, 255));		
			ball.getBody().applyLinearImpulse(new Vec2(15, 0), ball.body.getPosition(), true);

			playerPos = p2._ship.body.getPosition();
			Ball ball2 = new Ball(this, 0.2f, playerPos.x, playerPos.y, 0.02f, Color.rgb(255, 0, 0), Color.rgb(255, 255, 255));		
			ball2.getBody().applyLinearImpulse(new Vec2(-15, 0), ball2.body.getPosition(), true);
		}

		world.step(secs, 8, 3);
	}

	public void removeBodies()
	{
		for (int i = bodiesToRemove.size() - 1 ; i >= 0 ; i--)
		{
			removeItem(bodiesToRemove.get(i));
			bodiesToRemove.remove(i);
		}
		//bodiesToRemove.clear();
	}

	public void render()
	{
		for(Body b = world.getBodyList(); b != null; b = b.getNext())
		{
			Object o = b.getUserData();
			if(o instanceof Item)
			{
				((Item) o).updatePosition(this);
			}
		}
	}

	public void scored(int player)
	{
		if(player == 1)
		{
			//p1 marque
			p2._score--;
			
			if(p2._score == 0)
			{
				ta_right.updateScore("Lose !");
				ta_right.updateScore("Win !");
			}
			else
			{
				ta_right.updateScore(Integer.toString(p2._score));
			}
		}
		else
		{
			// p2 marque
			p1._score--;
			if(p1._score == 0)
			{
				ta_left.updateScore("Lose !");
				ta_right.updateScore("Win !");
			}
			else
			{
				ta_left.updateScore(Integer.toString(p1._score));
			}
		}
		reset_target = true;

		if(p2._score == 0 || p1._score == 0)
		{

			finished = true;
			gameLoop.stop();
		}
	}

	public Boolean pressed(String key)
	{
		if(input.contains(key))
			return true;
		return false;
	}

	public int getState(){return _state;}
	public float getScale(){return SCALE;}
}