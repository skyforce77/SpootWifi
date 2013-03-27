package fr.Skyforce77.SpootWifi.WifiPackets;

import org.getspout.spoutapi.sound.SoundEffect;

public class SoundEffectPacket extends WifiPacket{

	public SoundEffectPacket(SoundEffect effect) {
		super("SoundEffectPacket");
		this.getData().addInteger("SoundEffectValue", effect.getId());
	}
	
	public SoundEffect getEffect()
	{
		return SoundEffect.getSoundEffectFromId(this.getData().getInteger("SoundEffectValue"));
	}

}
