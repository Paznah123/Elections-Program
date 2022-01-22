package View.Views;

import Controller.Controller;
import Model.Entities.PoliticalParty;
import View.AlertBox;
import View.GUIMethods;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.util.ArrayList;

public class PoliticalPartyView extends AbstractView {

    private final ToggleGroup radioGroup;
    private VBox radioBox = new VBox();

    //================================================

    public PoliticalPartyView(ListView parties) {
        radioGroup = new ToggleGroup();
        createSectionTG();

        form.put("Party Name", new TextField());
        form.put("RadioButtons",radioBox);
        form.put("Creation Date", new DatePicker());

        list = parties;
        createView();
    }

    //================================================

    private void createSectionTG() {

        RadioButton[] radioButtons = new RadioButton[]{
                new RadioButton("Left"),
                new RadioButton("Center"),
                new RadioButton("Right")
        };

        radioButtons[0].setSelected(true);

        for (RadioButton btn: radioButtons) {
            btn.setToggleGroup(radioGroup);
            GUIMethods.initRadioButtonText(btn, 15, Color.DARKBLUE);
        }

        radioBox.setSpacing(10);
        radioBox.getChildren().addAll(radioButtons);
    }

    //================================================

    public void checkCriteria(Controller cont) {
        try {
            TextField partyName = ((TextField)form.get("Party Name"));
            String partyDate = ((DatePicker)form.get("Creation Date")).getValue().toString();

            if(partyName.getText().isEmpty())
                throw new IllegalArgumentException();

            LocalDate localDate = LocalDate.parse(partyDate);
            RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();
            PoliticalParty.Section section = PoliticalParty.Section.valueOf(selectedRadioButton.getText());

            PoliticalParty tmpParty = new PoliticalParty(partyName.getText(),section, localDate, new ArrayList<>());

            if(cont.add(tmpParty)) {
                cont.getElectionView().getRunElections().addNewParty(tmpParty.getName());
                cont.getElectionView().getElectionResults().initPartiesMap(cont);
                added.setVisible(true);
            }

        } catch(NullPointerException e ) {
            AlertBox.display("Error", "Please Pick Creation Date");
        } catch(IllegalArgumentException e) {
            AlertBox.display("Error", "Please Fill In Party Name");
        }
    }

    //================================================

}
