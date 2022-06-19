package fr.rclens.projetpointeuse.model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.Instant;
import java.util.Objects;

import fr.rclens.projetpointeuse.facade.IPlanning;
import fr.rclens.projetpointeuse.facade.ITimeSheet;

/**
 * Entity class to represent a planing
 */
public class Planning implements IPlanning, Serializable{
	private static final long serialVersionUID = 8754244760170872760L;
	private ITimeSheet[] days;
	
	/**
	 * @brief constructor for the class {@link Planning}
	 */
	public Planning() {
		days = new TimeSheet[5];
	}

	/**
	 * @brief Get the time sheet for a day
	 * 
	 * @throw IllegalArgumentException exception thrown when the day is not existing
	 * 
	 * @param dow represent the day of the week
	 */
	@Override
	public ITimeSheet getDayTimeSheet(DayOfWeek dow) {
		Objects.requireNonNull(dow);
		
		if(dow.getValue() > 5) {
			throw new IllegalArgumentException("day must be between MONDAY and FRIDAY");
		}
		
		return days[dow.getValue()-1];
	}

	/**
	 * Set the the TimeSheet for a day
	 * @param dow : DayOfWeek (the day in the week wanted)
	 * @param ts : ITimeSheet (the time sheet of the day)
	 */
	@Override
	public void setDayTimeSheet(DayOfWeek dow, ITimeSheet ts) {
		Objects.requireNonNull(dow);
		Objects.requireNonNull(ts);
		
		if(dow.getValue() > 5) {
			throw new IllegalArgumentException("day must be between MONDAY and FRIDAY");
		}
		
		days[dow.getValue()-1] = ts;
	}
}