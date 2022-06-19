package fr.rclens.projetpointeuse.model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.UUID;

import fr.rclens.projetpointeuse.facade.EmployeeManager;
import fr.rclens.projetpointeuse.facade.ICheck;
import fr.rclens.projetpointeuse.facade.IDay;
import fr.rclens.projetpointeuse.facade.IDepartment;
import fr.rclens.projetpointeuse.facade.IEmployee;
import fr.rclens.projetpointeuse.facade.IPlanning;
import fr.rclens.projetpointeuse.facade.ITimeSheet;

/**
 * Entity class to represent an employee
 */
public class Employee implements IEmployee, Serializable {
	private static final long serialVersionUID = -1543323551413494541L;
	private UUID idEmployee;
	private IDepartment department;
	private Duration stockHour;
	private IPlanning planning;
	private String name;
	private String firstname;
	private Map<LocalDate, IDay> calendar;
	
	/**
	 * @brief constructor for the class Employee
	 * 
	 * @throw NullPointerException exception thrown when parameter firstname or name is null
	 * 
	 * @param firstname represent the firstname of an employee
	 * @param name represent the lastname of an employee
	 */
	public Employee(String firstname, String name) {
		Objects.requireNonNull(firstname);
		Objects.requireNonNull(name);
		this.firstname = firstname;
		this.name = name;
		department = new Department("None");
		stockHour = Duration.ZERO;
		planning = new Planning();
		calendar = new TreeMap<LocalDate, IDay>();
	}

	/**
	 * @brief get the employee's unique UUID
	 * @return return the unique UUID of the employee
	 */
	@Override
	public UUID getUUID() {
		return idEmployee;
	}
	
	
	/**
	 * @brief set the UUID of an employee, this value can be set only once.
	 * @throws IllegalStateException exception thrown when the UUID have already been set
	 * @throws NullPointerException exception thrown if parameter id is null 
	 * @param id
	 */
	public void setUUID(UUID id) {
		Objects.requireNonNull(id);
		if(idEmployee != null) {
			throw new IllegalStateException("cannot reset the UUID of an employee");
		}
		idEmployee = id;
	}
	
	/**
	 * @brief get the actual department of an employee
	 * @return return the actual department of an employee or null if this employee isn't on a department
	 */
	@Override
	public IDepartment getDepartment() {
		return department;
	}
	
	/**
	 * @brief get the hour's stock of an employee
	 * @return return the hours's stock of the employee
	 */
	public Duration getHours() {
		return stockHour;
	}
	
	/**
	 * @brief get the name of an employee
	 * @return return the name of the employee
	 */
	@Override
	public String getLastName() {
		return name;
	}
	
	/**
	 * @brief get the first name of an employee
	 * @return return the first name of the employee
	 */
	@Override
	public String getFirstname() {
		return firstname;
	}
	
	/**
	 * @brief set the last name for an employee
	 * @param name the last name to set to the employee
	 */
	@Override
	public void setLastName(String name) {
		this.name = name;
	}
	
	/**
	 * @brief set the first name for an employee
	 * @param firstname the first name to set to the employee
	 */
	@Override
	public void setFirstname(String firstname) {
		this.firstname = firstname;
		
	}
	
	/**
	 * @brief Get the planing of an employee
	 * @return a IPlanning (the planing of the employee)
	 */
	public IPlanning getPlaning() {
		return this.planning;
	}
	
	/**
	 * @brief Set the planing of an employee
	 * @param planning : IPlanning (the planing of the employee)
	 */
	public void setPlaning(IPlanning planning) {
		this.planning = planning;
	}
	
	/**
	 * Re-define the comparator method equals for the {@link Employee}
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof IEmployee)) {
			return false;
		}
		IEmployee emp = (IEmployee)obj;
		if(emp.getFirstname().equals(firstname) &&
			emp.getLastName().equals(name) &&
			emp.getUUID().equals(idEmployee)) {
			return true;
		}
		return false;
	}

	/**
	 * @brief Get the day information for an employee
	 * @param date : LocalDate (the date of day wanted)
	 * @return a IDay (the information of the day)
	 */
	@Override
	public IDay getDayCheck(LocalDate date) {
		return calendar.get(date);
	}
	
