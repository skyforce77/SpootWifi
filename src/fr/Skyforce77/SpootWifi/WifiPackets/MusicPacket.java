package fr.Skyforce77.SpootWifi.WifiPackets;

public class MusicPacket extends WifiPacket{

	@Deprecated
	public MusicPacket(String music) {
		super("MusicPacket");
		this.getData().addString("MusicValue", music);
		this.getData().addFloat("MusicPower", 0.5F);
	}
	
	public MusicPacket(String music, Float power) {
		super("MusicPacket");
		this.getData().addString("MusicValue", music);
		this.getData().addFloat("MusicPower", power);
	}
	
	public String getMusic()
	{
		return this.getData().getString("MusicValue");
	}
	
	public Float getPower()
	{
		return this.getData().getFloat("MusicPower");
	}

}
