package Model;

import java.util.ArrayList;

public class VotingBox<E extends Citizen> {
	private int id;
	private static int count = 1;
	private String address;
	private ArrayList<E> listOfCitizen = new ArrayList<>();

	// because of the difference only in box type we decided to use enum
	public enum boxType { Normal, Corona, Army, CoronaArmy};
	private boxType boxType;

	public VotingBox(String address, ArrayList<E> listOfCitizen, boxType boxType) {
		this.id = count++;
		this.address = address;
		this.listOfCitizen = listOfCitizen;
		this.boxType = boxType;
	}

	public VotingBox(String address, boxType boxType) {
		this.id = count++;
		this.address = address;
		this.listOfCitizen = new ArrayList<>();
		this.boxType = boxType;
	}
	
// 	--------------------------------------------------------------------

	public int getId() {
		return id;
	}
		
	public String getAddress() {
		return address;
	}

	public boxType getBoxType() {
		return boxType;
	}
	
	public ArrayList<E> getListOfCitizen() {
		return listOfCitizen;
	}

// 	--------------------------------------------------------------------
	
	public boolean equals(VotingBox<E> votingBox) { 	   
		if (votingBox.getAddress().equals(this.getAddress())
				&& votingBox.getBoxType().equals(this.getBoxType()))  
			return true; 
		if (!(votingBox instanceof VotingBox)) 
			return false;
		return false;
	} 
	
	@Override
	public String toString() {
		return "\n("+ getId() +") - " + boxType.name().toString() + " Voting Box -" + "\n[address:" + address + "] ";
	}
	
}
