package Model.Entities;

import java.time.LocalDate;
import java.util.List;

public class PoliticalParty {

	public static final String CLASS_NAME = "PoliticalParty";

	private String namePoliticalParty;
	private LocalDate createDatePoliticalParty;
	private List<Candidate> candidatesList;

	public enum Section { Right, Center, Left };
	private Section section;

	//================================================

	public PoliticalParty(String namePoliticalParty, Section section, LocalDate createDatePoliticalParty,List<Candidate> candidatesList) {
		this.namePoliticalParty = namePoliticalParty;
		this.section = section;
		this.createDatePoliticalParty = createDatePoliticalParty;
		this.candidatesList = candidatesList;
	}

	//================================================

	public LocalDate getCreateDate() {
		return createDatePoliticalParty;
	}

	public String getName() {
		return namePoliticalParty;
	}

	public Section getSection() {
		return section;
	}

	public List<Candidate> getCandidatesList() {
		return candidatesList;
	}

	//================================================

	public boolean equals(PoliticalParty politicalParty) {
		if (politicalParty.getName().equalsIgnoreCase(this.getName()))
			return true; 
		if (!(politicalParty instanceof PoliticalParty)) 
			return false;
		return false;
	}

	@Override
	public String toString() {
		return "| "+this.namePoliticalParty +" | \n"+ this.section +" - "+ this.createDatePoliticalParty +"\n";
	}

}
