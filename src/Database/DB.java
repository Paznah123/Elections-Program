package Database;

import Model.Entities.*;
import View.Views.ElectionResults;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Database.Consts.CONNECTION_URL;
import static Database.Consts.USER_NAME;

public class DB {

    private static DB instance;
    private Connection con;

    private Parser parser;
    private StatementBuilder statmentBuilder;

    //=============================

    public static void init(String password) {
        if (instance == null) {
            instance = new DB();
            instance.connectDB(CONNECTION_URL, USER_NAME, password);
            instance.parser = new Parser() { };
            instance.statmentBuilder = new StatementBuilder(instance.con);
        }
    }

    //================================================

    private void connectDB(String url, String userName, String password) {
        try {
            this.con = DriverManager.getConnection(url, userName, password);
            System.out.println("Connected");
        } catch (SQLException e) { System.out.println(e); }
    }

    //================================================

    public static List fetch(String fetchQuery, String fetchType){
        List lst = new ArrayList<>();
        try {
            Statement st = instance.con.createStatement();
            ResultSet rs = st.executeQuery(fetchQuery);
            while (rs.next())
            {
                Object tmp = instance.fetchDecider(fetchType, rs);
                lst.add(tmp);
            }
        } catch (Exception e) { System.out.println(fetchQuery +" - "+ e); }

        return lst;
    }

    private Object fetchDecider(String fetchType, ResultSet rs) throws SQLException {
        switch (fetchType){
            case Queries.CITIZEN:
                return parser.toCitizen(rs);
            case Queries.CANDIDATE:
                return parser.toCandidate(rs);
            case Queries.VOTING_BOX:
                return parser.toVoteBox(rs);
            case Queries.POLITICAL_PARTY:
                return parser.toPolitiParty(rs);
            case Queries.VOTING_BOX_TYPE:
                return parser.toVoteBoxType(rs);
            case Queries.POLITICAL_PARTY_NAME:
                return parser.toPartyName(rs);
            case Queries.ELECTIONS:
                return parser.toElections(rs);
            case Queries.PARTY_RESULTS:
                return parser.toPartyResults(rs);
            default:
                return null;
        }
    }

    //================================================

    public static void insert(Object obj) {
        try {
            PreparedStatement st = instance.buildInsertStatement(obj);
            st.execute();
        } catch (Exception e) { System.out.println(e); }
    }

    private PreparedStatement buildInsertStatement(Object obj) throws SQLException {
        switch (obj.getClass().getSimpleName()){
            case Citizen.CLASS_NAME:
                return statmentBuilder.citizen((Citizen)obj);
            case Candidate.CLASS_NAME:
                return statmentBuilder.candidate((Candidate) obj);
            case PoliticalParty.CLASS_NAME:
                return statmentBuilder.politicalParty((PoliticalParty) obj);
            case VotingBox.CLASS_NAME:
                return statmentBuilder.voteBox((VotingBox) obj);
            case ElectionResults.CLASS_NAME:
                return statmentBuilder.electionResults();
            case PartyResults.CLASS_NAME:
                return statmentBuilder.partyResults((PartyResults) obj);
            default:
                return null;
        }
    }
}
