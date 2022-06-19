package fr.rclens.projetpointeuse.controller;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Entity class to stock the Time checks information to display in the viewTable from model and facade
 */
public class TimeCheckTable {

	private String idEmployee;
	private String name;
	private String firstname;
	private String dept;
	private LocalDate date;
	private LocalTime time;
	private String in_out;
	private String onTime;

	/**
	 * Constructor of {@link TimeCheckTable}
	 * @param idEmployee : String
	 * @param name : String
	 * @param firstname : String
	 * @param dept : String
	 * @param date : LocalDate
	 * @param time : LocalTime
	 * @param in_out : String
	 * @param onTime : String
	 */
	public TimeCheckTable(String idEmployee, String name, String firstname, String dept, LocalDate date, LocalTime time,
			String in_out, String onTime) {
		this.idEmployee = idEmployee;
		this.name = name;
		this.firstname = firstname;
		this.dept = dept;
		this.date = date;
		this.time = time;
		this.in_out = in_out;
		this.onTime = onTime;
	}

	
	public TimeCheckTable() {

	}

	/**
	 * Get function for the id employee
	 * @return idEmployee
	 */
	public String getIdEmployee() {
		return idEmployee;
	}

	/**
	 * Set function of the id Employee
	 * @param idEmployee : String (id employee)
	 */
	public void setIdEmployee(String idEmployee) {
		this.idEmployee = idEmployee;
	}

	/**
	 * Get function for the lastName
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set function of lastName
	 * @param name : String (lastName)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get function for the firstName
	 * @return String
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Set function of firstName
	 * @param name : String (firstName)
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * Get function for the department
	 * @return String
	 */
	public String getDept() {
		return dept;
	}

	/**
	 * Set function of department
	 * @param name : String (department)
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}

	/**
	 * Get function for the date
	 * @return LocalDate
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Set function of date
	 * @param name : LocalDate (date)
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * Get function for the time
	 * @return LocalTime
	 */
	public LocalTime getTime() {
		return time;
	}

	/**
	 * Set function of time
	 * @param name : LocalTime (time)
	 */
	public void setTime(LocalTime time) {
		this.time = time;
	}

	/**
	 * Get function for the in_out
	 * @return String
	 */
	public String getIn_out() {
		return in_out;
	}

	/**
	 * Set function of in_out
	 * @param name : String (in_out)
	 */
	public void setIn_out(String in_out) {
		this.in_out = in_out;
	}

	/**
	 * Get function for the onTime
	 * @return String
	 */
	public String getOnTime() {
		return onTime;
	}

	/**
	 * Set function of onTime
	 * @param name : String (onTime)
	 */
	public void setOnTime(String onTime) {
		this.onTime = onTime;
	}
}
