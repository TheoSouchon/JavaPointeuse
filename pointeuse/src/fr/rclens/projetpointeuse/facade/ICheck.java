package fr.rclens.projetpointeuse.facade;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public interface ICheck extends Serializable{
	public void setUUID(UUID uuid);
	public void setDate(LocalDateTime ldt);
	public UUID getUUID();
	public LocalDateTime getDate();
}
