package Database;

public class Queries {

    // =========================================== commands

    public static final String INSERT = " INSERT INTO ";
    public static final String SELECT = " SELECT ";
    public static final String JOIN = " JOIN ";
    public static final String GROUP_BY = " GROUP BY ";
    public static final String FROM = " FROM ";
    public static final String WHERE = " WHERE ";
    public static final String VALUES = " VALUES ";
    public static final String IN = " IN ";
    public static final String NOT_IN = " NOT IN ";
    public static final String ON = " ON ";

    // ============================================ table names

    public static final String CITIZEN = " citizen ";
    public static final String CANDIDATE = " candidate ";
    public static final String POLITICAL_PARTY = " political_party ";
    public static final String POLITICAL_PARTY_NAME = " party_name ";
    public static final String VOTING_BOX = " voting_box ";
    public static final String VOTING_BOX_TYPE = " voting_box_type ";
    public static final String ELECTIONS = " elections ";
    public static final String PARTY_RESULTS = " party_results ";

    // ============================================ Select queries

    public static final String GET_ALL_ELECTIONS = SELECT +"*"+ FROM + ELECTIONS;

    public static final String GET_ALL_PARTIES = SELECT +"*"+ FROM + POLITICAL_PARTY;

    public static final String GET_ALL_VOTE_BOXES = SELECT +"*"+ FROM + VOTING_BOX;

    public static final String GET_ALL_CITIZENS = SELECT +"*"+ FROM + CITIZEN;

    public static final String GET_CITIZENS_NO_CANDIDATES = SELECT +"*"+ FROM + CITIZEN + WHERE + "id_citizen"+ NOT_IN +"("+ SELECT + "id_citizen"+ FROM + CITIZEN + JOIN + CANDIDATE + ON +"id_citizen = id_candidate)";

    public static final String GET_PARTIES_IDS = SELECT +"id_political_party"+ FROM + POLITICAL_PARTY;

    public static final String GET_All_CANDIDATES = SELECT +"*"+ FROM + CITIZEN + JOIN + CANDIDATE + ON +"id_citizen = id_candidate"+ JOIN + POLITICAL_PARTY + ON +"id_party = id_political_party"+ WHERE +"id_party"+ IN +"("+ GET_PARTIES_IDS +")";

    public static String GET_CITIZENS_BY_VOTEBOX_ID(int votingBoxId) {
        return SELECT + "*"+ FROM + CITIZEN + WHERE +"id_voting_box = " + votingBoxId;
    }

    public static String GET_PARTY_CANDIDATES_BY_ID(int partyId) {
        return SELECT + "*"+ FROM + CANDIDATE + JOIN + CITIZEN + ON +"id_candidate = id_citizen"+ JOIN + POLITICAL_PARTY + WHERE +"id_party = " + partyId + GROUP_BY + "id_candidate";
    };

    public static String GET_PARTIES_RESULTS_BY_ELECTION_ID(int electionId) {
        return SELECT + "*"+ FROM + PARTY_RESULTS + JOIN + POLITICAL_PARTY + ON +"id_political_party = id_party"+ WHERE +"id_election = " + electionId;
    };

    // ============================================ table fields

    private static final String CITIZEN_VALUES = "(id_citizen, first_name, last_name, year_of_birth, insulation, sick_days, carry_weapon, id_voting_box)";
    private static final String CANDIDATE_VALUES = "(id_candidate, id_party)";
    private static final String VOTE_BOX_VALUES = "(address, box_type)";
    private static final String PARTY_VALUES = "(party_name, id_section, create_date)";
    private static final String ELECTION_VALUES = "(date_election)";
    private static final String PARTY_RESULTS_VALUES = "(id_election, id_party, num_votes)";

    // =========================================== Insert queries

    public static String INSERT_CITIZEN = INSERT + CITIZEN + CITIZEN_VALUES + VALUES +"(?, ?, ?, ?, ?, ?, ?, ?)";

    public static String INSERT_CANDIDATE = INSERT + CANDIDATE + CANDIDATE_VALUES + VALUES +"(?, ?)";

    public static String INSERT_VOTING_BOX = INSERT + VOTING_BOX + VOTE_BOX_VALUES + VALUES +"(?, ?)";

    public static String INSERT_POLITICAL_PARTY = INSERT + POLITICAL_PARTY + PARTY_VALUES + VALUES +"(?, ?, ?)";

    public static String INSERT_RESULTS = INSERT + ELECTIONS + ELECTION_VALUES + VALUES +"(?)";

    public static String INSERT_PARTY_RESULTS = INSERT + PARTY_RESULTS + PARTY_RESULTS_VALUES + VALUES +"(?, ?, ?)";

    // ===========================================

}
