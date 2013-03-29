package fr.Skyforce77.SpootWifi.Materials.Extended;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.material.MaterialData;
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
			SpoutManager.getSoundManager().playCustomMusic(SpootWifi.plugin, p, packet.getMusic(), true, null, -1, (int)(packet.getPower()*100));
		}
		if(wp instanceof NotificationPacket)
		{
			NotificationPacket packet = (NotificationPacket)wp;
			p.sendNotification(getString(packet.getTitle(), p, packet.getRender()), getString(packet.getText(), p, packet.getRender()), packet.getRender(), packet.getTime());
		}
		if(wp instanceof SoundEffectPacket)
		{
			SoundEffectPacket packet = (SoundEffectPacket)wp;
			SpoutManager.getSoundManager().playSoundEffect(p, packet.getEffect());
		}
		super.onPacketReceived(wp, is, p);
	}
	
	public static String getString(String text, SpoutPlayer sp, ItemStack item)
	{
		String s = "";
		
		for (int i = 0; i < text.length(); i++) {
			char indexCh = text.charAt(i);

			if ((indexCh == '%')
					&& i < text.length() - 1) {
				char indexNext = text.charAt(++i);

				switch (indexNext) {
				case 'p':
					s = s + sp.getName();
					break;
				case 'w':
					s = s + sp.getWorld().getName();
					break;
				case 'm':
					s = s + MaterialData.getMaterial(item.getTypeId(), item.getDurability()).getName();
					break;
				default:
					s = s + indexCh + indexNext;
					break;
				}
			} else if ((indexCh == '\u00A7' || indexCh == '\u0026')
					&& i < text.length() - 1) {
				char indexNext = text.charAt(++i);

				switch (indexNext) {
				case '0':
					s = s + ChatColor.BLACK;
					break;
				case '1':
					s = s + ChatColor.DARK_BLUE;
					break;
				case '2':
					s = s + ChatColor.DARK_GREEN;
					break;
				case '3':
					s = s + ChatColor.DARK_AQUA;
					break;
				case '4':
					s = s + ChatColor.DARK_RED;
					break;
				case '5':
					s = s + ChatColor.DARK_PURPLE;
					break;
				case '6':
					s = s + ChatColor.GOLD;
					break;
				case '7':
					s = s + ChatColor.GRAY;
					break;
				case '8':
					s = s + ChatColor.DARK_GRAY;
					break;
				case '9':
					s = s + ChatColor.BLUE;
					break;
				case 'a':
					s = s + ChatColor.GREEN;
					break;
				case 'b':
					s = s + ChatColor.AQUA;
					break;
				case 'c':
					s = s + ChatColor.RED;
					break;
				case 'd':
					s = s + ChatColor.LIGHT_PURPLE;
					break;
				case 'e':
					s = s + ChatColor.YELLOW;
					break;
				case 'f':
					s = s + ChatColor.WHITE;
					break;
				case 'k':
					s = s + ChatColor.MAGIC;
					break;
				case 'l':
					s = s + ChatColor.BOLD;
					break;
				case 'm':
					s = s + ChatColor.STRIKETHROUGH;
					break;
				case 'n':
					s = s + ChatColor.UNDERLINE;
					break;
				case 'o':
					s = s + ChatColor.ITALIC;
					break;
				case 'r':
					s = s + ChatColor.RESET;
					break;
				default:
					s = s + indexCh + indexNext;
					break;
				}
			} else {
				s = s + indexCh;
			}
		}
		
		return s;
	}

}
