package fr.rclens.projetpointeuse.facade;

import java.time.LocalTime;

/**
 * Entity class to represent the interface of TimeSheet
 */
public interface ITimeSheet {
	
	/**
	 * return the arrival of the timesheet
	 * @return the arrival of the timesheet
	 */
	LocalTime getArrival();
	
	/**
	 * return the departure of the timesheet
	 * @return the departure of the timesheet
	 */
	LocalTime getDeparture();
	
	/**
	 * set the arrival for the timesheet
	 * @param inst the arrival to setfor the timesheet
	 */
	void setArrival(LocalTime inst);
	
	/**
	 * set the departure for the timesheet
	 * @param inst the departure to set for the timesheet
	 */
	void setDeparture(LocalTime inst);
}
