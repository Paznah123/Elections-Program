import java.time.LocalDate;
import java.util.ArrayList;

import Controller.Controller;
import Model.Candidate;
import Model.Citizen;
import Model.MethodsUtil;
import Model.Model;
import Model.PoliticalParty;
import Model.VotingBox;
import View.View;
import javafx.application.*;
import javafx.stage.Stage;

public class Program extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
// -----------------------------------------------------------------------------------------------------
		Model theModel = new Model();
		View theView = new View();
		addDataBase(theModel);
		Controller controller = new Controller(theModel,theView);
	}
	
	private void addDataBase(Model theModel) {
		// Normal
		Citizen ronen = new Citizen("Ronen saviz", "205688690", 1994, false, 0);
		Citizen alon = new Citizen("Alon gritsovsky", "315688611", 1994, false, 0);
		Citizen jacob = new Citizen("Jacob biblow", "267594611", 1994, false, 0);

		// Army
		Citizen ziv = new Citizen("Ziv Arsaf", "123456789", 2002, false, false, 0);
		Citizen yuval = new Citizen("Yuval Lamdani", "987456321", 2001, false, true, 0);
		Citizen yosi = new Citizen("Yosi Kocav", "741852963", 2002, false, false, 0);

		// Corona
		Citizen dani = new Citizen("Dani oman", "546824648", 1990, true, 7);
		Citizen iky = new Citizen("Iky cohen", "224558215", 1989, true, 9);

		// CoronaArmy
		Citizen amit = new Citizen("Amit ishay", "789564611", 2000, true, true, 10);
		Citizen masa = new Citizen("Masa cohen", "223564611", 2001, true, true, 6);
		Citizen shlomi = new Citizen("Shlomi netanel", "589789658", 2000, true, true, 12);

		// Candidates
		Candidate bibi = new Candidate("Bibi Netanyahu", "999999999", 1980, false, 0, "Likud");
		Candidate july = new Candidate("July Edelstein", "666666666", 1958, false, 0, "Likud");
		Candidate lieberman = new Candidate("Avigdor Lieberman", "282828282", 1981, false, 0, "Israel is our Home");
		Candidate juliaMalinowski = new Candidate("Julia Malinowski", "456987123", 1981, false, 0, "Israel is our Home");
		Candidate gantz = new Candidate("Benny Gantz", "777777777", 1989, false, 0, "Blue and white");
		Candidate yair = new Candidate("Yair Lapid", "2222222222", 1989, false, 0, "Blue and white");
		
// -----------------------------------------------------------------------------------------------------		
// Voters & Candidates ListArrays

		ArrayList<Candidate> likudCandidatesList = new ArrayList<>();
		likudCandidatesList.add(bibi);
		likudCandidatesList.add(july);
		ArrayList<Candidate> israelisourHomeCandidatesList = new ArrayList<>();
		israelisourHomeCandidatesList.add(lieberman);
		israelisourHomeCandidatesList.add(juliaMalinowski);
		ArrayList<Candidate> blueandwhiteCandidatesList = new ArrayList<>();
		blueandwhiteCandidatesList.add(gantz);
		blueandwhiteCandidatesList.add(yair);

		// Normal (9 Citizen)
		ArrayList<Citizen> votingBoxListA1 = new ArrayList<>();
		votingBoxListA1.add(ronen);
		votingBoxListA1.add(alon);
		votingBoxListA1.add(jacob);
		votingBoxListA1.add(bibi);
		votingBoxListA1.add(july);
		votingBoxListA1.add(lieberman);
		votingBoxListA1.add(juliaMalinowski);
		votingBoxListA1.add(gantz);
		votingBoxListA1.add(yair);

		// Soldiers (3 Citizen)
		ArrayList<Citizen> votingBoxListA2 = new ArrayList<>();
		votingBoxListA2.add(ziv);
		votingBoxListA2.add(yuval);
		votingBoxListA2.add(yosi);

		// Corona (2 Citizen)
		ArrayList<Citizen> votingBoxListA3 = new ArrayList<>();
		votingBoxListA3.add(dani);
		votingBoxListA3.add(iky);

		// Soldiers Corona (3 citizen)
		ArrayList<Citizen> votingBoxListA4 = new ArrayList<>();
		votingBoxListA4.add(shlomi);
		votingBoxListA4.add(masa);
		votingBoxListA4.add(amit);

		theModel.addCitizens(votingBoxListA1);
		theModel.addCitizens(votingBoxListA2);
		theModel.addCitizens(votingBoxListA3);
		theModel.addCitizens(votingBoxListA4);
	
		// Political Parties
		PoliticalParty likud = new PoliticalParty("Likud", "right", LocalDate.of(1995, 03, 29), likudCandidatesList);
		PoliticalParty blueAndWhite = new PoliticalParty("Blue And White", "left", LocalDate.of(1990, 03, 23), blueandwhiteCandidatesList);
		PoliticalParty israelIsOurHome = new PoliticalParty("Israel Is Our Home", "center", LocalDate.of(1955, 03, 28),israelisourHomeCandidatesList);

		theModel.addPolitiParty(likud);
		theModel.addPolitiParty(blueAndWhite);
		theModel.addPolitiParty(israelIsOurHome);

		// Voting Boxes
		VotingBox<Citizen> normalBox1 = new VotingBox<>("Yigal Alon Hod Hasharon", votingBoxListA1, VotingBox.boxType.Normal);
		VotingBox<Citizen> armyBox2 = new VotingBox<>("Dizengoff Tel Aviv", votingBoxListA2, VotingBox.boxType.Army);
		VotingBox<Citizen> coronaBox3 = new VotingBox<>("Oiles ramat gan", votingBoxListA3, VotingBox.boxType.Corona);
		VotingBox<Citizen> coronaArmyBox4 = new VotingBox<>("Herzl  Ashkelon", votingBoxListA4, VotingBox.boxType.CoronaArmy);

		theModel.addVotingBox(normalBox1);
		theModel.addVotingBox(armyBox2);
		theModel.addVotingBox(coronaBox3);
		theModel.addVotingBox(coronaArmyBox4);
		
		theModel.getDisplay().noCandidatesListMaker(theModel);	
		
		for (int i = 0; i < theModel.getListOfCitizens().size(); i++) {
			MethodsUtil.setCorrectVotingBox(theModel.getListOfCitizens().get(i), theModel.getListOfVotingBoxs());
		}
		
	}

}