package View;

import java.util.ArrayList;

import Exceptions.EmptyFieldException;
import Model.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AddVoteBox {

	private TextField adress;
	private ChoiceBox<VotingBox.boxType> boxType;
	
	private GridPane mainGrid;
	private Text successfullyadded = new Text("Voting Box added");
	private boolean loadChoiceBox=false;
	
	private Button confirm = new Button("Confirm");

//---------------------------------------------------------------------
	
	public AddVoteBox(Model model) {
		this.adress = new TextField();
		this.boxType = new ChoiceBox<VotingBox.boxType>();
		
		this.mainGrid = createAddNode(model);
	}
	
	public GridPane getMainGrid() {
		return mainGrid;
	}
	
	public Button getConfirm() {
		return confirm;
	}
	
//---------------------------------------------------------------------

	private GridPane createAddNode(Model model) {
		mainGrid = new GridPane();

		mainGrid.setLayoutX(50);
		mainGrid.setLayoutY(50);
		GUIMethods.setGridLocation(mainGrid, -10, -10, 20, 20);

		mainGrid.add(GUIMethods.addDataFieldCreation("Enter Adress:",adress), 1, 1);
		
		createChoiceBox(model);
		
		mainGrid.add(GUIMethods.addDataFieldCreation("Enter Voting Box Type:",boxType), 1, 2);
		mainGrid.add(confirm, 2, 3);
		
		successfullyadded.setVisible(false);
		successfullyadded.setFill(Color.CORNFLOWERBLUE);
		mainGrid.add(successfullyadded, 1, 3);
		
		return mainGrid;
	}
	
	private void createChoiceBox(Model model) {
		successfullyadded.setFill(Color.CORNFLOWERBLUE);
		successfullyadded.setVisible(false);
		if(!loadChoiceBox) {
			boxType.setTooltip(new Tooltip("Select Vote Box Type"));
			ArrayList<VotingBox.boxType> boxTypesName= MethodsUtil.getAllTheTypesOfVotingBox();
			for (int i = 0; i <boxTypesName.size(); i++) {
				boxType.getItems().add(boxTypesName.get(i));
			}
			loadChoiceBox=true;
		}
	}
	
//---------------------------------------------------------------------
	
	public void checkCriteria(Model model) {
		try {
			if(adress.getText().isEmpty()) 
				throw new EmptyFieldException("Adress Field is Empty");
			Validation.validName(adress.getText(), "Address");
			VotingBox.boxType tmpBoxType = boxType.getValue();
			
			VotingBox<Citizen> voteBox = new VotingBox<Citizen>(adress.getText(),tmpBoxType);
		
			if(model.addVotingBox(voteBox))
				successfullyadded.setVisible(true);
		} catch(NullPointerException e) {
			AlertBox.display("Field Exception","Voting Box Type Field is Empty");
		} catch(EmptyFieldException e) {
			AlertBox.display("Field Exception",e.getMessage());
		}
	}
}
