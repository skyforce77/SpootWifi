package fr.Skyforce77.SpootWifi.WifiPackets;

import org.bukkit.entity.EntityType;

public class EntityTypePacket extends WifiPacket{

	public EntityTypePacket(EntityType type, String name) {
		super("EntityTypePacket");
		this.getData().addString("MobType", type.toString());
		this.getData().addString("CustomName", name);
	}
	
	public EntityTypePacket(String type, String name) {
		super("EntityTypePacket");
		this.getData().addString("MobType", type);
		this.getData().addString("CustomName", name);
	}
	
	public EntityType getEntityType()
	{
		return EntityType.valueOf(this.getData().getString("MobType"));
	}
	
	public String getCustomName()
	{
		return this.getData().getString("CustomName");
	}
	
	public boolean hasCustomName()
	{
		return !this.getData().getString("CustomName").equals("");
	}

}
