package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

import Exceptions.*;
import Model.Candidate;
import Model.Citizen;
import Model.Model;
import Model.MethodsUtil;
import Model.Validation;

public class AddCandidate {
	
	private int sickDaysNum = 0;
	private TextField name;
	private TextField newId;
	private TextField existId;
	private TextField year;
	private CheckBox insulation;
	private TextField sickDays;

	private  Button newCand = new Button("New Candidate");
	private  Button existCand = new Button("Existing Candidate");
	private  Button newConfirm = new Button("Confirm");
	private  Button existConfirm = new Button("Confirm");
	
	private ChoiceBox<String> newPartyName = new ChoiceBox<String>();
	private ChoiceBox<String> existPartyName = new ChoiceBox<String>();
	private Text successfullyAddedNew = new Text("Candidate added");
	private Text successfullyAddedExist = new Text("Candidate added");

	private boolean loadChoiceBox=false;

	private ArrayList<TextField> tfArr;

	private GridPane mainGrid;
	private GridPane newCandGrid;
	private GridPane existCandGrid;

//---------------------------------------------------------------------------------------------------------------------

	public AddCandidate(Model model) {
		this.name = new TextField();
		this.newId = new TextField();
		this.existId = new TextField();
		this.year = new TextField();
		this.insulation=  new CheckBox();
		this.sickDays = new TextField();
		this.newCandGrid = addNewCandidateGUI(model);
		this.existCandGrid = addExistingCandidateGUI(model);
		this.mainGrid = chooseNewOrExist();	
		createTextFieldArray();
	}
	
//---------------------------------------------------------------------------------------------------------------------

	public GridPane chooseNewOrExist() {
		mainGrid = new GridPane();
		mainGrid.add(newCand,0,0);
		mainGrid.add(existCand,0,1);
		GUIMethods.setGridLocation(mainGrid, 10, 10, 20, 20);

		mainGrid.setPadding(new Insets(10,10,10,10));
		
		return mainGrid;
	}
	
	public GridPane addNewCandidateGUI(Model model) {
		newCandGrid = new GridPane();
		GUIMethods.setGridLocation(newCandGrid, 10, 10, 20, 20);
		
		newCandGrid.add(GUIMethods.addDataFieldCreation("Full Name:",name), 0, 0);
		newCandGrid.add(GUIMethods.addDataFieldCreation("Id:",newId), 0, 1);
		newCandGrid.add(GUIMethods.addDataFieldCreation("Year Of Birth:",year), 0, 2);
		newCandGrid.add(GUIMethods.addDataFieldCreation("Political Party Name:",newPartyName), 0, 3);
		newCandGrid.add(GUIMethods.addDataFieldCreation("Insulation? ",insulation), 0, 4);
		newCandGrid.add(GUIMethods.addDataFieldCreation("Number Of Sick Days?",sickDays), 0, 5);

		createChoiceBox(model);
		
		newCandGrid.getChildren().get(5).setVisible(false);
		
		insulation.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(insulation.isSelected()) {
					newCandGrid.getChildren().get(5).setVisible(true);
				}
				if(!insulation.isSelected()) {
					newCandGrid.getChildren().get(5).setVisible(false);
				}
			}
			
		});

		newCandGrid.add(newConfirm, 1, 6);
		newCandGrid.add(successfullyAddedNew , 0, 6);
		
		return newCandGrid;
	}
		
	public GridPane addExistingCandidateGUI(Model model) {
		existCandGrid = new GridPane();
		GUIMethods.setGridLocation(existCandGrid, 20, 20, 10, 5);
		
		existCandGrid.add(GUIMethods.addDataFieldCreation("Id:",existId), 0, 0);
		existCandGrid.add(GUIMethods.addDataFieldCreation("Party Name:",existPartyName), 0,1);
		
		createChoiceBox(model);

		HBox hbox = new HBox(model.getDisplay().getOnlyCitizensList());
		GridPane mainPane = new GridPane();
		mainPane.add(hbox,0,0);
		mainPane.add(existCandGrid,1,0);
		
		existCandGrid.add(existConfirm, 0, 4);
		existCandGrid.add(successfullyAddedExist, 0, 5);
		GUIMethods.setGridLocation(mainPane, 20, 0, 10, 5);

		return mainPane;
	}
	
