package fr.rclens.projetpointeuse.model;

import java.io.Serializable;
import java.time.LocalTime;

import fr.rclens.projetpointeuse.facade.ITimeSheet;

/**
 * Entity class to represent a TimeSheet
 */
public class TimeSheet implements ITimeSheet, Serializable {
	private static final long serialVersionUID = -6528100360057750455L;
	private LocalTime arrivalHour;
	private LocalTime departureHour;
	
	/**
	 * @brief constructor for the class {@link TimeSheet}
	 * @param arrival : LocalTime (the date time of arrival)
	 * @param departure : LocalTime (the date time of departure)
	 */
	public TimeSheet(LocalTime arrival, LocalTime departure) {
		arrivalHour = arrival;
		departureHour = departure;
	}

	/**
	 * Get the date time of arrival
	 * @return LocalTime (the date time of arrival)
	 */
	@Override
	public LocalTime getArrival() {
		return arrivalHour;
	}

	/**
	 * Get the date time of departure
	 * @return LocalTime (the date time of departure)
	 */
	@Override
	public LocalTime getDeparture() {
		return departureHour;
	}

	/**
	 * Set the date time of arrival
	 * @param inst : LocalTime (the date time of arrival)
	 */
	@Override
	public void setArrival(LocalTime inst) {
		this.arrivalHour = inst;
		
	}
	/**
	 * Set the date time of departure
	 * @param inst : LocalTime (the date time of departure)
	 */
	@Override
	public void setDeparture(LocalTime inst) {
		this.departureHour = inst;
		
	}
}
