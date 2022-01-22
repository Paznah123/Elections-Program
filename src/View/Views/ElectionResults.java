package View.Views;

import Controller.Controller;
import Model.Entities.Citizen;
import Model.Entities.PoliticalParty;
import Model.Model;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ElectionResults {

	public static final String CLASS_NAME = "ElectionResults";

	private PieChart pieChartData;

	private LinkedHashMap<String, Integer> partiesVotes;

	//================================================

	public ElectionResults(Controller cont) {
		this.pieChartData = new PieChart();
		this.partiesVotes = new LinkedHashMap<>();

		initPartiesMap(cont);

		pieChartData.setTitle("Election Results");
	}

	public Node getMainGrid() { return pieChartData; }

	public LinkedHashMap<String, Integer> getPartiesVotes() { return partiesVotes; }

	//================================================

	public void initPartiesMap(Controller cont) {
		this.partiesVotes = new LinkedHashMap<>();

		List parties = cont.getModel().getPoliticalParties();

		partiesVotes.put("White Vote", 0);
		for (int i = 0; i < parties.size(); i++)
			partiesVotes.put(((PoliticalParty) parties.get(i)).getName(), 0);

	}

	//================================================

	public void fillPiechart(Model model) {
		List citizens = model.getCitizens();

		for (int i = 0; i < citizens.size(); i++) {
			String votedParty = ((Citizen)citizens.get(i)).getVotedFor();
			int numOfVotes = partiesVotes.get(votedParty);
			partiesVotes.put(votedParty,numOfVotes+1);
		}

		for (Map.Entry<String, Integer> entry : partiesVotes.entrySet())
			pieChartData.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));
	}

	//================================================
}