//---------------------------------------------------------------------------------------------------------------------
	
	private void createChoiceBox(Model model) {
		successfullyAddedNew.setFill(Color.CORNFLOWERBLUE);
		successfullyAddedNew.setVisible(false);
		successfullyAddedExist.setFill(Color.CORNFLOWERBLUE);
		successfullyAddedExist.setVisible(false);
		if(loadChoiceBox!=true) {
			newPartyName.setTooltip(new Tooltip("Select the Political Party"));
			existPartyName.setTooltip(new Tooltip("Select the Political Party"));
			for (int i = 0; i <model.getListOfPoliticalParties().size(); i++) {
				newPartyName.getItems().add(model.getListOfPoliticalParties().get(i).getNamePoliticalParty());
				existPartyName.getItems().add(model.getListOfPoliticalParties().get(i).getNamePoliticalParty());
			}
			loadChoiceBox=true;
		}
	}

	private void createTextFieldArray() {
		tfArr = new ArrayList<>();
		tfArr.add(name);
		tfArr.add(newId);
		tfArr.add(year);
	}
	
//---------------------------------------------------------------------------------------------------------------------
	
	public  void checkCriteria(String mode, Model model) {
		try {
			if(mode.equalsIgnoreCase("new")) {
				Validation.checkEmptyTextFields(tfArr);
				Validation.validName(name.getText(), "name");
				Validation.validId(newId.getText().replaceAll(" ", ""));
				Validation.validAge(Integer.parseInt(year.getText().replaceAll(" ", "")));
				if(insulation.isSelected())
					Validation.checkValidNumberOfSickDays(Integer.parseInt(sickDays.getText().replaceAll(" ", "")));
				
				if(newPartyName.getSelectionModel().isEmpty())
					throw new EmptyFieldException("Please Choose a Political Party");
				Candidate candidate = new Candidate(name.getText(),newId.getText().replaceAll(" ", ""),
													Integer.parseInt(year.getText().replaceAll(" ", "")),
													insulation.isSelected(),sickDaysNum,newPartyName.getValue());
				if(model.addCitizen(candidate)) {
					MethodsUtil.addToPartyCandidateList(model, candidate, newPartyName.getValue().toString());
					MethodsUtil.setCorrectVotingBox(candidate, model.getListOfVotingBoxs());
					successfullyAddedNew.setVisible(true);
				}
			}
			
			if(mode.equalsIgnoreCase("exist")) {
				if(existPartyName.getSelectionModel().isEmpty())
					throw new EmptyFieldException("Political Party Field is Empty");
				Validation.validId(existId.getText());
				Citizen citizen = MethodsUtil.getCitizenById(existId.getText(), model);
				
				if(MethodsUtil.citizenToCandidate(model, citizen, existPartyName.getValue())){
					MethodsUtil.setCorrectVotingBox(citizen, model.getListOfVotingBoxs());
					successfullyAddedExist.setVisible(true);
				}
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
			AlertBox.display("Not Exists Exception","Id Not Found In List!");	
		}
		
	}

//---------------------------------------------------------------------------------------------------------------------

	public GridPane getNewCandGrid() {
		return newCandGrid;
	}

	public GridPane getExistCandGrid() {
		return existCandGrid;
	}

	public GridPane getMainGrid() {
		return mainGrid;
	}
	
	public Button getNewConfirm() {
		return newConfirm;
	}

	public Button getExistConfirm() {
		return existConfirm;
	}
	
	public Button getNewCand() {
		return newCand;
	}

	public Button getExistCand() {
		return existCand;
	}
}
