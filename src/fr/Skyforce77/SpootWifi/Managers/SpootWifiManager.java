package fr.Skyforce77.SpootWifi.Managers;

public class SpootWifiManager {
	
	private static PacketManager packetmanager = new PacketManager();
	
	static PacketManager getPacketManager()
	{
		return packetmanager;
	}

}
