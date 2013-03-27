package fr.Skyforce77.SpootWifi.WifiPackets;

public class WoolColorPacket extends WifiPacket{

	public WoolColorPacket(Byte color) {
		super("WoolColorPacket");
		this.getData().addByte("WoolColor", color);
	}
	
	public byte getColor()
	{
		return this.getData().getByte("WoolColor");
	}

}
