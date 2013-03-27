package fr.Skyforce77.SpootWifi.WifiPackets;

import fr.Skyforce77.SpootWifi.Saves.SWFireworkMeta;

public class FireworkPacket extends WifiPacket{

	public FireworkPacket(SWFireworkMeta meta) {
		super("FireworkPacket");
		this.getData().addObject("FireworkValue", meta);
	}
	
	public SWFireworkMeta getMeta()
	{
		return (SWFireworkMeta)this.getData().getObject("FireworkValue");
	}

}
