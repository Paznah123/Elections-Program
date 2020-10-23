package View;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class View {

	private Stage mainWindow = new Stage();
	private BorderPane mainLayout = new BorderPane();
	private MainMenu menu = new MainMenu();
	private AnchorPane menuAnchor;
	
// -----------------------------------------------------------------------------------

	public View() {

		menuAnchor = menu.getMainMenu();
				
		mainLayout.setLeft(menuAnchor);
		
		Scene scene = new Scene(mainLayout,550,450);
		mainWindow.setTitle("Elections Program");
		mainWindow.setScene(scene);
		mainWindow.setResizable(false);
		mainWindow.show();
	}
	
// -----------------------------------------------------------------------------------

	public Stage getMainWindow() {
		return mainWindow;
	}

	public MainMenu getMenu() {
		return menu;
	}

	public BorderPane getMainLayout() {
		return mainLayout;
	}
				
}
