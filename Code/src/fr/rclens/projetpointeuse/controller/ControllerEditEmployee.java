package fr.rclens.projetpointeuse.controller;

import java.time.Duration;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Entity class to controller of the Add_edit view
 */
public class ControllerEditEmployee implements EventHandler<ActionEvent> {
	
	private EmployeeTable employeeTable;
	private Stage detailsStage, actualStage;
	private WindowController WC;

	/**
	 * Controller of the view
	 * @param employee
	 * @param detailsStage
	 * @param WC
	 */
	public ControllerEditEmployee(EmployeeTable employee, Stage detailsStage, WindowController WC) {
		this.employeeTable = employee;
		this.detailsStage = detailsStage;
		this.WC = WC;
	}

	
	/**
	 * Method to display the view if it's editing or adding an employee
	 */
	@Override
	public void handle(ActionEvent event) {
    	this.actualStage = new Stage();
    	Scene scene;
    	if (this.employeeTable == null) {
    		scene = this.setupAddEmployee();
    		this.actualStage.setTitle("Add Employee");
    	}
    	else {
    		scene = this.setupEditEmployee();
    		this.actualStage.setTitle("Edit Employee");
    	}		
    	this.actualStage.setScene(scene);
    	this.actualStage.show();
	}
	
	/**
	 * Method to edit the employee via the view
	 * @return the scene
	 */
	public Scene setupEditEmployee() {
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		
		HBox firstLine = new HBox();
		firstLine.setAlignment(Pos.CENTER);
    	Text TUUID = new Text("UUID : " + this.employeeTable.getUUIDProp());
		Text TDept = new Text("Department : ");
		TextField TFDept = new TextField(this.employeeTable.getdeptProp());
		firstLine.getChildren().addAll(TUUID, TDept, TFDept);
		HBox.setMargin(TUUID, new Insets(5, 15, 15, 5));
		HBox.setMargin(TDept, new Insets(5, 0, 15, 0));
		HBox.setMargin(TFDept, new Insets(5, 0, 15, 0));
		
		HBox secondLine = new HBox();
		secondLine.setAlignment(Pos.CENTER);
    	Text TFirstName = new Text("First name : ");
    	TextField TFFirstName = new TextField(this.employeeTable.getfirstNameProp());
		Text TLastName = new Text("Last name : ");
		TextField TFLastName = new TextField(this.employeeTable.getlastNameProp());
		secondLine.getChildren().addAll(TFirstName, TFFirstName, TLastName, TFLastName);
		HBox.setMargin(TFirstName, new Insets(0, 0, 15, 5));
		HBox.setMargin(TFFirstName, new Insets(0, 15, 15, 0));
		HBox.setMargin(TLastName, new Insets(0, 0, 15, 0));
		HBox.setMargin(TFLastName, new Insets(0, 0, 15, 0));
		
		HBox thirdLine = new HBox();
		thirdLine.setAlignment(Pos.CENTER);
    	Text THoursInStock = null;
    	Duration duration = this.employeeTable.getEmployee().getHours();
    	if (duration.isNegative()) {
    		THoursInStock = new Text("Miss " + duration.toHours() + " hours to do");
    	}
    	else {
    		THoursInStock = new Text("Have " + duration.toHours() + " hours in stock");
    	}
    	thirdLine.getChildren().addAll(THoursInStock);
		HBox.setMargin(THoursInStock, new Insets(0, 0, 15, 0));
    	
    	
    	HBox planningBox = new HBox();
    	planningBox.setAlignment(Pos.CENTER);
    	
    	VBox firstColumnPlanning = new VBox();
    	Text TDay = new Text("Day");
    	Text TMonday = new Text("Monday");
    	Text TTuesday = new Text("Tuesday");
    	Text TWesneday = new Text("Wesneday");
    	Text TThursday = new Text("Thursday");
    	Text TFriday = new Text("Friday");
    	firstColumnPlanning.getChildren().addAll(TDay, TMonday, TTuesday, TWesneday, TThursday, TFriday);
		VBox.setMargin(TMonday, new Insets(4, 1, 0, 0));
		VBox.setMargin(TTuesday, new Insets(7, 1, 0, 0));
		VBox.setMargin(TWesneday, new Insets(7, 1, 0, 0));
		VBox.setMargin(TThursday, new Insets(8, 1, 0, 0));
		VBox.setMargin(TFriday, new Insets(8, 1, 0, 0));
		
    	
    	VBox secondColumnPlanning = new VBox();
    	VBox thirdColumnPlanning = new VBox();
    	Text TTimeIn = new Text("Time In");
    	Text TTimeOut = new Text("Time Out");
    	secondColumnPlanning.getChildren().add(TTimeIn);
    	thirdColumnPlanning.getChildren().add(TTimeOut);
    	for (int i = 0; i < 5; i++) {
    		TextField TF1 = new TextField(this.employeeTable.getTimeSheetTableList().get(i).getarrivalTime());
    		secondColumnPlanning.getChildren().add(TF1);
    		TextField TF2 = new TextField(this.employeeTable.getTimeSheetTableList().get(i).getdepartureTime());
    		thirdColumnPlanning.getChildren().add(TF2);
    	}
    	planningBox.getChildren().addAll(firstColumnPlanning, secondColumnPlanning, thirdColumnPlanning);
    	
		HBox lastLine = new HBox();
		lastLine.setAlignment(Pos.CENTER);
		Button BSave = new Button("Save modifications");
		BSave.setOnAction(new ControllerSaveEmployee(employeeTable, vBox, detailsStage, actualStage, this.WC));
		Button BCancel = new Button("Cancel modifications");
		BCancel.setOnAction((ActionEvent event2) -> {
			this.actualStage.close();
		});
		lastLine.getChildren().addAll(BSave, BCancel);
		HBox.setMargin(BSave, new Insets(15, 15, 0, 5));
		HBox.setMargin(BCancel, new Insets(15, 0, 0, 0));
		
		vBox.getChildren().addAll(firstLine, secondLine, thirdLine, planningBox, lastLine);		
		return new Scene(vBox, 500, 400);
	}
	
