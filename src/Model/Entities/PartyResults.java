package Model.Entities;

public class PartyResults {

    public static final String CLASS_NAME = "PartyResults";

    private int electionId;
    private int partyId;
    private String partyName;
    private int numVotes;

    //================================================

    public PartyResults(int electionId, int partyId, int numVotes) {
        this.electionId = electionId;
        this.partyId = partyId;
        this.numVotes = numVotes;
    }

    //================================================

    public void setPartyName(String partyName) { this.partyName = partyName; }

    public int getElectionId() { return electionId; }

    public int getPartyId() { return partyId; }

    public String getPartyName() { return partyName; }

    public int getNumVotes() { return numVotes; }
}
