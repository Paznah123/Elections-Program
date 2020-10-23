package Controller;

import Model.MethodsUtil;
import Model.Model;
import Model.SceneObjects;
import View.ElectionResults;
import View.RunElections;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Controller {
	
	private Model model;
	private View view;
	private SceneObjects sceneObjects;
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		sceneObjects= new SceneObjects(model);
		buttons_setOnAction();
	}
	
	public void buttons_setOnAction() {
		view.getMenu().getExit().setOnAction(e -> view.getMainWindow().close());
		view.getMenu().getReset().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				sceneObjects.setRunElections(new RunElections(model));
				model.setElectionResults(new ElectionResults(model));
				sceneObjects.getRunElections().getNextButton().setOnAction(e -> sceneObjects.getRunElections().checkCriteria(model));				
				MethodsUtil.resetAllVotes(model);
			}
			
		});
		
		// Citizen Scene buttons
		view.getMenu().getAddCitizen().setOnAction(e -> view.getMainLayout().setCenter(sceneObjects.getAddCitizen().getMainGrid()));
		sceneObjects.getAddCitizen().getConfirm().setOnAction(e -> sceneObjects.getAddCitizen().checkCriteria(model));
		
		// Candidate Scene buttons
		view.getMenu().getAddCandidate().setOnAction(e -> view.getMainLayout().setCenter(sceneObjects.getAddCandidate().getMainGrid()));
		sceneObjects.getAddCandidate().getNewCand().setOnAction(e -> view.getMainLayout().setCenter(sceneObjects.getAddCandidate().getNewCandGrid()));
		sceneObjects.getAddCandidate().getNewConfirm().setOnAction(e -> sceneObjects.getAddCandidate().checkCriteria("new", model));
		sceneObjects.getAddCandidate().getExistCand().setOnAction(e -> view.getMainLayout().setCenter(sceneObjects.getAddCandidate().getExistCandGrid()));
		sceneObjects.getAddCandidate().getExistConfirm().setOnAction(e -> sceneObjects.getAddCandidate().checkCriteria("exist", model));
		
		// VoteBox Scene buttons
		view.getMenu().getAddVoteBox().setOnAction(e -> view.getMainLayout().setCenter(sceneObjects.getAddVoteBox().getMainGrid()));
		sceneObjects.getAddVoteBox().getConfirm().setOnAction(e -> sceneObjects.getAddVoteBox().checkCriteria(model));
		
		// Political Party Scene buttons
		view.getMenu().getAddPolitiParty().setOnAction(e -> view.getMainLayout().setCenter(sceneObjects.getAddPolitiParty().getMainGrid()));
		sceneObjects.getAddPolitiParty().getConfirm().setOnAction(e -> sceneObjects.getAddPolitiParty().checkCriteria(model));
		
		// Display buttons
		view.getMenu().getDispCitizen().setOnAction(e -> view.getMainLayout().setCenter(model.getDisplay().getCitizensList()));
		view.getMenu().getDispCandidate().setOnAction(e -> view.getMainLayout().setCenter(model.getDisplay().getCandidatesList()));
		view.getMenu().getDispVoteBox().setOnAction(e -> view.getMainLayout().setCenter(model.getDisplay().getVotingBoxsList()));
		view.getMenu().getDispPolitiParty().setOnAction(e -> view.getMainLayout().setCenter(model.getDisplay().getPartiesList()));
		
		// Elections Scene buttons
		view.getMenu().getDispRes().setOnAction(e -> view.getMainLayout().setCenter(model.getElectionResults().getMainLayout()));
		view.getMenu().getRunProgram().setOnAction(e -> view.getMainLayout().setCenter(sceneObjects.getRunElections().getMainGrid()));
		sceneObjects.getRunElections().getNextButton().setOnAction(e -> sceneObjects.getRunElections().checkCriteria(model));				
		
	}
}
