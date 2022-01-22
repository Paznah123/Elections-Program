package View.Views;

import Controller.Controller;
import Exceptions.AgeException;
import Exceptions.EmptyFieldException;
import Exceptions.IdException;
import Exceptions.OutOfBoundsNumberException;
import Model.Entities.Citizen;
import Model.MethodsUtil;
import Model.Validation;
import View.AlertBox;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CitizenView extends AbstractView {

    public CitizenView(ListView citizens) {
        form.put("First Name",new TextField());
        form.put("Last Name",new TextField());
        form.put("Id",new TextField());
        form.put("Year of birth",new TextField());
        form.put("Carrying a Weapon",new CheckBox());
        form.put("Insulation",new CheckBox());
        form.put("Number Of Sick Days",new TextField());

        list = citizens;

        createView();

        formGrid.getChildren().get(6).setVisible(false);

        CheckBox insulation = ((CheckBox)form.get("Insulation"));
        insulation.setOnAction(event -> formGrid.getChildren().get(6).setVisible(insulation.isSelected()));
    }

    //================================================

    public void checkCriteria(Controller cont) {
        try {
            if(((TextField)form.get("Year of birth")).getText().isEmpty())
                throw new AgeException("Please Enter Year Of Birth");

            String firstName = ((TextField)form.get("First Name")).getText().replaceAll(" ", "");
            String lastName = ((TextField)form.get("Last Name")).getText().replaceAll(" ", "");
            String strId = ((TextField)form.get("Id")).getText().replaceAll(" ", "");
            int birthYear = Integer.parseInt(((TextField)form.get("Year of birth")).getText().replaceAll(" ", ""));
            boolean carryWeapon = ((CheckBox)form.get("Carrying a Weapon")).isSelected();
            boolean insulation = ((CheckBox)form.get("Insulation")).isSelected();
            int sickDaysNum = 0;

            if(insulation)
                sickDaysNum = Integer.parseInt(((TextField)form.get("Number Of Sick Days")).getText().replaceAll(" ", ""));

            Validation.validateInput(firstName +" "+ lastName, strId, birthYear, sickDaysNum);

            Citizen citizen = new Citizen(firstName, lastName, strId, birthYear, insulation, carryWeapon, sickDaysNum);
            MethodsUtil.setCorrectVotingBox(citizen, cont.getModel().getVotingBoxs());

            if(cont.add(citizen)) {
                cont.getView().getOnlyCitizens().getItems().add(citizen);
                cont.getModel().getOnlyCitizens().add(citizen);
                added.setVisible(true);
            }

        } catch (IdException e) {
            AlertBox.display("Id Exception", e.getMessage());
        } catch (AgeException e) {
            AlertBox.display("Age Exception", e.getMessage());
        } catch (NumberFormatException e) {
            AlertBox.display("Error", "Please enter valid values");
        } catch (OutOfBoundsNumberException e) {
            AlertBox.display("Sick Days Error", "Please Fill in valid values");
        } catch (IllegalArgumentException e) {
            AlertBox.display("Error", e.getMessage());
        } catch (EmptyFieldException e) {
            AlertBox.display("Empty Name Exception", e.getMessage());
        }
    }
}