	/**
	 * Method to add the employee via the view
	 * @return the scene
	 */
	public Scene setupAddEmployee() {		
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		
		HBox firstLine = new HBox();
		firstLine.setAlignment(Pos.CENTER);
    	Text TUUID = new Text("UUID : None");
		Text TDept = new Text("Department : ");
		TextField TFDept = new TextField("");
		firstLine.getChildren().addAll(TUUID, TDept, TFDept);
		HBox.setMargin(TUUID, new Insets(5, 15, 15, 5));
		HBox.setMargin(TDept, new Insets(5, 0, 15, 0));
		HBox.setMargin(TFDept, new Insets(5, 0, 15, 0));
		
		HBox secondLine = new HBox();
		secondLine.setAlignment(Pos.CENTER);
    	Text TFirstName = new Text("First name : ");
    	TextField TFFirstName = new TextField("");
		Text TLastName = new Text("Last name : ");
		TextField TFLastName = new TextField("");
		secondLine.getChildren().addAll(TFirstName, TFFirstName, TLastName, TFLastName);
		HBox.setMargin(TFirstName, new Insets(0, 0, 15, 5));
		HBox.setMargin(TFFirstName, new Insets(0, 15, 15, 0));
		HBox.setMargin(TLastName, new Insets(0, 0, 15, 0));
		HBox.setMargin(TFLastName, new Insets(0, 0, 15, 0));
		
		HBox thirdLine = new HBox();
		thirdLine.setAlignment(Pos.CENTER);
    	Text THoursInStock = new Text("Have 0 hours in stock");
    	thirdLine.getChildren().addAll(THoursInStock);
		HBox.setMargin(THoursInStock, new Insets(0, 0, 15, 0));
    	
    	
    	HBox planningBox = new HBox();
    	planningBox.setAlignment(Pos.CENTER);
    	
    	VBox firstColumnPlanning = new VBox();
    	Text TDay = new Text("Day");
    	Text TMonday = new Text("Monday");
    	Text TTuesday = new Text("Tuesday");
    	Text TWesneday = new Text("Wesneday");
    	Text TThursday = new Text("Thursday");
    	Text TFriday = new Text("Friday");
    	firstColumnPlanning.getChildren().addAll(TDay, TMonday, TTuesday, TWesneday, TThursday, TFriday);
		VBox.setMargin(TMonday, new Insets(4, 1, 0, 0));
		VBox.setMargin(TTuesday, new Insets(7, 1, 0, 0));
		VBox.setMargin(TWesneday, new Insets(7, 1, 0, 0));
		VBox.setMargin(TThursday, new Insets(8, 1, 0, 0));
		VBox.setMargin(TFriday, new Insets(8, 1, 0, 0));
    	
    	VBox secondColumnPlanning = new VBox();
    	VBox thirdColumnPlanning = new VBox();
    	Text TTimeIn = new Text("Time In");
    	Text TTimeOut = new Text("Time Out");
    	secondColumnPlanning.getChildren().add(TTimeIn);
    	thirdColumnPlanning.getChildren().add(TTimeOut);
    	for (int i = 0; i < 5; i++) {
    		TextField TF1 = new TextField("08:00");
    		secondColumnPlanning.getChildren().add(TF1);
    		TextField TF2 = new TextField("17:00");
    		thirdColumnPlanning.getChildren().add(TF2);
    	}
    	planningBox.getChildren().addAll(firstColumnPlanning, secondColumnPlanning, thirdColumnPlanning);
    	
		HBox lastLine = new HBox();
		lastLine.setAlignment(Pos.CENTER);
		Button BSave = new Button("Save new employee");
		BSave.setOnAction(new ControllerSaveEmployee(null, vBox, detailsStage, actualStage, this.WC));
		Button BCancel = new Button("Cancel creation");
		BCancel.setOnAction((ActionEvent event2) -> {
			this.actualStage.close();
		});
		lastLine.getChildren().addAll(BSave, BCancel);
		HBox.setMargin(BSave, new Insets(15, 15, 0, 5));
		HBox.setMargin(BCancel, new Insets(15, 0, 0, 0));
		
		vBox.getChildren().addAll(firstLine, secondLine, thirdLine, planningBox, lastLine);		
		return new Scene(vBox, 500, 400);
	}
	
}
