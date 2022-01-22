package Controller;

import Database.DB;
import Exceptions.FoundInListsException;
import Model.Entities.*;
import Model.Model;
import View.View;
import View.Views.*;
import View.AlertBox;

import java.util.Map;
import java.util.Set;

public class Controller {
	
	private Model model;
	private View view;

	private ElectionView electionView;

	//================================================

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;

		createLogic();
	}

	//================================================

	public void createLogic() {
		CitizenView ctznView = ((CitizenView)view.getView(Citizen.class));
		ctznView.getConfirm().setOnAction(e -> ctznView.checkCriteria(this));

		CandidateView candView = ((CandidateView)view.getView(Candidate.class));
		candView.addLogic(this);

		PoliticalPartyView partyView = ((PoliticalPartyView)view.getView(PoliticalParty.class));
		partyView.getConfirm().setOnAction(e -> partyView.checkCriteria(this));

		VotingBoxView votingBoxView = ((VotingBoxView)view.getView(VotingBox.class));
		votingBoxView.getConfirm().setOnAction(e -> votingBoxView.checkCriteria(this));

		electionView = new ElectionView(this);
		view.addElectionView(electionView);
	}

	public void saveElections() {
		ElectionResults results = electionView.getElectionResults();
		results.fillPiechart(model);
		DB.insert(results);

		Set<Map.Entry <String, Integer>> partiesSet = results.getPartiesVotes().entrySet();
		int electionId = model.getElections().size()+1;
		int partyId = 0;
		for (Map.Entry <String, Integer> entry : partiesSet){
			PartyResults partyresults = new PartyResults(
					electionId,
					partyId,
					entry.getValue());
			DB.insert(partyresults);
			partyId++;
		}
	}

	//================================================

	public boolean add(Object obj) {
		try {
			if (model.add(obj)) {
				view.add(obj);
				return true;
			}
			throw new FoundInListsException(getExceptionDetails(obj));
		} catch (FoundInListsException e){
			AlertBox.display("Found In Lists Exception",e.getMessage());
		}
		return false;
	}

	//================================================

	private String getExceptionDetails(Object obj) {
		switch (obj.getClass().getSimpleName()){
			case Citizen.CLASS_NAME:
				return ((Citizen)obj).getId();
			case VotingBox.CLASS_NAME:
					return ((VotingBox)obj).getAddress();
			case PoliticalParty.CLASS_NAME:
					return ((PoliticalParty)obj).getName();
			case Candidate.CLASS_NAME:
				return ((Candidate)obj).getId();
			default:
				return "";
		}
	}

	//================================================

	public Model getModel() { return model; }

	public View getView() { return view; }

	public ElectionView getElectionView() { return electionView; }

}
