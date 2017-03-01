import java.lang.*;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.collision.shapes.*;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;

import javafx.scene.shape.*;
import javafx.scene.paint.*;

public class Ball extends Item
{
	public Ball(Game game, float rad, float x_pos, float y_pos, float stroke, Paint in, Paint out)
	{
		Circle circle = new Circle();
		circle.setRadius(game.getScale() * rad);
		circle.setStrokeWidth(game.getScale() * stroke);
		circle.setStrokeType(StrokeType.INSIDE);
		circle.setFill(in);
		circle.setStroke(out);
		shape = circle;

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
	
		CircleShape circleShape = new CircleShape();
		circleShape.m_radius = rad;
	
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.filter.categoryBits = Globals.mask_ball;
		fixtureDef.filter.maskBits = Globals.mask_wall | Globals.mask_target;
		fixtureDef.friction = 0.3f;
		fixtureDef.shape = circleShape;
		fixtureDef.density = 7.8f;
		fixtureDef.restitution = 0.3f;
	
		body = game.world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		body.setUserData(this);
	
		body.setTransform(new Vec2(x_pos, y_pos), 0);
		game.addShape(shape);
		updatePosition(game);
	}

	public void updatePosition(Game game)
	{
		shape.setTranslateX(body.getPosition().x * game.SCALE);
		shape.setTranslateY((game.HEIGHT - body.getPosition().y) * game.SCALE);
		shape.setRotate(-Math.toDegrees(body.getAngle()));
	}
}