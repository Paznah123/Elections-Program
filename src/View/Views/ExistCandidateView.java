package View.Views;

import Controller.Controller;
import Exceptions.EmptyFieldException;
import Exceptions.IdException;
import Model.Entities.Citizen;
import Model.MethodsUtil;
import Model.Validation;
import View.AlertBox;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ExistCandidateView extends AbstractView {

    public ExistCandidateView(ListView onlyCitizens) {
        form.put("Id", new TextField());
        form.put("Party Name", new ChoiceBox<>());

        list = onlyCitizens;

        createView();
    }

    //================================================

    public void checkCriteria(Controller cont) {
        try {
            String partyName = ((ChoiceBox)form.get("Party Name")).getSelectionModel().getSelectedItem().toString();
            String id = ((TextField)form.get("Id")).getText();

            if(partyName.isEmpty())
                throw new EmptyFieldException("Political Party Field is Empty");
            Validation.id(id);

            Citizen citizen = cont.getModel().getCitizenById(id);
            MethodsUtil.citizenToCandidate(cont,citizen, partyName);

            added.setVisible(true);

        } catch(IdException e) {
            AlertBox.display("Id Exception",e.getMessage());
        } catch (EmptyFieldException e) {
            AlertBox.display("Empty Field Error",e.getMessage());
        } catch(NumberFormatException e) {
            AlertBox.display("Error", "Please Fill in Sick Days");
        } catch(IllegalArgumentException e) {
            AlertBox.display("Error", e.getMessage());
        } catch (StringIndexOutOfBoundsException e) {
            AlertBox.display("Name Error", "Please enter a Name");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            AlertBox.display("Not Exists Exception","Id Not Found In List!");
        }
    }
}
