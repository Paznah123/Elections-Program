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

    //================================================

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

    //================================================

    public static void setGridLocation(GridPane grid, int translateY, int translateX, int Hgap, int Vgap) {
        grid.setTranslateY(translateY);
        grid.setTranslateX(translateX);
        grid.setHgap(Hgap);
        grid.setVgap(Vgap);
    }

    //================================================

    public static Node addDataFieldCreation(String data, Node node) {
        int i, j;

        GridPane grid = new GridPane();
        Label dataLabel = new Label(data);

        if(!(node instanceof CheckBox)) { i = 0; j = 1; }
        else 							{ i = 1; j = 0; }

        grid.add(dataLabel, 0, 0);
        grid.add(node,i, j);

        return grid;
    }

    //================================================
}
