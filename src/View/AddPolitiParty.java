package View;

import java.time.LocalDate;

import Model.Model;
import Model.PoliticalParty;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AddPolitiParty {
	
	private TextField partyName;
	private DatePicker dateparty;

	private final ToggleGroup radioGroup = new ToggleGroup();
	private RadioButton left = new RadioButton("Left");
	private RadioButton center = new RadioButton("Center");
	private RadioButton right = new RadioButton("Right");
	private VBox radioBox;

	private GridPane mainGrid;

	private Button confirm = new Button("Confirm");
	private Text successfullyAdded = new Text("Party added successfully");


// 	--------------------------------------------------------------------

	public AddPolitiParty() {
		this.partyName = new TextField();
		this.dateparty = new DatePicker();
		
		this.radioBox = createSectionTG();
		this.mainGrid = createAddNode();
	}

	public GridPane getMainGrid() {
		return mainGrid;
	}
	
	public Button getConfirm() {
		return confirm;
	}
	
// 	--------------------------------------------------------------------
	
	// case 4	
	public GridPane createAddNode() {
		mainGrid = new GridPane();
		GUIMethods.setGridLocation(mainGrid, 10, 10, 20, 20);
		
		mainGrid.add(GUIMethods.addDataFieldCreation("Enter Party Name:",partyName), 0, 0);
		mainGrid.add(radioBox, 0, 1);
		mainGrid.add(GUIMethods.addDataFieldCreation("Enter Creation Date: (YYYY-MM-DD)",dateparty), 0, 2);
		mainGrid.add(successfullyAdded, 0, 3);

		mainGrid.add(confirm, 1, 3);

		successfullyAdded.setFill(Color.CORNFLOWERBLUE);
		successfullyAdded.setVisible(false);
		
		return mainGrid;
	}
	
	private VBox createSectionTG() {
		radioBox = new VBox();
		
		left.setSelected(true);
		left.setToggleGroup(radioGroup);
		center.setToggleGroup(radioGroup);
		right.setToggleGroup(radioGroup);
		
		GUIMethods.initRadioButtonText(left, 15, Color.DARKBLUE);
		GUIMethods.initRadioButtonText(center, 15, Color.DARKBLUE);
		GUIMethods.initRadioButtonText(right, 15, Color.DARKBLUE);
		
		radioBox.setSpacing(10);
		radioBox.getChildren().addAll(left, center, right);
		
		return radioBox;
	}

// 	--------------------------------------------------------------------
	
	public void checkCriteria(Model model) {
		try {
			if(partyName.getText().isEmpty())
				throw new IllegalArgumentException();
			LocalDate localDate = LocalDate.parse(dateparty.getValue().toString());
			
			RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();
			String toogleGroupValue = selectedRadioButton.getText();
			
			PoliticalParty tmpParty = new PoliticalParty(partyName.getText(),toogleGroupValue, localDate);
			if(model.addPolitiParty(tmpParty)) {
				RunElections.addNewParty(tmpParty.getNamePoliticalParty());
				successfullyAdded.setVisible(true);
			}
		} catch(NullPointerException e ) {
			AlertBox.display("Error", "Please Pick Creation Date");
		} catch(IllegalArgumentException e) {
			AlertBox.display("Error", "Please Fill In Party Name");
		}
	}
	
// 	--------------------------------------------------------------------

}
