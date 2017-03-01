import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.ArrayList;
import javafx.geometry.*;
import javafx.scene.text.TextAlignment;
import javafx.scene.paint.Color;


public class Menu extends VBox
{
	private int selected;
	public ArrayList<MyLab> lab_array;
	public Menu(double spacing, double padding, double text_width, String[] texts)
	{
		super(spacing);
		setStyle("-fx-border-color:rgb(90,123,94);-fx-border-width:10;-fx-background-color:rgb(150,172,135)");
		//setStroke(Color.rgb(90,123,94));
		
		setPadding(new Insets(padding));
		lab_array = new ArrayList<MyLab>();

		double max_size = 0;
		for(String s : texts)
		{
			MyLab lab = new MyLab(s);
			lab.setPrefWidth(text_width);
			lab.setAlignment(Pos.CENTER);
			lab_array.add(lab);
			getChildren().add(lab);
			
		}
		selected = 0;
		lab_array.get(selected).setSelected();
	}

	public void up()
	{
		lab_array.get(selected).setDefault();
		selected--;
		if(selected == -1)
		{
			selected = lab_array.size() - 1;
		}
		lab_array.get(selected).setSelected();
	}
	public void down()
	{
		lab_array.get(selected).setDefault();
		selected++;
		if(selected == lab_array.size())
		{
			selected = 0;
		}
		lab_array.get(selected).setSelected();
	}

	public int select()
	{
		return selected;
	}
}