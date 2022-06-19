package fr.rclens.projetpointeuse.facade;
import java.time.LocalDate;

/**
 * Entity class to represent the interface of Day
 */
public interface IDay extends ITimeSheet {
	
	/**
	 * return the date of this IDay
	 * @return the date of this IDay
	 */
	public LocalDate getDate();
	
	/**
	 * return true if it's on time at arrival otherwise return false
	 * @return true if it's on time at arrival otherwise return false
	 */
	public boolean getOnTimeArrival();
	
	/**
	 * return true if it's on time at departure otherwise return false
	 * @return true if it's on time at departure otherwise return false
	 */
	public boolean getOnTimeDeparture();
}
