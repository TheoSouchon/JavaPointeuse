package fr.rclens.projetpointeuse.controler;
import fr.rclens.projetpointeuse.view.ViewTimeClock;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerSaveNetworkConfig implements EventHandler<ActionEvent> {
	
	private ViewTimeClock viewTimeClock;
	private TextField TFIp;
	private TextField TFPort;
	
	/**
	 * Create the controller
	 * @param viewTimeClock
	 * @param TFIp
	 * @param TFPort
	 */
	public ControllerSaveNetworkConfig(ViewTimeClock viewTimeClock, TextField TFIp, TextField TFPort) {
		this.viewTimeClock = viewTimeClock;
		this.TFIp = TFIp;
		this.TFPort = TFPort;
	}

	
	/**
	 * Handle the save of the network config of the Time Clock
	 */
	@Override
	public void handle(ActionEvent event) {
		int port = this.viewTimeClock.getPort();
		try {
			port = Integer.parseInt(this.TFPort.getText());
		}
		catch (NumberFormatException e) {
			alertIncorrectPort();
			return;
		}
		this.viewTimeClock.setIpAdress(this.TFIp.getText());
		this.viewTimeClock.setPort(port);
		((Stage)((Button)(event.getSource())).getScene().getWindow()).close();
	}
	
	/**
	 * Display an alert saying that the port is not a number
	 */
	public void alertIncorrectPort() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Config Network");
        alert.setHeaderText("Incorrect port.");
        alert.setContentText("The port you entered isn't a number. Please try again.");
        alert.showAndWait();
    }

	
}
