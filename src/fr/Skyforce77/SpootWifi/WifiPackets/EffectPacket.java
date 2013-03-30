package fr.Skyforce77.SpootWifi.WifiPackets;

import org.bukkit.Effect;

public class EffectPacket extends WifiPacket{

	public EffectPacket(Effect type) {
		super("EffectPacket");
		this.getData().addString("Effect", type.toString());
	}
	
	public EffectPacket(String type) {
		super("EffectPacket");
		this.getData().addString("Effect", type);
	}
	
	public Effect getEffect()
	{
		return Effect.valueOf(this.getData().getString("Effect"));
	}

}
