package Model;

public class Candidate extends Citizen {
	
	private String namePoliticalParty;
	
	public Candidate(String name, String id, int yearOfBirth, boolean insulation, int numberOfSickDays, String namePoliticalParty) {
		super(name, id, yearOfBirth, insulation,numberOfSickDays);
		this.namePoliticalParty = namePoliticalParty;
	}
	
// -----------------------------------------------------------------------------------------------------	

	public String getNamePoliticalParty() {
		return namePoliticalParty;
	}

	public boolean equals(Candidate candidate) { 	   
		if (candidate == this)  
		  	return true; 
	  	if (!(candidate instanceof Candidate)) 
	        return false;
	  	return false;
	}
	  
	@Override
	public String toString() {
		return super.toString()+" , " + namePoliticalParty +" Candidate" ;
	}

}
