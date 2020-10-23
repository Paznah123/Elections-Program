package View;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GUIMethods {
	
	public static DropShadow getDropShadow(Color color) {
		DropShadow tmpDropShadow = new DropShadow();
		
		tmpDropShadow.setRadius(5.0);
		tmpDropShadow.setOffsetX(3.0);
		tmpDropShadow.setOffsetY(3.0);
		tmpDropShadow.setColor(color);
		
		return tmpDropShadow;
	}

// ---------------------------------------------------------------------------

	public static Label initLabel(Label label, int fontSize, Color color) {
		label.setEffect(getDropShadow(color));
		label.setFont(new Font("Cambria",fontSize));
		label.setAlignment(Pos.BASELINE_CENTER);
		
		return label;
	}

	public static void initRadioButtonText(RadioButton section, int fontSize, Color color) {
		section.setTextFill(color);
		section.setFont(new Font("Cambria",fontSize));
	}

// ---------------------------------------------------------------------------

	public static void setGridLocation(GridPane grid, int translateY, int translateX, int Hgap, int Vgap) {
		grid.setTranslateY(translateY);
		grid.setTranslateX(translateX);
		grid.setHgap(Hgap);
		grid.setVgap(Vgap);	
	}

	public static void setNodeScaleLocation(Node node, double scaleY, double scaleX, int translateY, int translateX) {
		node.setTranslateY(translateY);
		node.setTranslateX(translateX);
		node.setScaleY(scaleY);
		node.setScaleX(scaleX);	
	}
	
// ---------------------------------------------------------------------------

	public static Node addDataFieldCreation(String data, Node node) {
		GridPane grid = new GridPane();
		Label dataLabel = new Label(data);
		if(!(node instanceof CheckBox)) {
			grid.add(dataLabel, 0, 0);
			grid.add(node,0, 1);
		} else {
			grid.add(dataLabel, 0, 0);
			grid.add(node,1, 0);	
		}
		return grid;
	}

// ---------------------------------------------------------------------------
	
}
