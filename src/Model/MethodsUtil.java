package Model;

import Controller.Controller;
import Database.DB;
import Model.Entities.Candidate;
import Model.Entities.Citizen;
import Model.Entities.PoliticalParty;
import Model.Entities.VotingBox;

import java.util.List;

public class MethodsUtil {

	//================================================ Candidate methods

	public static void citizenToCandidate(Controller cont, Citizen citizen, String namePoliticalParty) {
		Candidate newCandidate = new Candidate(citizen, namePoliticalParty);

		int partyId = addToPartyCandidateList(cont, newCandidate, namePoliticalParty);
		newCandidate.setPartyId(partyId);

		DB.insert(newCandidate);

		cont.getModel().replace(newCandidate);
		cont.getView().add(newCandidate);
		cont.getView().remove(newCandidate);
	}

	public static int addToPartyCandidateList(Controller cont, Candidate candidate, String partyName) {
		List parties = cont.getModel().getPoliticalParties();
		for (int i = 0; i < parties.size(); i++) {
			PoliticalParty tmpParty = (PoliticalParty) parties.get(i);
			if (tmpParty.getName().equalsIgnoreCase(partyName)) {
				cont.getView().getCandidates().getItems().add(candidate);
				tmpParty.getCandidatesList().add(candidate);
				return parties.indexOf(tmpParty)+1;
			}
		}
		return -1;
	}

	//================================================ Vote Box Distribution methods

	// determines and sets citizen correct voting box
	public static void setCorrectVotingBox(Citizen citizen, List listOfVotingBoxs) {
		VotingBox.BoxType boxType;
		if (citizen.getInsulation()) { // if insulated citizen/soldier set/show corona voting box
			if (citizen.checkIfSoldier())
				boxType = VotingBox.BoxType.ArmyCorona;
			else
				boxType = VotingBox.BoxType.Corona;
		} else {
			if (citizen.checkIfSoldier()) // if healthy soldier set/show army voting box
				boxType = VotingBox.BoxType.Army;
			else  // if healthy citizen set/show normal voting box
				boxType = VotingBox.BoxType.Normal;
		}
		voteBoxSetter(listOfVotingBoxs, boxType, citizen);
	}

	// setCorrectVotingBox Helper
	private static void voteBoxSetter(List listOfVotingBoxs, VotingBox.BoxType boxType, Citizen citizen) {
		for (int i = listOfVotingBoxs.size() - 1; i >= 0 ; i--) {
			VotingBox iVoteBox = (VotingBox) listOfVotingBoxs.get(i);
			if (iVoteBox.getBoxType().equals(boxType)) {
				citizen.setVotingBooth(iVoteBox.getId());
				iVoteBox.getListOfCitizen().add(citizen);
				return;
			}
		}
	}

	public static void resetAllVotes(Model model) {
		List ctzns = model.getCitizens();
		for (int i = 0; i < ctzns.size(); i++) {
			((Citizen)ctzns.get(i)).setVotedFor(null);
		}
	}

	//================================================

}
