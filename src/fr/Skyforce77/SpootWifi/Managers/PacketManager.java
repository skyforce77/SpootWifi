package fr.Skyforce77.SpootWifi.Managers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.getspout.spoutapi.sound.SoundEffect;

import fr.Skyforce77.SpootWifi.Saves.SWFireworkMeta;
import fr.Skyforce77.SpootWifi.WifiPackets.FireworkPacket;
import fr.Skyforce77.SpootWifi.WifiPackets.MusicPacket;
import fr.Skyforce77.SpootWifi.WifiPackets.NotificationPacket;
import fr.Skyforce77.SpootWifi.WifiPackets.SoundEffectPacket;
import fr.Skyforce77.SpootWifi.WifiPackets.WoolColorPacket;

public class PacketManager {

	protected PacketManager(){}
	
	MusicPacket getMusicPacket(String musicurl, float volume)
	{
		return new MusicPacket(musicurl, volume);
	}

	SoundEffectPacket getSoundEffectPacket(SoundEffect effect)
	{
		return new SoundEffectPacket(effect);
	}
	
	FireworkPacket getFireworkPacket(FireworkMeta meta)
	{
		return new FireworkPacket(new SWFireworkMeta(meta));
	}
	
	FireworkPacket getFireworkPacket(SWFireworkMeta meta)
	{
		return new FireworkPacket(meta);
	}
	
	WoolColorPacket getWoolColorPacket(byte color)
	{
		return new WoolColorPacket(color);
	}
	
	NotificationPacket getNotificationPacket(String line1, String line2, ItemStack render, Integer time)
	{
		return new NotificationPacket(line1, line2, render, time);
	}
	
	NotificationPacket getNotificationPacket(String line1, String line2, ItemStack render)
	{
		return new NotificationPacket(line1, line2, render, 4000);
	}
	
	NotificationPacket getNotificationPacket(String line1, String line2, Integer time)
	{
		return new NotificationPacket(line1, line2, new ItemStack(Material.PAPER), time);
	}
}
