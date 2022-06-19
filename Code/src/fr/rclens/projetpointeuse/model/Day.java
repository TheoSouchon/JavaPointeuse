package fr.rclens.projetpointeuse.model;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

import fr.rclens.projetpointeuse.facade.IDay;
import fr.rclens.projetpointeuse.facade.ITimeSheet;

/**
 * Entity class to represent a day
 */
public class Day extends TimeSheet implements IDay, Serializable{

	private static final long serialVersionUID = 108479955882789102L;
	private LocalDate date;
	private boolean onTimeArrival;
	private boolean onTimeDeparture;
	
	/**
	 * @brief Constructor of {@link Company}
	 * 
	 * @param arrival,	the date time of arrival in the company
	 * @param departure,the date time of departure out of the company
	 * @param date,		the date of the day
	 */
	public Day(LocalTime arrival, LocalTime departure, LocalDate date) {
		super(arrival, departure);
		this.date = date;
		onTimeArrival = onTimeDeparture = true;
	}

	/**
	 * @brief Get the date of the {@link Day}
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}
	
	/**
	 * @brief Set if the employee arrived on time for this {@link Day}
	 * @param param, true : arrived on time, false : not
	 */
	public void setOnTimeArrival(boolean param) {
		onTimeArrival = param;
	}
	
	/**
	 * @brief Set if the employee departed on time for this {@link Day}
	 * @param param, true : departed on time, false : not
	 */
	public void setOnTimeDeparture(boolean param) {
		onTimeDeparture = param;
	}
	
	/**
	 * @brief Get if the employee arrived on time for this {@link Day}
	 * @return true : arrived on time, false : not
	 */
	public boolean getOnTimeArrival() {
		return onTimeArrival;
	}
	
	/**
	 * @brief Get if the employee departed on time for this {@link Day}
	 * @return true : departed on time, false : not
	 */
	public boolean getOnTimeDeparture() {
		return onTimeDeparture;
	}
}
