package View.Views;

import Controller.Controller;
import Exceptions.EmptyFieldException;
import Model.Entities.VotingBox;
import Model.Validation;
import View.AlertBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import java.util.ArrayList;
import java.util.List;

public class VotingBoxView extends AbstractView {

    public VotingBoxView(ListView votingBoxes) {
        form.put("Address" , new TextField());
        form.put("Voting Box Type" , new ChoiceBox<>());

        this.list = votingBoxes;

        createView();
        createChoiceBox();
    }

    //================================================

    private void createChoiceBox() {
        ChoiceBox<VotingBox.BoxType> boxType = ((ChoiceBox)form.get("Voting Box Type"));

        boxType.setTooltip(new Tooltip("Select Vote Box Type"));

        List boxTypesName = List.of(VotingBox.BoxType.values());
        for (int i = 0; i < boxTypesName.size(); i++)
            boxType.getItems().add((VotingBox.BoxType) boxTypesName.get(i));

        boxType.setValue((VotingBox.BoxType)boxTypesName.get(0));
    }

    //================================================

    public void checkCriteria(Controller cont) {
        try {
            ChoiceBox<VotingBox.BoxType> boxType = ((ChoiceBox)form.get("Voting Box Type"));

            String addressStr = ((TextField)form.get("Address")).getText();

            if(addressStr.isEmpty())
                throw new EmptyFieldException("Address Field is Empty");

            Validation.name(addressStr, "Address");
            VotingBox.BoxType tmpBoxType = boxType.getValue();
            int id = cont.getView().getVotingBoxes().getItems().size() + 1;
            VotingBox voteBox = new VotingBox(id, addressStr, new ArrayList(),tmpBoxType);

            if(cont.add(voteBox))
                added.setVisible(true);

        } catch(NullPointerException e) {
            AlertBox.display("Field Exception","Voting Box Type Field is Empty");
        } catch(EmptyFieldException e) {
            AlertBox.display("Field Exception",e.getMessage());
        }
    }
}
