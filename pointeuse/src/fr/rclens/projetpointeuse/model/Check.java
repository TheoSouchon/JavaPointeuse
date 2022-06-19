package fr.rclens.projetpointeuse.model;

import java.time.LocalDateTime;
import java.util.UUID;

import fr.rclens.projetpointeuse.facade.ICheck;

public class Check implements ICheck {
	
	private static final long serialVersionUID = 7204427785790945115L;
	private UUID uuid;
	private LocalDateTime date;
	
	public Check(UUID id, LocalDateTime ldt) {
		uuid = id;
		date = ldt;
	}

	@Override
	public void setUUID(UUID id) {
		uuid = id;
	}

	@Override
	public void setDate(LocalDateTime ldt) {
		date = ldt;

	}

	@Override
	public UUID getUUID() {
		return uuid;
	}

	@Override
	public LocalDateTime getDate() {
		return date;
	}

}
