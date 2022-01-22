import Controller.Controller;

import Database.DB;
import Model.Model;
import View.View;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Program extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please Enter DB password: ");
        DB.init(scan.next());

        Model model = new Model();
        View view = new View(model.getLists());
        Controller controller = new Controller(model,view);

    }
}