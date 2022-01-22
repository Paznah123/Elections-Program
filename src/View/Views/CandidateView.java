package View.Views;

import Controller.Controller;
import Model.Entities.PoliticalParty;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class CandidateView {

    private NewCandidateView newCand;
    private ExistCandidateView existCand;

    private VBox vbox;

    private TabPane tabPane = new TabPane();

    private Tab[] tabs = {
            new Tab("New"),
            new Tab("Existing")
    };

    //================================================

    public CandidateView(ListView onlyCitizens, ListView candidates) {
        newCand = new NewCandidateView(candidates);
        existCand = new ExistCandidateView(onlyCitizens);

        for (Tab t: tabs)
            tabPane.getTabs().add(t);

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        tabs[0].setContent(newCand.getMainGrid());
        tabs[1].setContent(existCand.getMainGrid());

        vbox = new VBox(tabPane);
    }

    public VBox getMainGrid() { return vbox; }

    //================================================

    public void addLogic(Controller cont) {
        List parties = cont.getModel().getPoliticalParties();
        String[] partyNames = new String[parties.size()];

        for (int i = 0; i <parties.size(); i++)
            partyNames[i] = ((PoliticalParty)parties.get(i)).getName();

        initChoiceBox((ChoiceBox<String>)newCand.form.get("Party Name"), partyNames);
        initChoiceBox((ChoiceBox<String>)existCand.form.get("Party Name"), partyNames);

        newCand.getConfirm().setOnAction(e -> newCand.checkCriteria(cont));
        existCand.getConfirm().setOnAction(e -> existCand.checkCriteria(cont));
    }

    private void initChoiceBox(ChoiceBox<String> choiceBox, String[] list) {
        choiceBox.getItems().addAll(list);
        choiceBox.setValue(list[1]);
    }

    //================================================

}
