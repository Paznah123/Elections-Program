package Model.Entities;

public class Candidate extends Citizen {

	public static final String CLASS_NAME = "Candidate";

	private String namePoliticalParty;
	private int partyId;

	public Candidate(Citizen ctzn, String namePoliticalParty) {
		super(ctzn.getFirstName(), ctzn.getLastName(), ctzn.getId(), ctzn.getYearOfBirth(), ctzn.getInsulation(), ctzn.getCarryWeapon(), ctzn.getNumberOfSickDays());
		this.setVotingBooth(ctzn.getVotingBooth());
		this.namePoliticalParty = namePoliticalParty;
	}

	//================================================

	public int getPartyId() { return partyId;}

	public void setPartyId(int partyId) { this.partyId = partyId; }

	public boolean equals(Candidate candidate) {
		if (candidate == this)  
		  	return true; 
	  	if (!(candidate instanceof Candidate)) 
	        return false;
	  	return false;
	}
	  
	@Override
	public String toString() {
		return super.toString()+"\n" + namePoliticalParty +" Candidate" ;
	}

}
