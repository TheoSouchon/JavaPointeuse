package fr.rclens.projetpointeuse.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import fr.rclens.projetpointeuse.facade.EmployeeManager;
import fr.rclens.projetpointeuse.facade.IDay;
import fr.rclens.projetpointeuse.facade.IEmployee;
import fr.rclens.projetpointeuse.model.Company;
import fr.rclens.projetpointeuse.model.Employee;
import fr.rclens.projetpointeuse.model.TCPServer;
import fr.rclens.projetpointeuse.stub.Stub;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

/**
 * Entity class to represent our principal controller view
 */
public class WindowController {

	private EmployeeManager employeeManager;
	private List<IEmployee> employees;
	private List<EmployeeTable> employeesTableList;
	private ObservableList<EmployeeTable> employeesTableObsList;
	
	@FXML
	private TabPane IdTablePane;
	
	@FXML
	private TextField IdTextFieldIdEmployee2, IdTxtFieldFirstNameEmployee2, IdTxtFieldDept2,
			IdTxtFieldLastNameEmployee2, IdTxtFieldDate2, IdTxtFieldTime2, IdTxtFieldTimeInOut2, IdTxtFieldOnTime2,
			IdTxtFieldLastNameEmployee, IdTxtFieldIdEmployee, IdTxtFieldFirstNameEmployee, IdTxtFieldDate,
			IdTxtFieldTime, TFSettingsIpAddress, TFSettingsPort;

	@FXML
	private Button IdButtonRefreshTimeChecks, BSettingsSave, BSettingsCancel, ButtonRefreshEmployeeTable,
			ButtonAddEmployee;

	@FXML
	private RadioButton IdRadioButtonToday, IdRadioButtonAllTime;

	@FXML
	private GridPane IdGridPaneTimeChecks2; // de TimeChecks

	@FXML
	private TableView<TimeCheckTable> IdTableViewTimeCheck;

	@FXML
	private TableColumn<TimeCheckTable, String> IdTableColumnIdEmployee;
	@FXML
	private TableColumn<TimeCheckTable, String> IdTableColumnLastName;
	@FXML
	private TableColumn<TimeCheckTable, String> IdTableColumnFirstName;
	@FXML
	private TableColumn<TimeCheckTable, String> IdTableColumnDept;
	@FXML
	private TableColumn<TimeCheckTable, LocalDate> IdTableColumnDate;
	@FXML
	private TableColumn<TimeCheckTable, LocalTime> IdTableColumnTime;
	@FXML
	private TableColumn<TimeCheckTable, String> IdTableColumnInOut;
	@FXML
	private TableColumn<TimeCheckTable, String> IdTableColumnOnTime;

	@FXML
	private TableView<EmployeeTable> IdTableEmployee;
	@FXML
	private TableColumn<Employee, StringProperty> IdEmpTableColumnIdEmployee, IdEmpTableColumnLastName,
			IdEmpTableColumnFirstName, IdEmpTableColumnDept, IdEmpTableColumnMissingHours;
	@FXML
	private TableColumn<Employee, Button> IdEmpTableColumnDetails;
	
	@FXML ToggleGroup tgDate;

	// private ArrayList<Employee> listEmployee;

	private TCPServer tcpServer;

