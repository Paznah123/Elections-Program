package Database;

import Model.Entities.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class StatementBuilder {

    private Connection con;

    public StatementBuilder(Connection con) { this.con = con; }

    //================================================

    public PreparedStatement citizen(Citizen citizen) throws SQLException {
        PreparedStatement st = con.prepareStatement(Queries.INSERT_CITIZEN);

        st.setInt(1, Integer.parseInt(citizen.getId()));
        st.setString(2, citizen.getFirstName());
        st.setString(3, citizen.getLastName());
        st.setInt(4, citizen.getYearOfBirth());
        st.setBoolean(5, citizen.getInsulation());
        st.setInt(6, citizen.getNumberOfSickDays());
        st.setBoolean(7, citizen.getCarryWeapon());
        st.setInt(8, citizen.getVotingBooth());

        return st;
    }

    //================================================

    public PreparedStatement candidate(Candidate candidate) throws SQLException {
        PreparedStatement st = con.prepareStatement(Queries.INSERT_CANDIDATE);

        st.setInt(1, Integer.parseInt(candidate.getId()));
        st.setInt(2, candidate.getPartyId());

        return st;
    }

    //================================================

    public PreparedStatement voteBox(VotingBox votingBox) throws SQLException {
        PreparedStatement st = con.prepareStatement(Queries.INSERT_VOTING_BOX);

        st.setString(1, votingBox.getAddress());
        st.setInt(2, votingBox.getBoxType().ordinal()+1);

        return st;
    }

    //================================================

    public PreparedStatement politicalParty(PoliticalParty party) throws SQLException {
        PreparedStatement st = con.prepareStatement(Queries.INSERT_POLITICAL_PARTY);

        st.setString(1, party.getName());
        st.setInt(2, party.getSection().ordinal()+1);
        st.setDate(3, Date.valueOf(party.getCreateDate()));

        return st;
    }

    //================================================

    public PreparedStatement electionResults() throws SQLException {
        PreparedStatement st = con.prepareStatement(Queries.INSERT_RESULTS);

        Date electionDate = Date.valueOf(LocalDate.now());

        st.setDate(1, electionDate);

        return st;
    }

    //================================================

    public PreparedStatement partyResults(PartyResults partyResults) throws SQLException {
        PreparedStatement st = con.prepareStatement(Queries.INSERT_PARTY_RESULTS);

        st.setInt(1, partyResults.getElectionId());
        st.setInt(2, partyResults.getPartyId());
        st.setInt(3, partyResults.getNumVotes());

        return st;
    }
}