	/**
	 * @brief remove the department of this {@link Employee}
	 */
	public void removeDepartment() {
		department = null;
	}
	
	/**
	 * @brief Add a department for this {@link Employee}
	 * @param departmentParam : IDepartment (the department attach to the employee)
	 */
	public void addDepartment(IDepartment departmentParam) {
		department = departmentParam;
	}
	
	/**
	 * @brief Add a time check sheet for a employee, to track his entry and exit in the company
	 * @param check : ICheck (the time check sheet for tracking the employee)
	 */
	synchronized public void addTimeSheetToCalendar(ICheck check) {
		LocalDate date = check.getDate().toLocalDate();
		LocalTime time = check.getDate().toLocalTime();
		EmployeeManager emp = Company.getInstance();
		if (calendar.containsKey(date)) {
			if (calendar.get(date).getDeparture() == null) {
				LocalTime departure = emp.getEmployee(check.getUUID()).getPlaning().getDayTimeSheet(check.getDate().getDayOfWeek()).getDeparture();
				calendar.get(date).setDeparture(time);
				if(departure.equals(time)) {
					((Day)calendar.get(date)).setOnTimeDeparture(true);
				} else {
					((Day)calendar.get(date)).setOnTimeDeparture(false);
					Duration check1 = Duration.ofHours(time.getHour());
					check1 = check1.plusMinutes(time.getMinute());
					Duration planningDeparture = Duration.ofHours(departure.getHour()); 
					planningDeparture = planningDeparture.plusMinutes(departure.getMinute());
					Duration hourGap = planningDeparture.minus(check1);
					stockHour = stockHour.minus(hourGap);
				}
			}
		} else {
			calendar.put(date, new Day(time, null, date));
			LocalTime arrival = emp.getEmployee(check.getUUID()).getPlaning().getDayTimeSheet(check.getDate().getDayOfWeek()).getArrival();
			if(arrival.equals(time)) {
				((Day)calendar.get(date)).setOnTimeArrival(true);
			} else {
				((Day)calendar.get(date)).setOnTimeArrival(false);
				Duration check1 = Duration.ofHours(time.getHour());
				check1 = check1.plusMinutes(time.getMinute());
				Duration planningArrival = Duration.ofHours(arrival.getHour()); 
				planningArrival = planningArrival.plusMinutes(arrival.getMinute());
				Duration hourGap = planningArrival.minus(check1);
				stockHour = stockHour.plus(hourGap);
			}
		}
	}
	
	
	/**
	 * Get every time check sheets of the {@link Employee}
	 * @return List<IDay> (the list of all time check sheets)
	 */
	public List<IDay> getAllDayCheck(){
		List<IDay> dayCheck = new ArrayList<IDay>(calendar.values());
		return dayCheck;
	}
	
	/**
	 * Get the time check sheet for a specific day
	 * @param day : int (the position of the day in the week 1 to 5)
	 * @return ITimeSheet (the time check sheet)
	 */
	public ITimeSheet getDayPlaning(int day) {
		DayOfWeek dow = null;
		switch(day) {
			case 1:
				dow = DayOfWeek.MONDAY;
				break;
			case 2:
				dow = DayOfWeek.TUESDAY;
				break;
			case 3:
				dow = DayOfWeek.WEDNESDAY;
				break;
			case 4:
				dow = DayOfWeek.THURSDAY;
				break;
			case 5:
				dow = DayOfWeek.FRIDAY;
				break;
			default:
				throw new IllegalArgumentException("Day must be between MONDAY and FRIDAY");
		}
		return this.planning.getDayTimeSheet(dow);
	}
	
	/**
	 * @brief get the employee's stock of hours
	 * @return return the stock of hour of the employee
	 */
	public Duration getStockHour() {
		return stockHour;
	}
}
