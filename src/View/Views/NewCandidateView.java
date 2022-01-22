package View.Views;

import Controller.Controller;
import Exceptions.AgeException;
import Exceptions.EmptyFieldException;
import Exceptions.IdException;
import Exceptions.OutOfBoundsNumberException;
import Model.Entities.Candidate;
import Model.Entities.Citizen;
import Model.MethodsUtil;
import Model.Validation;
import View.AlertBox;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class NewCandidateView extends AbstractView {

    public NewCandidateView(ListView candidates) {
        form.put("First Name",new TextField());
        form.put("Last Name",new TextField());
        form.put("Id",new TextField());
        form.put("Year of birth",new TextField());
        form.put("Party Name",new ChoiceBox<>());
        form.put("Insulation",new CheckBox());
        form.put("Number Of Sick Days",new TextField());

        list = candidates;

        createView();

        formGrid.getChildren().get(6).setVisible(false);

        CheckBox insulation = ((CheckBox)form.get("Insulation"));
        insulation.setOnAction(event -> formGrid.getChildren().get(6).setVisible(insulation.isSelected()));
    }

    //================================================

    public void checkCriteria(Controller cont) {
        try {
            String firstName = ((TextField)form.get("First Name")).getText().replaceAll(" ", "");
            String lastName = ((TextField)form.get("Last Name")).getText().replaceAll(" ", "");
            String strId = ((TextField)form.get("Id")).getText().replaceAll(" ", "");
            int birthYear = Integer.parseInt(((TextField)form.get("Year of birth")).getText().replaceAll(" ", ""));
            String partyName = ((ChoiceBox)form.get("Party Name")).getSelectionModel().getSelectedItem().toString();
            boolean insulation = ((CheckBox)form.get("Insulation")).isSelected();
            int sickDaysNum = 0;

            if(insulation)
                sickDaysNum = Integer.parseInt(((TextField)form.get("Number Of Sick Days")).getText().replaceAll(" ", ""));

            Validation.validateInput(firstName +" "+ lastName, strId, birthYear, sickDaysNum);

            if(((ChoiceBox<String>)form.get("Party Name")).getSelectionModel().isEmpty())
                throw new EmptyFieldException("Please Choose a Political Party");

            Citizen ctzn = new Citizen(firstName, lastName, strId, birthYear, insulation, false, sickDaysNum);
            MethodsUtil.setCorrectVotingBox(ctzn, cont.getModel().getVotingBoxs());
            Candidate candidate = new Candidate(ctzn, partyName);

            if(cont.add(ctzn)) {
                int partyId = MethodsUtil.addToPartyCandidateList(cont, candidate, partyName);
                candidate.setPartyId(partyId);
                if (cont.add(candidate))
                    added.setVisible(true);
            }
        } catch(IdException e) {
            AlertBox.display("Id Exception",e.getMessage());
        } catch(AgeException e ) {
            AlertBox.display("Age Exception",e.getMessage());
        } catch(OutOfBoundsNumberException e ) {
            AlertBox.display("Voting Box Error",e.getMessage());
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
