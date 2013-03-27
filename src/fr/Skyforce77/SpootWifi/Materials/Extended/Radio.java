package fr.Skyforce77.SpootWifi.Materials.Extended;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.Materials.Basics.ItemReceiver;
import fr.Skyforce77.SpootWifi.Utils.RessourceManager;
import fr.Skyforce77.SpootWifi.WifiPackets.MusicPacket;
import fr.Skyforce77.SpootWifi.WifiPackets.NotificationPacket;
import fr.Skyforce77.SpootWifi.WifiPackets.SoundEffectPacket;
import fr.Skyforce77.SpootWifi.WifiPackets.WifiPacket;

public class Radio extends ItemReceiver{

	public Radio(Plugin plugin, String name) {
		super(plugin, name, RessourceManager.getTexture("radio.png"));
	}

	@Override
	public void onPacketReceived(WifiPacket wp, ItemStack is, SpoutPlayer p) {
		if(wp instanceof MusicPacket)
		{
			MusicPacket packet = (MusicPacket)wp;
			SpoutManager.getSoundManager().playCustomMusic(SpootWifi.plugin, p, packet.getMusic(), true);
		}
		if(wp instanceof NotificationPacket)
		{
			NotificationPacket packet = (NotificationPacket)wp;
			p.sendNotification(packet.getTitle(), packet.getText(), packet.getRender(), packet.getTime());
		}
		if(wp instanceof SoundEffectPacket)
		{
			SoundEffectPacket packet = (SoundEffectPacket)wp;
			SpoutManager.getSoundManager().playSoundEffect(p, packet.getEffect());
		}
		super.onPacketReceived(wp, is, p);
	}

}
