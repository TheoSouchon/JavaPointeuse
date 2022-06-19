package fr.rclens.projetpointeuse.model;

import java.time.LocalDateTime;
import java.util.UUID;

import fr.rclens.projetpointeuse.facade.ICheck;

/**
 * Entity class to represent a Check
 */
public class Check implements ICheck {
	
	private static final long serialVersionUID = 7204427785790945115L;
	private UUID uuid;
	private LocalDateTime date;
	
	/**
	 * Constructor of a {@link Check] 
	 * 
	 * @param id	Represent the {@link Employee} id of a check
	 * @param ldt	Represent the date time of the check
	 */
	public Check(UUID id, LocalDateTime ldt) {
		uuid = id;
		date = ldt;
	}

	/**
	 * Method to set the id of the {@link Check}
	 * 
	 * @param id	Represent the {@link Employee} id of a check
	 */
	@Override
	public void setUUID(UUID id) {
		uuid = id;
	}

	
	/**
	 * Method to set the date time of the check
	 * 
	 * @param ldt	Represent the date time of the check
	 */
	@Override
	public void setDate(LocalDateTime ldt) {
		date = ldt;

	}

	/**
	 * Method to get the {@link Employee} id of a check
	 * 
	 * @return {@link UUID}, the UUID of the game
	 */
	@Override
	public UUID getUUID() {
		return uuid;
	}

	/**
	 * Method to get the date time of the check
	 * 
	 * @return {@link LocalDateTime}, the local date of the check
	 */
	@Override
	public LocalDateTime getDate() {
		return date;
	}

}
