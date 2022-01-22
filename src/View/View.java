package View;

import Model.Entities.Candidate;
import Model.Entities.Citizen;
import Model.Entities.PoliticalParty;
import Model.Entities.VotingBox;
import View.Views.*;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class View {

    private Stage mainWindow = new Stage();

    private TabPane tabPane = new TabPane();

    private Tab[] tabs = {
            new Tab("Citizen"),
            new Tab("Candidate"),
            new Tab("Voting box"),
            new Tab("Political party"),
            new Tab("Elections")
    };

    private ListView[] listViews = {
            new ListView(),
            new ListView(),
            new ListView(),
            new ListView(),
            new ListView()
    };

    private Object[] viewObjs;

    public View(List[] lists) {
        for (int i = 0; i < listViews.length; i++)
            listViews[i].getItems().addAll(lists[i]);

        for (Tab t: tabs)
            tabPane.getTabs().add(t);

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        createViews();

        VBox vbox = new VBox(tabPane);

        Scene scene = new Scene(vbox, 420, 500);

        mainWindow.setTitle("Election Program");
        mainWindow.setScene(scene);
        mainWindow.setResizable(false);
        mainWindow.show();
    }

    private void createViews() {
        viewObjs = new Object[]{
                new CitizenView(listViews[0]),
                new CandidateView(listViews[1], listViews[2]),
                new VotingBoxView(listViews[3]),
                new PoliticalPartyView(listViews[4])
        };

        tabs[0].setContent(((CitizenView)viewObjs[0]).getMainGrid());
        tabs[1].setContent(((CandidateView)viewObjs[1]).getMainGrid());
        tabs[2].setContent(((VotingBoxView)viewObjs[2]).getMainGrid());
        tabs[3].setContent(((PoliticalPartyView)viewObjs[3]).getMainGrid());
    }

    public void addElectionView(ElectionView electionView) {
        tabs[4].setContent(electionView.getMainGrid());
    }

    //================================================

    public void add(Object obj) {
        switch (obj.getClass().getSimpleName()){
            case Citizen.CLASS_NAME:
                listViews[0].getItems().add(obj);
                break;
            case Candidate.CLASS_NAME:
                listViews[2].getItems().add(obj);
                break;
            case VotingBox.CLASS_NAME:
                listViews[3].getItems().add(obj);
                break;
            case PoliticalParty.CLASS_NAME:
                listViews[4].getItems().add(obj);
                break;
            default:
                break;
        }
    }

    public void remove(Citizen citizen) {
        boolean removed = false;
        ObservableList<Citizen> citizensObservableList = listViews[1].getItems();
        for (int i = 0; i < citizensObservableList.size() && !removed ; i++) {
            if (citizensObservableList.get(i).getId().equals(citizen.getId())) {
                citizensObservableList.remove(i);
                removed = true;
            }
        }
    }
    //================================================

    public Object getView(Class cls) {
        switch (cls.getSimpleName()){
            case Citizen.CLASS_NAME:
                return viewObjs[0];
            case Candidate.CLASS_NAME:
                return viewObjs[1];
            case VotingBox.CLASS_NAME:
                return viewObjs[2];
            case PoliticalParty.CLASS_NAME:
                return viewObjs[3];
            default:
                return null;
        }
    }

    //================================================

    public ListView<Citizen> getOnlyCitizens() { return listViews[1]; }

    public ListView<VotingBox> getVotingBoxes() { return listViews[2]; }

    public ListView<PoliticalParty> getParties() { return listViews[3]; }

    public ListView<Candidate> getCandidates() { return listViews[4]; }

    //================================================
}
