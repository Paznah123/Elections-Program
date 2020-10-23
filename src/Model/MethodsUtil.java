package Model;


import java.util.ArrayList;
import Model.VotingBox.boxType;
import javafx.collections.ObservableList;

public class MethodsUtil {

// -----------------------------------------------------------------------------------------------------	
// Vote Box Distribution methods
	
	// determines and sets citizen correct voting box
	public static void setCorrectVotingBox(Citizen citizen, ArrayList<VotingBox<Citizen>> listOfVotingBoxs) {
		
		VotingBox.boxType boxType;
		// SetOrShow = "set" sets to correct vote box , "show" shows eligible vote box
		if (citizen.getInsulation()) { // if insulated citizen/soldier set/show corona voting box
			if (citizen.checkIfSoldier()) {
				boxType = VotingBox.boxType.CoronaArmy;
				voteBoxSetter(listOfVotingBoxs, boxType, citizen);
			}
			if (citizen.getAge() >= 21) {
				boxType = VotingBox.boxType.Corona;
				voteBoxSetter(listOfVotingBoxs, boxType, citizen);
			}
		} else {
			if (citizen.checkIfSoldier()) { // if healthy soldier set/show army voting box
				boxType = VotingBox.boxType.Army;
				voteBoxSetter(listOfVotingBoxs, boxType, citizen);
			}
			if (citizen.getAge() >= 21) { // if healthy citizen set/show normal voting box
				boxType = VotingBox.boxType.Normal;
				voteBoxSetter(listOfVotingBoxs, boxType, citizen);
			}
		}
	}
	
	// setCorrectVotingBox Helper
	private static void voteBoxSetter(ArrayList<VotingBox<Citizen>> listOfVotingBoxs, VotingBox.boxType boxType	, Citizen citizen) {
		VotingBox.boxType chosenBoxType = listOfVotingBoxs.get(citizen.getVotingBooth()).getBoxType();
		ArrayList<Citizen> chosenVotingBoxList = listOfVotingBoxs.get(citizen.getVotingBooth()).getListOfCitizen();
		
		if (chosenBoxType != boxType) { // if wrong voting box type is chosen
			boolean citizenAdded = false; 	// loop descend so citizen distribution in voting boxes will be better
			for (int i = listOfVotingBoxs.size() - 1; i >= 0 || !citizenAdded; i--) {
				VotingBox<Citizen> iVoteBox = listOfVotingBoxs.get(i);
				if (iVoteBox.getBoxType().equals(boxType)) {
					citizenAdded = true;
					citizen.setVotingBooth(iVoteBox.getId());
					iVoteBox.getListOfCitizen().add(citizen);
				}
			}
		}
		else { // if citizen/soldier chose eligible voting box
			chosenVotingBoxList.add(citizen);
			citizen.setVotingBooth(citizen.getVotingBooth()+1);
		}
	}

	public static ArrayList<VotingBox.boxType> getAllTheTypesOfVotingBox () {
		ArrayList<VotingBox.boxType> boxTypeNames =new ArrayList<>();
		for (boxType Types  : boxType.values()) { 
		    boxTypeNames.add(Types);
		}
		return boxTypeNames;
	}
	
	
	public static void resetAllVotes(Model model) {
		for (int i = 0; i < model.getListOfCitizens().size(); i++) {
			model.getListOfCitizens().get(i).setVotedFor(null);
		}
	}
// -----------------------------------------------------------------------------------------------------		
// Citizen methods

	public static Citizen getCitizenById(String id, Model model) {
		ObservableList<Citizen> citizenList = model.getDisplay().getOnlyCitizensList().getItems();
			for (int j = 0; j < citizenList.size(); j++) {
				if(citizenList.get(j).getId().equals(id))
					return citizenList.get(j);
			}
		return null;		
	}
	
// -----------------------------------------------------------------------------------------------------		
// Candidate methods

	public static boolean citizenToCandidate(Model model, Citizen citizen, String namePoliticalParty) {
		boolean candidateAdded = false;
		for (int i = 0; i < model.getListOfVotingBoxs().size() || !candidateAdded; i++) {
			ArrayList<Citizen> citizenList = model.getListOfVotingBoxs().get(i).getListOfCitizen();
			for (int j = 0; j < citizenList.size() && !candidateAdded; j++) {
				// loops until find the correct citizen and makes him candidate
				Citizen tmpCitizen = citizenList.get(j);
				if (tmpCitizen.equals(citizen)) {
					// puts candidate in old citizen voting box spot
					Candidate newCandidate = new Candidate(tmpCitizen.getName(), citizen.getId(), tmpCitizen.getYearOfBirth(),
																	 tmpCitizen.getInsulation(), 
																	 tmpCitizen.getNumberOfSickDays(), 
																		namePoliticalParty);
					newCandidate.setVotingBooth(citizen.getVotingBooth());
					model.replace(newCandidate);
					addToPartyCandidateList(model, newCandidate, namePoliticalParty);
					candidateAdded = true;
				}
			}
		}
		return candidateAdded;
	}
	
	public static void addToPartyCandidateList(Model model, Candidate candidate, String namePoliticalParty) {	
		boolean added = false;
		for (int i = 0; i < model.getListOfPoliticalParties().size() && !added; i++) {
			// adds candidate to candidates list
			PoliticalParty tmpParty = model.getListOfPoliticalParties().get(i);
			model.getDisplay().getCandidatesList().getItems().add(candidate);
			if (tmpParty.getNamePoliticalParty().equalsIgnoreCase(namePoliticalParty)) {
					tmpParty.getCandidatesList().add(candidate);
					added = true;
			}
		}
	}
	
// -----------------------------------------------------------------------------------------------------		

}
