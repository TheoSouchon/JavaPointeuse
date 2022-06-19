package fr.rclens.projetpointeuse.controler;

import fr.rclens.projetpointeuse.view.ViewTimeClock;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerConfigIpPort implements EventHandler<ActionEvent> {
	
	private ViewTimeClock viewTimeClock;
	
	/**
	 * Create the controller
	 * @param viewTimeClock
	 */
	public ControllerConfigIpPort(ViewTimeClock viewTimeClock) {
		this.viewTimeClock = viewTimeClock;
	}
	
	/**
	 * Create and display the settings window
	 */
	@Override
	public void handle(ActionEvent event) {
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.TOP_CENTER);
		
		Text TIp = new Text("IP Address : ");
		TextField TFIp = new TextField(this.viewTimeClock.getIpAdress());
		Text TPort = new Text("Port : ");
		TextField TFPort = new TextField(((Integer)this.viewTimeClock.getPort()).toString());
		
		Button BSave = new Button("Save configuration");
		BSave.setOnAction(new ControllerSaveNetworkConfig(this.viewTimeClock, TFIp, TFPort));
		
		vBox.getChildren().addAll(TIp, TFIp, TPort, TFPort, BSave);		
		Scene scene = new Scene(vBox, 500, 400);
		
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
	
	
}
