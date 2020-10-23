package View;

import Exceptions.*;
import Model.Citizen;
import Model.Model;
import Model.MethodsUtil;
import Model.Validation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AddCitizen {

	private int sickDaysNum = 0;
	private TextField name;
	private TextField id;
	private TextField year;
	private CheckBox carryWeapon;
	private CheckBox insulation;
	private TextField sickDays;

	private GridPane mainGrid;

	private Button confirm = new Button("Confirm");
	private Text successfullyAdded = new Text("Citizen added");

// -----------------------------------------------------------------------------------

	public AddCitizen() {
		this.name = new TextField();
		this.id = new TextField();
		this.year = new TextField();
		this.carryWeapon = new CheckBox();
		this.insulation=  new CheckBox();
		this.sickDays = new TextField();
		
		this.mainGrid = createAddNode();	
	}

	public GridPane getMainGrid() {
		return mainGrid;
	}
	
	public Button getConfirm() {
		return confirm;
	}

// -----------------------------------------------------------------------------------

	public GridPane createAddNode() {
		mainGrid = new GridPane();
		mainGrid.setPadding(new Insets(10, 10, 10, 10));
		
		GUIMethods.setGridLocation(mainGrid, 0, 0, 20, 20);
		successfullyAdded.setFill(Color.CORNFLOWERBLUE);
		successfullyAdded.setVisible(false);
		
		mainGrid.add(GUIMethods.addDataFieldCreation("Full Name:",name), 0, 0);
		mainGrid.add(GUIMethods.addDataFieldCreation("Id:",id), 0, 1);
		mainGrid.add(GUIMethods.addDataFieldCreation("Year Of Birth:",year), 0, 2);
		mainGrid.add(GUIMethods.addDataFieldCreation("Carrying a Weapon? ",carryWeapon), 0, 3);
		mainGrid.add(GUIMethods.addDataFieldCreation("Insulation? ",insulation), 0, 4);
		mainGrid.add(GUIMethods.addDataFieldCreation("Number Of Sick Days?",sickDays), 0, 5);
		
		mainGrid.getChildren().get(5).setVisible(false);
		
		insulation.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (insulation.isSelected()) {
					mainGrid.getChildren().get(5).setVisible(true);
				}
				if (!insulation.isSelected()) {
					mainGrid.getChildren().get(5).setVisible(false);
				}
			}

		});

		mainGrid.add(confirm, 1, 6);
		mainGrid.add(successfullyAdded , 0, 6);

		return mainGrid;
	}

// -----------------------------------------------------------------------------------

	public void checkCriteria(Model model) {
		try {
			Validation.validName(name.getText().replaceAll(" ", ""), "Name");
			Validation.validId(id.getText().replaceAll(" ", ""));
			Validation.checkIdExists(model.getListOfVotingBoxs(), id.getText().replaceAll(" ", ""));
			if(year.getText().isEmpty())
				throw new AgeException("Please Enter Year Of Birth");
			Validation.validAge(Integer.parseInt(year.getText().replaceAll(" ", "")));
			if(insulation.isSelected())
				Validation.checkValidNumberOfSickDays(Integer.parseInt(sickDays.getText().replaceAll(" ", "")));
			
			Citizen citizen = new Citizen(name.getText().replaceAll(" ", ""), id.getText().replaceAll(" ", ""), Integer.parseInt(year.getText().replaceAll(" ", "")),
					insulation.isSelected(), carryWeapon.isSelected(), sickDaysNum);

			MethodsUtil.setCorrectVotingBox(citizen, model.getListOfVotingBoxs());
			model.addCitizen(citizen);
			successfullyAdded.setVisible(true);
			
		} catch (IdException e) {
			AlertBox.display("Id Exception", e.getMessage());
		} catch (FoundInListsException e) {
			AlertBox.display("Found In Lists Exception", e.getMessage());
		} catch (AgeException e) {
			AlertBox.display("Age Exception", e.getMessage());
		} catch (NumberFormatException e) {
			AlertBox.display("Error", "Please Fill in Sick Days");
		} catch (OutOfBoundsNumberException e) {
			AlertBox.display("Sick Days Error", "Please Fill in valid values");
		} catch (IllegalArgumentException e) {
			AlertBox.display("Error", e.getMessage());
		} catch (StringIndexOutOfBoundsException e) {
			AlertBox.display("Empty Name Exception", "Please enter a Name");
		}

	}
}
