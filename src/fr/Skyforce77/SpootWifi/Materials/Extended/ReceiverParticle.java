package fr.Skyforce77.SpootWifi.Materials.Extended;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.GUI.ParticleReceiverGui;
import fr.Skyforce77.SpootWifi.Materials.Basics.Receiver;
import fr.Skyforce77.SpootWifi.WifiPackets.ParticlePacket;
import fr.Skyforce77.SpootWifi.WifiPackets.WifiPacket;

public class ReceiverParticle extends Receiver{
	
	public ReceiverParticle(Plugin plugin, String name) {
		super(plugin, name, 41, getDesign(plugin, 0));
		setBlockDesign(getDesign(plugin, 1), 1);
		setGui(ParticleReceiverGui.class);
	}
	
	private static BlockDesign getDesign(Plugin p, int design)
	{
		if(design == 1)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{45,51,51,51,51,55});
		}
		else if(design == 0)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{46,52,52,52,52,56});
		}
		else
		{
			return null;
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
	public boolean onBlockInteract(World world, int x, int y, int z,
			SpoutPlayer player) {
		Block b = new Location(world,x,y,z).getBlock();
		if(!player.isSneaking() && SpootWifi.canInteract(player, b))
		{
			openGui(player, SpootWifi.save.getChannel(b).getSWBlock(b).getStorage());
			return true;
		}
		return false;
	}
	
	@Override
	public void onPacketReceived(WifiPacket wp, SpoutBlock b) {
		if(wp instanceof ParticlePacket)
		{
			try
			{
				ParticlePacket mp = (ParticlePacket)wp;
				int x = SpootWifi.save.getChannel(b).getSWBlock(b).getStorage().getInteger("x");
				int y = SpootWifi.save.getChannel(b).getSWBlock(b).getStorage().getInteger("y");
				int z = SpootWifi.save.getChannel(b).getSWBlock(b).getStorage().getInteger("z");
				Location loc = b.getLocation().add(0.5,0.5,0.5).add(x,y,z);
				mp.getParticle().spawn(loc);
			}
			catch(Exception e){}
		}
		super.onPacketReceived(wp, b);
	}

}
