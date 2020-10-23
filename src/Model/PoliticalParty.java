package Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class PoliticalParty {
	
	private String namePoliticalParty;
	private String section;
	private LocalDate createDatePoliticalParty;
	private ArrayList<Candidate> candidatesList;
	
	public PoliticalParty(String namePoliticalParty, String section, LocalDate createDatePoliticalParty,ArrayList<Candidate> candidatesList) {
		this.namePoliticalParty = namePoliticalParty;
		this.section = section;
		this.createDatePoliticalParty = createDatePoliticalParty;
		this.candidatesList = candidatesList;
	}

	public PoliticalParty(String namePoliticalParty, String section, LocalDate createDatePoliticalParty) {
		this.namePoliticalParty = namePoliticalParty;
		this.section = section;
		this.createDatePoliticalParty = createDatePoliticalParty;
		this.candidatesList = new ArrayList<>();
	}	
	
// 	--------------------------------------------------------------------

	public LocalDate getCreateDatePoliticalParty() {
		return createDatePoliticalParty;
	}


	public String getNamePoliticalParty() {
		return namePoliticalParty;
	}

	public String getSection() {
		return section;
	}

	public ArrayList<Candidate> getCandidatesList() {
		return candidatesList;
	}

// 	--------------------------------------------------------------------

	public boolean equals(PoliticalParty politicalParty) { 	   
		if (politicalParty.getNamePoliticalParty().equalsIgnoreCase(this.getNamePoliticalParty()))  
			return true; 
		if (!(politicalParty instanceof PoliticalParty)) 
			return false;
		return false;
	} 
	
	@Override
	public String toString() {
		StringBuffer stPoliticalParty = new StringBuffer();
		stPoliticalParty.append("\n"+"|"+this.namePoliticalParty+"| -"+" ");
		stPoliticalParty.append(this.section+" - ");
		stPoliticalParty.append(this.createDatePoliticalParty.getDayOfMonth()+"-"+ 
									this.createDatePoliticalParty.getMonthValue() +"-"+
										this.createDatePoliticalParty.getYear());
		return stPoliticalParty.toString()+"\n";
		
	}
	
	
	
}
