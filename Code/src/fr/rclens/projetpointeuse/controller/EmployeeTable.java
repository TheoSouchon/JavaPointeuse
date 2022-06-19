package fr.rclens.projetpointeuse.controller;

import java.util.ArrayList;
import java.util.List;

import fr.rclens.projetpointeuse.facade.IEmployee;
import fr.rclens.projetpointeuse.model.Department;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Entity class to stock the employee information to display in the viewTable from model and facade
 */
public class EmployeeTable {

	private IEmployee employee;
	private StringProperty UUIDProp;
	private StringProperty firstNameProp;
	private StringProperty lastNameProp;
	private StringProperty deptProp;
	private StringProperty missHoursProp;
	private List<TimeSheetTable> timeSheetTableList;
	
	/**
	 * Constructor of {@link EmployeeTable}
	 * @param employeePass
	 */
	public EmployeeTable(IEmployee employeePass) {
		this.employee = employeePass;
		this.setUUIDProp(employee.getUUID().toString());
		this.setfirstNameProp(employee.getFirstname());
		this.setlastNameProp(employee.getLastName());
		this.setdeptProp(employee.getDepartment().getName());
		if (employee.getHours().isZero()) {
			this.setmissHoursProp("No");
		}
		else {
			this.setmissHoursProp("Yes");
		}
		this.timeSheetTableList = new ArrayList<>();
		for (int i = 1; i < 6; i++) {
			this.timeSheetTableList.add(new TimeSheetTable(this.employee, i));
		}
	}
	
	/**
	 * Get the employee
	 * @return employee : IEmployee
	 */
	public IEmployee getEmployee() {
		return employee;
	}


	/**
	 * Set the employee
	 * @param employee : IEmployee (the employee to set)
	 */
	public void setEmployee(IEmployee employee) {
		this.employee = employee;
	}

	
	/**
	 * Get the list of TimeSheet
	 * @return timeSheetTableList
	 */
	public List<TimeSheetTable> getTimeSheetTableList() {
		return timeSheetTableList;
	}

	/**
	 * Set the list of all TimeSheet
	 * @param timeSheetTableList
	 */
	public void setTimeSheetTableList(List<TimeSheetTable> timeSheetTableList) {
		this.timeSheetTableList = timeSheetTableList;
	}


	/**
	 * Set the UUID
	 * @param value : String (the UUID)
	 */
	public void setUUIDProp(String value) { 
		UUIDPropProperty().set(value);
		//this.employee.setUUID(UUID.fromString(value));
	}
	
	/**
	 * Get the UUID
	 * @return String (the UUID)
	 */
    public String getUUIDProp() { 
    	return UUIDPropProperty().get(); 
    }
    
    /**
     * Convert the UUID as a simpleStringProperty for the view
     * @return the UIID
     */
    public StringProperty UUIDPropProperty() { 
        if (UUIDProp == null) {
        	UUIDProp = new SimpleStringProperty(this, "UUID");
        }
        return UUIDProp; 
    }
    
    /***
     * Set the firstName
     * @param value : String (the firstName to set)
     */
    public void setfirstNameProp(String value) { 
    	firstNamePropProperty().set(value);
		this.employee.setFirstname(value);
	}
    
    /**
     * get the firstName
     * @return String (the firstName)
     */
    public String getfirstNameProp() { 
    	return firstNamePropProperty().get(); 
    }
    
    /**
     * Convert the firstName as a StringProperty for the view
     * @return firstName
     */
    public StringProperty firstNamePropProperty() { 
        if (firstNameProp == null) {
        	firstNameProp = new SimpleStringProperty(this, "First Name");
        }
        return firstNameProp; 
    }
    
    /***
     * Set the lastName
     * @param value : String (thelastName to set)
     */
    public void setlastNameProp(String value) { 
    	lastNamePropProperty().set(value);
		this.employee.setLastName(value);
	}
    
    /**
     * Get the firstName
     * @return String (the lastName)
     */
    public String getlastNameProp() { 
    	return lastNamePropProperty().get(); 
    }
    
    /**
     * Convert the lastName as a StringProperty for the view
     * @return lastName
     */
    public StringProperty lastNamePropProperty() { 
        if (lastNameProp == null) {
        	lastNameProp = new SimpleStringProperty(this, "Last Name");
        }
        return lastNameProp; 
    }
    
    /**
     * Set the department
     * @param value : String (Department)
     */
    public void setdeptProp(String value) { 
    	deptPropProperty().set(value);
		this.employee.addDepartment(new Department(value));
	}
    
    /**
     * Get the department
     * @return String (department)
     */
    public String getdeptProp() { 
    	return deptPropProperty().get(); 
    }
    
    /**
     * Convert the department as a StringProperty for the view
     * @return department
     */
    public StringProperty deptPropProperty() { 
        if (deptProp == null) {
        	deptProp = new SimpleStringProperty(this, "Department");
        }
        return deptProp; 
    }
    
    /**
     * Set the number of miss hours via the view
     * @param value : String (Text to display in the view)
     */
    public void setmissHoursProp(String value) { 
    	missHoursPropProperty().set(value);
	}
    
    /**
     * Get the miss Hours from the view
     * @return String
     */
    public String getmissHoursProp() { 
    	return missHoursPropProperty().get(); 
    }
    
    /**
     * Convert the missHours as a StringProperty for the view
     * @return missHoursProp
     */
    public StringProperty missHoursPropProperty() { 
        if (missHoursProp == null) {
        	missHoursProp = new SimpleStringProperty(this, "No");
        }
        return missHoursProp; 
    }

}
