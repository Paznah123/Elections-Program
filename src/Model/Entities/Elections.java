package Model.Entities;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Elections {

    private int electionId;
    private LocalDate electionDate;
    private HashMap<String,Integer> partiesVotes;

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Elections - "+ electionId +" - "+ electionDate +"\n");
        str.append("=== Political Parties Votes ===\n");
        for (Map.Entry<String, Integer> entry : partiesVotes.entrySet()){
            str.append(entry.getKey() +" - "+ entry.getValue() +"\n");
        }
        str.append("===== ===== ===== =====");

        return str.toString();
    }

    public Elections(int id, LocalDate electionDate, HashMap<String,Integer> partiesVotes) {
        this.electionId = id;
        this.electionDate = electionDate;
        this.partiesVotes = partiesVotes;
    }
}
