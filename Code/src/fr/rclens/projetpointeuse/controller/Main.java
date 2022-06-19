package fr.rclens.projetpointeuse.controller;

import java.io.File;

import fr.rclens.projetpointeuse.facade.EmployeeManager;
import fr.rclens.projetpointeuse.model.Company;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Entity class Main for the view
 */
public class Main extends Application {

	/**
	 * Method called to start the view
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		java.net.URL url = new File("src/fr/rclens/projetpointeuse/view/Window.fxml").toURI().toURL();

		Parent root = FXMLLoader.load(url);

		primaryStage.setTitle("ManagerPointeuse");
		Scene scene = new Scene(root);
		
		EmployeeManager empManager = Company.getInstance();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		    	empManager.saveData("test.dat");
		    	//tcpServer.stop();
		    	Platform.exit();
		    }
		});
		
		
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
