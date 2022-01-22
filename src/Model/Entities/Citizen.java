
package Model.Entities;

import java.time.LocalDate;
import java.time.Year;

public class Citizen {

	public static final String CLASS_NAME = "Citizen";

	private String firstName, lastName, id, votedFor;
	private int yearOfBirth, votingBooth, numberOfSickDays;
	private boolean insulation, carryWeapon;

	//================================================

	public Citizen(String firstName, String lastName, String id, int yearOfBirth, boolean insulation, boolean carryWeapon, int numberOfSickDays) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.yearOfBirth = yearOfBirth;
		this.insulation = insulation;
		this.numberOfSickDays = numberOfSickDays;
		this.votedFor = null;
		this.carryWeapon = carryWeapon;
	}

	//================================================

	public String getFirstName() { return firstName; }

	public String getLastName() { return lastName; }

	public String getId() { return id; }
	
	public int getAge() {
		return Year.now().getValue() - yearOfBirth;
	}
	
	public int getYearOfBirth() {
		return yearOfBirth;
	}

	//================================================

	public boolean getInsulation() {
		return insulation;
	}

	public int getNumberOfSickDays() {
		return numberOfSickDays;
	}

	//================================================
	
	public int getVotingBooth() {
		return votingBooth;
	}
	
	public void setVotingBooth(int votingBooth) { this.votingBooth = votingBooth; }

	//================================================
	
	public String getVotedFor() {
		return votedFor;
	}

	public void setVotedFor(String votedFor) {
		this.votedFor = votedFor;
	}

	//================================================

	public boolean checkIfSoldier() {
		int ageCitizen = this.getAge();
		return (ageCitizen < 21 && ageCitizen >= 18) ? true : false;
	}

	public boolean getCarryWeapon() {
		return carryWeapon;
	}

	public String checkCarryWeaponSt() {
		return (carryWeapon) ? ", Carries Weapon" : " ";
	}

	//================================================

	public String insulationState() {
		return (insulation) ? "Insulated " +"("+ getDateInsulation() +")" : "Healthy";
	}

	private String getDateInsulation() {
		int month = LocalDate.now().minusDays(numberOfSickDays).getMonthValue();
		int day =LocalDate.now().minusDays(numberOfSickDays).getDayOfMonth();

		return month +"-"+ day;
	}

	//================================================

	public boolean equals(Citizen citizen) {
		if (citizen.getId().equals(this.getId()))
		  	return true; 
	  	if (!(citizen instanceof Citizen)) 
	        return false;
	  	return false;
	  }
	 
	@Override
	public String toString() {
		return firstName +" "+ lastName + " - " + yearOfBirth + " \nId - " + id + " \n" + insulationState() + checkCarryWeaponSt() +"\nVote Box: " + getVotingBooth();
	}

}
