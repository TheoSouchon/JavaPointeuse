package fr.rclens.projetpointeuse.controller;

import java.time.DayOfWeek;
import java.time.LocalTime;

import fr.rclens.projetpointeuse.facade.IEmployee;
import fr.rclens.projetpointeuse.facade.ITimeSheet;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Entity class to stock the Time sheet information to display in the viewTable from model and facade
 */
public class TimeSheetTable {
	
	private IEmployee employee;
	private StringProperty day;
	private int dayNum;
	private StringProperty arrivalTime;
	private StringProperty departureTime;
	
	/**
	 * Constructor of {@link TimeSheetTable}
	 * @param emp : IEmployee
	 * @param dayNumber : int
	 */
	public TimeSheetTable(IEmployee emp, int dayNumber) {
		this.employee = emp;
		this.dayNum = dayNumber;
		switch(dayNum) {
			case 1:
				setday("Monday");
				break;
			case 2:
				setday("Tuesday");
				break;
			case 3:
				setday("Wednesday");
				break;
			case 4:
				setday("Thursday");
				break;
			case 5:
				setday("Friday");
				break;
			default:
				throw new IllegalArgumentException("Day must be between 1 and 5");
		}
		this.setarrivalTime(this.employee.getDayPlaning(dayNum).getArrival().toString());
		this.setdepartureTime(this.employee.getDayPlaning(dayNum).getDeparture().toString());
	}
	
	
	/**
	 * Method to set day
	 * @param value : String (day of the week)
	 */
	public void setday(String value) { 
		dayProperty().set(value);
	}
	
	/**
	 * Get the day property
	 * @return String
	 */
    public String getdayProp() { 
    	return dayProperty().get(); 
    }
    
    /**
     * Convert the day as a SimpleStringProperty for the view
     * @return day
     */
    public StringProperty dayProperty() { 
        if (day == null) {
        	day = new SimpleStringProperty(this, "Monday");
        }
        return day; 
    }
    
    
    /**
     * Set the arrival Time
     * @param value : String (the arrival)
     */
    public void setarrivalTime(String value) { 
    	arrivalTimeProperty().set(value);
    	ITimeSheet ts = this.employee.getPlaning().getDayTimeSheet(DayOfWeek.of(dayNum));
    	ts.setArrival(LocalTime.parse(value));
    	this.employee.getPlaning().setDayTimeSheet(DayOfWeek.of(dayNum), ts);
		//this.employee.setUUID(UUID.fromString(value));
	}
    
    /**
     * Get the arrival Time
     * @return String
     */
    public String getarrivalTime() { 
    	return arrivalTimeProperty().get(); 
    }
    
    /**
     * Convert the arrival Time as a SimpleStringProperty for the view
     * @return arrivalTime
     */
    public StringProperty arrivalTimeProperty() { 
        if (arrivalTime == null) {
        	arrivalTime = new SimpleStringProperty(this, "8:00");
        }
        return arrivalTime; 
    }
    
    /**
     * Set the departure Time
     * @param value : String (the departure)
     */
    public void setdepartureTime(String value) { 
    	departureTimeProperty().set(value);
    	ITimeSheet ts = this.employee.getPlaning().getDayTimeSheet(DayOfWeek.of(dayNum));
    	ts.setDeparture(LocalTime.parse(value));
    	this.employee.getPlaning().setDayTimeSheet(DayOfWeek.of(dayNum), ts);
	}
    
    /**
     * Get the departure Time
     * @return String
     */
    public String getdepartureTime() { 
    	return departureTimeProperty().get(); 
    }
    
    /**
     * Convert the departureTime as a SimpleStringProperty for the view
     * @return departureTime
     */
    public StringProperty departureTimeProperty() { 
        if (departureTime == null) {
        	departureTime = new SimpleStringProperty(this, "17:00");
        }
        return departureTime; 
    }
    
}
