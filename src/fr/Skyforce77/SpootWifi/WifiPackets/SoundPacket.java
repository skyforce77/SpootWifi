package fr.Skyforce77.SpootWifi.WifiPackets;

import org.bukkit.Sound;

public class SoundPacket extends WifiPacket{
	
	public SoundPacket(String music, Float volume, Float high) {
		super("SoundPacket");
		this.getData().addString("Sound", music);
		this.getData().addFloat("SoundVolume", volume);
		this.getData().addFloat("SoundHigh", high);
	}
	
	public Sound getSound()
	{
		return Sound.valueOf(this.getData().getString("Sound"));
	}
	
	public Float getVolume()
	{
		return this.getData().getFloat("SoundVolume");
	}
	
	public Float getHigh()
	{
		return this.getData().getFloat("SoundHigh");
	}

}
