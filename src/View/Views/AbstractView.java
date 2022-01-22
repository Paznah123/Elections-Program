package View.Views;

import View.GUIMethods;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.LinkedHashMap;
import java.util.Map;

public class AbstractView {

    GridPane mainGrid;
    GridPane confirmGrid;
    GridPane formGrid;
    GridPane listGrid;

    Button confirm;
    Text added;
    LinkedHashMap<String, Object> form;

    ListView list;

    //================================================

    public AbstractView() {
        mainGrid = new GridPane();
        confirmGrid = new GridPane();
        formGrid = new GridPane();
        listGrid = new GridPane();

        confirm = new Button("Confirm");
        added = new Text("Added");
        form = new LinkedHashMap<>();
        list = new ListView();

        added.setFill(Color.CORNFLOWERBLUE);
        added.setVisible(false);

        confirmGrid.add(confirm,0,0);
        confirmGrid.add(added,1,0);
        confirmGrid.setHgap(10);

    }

    //================================================

    void createView() {
        GUIMethods.setGridLocation(formGrid, 5, 5, 20, 15);
        GUIMethods.setGridLocation(listGrid, 5, 5, 20, 15);

        int i = 0;
        for (Map.Entry<String, Object> entry : form.entrySet()){
            formGrid.add(GUIMethods.addDataFieldCreation(entry.getKey(), (Node) entry.getValue()), 0, i);
            i++;
        }
        formGrid.add(confirmGrid, 0, i);

        listGrid.add(list,0,0);
        listGrid.setPadding(new Insets(5,10,10,10));

        mainGrid.add(formGrid,0,0);
        mainGrid.add(listGrid,1,0);
    }

    //================================================

    public GridPane getMainGrid() { return mainGrid; }

    public Button getConfirm() { return confirm; }
}
