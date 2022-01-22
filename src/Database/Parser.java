package Database;

import Model.Entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;

public interface Parser {

    //================================================

    default Citizen toCitizen(ResultSet rs) throws SQLException {
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String citizenId = rs.getString("id_citizen");
        int birthYear = Integer.parseInt(rs.getString("year_of_birth"));
        boolean insulation = Integer.parseInt(rs.getString("insulation")) == 0 ? false : true;
        boolean carryWeapon = Integer.parseInt(rs.getString("carry_weapon")) == 0 ? false : true;
        int sickDays = Integer.parseInt(rs.getString("sick_days"));
        int votingBooth = Integer.parseInt(rs.getString("id_voting_box"));

        Citizen ctzn = new Citizen(firstName,
                                    lastName,
                                    citizenId,
                                    birthYear,
                                    insulation,
                                    carryWeapon,
                                    sickDays);
        ctzn.setVotingBooth(votingBooth);

        return ctzn;
    }

    //================================================

    default Candidate toCandidate(ResultSet rs) throws SQLException {
        String partyName = rs.getString("party_name");
        int partyId = Integer.parseInt(rs.getString("id_party"));

        Citizen ctzn = toCitizen(rs);
        Candidate cand = new Candidate(ctzn, partyName);
        cand.setPartyId(partyId);

        return cand;
    }

    //================================================

    default PoliticalParty toPolitiParty(ResultSet rs) throws SQLException {
        String partyName = rs.getString("party_name");
        PoliticalParty.Section section =  PoliticalParty.Section.values()[Integer.parseInt(rs.getString("id_section"))-1];
        LocalDate createDate = LocalDate.parse(rs.getString("create_date"));
        int partyId = Integer.parseInt(rs.getString("id_political_party"));
        List candidates = DB.fetch(Queries.GET_PARTY_CANDIDATES_BY_ID(partyId), Queries.CANDIDATE);

        return new PoliticalParty(partyName, section, createDate, candidates);
    }

    //================================================

    default VotingBox toVoteBox(ResultSet rs) throws SQLException {
        int id = Integer.parseInt(rs.getString("id_voting_box"));
        String address = rs.getString("address");
        List citizens = DB.fetch(Queries.GET_CITIZENS_BY_VOTEBOX_ID(id),Queries.CITIZEN);
        int boxTypeIndex = Integer.parseInt(rs.getString("box_type")) - 1;

        return new VotingBox(id, address, citizens, VotingBox.BoxType.values()[boxTypeIndex]);
    }

    default VotingBox.BoxType toVoteBoxType(ResultSet rs) throws SQLException {
        return VotingBox.BoxType.valueOf(rs.getString("box_type"));
    }

    //================================================

    default Elections toElections(ResultSet rs) throws SQLException {
        int id = Integer.parseInt(rs.getString("id_election"));
        LocalDate electionDate = LocalDate.parse(rs.getString("date_election"));

        List partiesVotes = DB.fetch(Queries.GET_PARTIES_RESULTS_BY_ELECTION_ID(id), Queries.PARTY_RESULTS);

        LinkedHashMap<String, Integer> partiesVotesMap = new LinkedHashMap<>();
        for (Object res: partiesVotes) {
            PartyResults results = (PartyResults) res;
            partiesVotesMap.put(results.getPartyName(), results.getNumVotes());
        }

        return new Elections(id, electionDate, partiesVotesMap);
    }

    default PartyResults toPartyResults(ResultSet rs) throws SQLException {
        int electionId = Integer.parseInt(rs.getString("id_election"));
        int partyId = Integer.parseInt(rs.getString("id_party"));
        int numVotes = Integer.parseInt(rs.getString("num_votes"));

        PartyResults results = new PartyResults(electionId, partyId, numVotes);
        results.setPartyName(rs.getString("party_name"));

        return results;
    }

    default String toPartyName(ResultSet rs) throws SQLException {
        return rs.getString("party_name");
    }
}
