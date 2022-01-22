package Model;

import Database.DB;
import Database.Queries;
import Model.Entities.Candidate;
import Model.Entities.Citizen;
import Model.Entities.PoliticalParty;
import Model.Entities.VotingBox;

import java.util.List;

public class Model {

	private List[] lists;

	//================================================

	public Model() {
		lists = new List[]{
				DB.fetch(Queries.GET_ALL_CITIZENS, Queries.CITIZEN),
				DB.fetch(Queries.GET_CITIZENS_NO_CANDIDATES, Queries.CITIZEN),
				DB.fetch(Queries.GET_All_CANDIDATES, Queries.CANDIDATE),
				DB.fetch(Queries.GET_ALL_VOTE_BOXES, Queries.VOTING_BOX),
				DB.fetch(Queries.GET_ALL_PARTIES, Queries.POLITICAL_PARTY),
				DB.fetch(Queries.GET_ALL_ELECTIONS, Queries.ELECTIONS)
		};

	}

	//================================================

	public boolean add(Object obj) {
		if (!find(obj)) {
			listGetter(obj).add(obj);
			DB.insert(obj);
			return true;
		}
		return false;
	}

	private List listGetter(Object obj) {
		switch (obj.getClass().getSimpleName()){
			case Citizen.CLASS_NAME:
				return lists[0];
			case Candidate.CLASS_NAME:
				return lists[2];
			case VotingBox.CLASS_NAME:
				return lists[3];
			case PoliticalParty.CLASS_NAME:
				return lists[4];
			default:
				return null;
		}
	}

	private boolean find(Object obj) {
		switch (obj.getClass().getSimpleName()){
			case Citizen.CLASS_NAME:
				return lists[0].stream().anyMatch(o -> ((Citizen)obj).getId().equals(((Citizen) o).getId()));
			case Candidate.CLASS_NAME:
				return lists[2].stream().anyMatch(o -> ((Candidate)obj).equals((Candidate) o));
			case VotingBox.CLASS_NAME:
				return lists[3].stream().anyMatch(o -> ((VotingBox)obj).equals((VotingBox) o));
			case PoliticalParty.CLASS_NAME:
				return lists[4].stream().anyMatch(o -> ((PoliticalParty)obj).equals((PoliticalParty) o));
			default:
				return false;
		}
	}

	//================================================

	public Citizen getCitizenById(String id) {
		List<Citizen> citizenList = lists[1];
		for (int j = 0; j < citizenList.size(); j++) {
			if(citizenList.get(j).getId().equals(id))
				return citizenList.get(j);
		}
		return null;
	}

	public boolean replace(Citizen citizen) {
		for (int i = 0; i < lists[0].size(); i++) {
			if (((Citizen)lists[0].get(i)).getId().equals(citizen.getId())) {
				lists[0].add(i, citizen);
				return true;
			}
		}
		return false;
	}

	//================================================

	public List getCitizens() { return lists[0]; }

	public List getOnlyCitizens() { return lists[1]; }

	public List getVotingBoxs() { return lists[3]; }

	public List getPoliticalParties() { return lists[4]; }

	public List getElections() { return lists[5]; }

	public List[] getLists() { return lists; }
}
