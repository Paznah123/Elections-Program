package View;

import Model.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class RunElections {
	
	private Button nextButton = new Button("Next");
	private CheckBox protectionSuit = new CheckBox("Yes");
	private Text citizenDetails;
	private int citizenIndex = 0;
	
	private static ToggleGroup partyNameToggleGroup;
	private static VBox vbox = new VBox();
	private GridPane mainGrid;
	
//---------------------------------------------------------------------
	
	public RunElections(Model model) {
		RunElections.vbox = createPartyNamesRadioButtons(model);
		this.mainGrid = createAddNoe(model);
	}

	public GridPane getMainGrid() {
		return mainGrid;
	}
	
	public Button getNextButton() {
		return nextButton;
	}
	
// 	--------------------------------------------------------------------
	
	public GridPane createAddNoe(Model model) {
		mainGrid = new GridPane();

		mainGrid.setPadding(new Insets(10, 10, 10, 10));

		mainGrid.setHgap(10);
		mainGrid.setVgap(10);
		
		nextButton.setVisible(true);
		
		citizenDetails = new Text(model.getListOfCitizens().get(citizenIndex).getName() +
									", " + model.getListOfCitizens().get(citizenIndex).getId());
		mainGrid.add(citizenDetails, 0, 0);
		mainGrid.add(GUIMethods.addDataFieldCreation("Did You Bring Protection Suit? ", protectionSuit), 0, 1);
		mainGrid.add(GUIMethods.initLabel(new Label("Select a Party:"), 15, Color.DARKBLUE), 0, 2);
		mainGrid.add(vbox, 0, 3);
		mainGrid.getChildren().get(1).setVisible(false);
		
		protectionSuit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (protectionSuit.isSelected()) 
						vbox.setVisible(true);				
				if (!protectionSuit.isSelected()) 
						vbox.setVisible(false);
			}

		});

		mainGrid.add(nextButton, 0,4);

		return mainGrid;

	}

	private VBox createPartyNamesRadioButtons(Model model) {
		partyNameToggleGroup = new ToggleGroup();
		vbox = new VBox();
		addNewParty("White Vote");
		for (int i = 0; i < model.getListOfPoliticalParties().size(); i++) {
			RadioButton rb = new RadioButton(model.getListOfPoliticalParties().get(i).getNamePoliticalParty());
			GUIMethods.initRadioButtonText(rb, 15, Color.BLACK);
			rb.setToggleGroup(partyNameToggleGroup);		
			vbox.getChildren().add(rb);
		}
		vbox.setSpacing(5);
		return vbox;
	}

	public static void addNewParty(String politiPartyName) {
		RadioButton rb = new RadioButton(politiPartyName);
		rb.setToggleGroup(partyNameToggleGroup);
		GUIMethods.initRadioButtonText(rb, 15, Color.BLACK);
		vbox.getChildren().add(rb);
	}
	
// 	--------------------------------------------------------------------

	public void checkCriteria(Model model) {
		try {
			if(mainGrid.getChildren().get(1).isVisible() && !protectionSuit.isSelected()) 
				throw new IllegalArgumentException("Please Bring your Protection Suit!");
			
			RadioButton selectedRadioButton = (RadioButton) partyNameToggleGroup.getSelectedToggle();
			String selectedParty = selectedRadioButton.getText();
			
			if (citizenIndex != model.getListOfCitizens().size() - 1)
				citizenIndex++;
			else {
				citizenIndex = 0;
				citizenDetails.setVisible(false);
				nextButton.setVisible(false);
				model.getElectionResults().fillPiechart(model);
			}
			citizenDetails.setText(model.getListOfCitizens().get(citizenIndex).getName() +", "+ model.getListOfCitizens().get(citizenIndex).getId());
			
			if(model.getListOfCitizens().get(citizenIndex).getInsulation())
				mainGrid.getChildren().get(1).setVisible(true);
			
			if(!selectedParty.equals("White Vote")) 
				model.getListOfCitizens().get(citizenIndex).setVotedFor(selectedParty);
		} catch (IllegalArgumentException e) {
			AlertBox.display("Protection Suit Exception", e.getMessage());
		} catch (NullPointerException e) {
			AlertBox.display("Error", "Please Choose a Party");
		}
	}

}
