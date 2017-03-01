import java.util.ArrayList;
import org.jbox2d.dynamics.Body;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;
import javafx.scene.paint.Color;
import static java.lang.Math.random;

public class MyCollider implements ContactListener
{
   private Game _game;
   public MyCollider(Game game)
   {
      _game = game;
   }
   @Override
   public void beginContact(Contact contact)
   {
      
   }
   @Override
   public void endContact(Contact contact) {
     
   }

   @Override
   public void preSolve(Contact contact, Manifold oldManifold) {
       // TODO Auto-generated method stub

      Object object1 = contact.getFixtureA().getBody().getUserData();
      Object object2 = contact.getFixtureB().getBody().getUserData();

      if((object1 instanceof Wall || object1 instanceof Target) && object2 instanceof Ball)
      {
         if(object1 instanceof Wall)
         {
            contact.setEnabled(false);
         }
         _game.bodiesToRemove.add((Item)object2);
      }
      if(object1 instanceof Ball && (object2 instanceof Wall || object2 instanceof Target))
      {
         if(object2 instanceof Wall)
         {
            contact.setEnabled(false);
         }
         _game.bodiesToRemove.add((Item)object1);
      }

      if(object1 == _game.left && object2 == _game.target)
      {
         contact.setEnabled(false);
         _game.scored(0);
      }
      if(object1 == _game.right && object2 == _game.target)
      {
         contact.setEnabled(false);
         _game.scored(1);
      }

   }

   @Override
   public void postSolve(Contact contact, ContactImpulse impulse) {

   }
}