import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.io.File;
import java.io.FileInputStream;


public class TerrainArea extends VBox
{
	private Text _playername;
	private Text _score;

	public TerrainArea(float width, float height, String color, String strname, String strscore)
	{
		super(height / 4);
		Font scorefont = new Font(70);
		Font namefont = new Font(50);
		try
		{
			scorefont = Font.loadFont(new FileInputStream(new File("fonts/score.ttf")), 70);
		}
		catch(Exception e){}
		try
		{
			namefont = Font.loadFont(new FileInputStream(new File("fonts/name.ttf")), 50);
		}
		catch(Exception e){}
		setMaxHeight(height);
		setMinHeight(height);

		setPadding(new Insets(height / 4, 0, 0, 0));

		setStyle("-fx-background-color:" + color);

		_playername = new Text(strname);
		_playername.setFill(Color.rgb(255, 255, 255));
		_playername.setStrokeWidth(3);
		_playername.setStroke(Color.rgb(0, 0, 0));
		_playername.setFont(namefont);
		_playername.setTextAlignment(TextAlignment.CENTER);
		_playername.setWrappingWidth(width);

		_score = new Text(strscore);
		_score.setFill(Color.rgb(255, 255, 255));
		_score.setStrokeWidth(2);
		_score.setStroke(Color.rgb(0, 0, 0));
		_score.setFont(scorefont);
//new Font(40));
		_score.setTextAlignment(TextAlignment.CENTER);
		_score.setWrappingWidth(width);

		getChildren().addAll(_playername, _score);
	}

	public void updateScore(String strscore)
	{
		_score.setText(strscore);
	}
}