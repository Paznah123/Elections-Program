package View.Views;

import Controller.Controller;

import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class ElectionView {

    private RunElections runElections;
    private ElectionResults electionResults;

    private VBox vbox;

    private TabPane tabPane = new TabPane();

    private Tab[] tabs = {
            new Tab("Run Program"),
            new Tab("Results"),
            new Tab("Past Results")
    };

    //================================================

    public ElectionView(Controller cont) {
        runElections = new RunElections(cont);
        electionResults = new ElectionResults(cont);

        for (Tab t: tabs)
            tabPane.getTabs().add(t);

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        ListView list = new ListView();
        list.getItems().addAll(cont.getModel().getElections());

        tabs[0].setContent(runElections.getMainGrid());
        tabs[1].setContent(electionResults.getMainGrid());
        tabs[2].setContent(list);

        vbox = new VBox(tabPane);
    }

    //================================================

    public VBox getMainGrid() {
        return vbox;
    }

    public ElectionResults getElectionResults() { return electionResults; }

    public RunElections getRunElections() { return runElections; }
}
