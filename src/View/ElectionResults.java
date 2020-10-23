package View;

import Model.Model;
import Model.PoliticalParty;
import Model.VotingBox;

import java.util.ArrayList;

import Model.Citizen;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.GridPane;

public class ElectionResults {

	private GridPane grid;
	private PieChart pieChartData;
	private Node mainLayout;

//---------------------------------------------------------------------------------------------------------------------

	public ElectionResults(Model model) {
		this.grid = new GridPane();
		this.pieChartData = new PieChart();
		this.mainLayout = createAddNode(model);

	}

//---------------------------------------------------------------------------------------------------------------------

	private Node createAddNode(Model model) {
		pieChartData.setTitle("Election Results:");
		
		grid.setLayoutX(50);
		grid.setLayoutY(50);
		GUIMethods.setGridLocation(grid, 15, 75, 10, 10);
		
		grid.add(GUIMethods.addDataFieldCreation("",pieChartData), 1, 0);
		
		return pieChartData;
	}
	
	public void fillPiechart(Model model) {
		ArrayList<PoliticalParty> partiesList = model.getListOfPoliticalParties();
		
		for (int i = 0; i < partiesList.size(); i++) {
			int partyVotes = calculateAllVotesByParty(model.getListOfVotingBoxs(),partiesList.get(i).getNamePoliticalParty());
			pieChartData.getData().add(new PieChart.Data(partiesList.get(i).getNamePoliticalParty(),partyVotes));
		}
		
		pieChartData.getData().add(new PieChart.Data("White Votes",emptyVoteCollector(model)));

	}

	private int emptyVoteCollector(Model model) {
		int emptyVotes = 0;
		ArrayList<VotingBox<Citizen>> voteBoxList = model.getListOfVotingBoxs();
		for (int i = 0; i < voteBoxList.size(); i++) {
			ArrayList<Citizen> citizensList = voteBoxList.get(i).getListOfCitizen();
			for (int j = 0; j < citizensList.size(); j++) {
				String votedFor = citizensList.get(j).getVotedFor();
				if (votedFor == null) {
					emptyVotes++;
				}
			}
		}
		return emptyVotes;
		
	}
	
//---------------------------------------------------------------------------------------------------------------------

	private int calculateAllVotesByParty(ArrayList<VotingBox<Citizen>> listOfVotingBoxs, String namePoliticalParty) {
		int numberOfVotes = 0;
		// loops for all voting box
		for (int i = 0; i < listOfVotingBoxs.size(); i++) {
			ArrayList<Citizen> citizensList = listOfVotingBoxs.get(i).getListOfCitizen();
			numberOfVotes += partyVoteCollector(citizensList, namePoliticalParty);
		}
		return numberOfVotes;
	}
	
	private int partyVoteCollector(ArrayList<Citizen> listOfCitizenAtVotingBox, String namePoliticalParty) {
		int partyNumOfVotes = 0;
		for (int i = 0; i < listOfCitizenAtVotingBox.size(); i++) {
			String votedFor = listOfCitizenAtVotingBox.get(i).getVotedFor();
			// deals with null exception
			if (votedFor == null) {
			} // checks each citizen if he voted given party
			else if (votedFor.equalsIgnoreCase(namePoliticalParty))
				partyNumOfVotes++;
		}
		return partyNumOfVotes;
	}

//---------------------------------------------------------------------------------------------------------------------

	public Node getMainLayout() {
		return mainLayout;
	}
}
