import javafx.scene.control.*;
import javafx.scene.paint.*;

public class MyLab extends Label
{
	public MyLab(String text)
	{
		super(text);
		setDefault();
	}
	public void setDefault()
	{
		setUnderline(false);
		//setStyle("-fx-background-color:#000000;-fx-text-fill:#FF0000;");
	}
	public void setSelected()
	{
		setUnderline(true);
		//setStyle("-fx-background-color:#FF0000;-fx-text-fill:#000000;");
	}
}