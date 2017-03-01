import org.jbox2d.common.Vec2;
import java.lang.Math;
import org.jbox2d.dynamics.Body;
import javafx.scene.paint.*;

public class Player
{
	public int _score;
	private Game _game;
	private int _dir;

	public Ship _ship;
	private float _move_force;
	private float _air_friction;

	static int up = 0;
	static int down = 1;
	static int left = 2;
	static int right = 3;

	private PlayerParams _param;

	public Player(Game game, int dir , float move_force, float air_friction, PlayerParams param)
	{
		_score = 5;
		_game = game;
		_dir = dir;
		_move_force = move_force;
		_air_friction = air_friction;

		_param = param;

		_ship = new Ship(_game, 0.35f, (_game.WIDTH / 2)  + (-_dir * (_game.WIDTH / 4)), _game.HEIGHT * 0.5f, 0.15f, Color.rgb(0, 255, 0), Color.rgb(0, 0, 0));
	}

	public void move()
	{
		if(_param._isIa)
			moveIa();
		else
			moveKeys();

		Vec2 vel = _ship.body.getLinearVelocity();
		_ship.body.setLinearVelocity(new Vec2(vel.x * _air_friction, vel.y * _air_friction));
	}

	public void moveIa()
	{
		Vec2 shipPos = _ship.body.getPosition();
		Vec2 targetPos = _game.target.body.getPosition();

		if(shipPos.y > targetPos.y)
			_ship.body.applyForceToCenter(new Vec2(0, -_move_force));

		else if(shipPos.y < targetPos.y)
			_ship.body.applyForceToCenter(new Vec2(0, _move_force));

		if(Math.abs(shipPos.x - targetPos.x) > _game.WIDTH / 4)
			_ship.body.applyForceToCenter(new Vec2(_dir * _move_force, 0));

		else
			_ship.body.applyForceToCenter(new Vec2(_dir * -_move_force, 0));
	}

	public void moveKeys()
	{
		if(getKeyPressed(up))
			_ship.body.applyForceToCenter(new Vec2(0,  _move_force));

		if(getKeyPressed(down))
			_ship.body.applyForceToCenter(new Vec2(0, -_move_force));

		if(getKeyPressed(left))
			_ship.body.applyForceToCenter(new Vec2(-_move_force, 0));

		if(getKeyPressed(right))
			_ship.body.applyForceToCenter(new Vec2(_move_force, 0));
	}

	public Boolean getKeyPressed(int key)
	{
		return _game.pressed(_param._keys[key]);
	}

	public int getScore(){return _score;}
}