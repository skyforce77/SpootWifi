package fr.Skyforce77.SpootWifi.WifiPackets;

public class MusicPacket extends WifiPacket{

	public MusicPacket(String music) {
		super("MusicPacket");
		this.getData().addString("MusicValue", music);
	}
	
	public String getMusic()
	{
		return this.getData().getString("MusicValue");
	}

}
