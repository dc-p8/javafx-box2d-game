import javafx.scene.shape.*;
import org.jbox2d.dynamics.Body;

public abstract class Item
{
	public Shape shape;

	public Body body;

	public abstract void updatePosition(Game g);
	public Body getBody()
	{
		return body;
	}

	public Shape getShape()
	{
		return shape;
	}
}