	/**
	 * Function called after fxml element loaded
	 */
	public void initialize() {
	
		IdRadioButtonToday.setSelected(false);
		IdRadioButtonAllTime.setSelected(true);

		this.employeeManager = Company.getInstance();
		this.employees = Stub.managerData().getAllEmployees();
		if (this.employees != null && !this.employees.isEmpty()) {
			for (int i = 0; i < this.employees.size(); i++) {
				System.out.println("ID EMP " + i + " : " + this.employees.get(i).getUUID().toString());
			}
		}

		// Time Checks
		this.setTimeChecksTable();

		//sort by date for checkings
		tgDate.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> changed, Toggle oldVal, Toggle newVal) {
				if(IdRadioButtonToday.isSelected()) {
					IdTableViewTimeCheck.setItems(getTodayEmployee());
				} else {
					IdTableViewTimeCheck.setItems(getEmployee());
				}
			}
		});
		
		// Employees
		this.setupEmployeeTableList();
		this.setEmployeeTable();
		this.ButtonRefreshEmployeeTable.setOnAction((ActionEvent eventRefreshEmp) -> {
			this.setupEmployeeTableList();
			this.refreshEmployeeTable();
		});
		this.ButtonAddEmployee.setOnAction(new ControllerEditEmployee(null, null, this));
		
		// Settings and server
		this.tcpServer = new TCPServer("localhost", 8080);
		new Thread(this.tcpServer).start();
		TFSettingsIpAddress.setText(tcpServer.getIpAddress());
		TFSettingsPort.setText(Integer.toString(tcpServer.getPort()));
		
		
		//Stage stage = (Stage) this.IdTablePane.getScene().getWindow();
		
	}
	
	/**
	 * Function to return the list of all Time Check to display
	 * @return ObservableList<TimeCheckTable>
	 */
	public ObservableList<TimeCheckTable> getEmployee() {

		ObservableList<TimeCheckTable> timeCheck = FXCollections.observableArrayList();
		for (IEmployee employee : employees) {

			for (IDay iday : employee.getAllDayCheck()) {
				if (iday.getArrival() != null) {

					if (iday.getOnTimeArrival()) {
						timeCheck.add(new TimeCheckTable(employee.getUUID().toString(), employee.getFirstname(),
								employee.getLastName(), employee.getDepartment().getName(), iday.getDate(),
								iday.getArrival(), "In", "Oui"));
					} else {
						timeCheck.add(new TimeCheckTable(employee.getUUID().toString(), employee.getFirstname(),
								employee.getLastName(), employee.getDepartment().getName(), iday.getDate(),
								iday.getArrival(), "In", "Non"));
					}
				}
				
				if (iday.getDeparture() != null) {
					if (iday.getOnTimeDeparture()) {
						timeCheck.add(new TimeCheckTable(employee.getUUID().toString(), employee.getFirstname(),
								employee.getLastName(), employee.getDepartment().getName(), iday.getDate(),
								iday.getDeparture(), "Out", "Oui"));
					} else {
						timeCheck.add(new TimeCheckTable(employee.getUUID().toString(), employee.getFirstname(),
								employee.getLastName(), employee.getDepartment().getName(), iday.getDate(),
								iday.getDeparture(), "Out", "Non"));
					}
				}

			}
		}
		return timeCheck;
	}

	/**
	 * Function to return the list of today Time Check to display
	 * @return ObservableList<TimeCheckTable>
	 */
	public ObservableList<TimeCheckTable> getTodayEmployee() {
		ObservableList<TimeCheckTable> timeCheck = FXCollections.observableArrayList();
		LocalDate now = java.time.LocalDate.now();
		for (IEmployee employee : employees) {
			if(employee.getDayCheck(now)!=null) {
				if(employee.getDayCheck(now).getArrival()!=null) {
					timeCheck.add(new TimeCheckTable(employee.getUUID().toString(),
							employee.getLastName(), employee.getFirstname(), employee.getDepartment().getName(), employee.getDayCheck(now).getDate(),
							employee.getDayCheck(now).getArrival(), "In", "Oui"));
				}
				if(employee.getDayCheck(now).getDeparture()!=null) {
					timeCheck.add(new TimeCheckTable(employee.getUUID().toString(),
							employee.getLastName(),employee.getFirstname(), employee.getDepartment().getName(), employee.getDayCheck(now).getDate(),
							employee.getDayCheck(now).getDeparture(), "Out", "Oui"));
				}
			}
		}
		return timeCheck;
		
	}


	/**
	 * Function to set up the column of the table view for the time checks
	 */
	public void setTimeChecksTable() {
		IdTableColumnIdEmployee.setCellValueFactory(new PropertyValueFactory<TimeCheckTable, String>("idEmployee"));
		IdTableColumnLastName.setCellValueFactory(new PropertyValueFactory<TimeCheckTable, String>("name"));
		IdTableColumnFirstName.setCellValueFactory(new PropertyValueFactory<TimeCheckTable, String>("firstname"));
		IdTableColumnDept.setCellValueFactory(new PropertyValueFactory<TimeCheckTable, String>("dept"));
		IdTableColumnDate.setCellValueFactory(new PropertyValueFactory<TimeCheckTable, LocalDate>("date"));
		IdTableColumnTime.setCellValueFactory(new PropertyValueFactory<TimeCheckTable, LocalTime>("time"));
		IdTableColumnInOut.setCellValueFactory(new PropertyValueFactory<TimeCheckTable, String>("in_out"));
		IdTableColumnOnTime.setCellValueFactory(new PropertyValueFactory<TimeCheckTable, String>("onTime"));
		IdTableViewTimeCheck.setItems(getEmployee());
	}

	/**
	 * Method to refresh the date in TableView for the time checks
	 */
	public void clickBtnRefreshTimeChecks() {

		IdTableViewTimeCheck.setItems(getEmployee());
	}

	
	/**
	 * Method to get the data of employeeTable
	 */
	public void setupEmployeeTableList() {
		this.employeesTableList = new ArrayList<>();
		for (IEmployee employee : this.employees) {
			employeesTableList.add(new EmployeeTable(employee));
		}
	}

	/**
	 * Method to refresh the employee table
	 */
	public void refreshEmployeeTable() {
		this.employeesTableObsList = FXCollections.observableArrayList(employeesTableList);
		this.IdTableEmployee.setItems(this.employeesTableObsList);
		if (this.IdTableEmployee.getColumns().size() == 6) {
			this.IdTableEmployee.getColumns().remove(5);
		}
		this.addButtonToTable();
	}

	/**
	 * Method to set up the columns of the TableView for the employee list
	 */
	public void setEmployeeTable() {
		this.employeesTableObsList = FXCollections.observableArrayList(employeesTableList);
		this.IdTableEmployee.setItems(this.employeesTableObsList);
		this.IdEmpTableColumnIdEmployee.setCellValueFactory(new PropertyValueFactory("UUIDProp"));
		this.IdEmpTableColumnFirstName.setCellValueFactory(new PropertyValueFactory("firstNameProp"));
		this.IdEmpTableColumnLastName.setCellValueFactory(new PropertyValueFactory("lastNameProp"));
		this.IdEmpTableColumnDept.setCellValueFactory(new PropertyValueFactory("deptProp"));
		this.IdEmpTableColumnMissingHours.setCellValueFactory(new PropertyValueFactory("missHoursProp"));
		this.addButtonToTable();
	}

	/**
	 * Method to add a button details on each row of the tableView of employee list
	 */
	private void addButtonToTable() {
		TableColumn<EmployeeTable, Void> colBtn = new TableColumn<>("Details");

		Callback<TableColumn<EmployeeTable, Void>, TableCell<EmployeeTable, Void>> cellFactory = new Callback<TableColumn<EmployeeTable, Void>, TableCell<EmployeeTable, Void>>() {
			@Override
			public TableCell<EmployeeTable, Void> call(final TableColumn<EmployeeTable, Void> param) {
				final TableCell<EmployeeTable, Void> cell = new TableCell<EmployeeTable, Void>() {

					private final Button btn = new Button("Details");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                        	EmployeeTable empTable = getTableView().getItems().get(getIndex());
                        	Stage stage = new Stage();
                        	VBox vBox = new VBox();
                    		vBox.setAlignment(Pos.CENTER);
                    		
                    		HBox firstLine = new HBox();
                    		firstLine.setAlignment(Pos.CENTER);
                        	Text TUUID = new Text("UUID : " + empTable.getUUIDProp());
                    		Text TDept = new Text("Department : " + empTable.getdeptProp());
                    		firstLine.getChildren().addAll(TUUID, TDept);
                    		HBox.setMargin(TUUID, new Insets(5, 15, 15, 5));
                    		HBox.setMargin(TDept, new Insets(5, 15, 0, 0));
                    		
                    		HBox secondLine = new HBox();
                    		secondLine.setAlignment(Pos.CENTER);
                        	Text TFirstName = new Text("First name : " + empTable.getfirstNameProp());
                    		Text TLastName = new Text("Last name : " + empTable.getlastNameProp());
                    		secondLine.getChildren().addAll(TFirstName, TLastName);
                    		HBox.setMargin(TFirstName, new Insets(0, 15, 15, 5));
                    		HBox.setMargin(TLastName, new Insets(0, 0, 15, 0));
                    		
                    		
                    		HBox thirdLine = new HBox();
                    		thirdLine.setAlignment(Pos.CENTER);
                        	Text THoursInStock = null;
                        	Duration duration = empTable.getEmployee().getHours();
                        	if (duration.isNegative()) {
                        		THoursInStock = new Text("Miss " + duration.toHours() + " hours to do");
                        	}
                        	else {
                        		THoursInStock = new Text("Have " + duration.toHours() + " hours in stock");
                        	}
                        	thirdLine.getChildren().addAll(THoursInStock);
                    		HBox.setMargin(THoursInStock, new Insets(0, 0, 15, 5));
                        	
                        	HBox fourthLine = new HBox();
                        	fourthLine.setAlignment(Pos.CENTER);
                        	TableView<TimeSheetTable> TVPlaning = new TableView<>();
                        	TVPlaning.setItems(FXCollections.observableArrayList(empTable.getTimeSheetTableList()));
                        	TableColumn<TimeSheetTable, StringProperty> TCDay = new TableColumn<>("Day");
                        	TCDay.setCellValueFactory(new PropertyValueFactory("day"));
                        	TableColumn<TimeSheetTable, StringProperty> TCTimeIn = new TableColumn<>("Time In");
                        	TCTimeIn.setCellValueFactory(new PropertyValueFactory("arrivalTime"));
                        	TableColumn<TimeSheetTable, StringProperty> TCTimeOut = new TableColumn<>("Time Out");
                        	TCTimeOut.setCellValueFactory(new PropertyValueFactory("departureTime"));
                        	TVPlaning.getColumns().addAll(TCDay, TCTimeIn, TCTimeOut);
                        	fourthLine.getChildren().addAll(TVPlaning);
                    		
                    		HBox lastLine = new HBox();
                    		lastLine.setAlignment(Pos.CENTER);
                    		Button BEdit = new Button("Edit employee");
                    		BEdit.setOnAction(new ControllerEditEmployee(empTable, stage, null));
                    		Button BDelete = new Button("Delete employee");
                    		BDelete.setOnAction((ActionEvent event2) -> {
                    			employeeManager.deleteEmployee(empTable.getEmployee().getUUID());
                    			setupEmployeeTableList();
                    			refreshEmployeeTable();
                    			stage.close();
                    		});
                    		lastLine.getChildren().addAll(BEdit, BDelete);
                    		HBox.setMargin(BEdit, new Insets(15, 15, 0, 5));
                    		HBox.setMargin(BDelete, new Insets(15, 0, 0, 0));
                    		
                    		vBox.getChildren().addAll(firstLine, secondLine, thirdLine, fourthLine, lastLine);		
                    		Scene scene = new Scene(vBox, 500, 400);
                    		
                    		stage.setScene(scene);
                    		stage.setTitle("Employee Details");
                    		stage.show();
                            //System.out.println("selectedData: " + empTable);
                        });
                    }

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		};
		colBtn.setCellFactory(cellFactory);
		this.IdTableEmployee.getColumns().add(colBtn); // <TableColumn prefWidth="100.0" text="Details"
														// fx:id="IdEmpTableColumnDetails"/>
	}

	
	/**
	 * Method to cancel the modification of parameters
	 */
	public void settingsCancel() {
		TFSettingsIpAddress.setText(tcpServer.getIpAddress());
		TFSettingsPort.setText(Integer.toString(tcpServer.getPort()));
	}

	/**
	 * Method to save the modification of parameters
	 */
	public void settingsSave() {
		int port = 0;
		try {
			port = Integer.parseInt(TFSettingsPort.getText());
		} catch (NumberFormatException e) {
			alertIncorrectPort();
			return;
		}
		// this.tcpServer.stop();
		tcpServer.changeAddress(TFSettingsIpAddress.getText(), port);
		new Thread(this.tcpServer).start();
	}

	/**
	 * Method to display an error if the port is already in used
	 */
	public void alertIncorrectPort() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Config Network");
		alert.setHeaderText("Incorrect port.");
		alert.setContentText("The port you entered isn't a number. Please try again.");
		alert.showAndWait();
	}

}
