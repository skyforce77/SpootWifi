package fr.Skyforce77.SpootWifi.WifiPackets;

import org.bukkit.entity.EntityType;

public class EntityTypePacket extends WifiPacket{

	public EntityTypePacket(EntityType type) {
		super("EntityTypePacket");
		this.getData().addString("MobType", type.toString());
	}
	
	public EntityTypePacket(String type) {
		super("EntityTypePacket");
		this.getData().addString("MobType", type);
	}
	
	public EntityType getEntityType()
	{
		return EntityType.valueOf(this.getData().getString("MobType"));
	}

}
