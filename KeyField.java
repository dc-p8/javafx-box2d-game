import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.event.*;
import java.util.ArrayList;

public class KeyField extends TextField
{
	static ArrayList<String> notAllowedKeys = initnotallowed();

	static ArrayList<String> initnotallowed()
	{
		ArrayList<String> ret = new ArrayList<String>();;
		ret.add("ESCAPE");
		ret.add("TAB");
		return ret;
	}

	public KeyField(String key)
	{
		super(key);
		
		setEditable(false);
		setOnKeyPressed(
    	new EventHandler<KeyEvent>()
    	{
    	    public void handle(KeyEvent e)
    	    {
    	        String code = e.getCode().toString();
    	        if(!notAllowedKeys.contains(code))
    	        {
    	        	setText(code);
    	        }
    	    }
    	});
	}
}