package fr.rclens.projetpointeuse.facade;

import java.time.DayOfWeek;

/**
 * Entity class to represent the interface of Planning
 */
public interface IPlanning {
	
	/**
	 * return the planning of the given day
	 * @param day the day to look for the planning
	 * @return the planning of the given day
	 */
	ITimeSheet getDayTimeSheet(DayOfWeek day);
	
	/**
	 * set the planning of the given day
	 * @param day the day to set the planning
	 * @param its the planning of the day (arrival and departure)
	 */
	void setDayTimeSheet(DayOfWeek day, ITimeSheet its);
}
