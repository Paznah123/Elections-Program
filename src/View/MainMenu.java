package View;

import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MainMenu {
	
	private AnchorPane mainMenu;
	private Accordion mainMenuAcc;

	private TitledPane register;
	private TitledPane display;
	private TitledPane elections;
	
	private  Button addCitizen = new Button("Citizen");
	private  Button addCandidate = new Button("Candidate");
	private  Button addVoteBox = new Button("Voting Box");
	private  Button addPolitiParty = new Button("Political Party");
	
	private  Button dispCitizen = new Button("Citizens");
	private  Button dispCandidate = new Button("Candidates");
	private  Button dispVoteBox = new Button("Voting Boxes");
	private  Button dispPolitiParty = new Button("Political Parties");
	
	private  Button runProgram = new Button("Run Program");
	private  Button dispRes = new Button("Display Results");
	private  Button exit = new Button("Exit");
	
	private  Button reset = new Button("Reset");
	
	private Button[] buttons = {addCitizen,addCandidate,addVoteBox,addPolitiParty,
			dispCitizen,dispCandidate,dispVoteBox,dispPolitiParty,
				runProgram,dispRes};

//---------------------------------------------------------------------------------------------------------------------

	public MainMenu() {
		this.mainMenu = new AnchorPane();
		this.mainMenuAcc = new Accordion();
		this.register = new TitledPane();
		this.display = new TitledPane();
		this.elections = new TitledPane();
		
		mainMenu = createMenu();
	}

//---------------------------------------------------------------------------------------------------------------------

	private AnchorPane createMenu() {
		
		mainMenu.setPrefHeight(50);
		mainMenu.setPrefWidth(50);
		
		fillMenuSection("Register", register, buttons, 0, 3);
		fillMenuSection("Display", display, buttons, 4, 7);
		fillMenuSection("Elections", elections, buttons, 8, 9);

		VBox vbox = new VBox(reset, exit);
		vbox.setSpacing(5);
	
		mainMenuAcc.getPanes().addAll(register, display, elections);
		mainMenu.getChildren().addAll(mainMenuAcc, vbox);

		GUIMethods.setNodeScaleLocation(vbox, 1.15, 1.15, 390, 10);
		
		return mainMenu;
		
	}

	private TitledPane fillMenuSection(String title, TitledPane titledPane, Button[] buttons, int startIndex, int endIndex) {
		titledPane.setText(title);
		GridPane grid = new GridPane();
		
		for (int i = startIndex; i <= endIndex; i++) {
			grid.add(buttons[i], 0, i);
		}
		
		grid.setVgap(2);
		titledPane.setContent(grid);
		titledPane.setCollapsible(true);
		
		return titledPane;
	}

//---------------------------------------------------------------------------------------------------------------------
	
	public AnchorPane getMainMenu() {
		return mainMenu;
	}
	
	public  Button getExit() {
		return exit;
	}
	
	 public Button getReset() {
		return reset;
	}
	 
//---------------------------------------------------------------------------------------------------------------------

	public  Button getAddCitizen() {
		return addCitizen;
	}


	public  Button getAddCandidate() {
		return addCandidate;
	}


	public  Button getAddVoteBox() {
		return addVoteBox;
	}

	public  Button getAddPolitiParty() {
		return addPolitiParty;
	}

//---------------------------------------------------------------------------------------------------------------------

	public  Button getDispCitizen() {
		return dispCitizen;
	}

	public Button getDispCandidate() {
		return dispCandidate;
	}
	
	public  Button getDispVoteBox() {
		return dispVoteBox;
	}


	public  Button getDispPolitiParty() {
		return dispPolitiParty;
	}

//---------------------------------------------------------------------------------------------------------------------

	public  Button getRunProgram() {
		return runProgram;
	}


	public  Button getDispRes() {
		return dispRes;
	}

}
