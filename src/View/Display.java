package View;

import Model.Candidate;
import Model.Citizen;
import Model.Model;
import Model.PoliticalParty;
import Model.VotingBox;
import javafx.scene.control.ListView;

public class Display {
	
	private ListView<Citizen> citizensList;
	private ListView<Candidate> candidatesList;
	private ListView<VotingBox<Citizen>> votingBoxsList;
	private ListView<PoliticalParty> partiesList;
	private ListView<Citizen> onlyCitizensList;

	public Display(Model model) {
		this.citizensList = new ListView<Citizen>();
		this.votingBoxsList = new ListView<VotingBox<Citizen>>();
		this.partiesList = new ListView<PoliticalParty>();
		this.candidatesList = new ListView<Candidate>();
	}
	
	public void noCandidatesListMaker(Model model){
		onlyCitizensList = new ListView<Citizen>();
		for (int i = 0; i < model.getListOfCitizens().size(); i++) {
			if (!(model.getListOfCitizens().get(i) instanceof Candidate) 
					&& !model.getListOfCitizens().get(i).checkIfSoldier()) 
				onlyCitizensList.getItems().add(model.getListOfCitizens().get(i));
		}
	}
	
// -----------------------------------------------------------------------------------

	public ListView<Citizen> getCitizensList() {
		return citizensList;
	}

	public ListView<VotingBox<Citizen>> getVotingBoxsList() {
		return votingBoxsList;
	}

	public ListView<PoliticalParty> getPartiesList() {
		return partiesList;
	}

	public ListView<Citizen> getOnlyCitizensList() {
		return onlyCitizensList;
	}

	public ListView<Candidate> getCandidatesList() {
		return candidatesList;
	}		

// ------------------------------------------------------------------------------------
	
}

