package fr.rclens.projetpointeuse.controller;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import fr.rclens.projetpointeuse.facade.EmployeeManager;
import fr.rclens.projetpointeuse.facade.IDepartment;
import fr.rclens.projetpointeuse.facade.IEmployee;
import fr.rclens.projetpointeuse.model.Company;
import fr.rclens.projetpointeuse.model.Department;
import fr.rclens.projetpointeuse.model.TimeSheet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Entity class to controller of the Add_edit view
 */
public class ControllerSaveEmployee implements EventHandler<ActionEvent> {

	private EmployeeTable employee;
	private VBox vBox;
	private Stage detailsStage, editStage;
	private EmployeeManager empManager;
	private WindowController WC;
	
	/**
	 * Constructor of the controller	
	 * @param employee
	 * @param vBox
	 * @param detailsStage
	 * @param editStage
	 * @param WC
	 */
	public ControllerSaveEmployee(EmployeeTable employee, VBox vBox, Stage detailsStage, Stage editStage, WindowController WC) {
		this.employee = employee;
		this.vBox = vBox;
		this.detailsStage = detailsStage;
		this.editStage = editStage;
		this.empManager = Company.getInstance();
		this.WC = WC;
	}

	/**
	 * Detect if the save button is pressed
	 */
	@Override
	public void handle(ActionEvent event) {
		if (this.employee == null) {
			this.addEmployee();
		}
		else {
			this.saveEditedEmployee();
		}
	}
	
	/**
	 * check integrity of field before saving the employee
	 */
	public void saveEditedEmployee() {
		if (!arePlanningTextFieldsOK()) {
			alertIncorrectTimeFormat();
			return;
		}
		else if (!areTextFieldsFilled()) {
			alertTextFieldEmpty();
			return;
		}
		// Save department
		HBox hb = (HBox) this.vBox.getChildren().get(0);
		TextField TF = (TextField) hb.getChildren().get(2);
		this.employee.setdeptProp(TF.getText());
		
		// Save name
		hb = (HBox) this.vBox.getChildren().get(1);
		TF = (TextField) hb.getChildren().get(1);
		this.employee.setfirstNameProp(TF.getText());
		TF = (TextField) hb.getChildren().get(3);
		this.employee.setlastNameProp(TF.getText());
		
		// Save planning
		hb = (HBox) this.vBox.getChildren().get(3);
		VBox vb;
		for (int i = 1; i < 6; i++) {
			vb = (VBox) hb.getChildren().get(1);
			TF = (TextField) vb.getChildren().get(i);
			this.employee.getTimeSheetTableList().get(i-1).setarrivalTime(TF.getText());
			vb = (VBox) hb.getChildren().get(2);
			TF = (TextField) vb.getChildren().get(i);
			this.employee.getTimeSheetTableList().get(i-1).setdepartureTime(TF.getText());
		}
		// Close the windows
		this.editStage.close();
		this.detailsStage.close();
	}
	
	/**
	 * Add the employee after integrity check of fields
	 */
	public void addEmployee() {
		if (!arePlanningTextFieldsOK()) {
			alertIncorrectTimeFormat();
			return;
		}
		else if (!areTextFieldsFilled()) {
			alertTextFieldEmpty();
			return;
		}
		// Save department
		HBox hb = (HBox) this.vBox.getChildren().get(0);
		TextField TF = (TextField) hb.getChildren().get(2);
		String dept = TF.getText();
		
		// Save name
		hb = (HBox) this.vBox.getChildren().get(1);
		TF = (TextField) hb.getChildren().get(1);
		String firstName = TF.getText();
		TF = (TextField) hb.getChildren().get(3);
		String lastName = TF.getText();
		
		// Create employee
		IEmployee emp = this.empManager.createEmployee(firstName, lastName);
		IDepartment department = this.empManager.getDepartment(dept);
		if (department == null) {
			this.empManager.createDepartment(dept);
			this.empManager.getDepartment(dept).addEmployee(emp);
		}
		else {
			department.addEmployee(emp);
		}
		
		// Save planning
		hb = (HBox) this.vBox.getChildren().get(3);
		VBox vb;
		LocalTime LTA, LTD;
		for (int i = 1; i < 6; i++) {
			vb = (VBox) hb.getChildren().get(1);
			TF = (TextField) vb.getChildren().get(i);
			LTA = LocalTime.parse(TF.getText());
			vb = (VBox) hb.getChildren().get(2);
			TF = (TextField) vb.getChildren().get(i);
			LTD = LocalTime.parse(TF.getText());
			emp.getPlaning().setDayTimeSheet(DayOfWeek.of(i), new TimeSheet(LTA, LTD));
		}
		
		// Close the windows and update employee list
		this.WC.refreshEmployeeTable(); // DOESNT WORKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK
		this.editStage.close();
	}
	
	/**
	 * Check if the text fields are empty or not
	 * @return true : filled , false : empty
	 */
	public boolean areTextFieldsFilled() {
		HBox hb = (HBox) this.vBox.getChildren().get(0);
		TextField TF = (TextField) hb.getChildren().get(2);
		if (TF.getText() == "") {
			return false;
		}
		hb = (HBox) this.vBox.getChildren().get(1);
		TF = (TextField) hb.getChildren().get(1);
		if (TF.getText() == "") {
			return false;
		}
		TF = (TextField) hb.getChildren().get(3);
		if (TF.getText() == "") {
			return false;
		}
		return true;
	}
	
	/**
	 * Check if it respect all integrity checks
	 * @return true : it respects , false : not
	 */
	public boolean arePlanningTextFieldsOK() {
		HBox hBox = (HBox) this.vBox.getChildren().get(3);
		try {
			LocalTime LTA, LTD;
			VBox vb;
			TextField TF;
			for (int i = 1; i < 6; i++) {
				vb = (VBox) hBox.getChildren().get(1);
				TF = (TextField) vb.getChildren().get(i);
				LTA = LocalTime.parse(TF.getText());
				vb = (VBox) hBox.getChildren().get(2);
				TF = (TextField) vb.getChildren().get(i);
				LTD = LocalTime.parse(TF.getText());
				if (LTD.compareTo(LTA) < 0) {
					return false;
				}
			}
		}
		catch(DateTimeParseException DPE) {
			return false;
		}
		return true;
	}
	
	/**
	 * Method to display if an error occurs during the edit or save of an employee
	 */
	public void alertIncorrectTimeFormat() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Editing or adding employee");
		alert.setHeaderText("Incorrect time.");
		alert.setContentText("The time you entered in one of the planning field isn't reconized as a time or "
				+ "a departure time is inferior to an arrival time, times must"
				+ "have the formet HH:MM. Please try again.");
		alert.showAndWait();
	}
	
	/**
	 * Method to display if we try to edit or save with empty fields
	 */
	public void alertTextFieldEmpty() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Editing or adding employee");
		alert.setHeaderText("Empty text field.");
		alert.setContentText("One of the text field is empty. Please fill it and try again.");
		alert.showAndWait();
	}
}
