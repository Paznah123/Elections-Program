package View.Views;

import Controller.Controller;
import Model.Entities.Citizen;
import Model.Entities.PoliticalParty;
import Model.Model;
import View.AlertBox;
import View.GUIMethods;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.List;

public class RunElections {

	private GridPane mainGrid;
	private VBox vbox;
	private ToggleGroup partyNameToggleGroup = new ToggleGroup();

	private Button nextButton = new Button("Next");
	private CheckBox protectionSuit = new CheckBox("Yes");

	private Text citizenDetails;
	private int citizenIndex = 0;

	//================================================
	
	public RunElections(Controller cont) {
		createPartyNamesRadioButtons(cont.getModel());
		createAddNode(cont.getModel());

		nextButton.setOnAction(e -> checkCriteria(cont));
	}

	public GridPane getMainGrid() {
		return mainGrid;
	}

	//================================================
	
	public void createAddNode(Model model) {
		mainGrid = new GridPane();

		mainGrid.setPadding(new Insets(10, 10, 10, 10));

		mainGrid.setHgap(10);
		mainGrid.setVgap(10);
		
		nextButton.setVisible(true);

		Citizen ctzn = (Citizen) model.getCitizens().get(citizenIndex);
		citizenDetails = new Text(ctzn.getFirstName() +" "+ ctzn.getLastName() + ", " + ctzn.getId());

		mainGrid.add(citizenDetails, 0, 0);
		mainGrid.add(GUIMethods.addDataFieldCreation("Did You Bring Protection Suit? ", protectionSuit), 0, 1);
		mainGrid.add(GUIMethods.initLabel(new Label("Select a Party:"), 15, Color.DARKBLUE), 0, 2);
		mainGrid.add(vbox, 0, 3);
		mainGrid.getChildren().get(1).setVisible(false);
		
		protectionSuit.setOnAction(e -> vbox.setVisible(protectionSuit.isSelected()));

		mainGrid.add(nextButton, 0,4);
	}

	private void createPartyNamesRadioButtons(Model model) {
		vbox = new VBox();
		List parties = model.getPoliticalParties();

		addNewParty("White Vote");
		for (int i = 0; i < parties.size(); i++)
			addNewParty(((PoliticalParty)parties.get(i)).getName());

		vbox.setSpacing(5);
	}

	public void addNewParty(String politiPartyName) {
		RadioButton rb = new RadioButton(politiPartyName);
		GUIMethods.initRadioButtonText(rb, 15, Color.BLACK);
		rb.setToggleGroup(partyNameToggleGroup);
		vbox.getChildren().add(rb);
	}

	private void endElections() {
		citizenIndex = 0;
		citizenDetails.setVisible(false);
		nextButton.setVisible(false);
		mainGrid.getChildren().get(1).setVisible(false);
	}

	//================================================

	public void checkCriteria(Controller cont) {
		try {
			if(mainGrid.getChildren().get(1).isVisible() && !protectionSuit.isSelected()) 
				throw new IllegalArgumentException("Please Bring your Protection Suit!");

			Citizen ctzn = (Citizen) cont.getModel().getCitizens().get(citizenIndex);

			RadioButton selectedRadioButton = (RadioButton) partyNameToggleGroup.getSelectedToggle();
			String selectedParty = selectedRadioButton.getText();

			ctzn.setVotedFor(selectedParty);

			if (citizenIndex != cont.getModel().getCitizens().size() - 1)
				citizenIndex++;
			else {
				endElections();
				cont.saveElections();
			}

			ctzn = (Citizen) cont.getModel().getCitizens().get(citizenIndex);
			citizenDetails.setText(ctzn.getFirstName() +" "+ ctzn.getLastName() +", "+ ctzn.getId());
			
			if(ctzn.getInsulation()) mainGrid.getChildren().get(1).setVisible(true);

		} catch (IllegalArgumentException e) {
			AlertBox.display("Protection Suit Exception", e.getMessage());
		} catch (NullPointerException e) {
			AlertBox.display("Error", "Please Choose a Party");
		}
	}
}
