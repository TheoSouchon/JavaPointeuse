package fr.rclens.projetpointeuse.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import fr.rclens.projetpointeuse.controler.ControllerConfigIpPort;
import fr.rclens.projetpointeuse.controler.ControllerSendTimeCheck;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewTimeClock extends Application {
	
	private Scene scene;
	private String ipAdress;
	private int port;
	private Text TTime;
	private Text TTimeRounded;
	private Text TDate;
	
	
	/**
	 * Initialize some fields of the Time Clock
	 */
	@Override
	public void init() throws Exception {
		super.init();
		this.ipAdress = "localhost";
		this.port = 8080;
		this.TTime = new Text("7:35");
		this.TTimeRounded = new Text("(Rounded to 7:30)");
		this.TDate = new Text("April 4th, 2015");
		this.TDate.setFont(new Font(20));
		
		// Manage the display of the correct date and time
		AnimationTimer timer = new AnimationTimer() {
		    @Override
		    public void handle(long now) {
		        TTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
		        LocalDateTime roundedTime = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).plusMinutes(15 * (LocalDateTime.now().getMinute() / 15));
		        if ((LocalDateTime.now().getMinute() % 15) > 7) {
		        	roundedTime = roundedTime.plusMinutes(15);
		        }
		        TTimeRounded.setText("(Rounded to " + roundedTime.format(DateTimeFormatter.ofPattern("HH:mm")) + ")");
		        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("LLL dd, yyyy"));
		        TDate.setText(date);
		    }
		};
		timer.start();	
	}
	
	
	/**
	 * Create and display the Time Clock
	 */
	@Override
	public void start(Stage stage) throws Exception {
		VBox fp=new VBox(5);
        //fp.setStyle("-fx-background-color:#654343");
        fp.setAlignment(Pos.TOP_CENTER);
        fp.getChildren().addAll(this.timeClock());
        scene = new Scene(fp,600,500);
        stage.setTitle("Time Clock Emulator");
        stage.setScene(this.scene);
        stage.show();
	}
	
	
	
	/**
	 * Create the Time Clock
	 * @return an BorderPane that contain the the Time Clock
	 */
	public BorderPane timeClock() {
		BorderPane page = new BorderPane();
        page.setTop(this.theTop());
        page.setCenter(this.theCenter());
        page.setBottom(this.theBottom());
        return page;
	}
	
	
	/**
	 * Create the top of the Time Clock
	 * @return an HBox that contain the top line of the Time Clock
	 */
	public HBox theTop() {
		HBox box = new HBox();
		Button BConfig = new Button("Config IP/Port");
		BConfig.setOnAction(new ControllerConfigIpPort(this));
		box.getChildren().addAll(TDate, BConfig);
		HBox.setMargin(TDate, new Insets(5, 15, 15, 5));
		HBox.setMargin(BConfig, new Insets(5, 15, 15, 0));
		return box;
	}
	
	
	/**
	 * Create the center of the Time Clock
	 * @return an HBox that contain the middle line of the Time Clock
	 */
	public HBox theCenter() {
		HBox box = new HBox();	
		box.getChildren().addAll(TTime, TTimeRounded);
		HBox.setMargin(TTime, new Insets(0, 15, 15, 5));
		HBox.setMargin(TTimeRounded, new Insets(0, 15, 15, 0));
		return box;
	}
	
	
	/**
	 * Create the bottom of the Time Clock
	 * @return an HBox that contain the bottom line of the Time Clock
	 */
	public HBox theBottom() {
		HBox box = new HBox();
		TextField TFUid = new TextField();
		TFUid.setPromptText("User ID");
		
		Button BCheck = new Button("Check In/Out");
		BCheck.setOnAction(new ControllerSendTimeCheck(this, TFUid));
		box.getChildren().addAll(TFUid, BCheck);
		HBox.setMargin(TFUid, new Insets(0, 15, 15, 5));
		HBox.setMargin(BCheck, new Insets(0, 15, 15, 0));
		return box;
	}

	
	/**
	 * @return the IP Address
	 */
	public String getIpAdress() {
		return ipAdress;
	}
	
	/**
	 * Set the IP Address
	 * @param ipAdress the IP Address
	 */
	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
	
	/**
	 * Set the port
	 * @param port the port
	 */
	public void setPort(int port) {
		this.port = port;
	}
	
	
	
}
