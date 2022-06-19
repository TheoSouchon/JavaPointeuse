package fr.rclens.projetpointeuse.facade;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity class to represent the interface of Check
 */
public interface ICheck extends Serializable{
	
	/**
	 * set the UUID for this check
	 * @param uuid the UUId to set
	 */
	public void setUUID(UUID uuid);
	
	/**
	 * set the date and time for this check
	 * @param ldt the date and time for this check
	 */
	public void setDate(LocalDateTime ldt);
	
	/**
	 * return the UUID of this check
	 * @return the UUID of this check
	 */
	public UUID getUUID();
	
	/**
	 * return the date and time of this check
	 * @return the date and time of this check
	 */
	public LocalDateTime getDate();
}
