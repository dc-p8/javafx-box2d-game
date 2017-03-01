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

public class Target extends Item
{
	float init_x;

	public Target(Game game, float x_pos, float y_pos, float width, float height, float stroke, Paint in, Paint out)
	{
		init_x = x_pos;
		
		Rectangle rectangle = new Rectangle(game.SCALE * width, game.SCALE * height);
		rectangle.setStrokeWidth(game.SCALE * stroke);
		rectangle.setStrokeType(StrokeType.INSIDE);
		rectangle.setFill(in);
		rectangle.setStroke(out);
		shape = rectangle;

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;

		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(width / 2, height / 2);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.filter.categoryBits = Globals.mask_target;
		fixtureDef.filter.maskBits = Globals.mask_ball | Globals.mask_wall | Globals.mask_target;
		fixtureDef.friction = 0.0f;
		fixtureDef.shape = polygonShape;
		fixtureDef.density = 0.35f;
		fixtureDef.restitution = 0.7f;

		body = game.world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		body.setUserData(this);
	
		body.setTransform(new Vec2(x_pos, y_pos), 0);
		
		updatePosition(game);
		game.addShape(shape);
	}

	public void resetPosition()
	{
		System.out.println("test");
		body.setTransform(new Vec2(init_x, body.getPosition().y), body.getAngularVelocity());
	}

	public void updatePosition(Game game)
	{
		Rectangle rec = (Rectangle)shape;
		shape.setTranslateX((body.getPosition().x * game.SCALE) - (rec.getWidth() / 2));
		shape.setTranslateY(((game.HEIGHT - body.getPosition().y) * game.SCALE) - (rec.getHeight() / 2));
		shape.setRotate(-Math.toDegrees(body.getAngle()));
	}
}