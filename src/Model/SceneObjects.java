package Model;

import View.AddCandidate;
import View.AddCitizen;
import View.AddPolitiParty;
import View.AddVoteBox;
import View.RunElections;

public class SceneObjects {

	private AddCitizen addCitizen;
	private AddCandidate addCandidate;
	private AddPolitiParty addPolitiParty;
	private AddVoteBox addVoteBox;
	private RunElections runElections;

//----------------------------------------------------------

	public SceneObjects(Model model) {
		this.addCitizen = new AddCitizen();
		this.addCandidate = new AddCandidate(model);
		this.addPolitiParty = new AddPolitiParty();
		this.addVoteBox = new AddVoteBox(model);
		this.runElections = new RunElections(model);
	}

//----------------------------------------------------------

	public void setRunElections(RunElections runElections) {
		this.runElections = runElections;
	}
	
//----------------------------------------------------------

	public AddCitizen getAddCitizen() {
		return addCitizen;
	}

	public AddCandidate getAddCandidate() {
		return addCandidate;
	}

	public AddPolitiParty getAddPolitiParty() {
		return addPolitiParty;
	}

	public AddVoteBox getAddVoteBox() {
		return addVoteBox;
	}

	public RunElections getRunElections() {
		return runElections;
	}

}
