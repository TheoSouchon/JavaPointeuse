package fr.rclens.projetpointeuse.controler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import fr.rclens.projetpointeuse.facade.ICheck;
import fr.rclens.projetpointeuse.model.Check;
import fr.rclens.projetpointeuse.model.ClientTimeCheck;
import fr.rclens.projetpointeuse.view.ViewTimeClock;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class ControllerSendTimeCheck implements EventHandler<ActionEvent> {
	
	private ViewTimeClock viewTimeClock;
	private TextField TFUid;
	
	
	/**
	 * Create the controller with the references to the viewTimeClock and the UUID textfield
	 * @param viewTimeClock
	 * @param TFUid
	 */
	public ControllerSendTimeCheck(ViewTimeClock viewTimeClock, TextField TFUid) {
		this.viewTimeClock = viewTimeClock;
		this.TFUid = TFUid;
	}

	
	/**
	 * Handle the creation of the thread that send the time checks to the main app
	 */
	@Override
	public void handle(ActionEvent event) {
		UUID uuid = UUID.fromString(TFUid.getText());
		LocalDateTime roundedTime = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).plusMinutes(15 * (LocalDateTime.now().getMinute() / 15));
        if ((LocalDateTime.now().getMinute() % 15) > 7) {
        	roundedTime = roundedTime.plusMinutes(15);
        }
		ICheck check = new Check(uuid, roundedTime);
		new Thread(new ClientTimeCheck(viewTimeClock.getIpAdress(),
				viewTimeClock.getPort(), check)).start();
		TFUid.setText("");
	}
	

}
