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

public class Separator
{
	public Body body;
	public Separator(Game g, float x, float y, float w, float h)
	{
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.STATIC;

		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(w / 2, h / 2);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.filter.categoryBits = Globals.mask_separator;
		fixtureDef.filter.maskBits = Globals.mask_ship;
		fixtureDef.friction = 0f;
		fixtureDef.shape = polygonShape;
		fixtureDef.density = 0f;
		fixtureDef.restitution = 0f;

		body = g.world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		body.setUserData(this);
	
		body.setTransform(new Vec2(x, y), 0);
	}
	public Body getBody()
	{
		return body;
	}
}