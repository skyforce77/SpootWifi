package fr.Skyforce77.SpootWifi.Materials.Extended;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.Materials.Basics.Receiver;
import fr.Skyforce77.SpootWifi.WifiPackets.EntityTypePacket;
import fr.Skyforce77.SpootWifi.WifiPackets.WifiPacket;

public class ReceiverMob extends Receiver{
	
	public ReceiverMob(Plugin plugin, String name) {
		super(plugin, name, 41, getDesign(plugin, 0));
		setBlockDesign(getDesign(plugin, 1), 1);
	}
	
	private static BlockDesign getDesign(Plugin p, int design)
	{
		if(design == 1)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{45,51,51,51,51,8});
		}
		else
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{46,52,52,52,52,9});
		}
	}
	
	@Override
	public void onPowered(SpoutBlock b, boolean powered) {
		if(powered && b.getCustomBlockData() == Byte.parseByte("0"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)1);
		}
		else if(!powered && b.getCustomBlockData() == Byte.parseByte("1"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)0);
		}
	}
	
	@Override
	public void onPacketReceived(WifiPacket wp, SpoutBlock b) {
		if(wp instanceof EntityTypePacket)
		{
			try
			{
				EntityTypePacket mp = (EntityTypePacket)wp;
				Location loc = b.getLocation().add(0.5,2,0.5);
				LivingEntity en = (LivingEntity)b.getWorld().spawnEntity(loc, mp.getEntityType());
				en.setCustomName(getString(mp.getCustomName()));
				en.setCustomNameVisible(mp.hasCustomName());
			}
			catch(Exception e){}
		}
		super.onPacketReceived(wp, b);
	}
	
	public static String getString(String text)
	{
		String s = "";
		
		for (int i = 0; i < text.length(); i++) {
			char indexCh = text.charAt(i);

			if ((indexCh == '\u00A7' || indexCh == '\u0026')
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
