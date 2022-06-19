package fr.rclens.projetpointeuse.facade;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import fr.rclens.projetpointeuse.model.TimeSheet;

/**
 * Entity class to represent the interface of Employee
 */
public interface IEmployee {
	
	/**
	 * return the UUID of the employee
	 * @return the UUID of the employee
	 */
	public UUID getUUID();
	
	/**
	 * return the department of the employee
	 * @return the department of the employee 
	 */
	public IDepartment getDepartment();
	
	/**
	 * return the stock of hour of the employee
	 * @return the stock of hour of the employee
	 */
	public Duration getHours();
	
	/**
	 * return the lastname of the employee
	 * @return the lastname of the employee
	 */
	public String getLastName();
	
	/**
	 * return the firstname of the employee
	 * @return the firstname of the employee
	 */
	public String getFirstname();
	
	/**
	 * return the pair of arrival and departure check of the given date
	 * @param date the date to look for the pair of check
	 * @return the pair of arrival and departure check of the given date
	 */
	public IDay getDayCheck(LocalDate date);
	
	/**
	 * set the name (lastname) of the employee
	 * @param name the name to set to the employee
	 */
	public void setLastName(String name);
	
	/**
	 * set the firstname of the employee
	 * @param firstname the firstname to set to the employee
	 */
	public void setFirstname(String firstname);
	
	/**
	 * add the employee to the given department
	 * @param departmentParam the department where the employee while be affected
	 */
	public void addDepartment(IDepartment departmentParam);
	
	/**
	 * return all the check of the employee
	 * @return all the check of the employee
	 */
	public List<IDay> getAllDayCheck();
	
	/**
	 * return the planning of the employee for the given day
	 * @param day the day to look for the planning
	 * @return the planning of the employee for the given day
	 */
	public ITimeSheet getDayPlaning(int day);
	
	/**
	 * set the planning for the week of the employee
	 * @param planning the planning for the week to set to the employee
	 */
	public void setPlaning(IPlanning planning);
	
	/**
	 * return the planning of the week of the employee
	 * @return the planning of the week of the employee
 	 */
	public IPlanning getPlaning();
	
	/**
	 * set the UUID of the employee
	 * @param id the UUID to set to the employee
	 */
	public void setUUID(UUID id);
	
	/**
	 * return the stock of hour of the employee
	 * @return the stock of hour of the employee
	 */
	public Duration getStockHour();
}
