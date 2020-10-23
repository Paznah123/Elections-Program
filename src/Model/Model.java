package Model;

import java.util.ArrayList;

import Exceptions.FoundInListsException;
import View.AlertBox;
import View.Display;
import View.ElectionResults;
import javafx.collections.ObservableList;

public class Model {
	
	private Display display;
	
	private ArrayList<Citizen> listOfCitizens;
	private ArrayList<PoliticalParty> listOfPoliticalParties;
	private ArrayList<VotingBox<Citizen>> listOfVotingBoxs;
		
	private ElectionResults electionResults;
	
// -----------------------------------------------------------------------------------

	public Model() {
		this.display = new Display(this);
		this.listOfCitizens = new ArrayList<>();
		this.listOfPoliticalParties = new ArrayList<>();
		this.listOfVotingBoxs = new ArrayList<>();
		this.electionResults = new ElectionResults(this);
	}
	
// -----------------------------------------------------------------------------------
	
	private boolean checkIdExists(String id) {
		boolean valid = false;
		try {
			for (int i = 0; i < listOfCitizens.size(); i++) {
				if (listOfCitizens.get(i).getId().equals(id)) {
					valid = true;
					throw new FoundInListsException(listOfCitizens.get(i).getId());
				}
			}
		} catch(FoundInListsException e) {
			AlertBox.display("Found In Lists Exception",e.getMessage());
		}
		return valid;
	}
	
	public boolean checkVoteBoxExists(VotingBox<Citizen> voteBox) {
		boolean valid = false;
		try {
			for (int i = 0; i < listOfVotingBoxs.size(); i++) {
				if (listOfVotingBoxs.get(i).equals(voteBox)) {
					valid = true;
					throw new FoundInListsException(listOfVotingBoxs.get(i).getAddress());
				}
			}
		} catch(FoundInListsException e) {
			AlertBox.display("Found In Lists Exception",e.getMessage());
		}
		return valid;
	}
	
	public boolean checkPartyExists(PoliticalParty politiParty) {
		boolean valid = false;
		try {
			for (int i = 0; i < listOfPoliticalParties.size(); i++) {
				if (listOfPoliticalParties.get(i).equals(politiParty)) {
					valid = true;
					throw new FoundInListsException(listOfPoliticalParties.get(i).getNamePoliticalParty());
				}
			}
		} catch(FoundInListsException e) {
			AlertBox.display("Found In Lists Exception",e.getMessage());
		}
		return valid;
	}
	
// -----------------------------------------------------------------------------------

	public boolean addCitizen(Citizen citizen) {
		if (!checkIdExists(citizen.getId())) {
			this.listOfCitizens.add(citizen);
			display.getCitizensList().getItems().add(citizen);
			return true;
		}
		return false;
	}
	
	public boolean addVotingBox(VotingBox<Citizen> voteBox) {
		if (!checkVoteBoxExists(voteBox)) {
			this.listOfVotingBoxs.add(voteBox);
			display.getVotingBoxsList().getItems().add(voteBox);
			return true;
		}
		return false;
	}
	
	public boolean addPolitiParty(PoliticalParty politiParty) {
		if (!checkPartyExists(politiParty)) {
			this.listOfPoliticalParties.add(politiParty);
			display.getPartiesList().getItems().add(politiParty);
			addCandidates(politiParty);
			return true;
		}
		return false;
	}
	
	private void addCandidates(PoliticalParty politiParty) {
		for (int i = 0; i < politiParty.getCandidatesList().size(); i++) {
			display.getCandidatesList().getItems().add(politiParty.getCandidatesList().get(i));
		}
	}

// -----------------------------------------------------------------------------------

	public void addCitizens(ArrayList<Citizen> citizensList) {
		for (int i = 0; i < citizensList.size(); i++) {
			this.getListOfCitizens().add(citizensList.get(i));
			this.getDisplay().getCitizensList().getItems().add(citizensList.get(i));
		}
	}
	
	public void addVotingBoxs(ArrayList<VotingBox<Citizen>> voteboxList) {
		for (int i = 0; i < voteboxList.size(); i++) {
			this.getListOfVotingBoxs().add(voteboxList.get(i));
			this.getDisplay().getVotingBoxsList().getItems().add(voteboxList.get(i));	
		}
	}
	
	
	public void addPolitiParty(ArrayList<PoliticalParty> partiesList) {
		for (int i = 0; i < partiesList.size(); i++) {
			this.getListOfPoliticalParties().add(partiesList.get(i));
			this.getDisplay().getPartiesList().getItems().add(partiesList.get(i));
		}
	}
	
// -----------------------------------------------------------------------------------

	public boolean replace(Citizen citizen) {
		for (int i = 0; i < listOfCitizens.size(); i++) {
			if (listOfCitizens.get(i).getId().equals(citizen.getId())) {	
				listOfCitizens.remove(i);
				removeFromListView(citizen);
				listOfCitizens.add(i, citizen);
				return true;
			}
		}
		return false;
	}

	private void removeFromListView(Citizen citizen) {
		boolean removed = false;
		ObservableList<Citizen> citizensObservableList = display.getOnlyCitizensList().getItems();
		for (int i = 0; i < citizensObservableList.size() && !removed ; i++) {
			if (citizensObservableList.get(i).getId().equals(citizen.getId())) {
				citizensObservableList.remove(i);
				removed = true;
			}
		}
	}
	
// -----------------------------------------------------------------------------------
	
	public ArrayList<Citizen> getListOfCitizens() {
		return listOfCitizens;
	}

	public ArrayList<PoliticalParty> getListOfPoliticalParties() {
		return listOfPoliticalParties;
	}

	public ArrayList<VotingBox<Citizen>> getListOfVotingBoxs() {
		return listOfVotingBoxs;
	}

	public Display getDisplay() {
		return display;
	}

	public ElectionResults getElectionResults() {
		return electionResults;
	}

	public void setElectionResults(ElectionResults electionResults) {
		this.electionResults = electionResults;
	}

}
