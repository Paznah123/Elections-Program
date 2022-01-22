package Model.Entities;

import java.util.List;

public class VotingBox {

	public static final String CLASS_NAME = "VotingBox";

	private int id;
	private static int count = 1;
	private String address;
	private List<Citizen> listOfCitizen;

	public enum BoxType { Normal, Army, Corona, ArmyCorona };
	private BoxType boxType;

	//================================================

	public VotingBox(int id, String address, List listOfCitizen, BoxType boxType) {
		this.id = id;
		this.address = address;
		this.listOfCitizen = listOfCitizen;
		this.boxType = boxType;
	}

	//================================================

	public int getId() { return id; }
		
	public String getAddress() { return address; }

	public BoxType getBoxType() { return boxType; }
	
	public List<Citizen> getListOfCitizen() { return listOfCitizen; }

	//================================================
	
	public boolean equals(VotingBox votingBox) {
		if (!(votingBox instanceof VotingBox))
			return false;
		if (votingBox.getAddress().equals(this.getAddress())
				&& votingBox.getBoxType().equals(this.getBoxType()))  
			return true;
		return false;
	} 
	
	@Override
	public String toString() {
		return "\n("+ getId() +")" + " [" + address + "]\n- " + boxType.name() + " Voting Box -";
	}
	
}
