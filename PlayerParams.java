import javafx.scene.Scene;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class PlayerParams extends VBox
{
	public String[] _keys;
	public String _name;
	public Boolean _isIa;

	KeyField _k_up;
	KeyField _k_down;
	KeyField _k_left;
	KeyField _k_right;

	TextField tf;

	public PlayerParams(double sep, String name, String[] keys, Boolean isIa)
	{
		super(sep);

		_keys = keys;
		_name = name;
		//_isIa : dans la callback

		HBox head = new HBox(0);

		tf = new TextField(_name);
		Label lab = new Label(" : ");
		lab.setPrefHeight(25);
		
		ToggleGroup tg = new ToggleGroup();
		tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
    	public void changed(ObservableValue<? extends Toggle> ov,
        Toggle old_toggle, Toggle new_toggle) {
            if (tg.getSelectedToggle() != null)
            {
            	_isIa = (Boolean)tg.getSelectedToggle().getUserData();
            	System.out.println(name + " " + _isIa);
            }
        }
		});

		RadioButton rb1 = new RadioButton("IA");
		head.setMargin(rb1, new Insets(0, 0, 0, 30));
		rb1.setUserData(true);
		rb1.setPrefHeight(25);
		rb1.setToggleGroup(tg);
		rb1.setSelected(isIa);
		
		RadioButton rb2 = new RadioButton("Joueur");
		head.setMargin(rb2, new Insets(0, 0, 0, 15));
		rb2.setUserData(false);
		rb2.setPrefHeight(25);
		rb2.setToggleGroup(tg);
		rb2.setSelected(!isIa);

		head.getChildren().addAll(tf, lab, rb1, rb2);

		_k_up = new KeyField(keys[0]);
		_k_down = new KeyField(keys[1]);
		_k_left = new KeyField(keys[2]);
		_k_right  = new KeyField(keys[3]);

		GridPane keyGrid = new GridPane();
		keyGrid.setPadding(new Insets(0, 0, 0, 50));
     	keyGrid.add(new Label("Up : "), 0, 0);
     	keyGrid.add(_k_up, 1, 0);

     	keyGrid.add(new Label("Down : "), 0, 1);
     	keyGrid.add(_k_down, 1, 1);

     	keyGrid.add(new Label("Left : "), 0, 2);
     	keyGrid.add(_k_left, 1, 2);

     	keyGrid.add(new Label("Right : "), 0, 3);
     	keyGrid.add(_k_right, 1, 3);

		getChildren().addAll(head, keyGrid);
	}

	public void update()
	{
		_name = tf.getText();
		_keys[0] = _k_up.getText();
		_keys[1] = _k_down.getText();
		_keys[2] = _k_left.getText();
		_keys[3] = _k_right.getText();
	}
}