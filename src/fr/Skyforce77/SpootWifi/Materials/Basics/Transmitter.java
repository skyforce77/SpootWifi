package fr.Skyforce77.SpootWifi.Materials.Basics;

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
import fr.Skyforce77.SpootWifi.GUI.ChooseGui;

public class Transmitter extends Configurable{

	public Transmitter(Plugin plugin, String name) {
		super(plugin, name, 42, getDesign(plugin,0));
		setBlockDesign(getDesign(plugin,1), 1);
	}
	
	
	
	public Transmitter(Plugin plugin, String name, boolean isOpaque,
			BlockDesign design, boolean rotate, boolean mirroredRotate,
			boolean fullRotate) {
		super(plugin, name, isOpaque, design, rotate, mirroredRotate, fullRotate);
	}



	public Transmitter(Plugin plugin, String name, boolean isOpaque,
			BlockDesign design, boolean rotate) {
		super(plugin, name, isOpaque, design, rotate);
	}



	public Transmitter(Plugin plugin, String name, boolean isOpaque,
			BlockDesign design) {
		super(plugin, name, isOpaque, design);
	}



	public Transmitter(Plugin plugin, String name, boolean isOpaque,
			boolean rotate, boolean mirroredRotate, boolean fullRotate) {
		super(plugin, name, isOpaque, rotate, mirroredRotate, fullRotate);
	}



	public Transmitter(Plugin plugin, String name, boolean isOpaque,
			boolean rotate) {
		super(plugin, name, isOpaque, rotate);
	}



	public Transmitter(Plugin plugin, String name, boolean isOpaque) {
		super(plugin, name, isOpaque);
	}



	public Transmitter(Plugin plugin, String name, int blockId,
			BlockDesign design, boolean rotate, boolean mirroredRotate,
			boolean fullRotate) {
		super(plugin, name, blockId, design, rotate, mirroredRotate, fullRotate);
	}



	public Transmitter(Plugin plugin, String name, int blockId,
			BlockDesign design, boolean rotate) {
		super(plugin, name, blockId, design, rotate);
	}



	public Transmitter(Plugin plugin, String name, int blockId,
			BlockDesign design) {
		super(plugin, name, blockId, design);
	}



	public Transmitter(Plugin plugin, String name, int blockId, boolean rotate,
			boolean mirroredRotate, boolean fullRotate) {
		super(plugin, name, blockId, rotate, mirroredRotate, fullRotate);
	}



	public Transmitter(Plugin plugin, String name, int blockId, boolean rotate) {
		super(plugin, name, blockId, rotate);
	}



	public Transmitter(Plugin plugin, String name, int blockId, int metadata,
			BlockDesign design, boolean rotate, boolean mirroredRotate,
			boolean fullRotate) {
		super(plugin, name, blockId, metadata, design, rotate, mirroredRotate,
				fullRotate);
	}



	public Transmitter(Plugin plugin, String name, int blockId, int metadata,
			BlockDesign design, boolean rotate) {
		super(plugin, name, blockId, metadata, design, rotate);
	}



	public Transmitter(Plugin plugin, String name, int blockId, int metadata,
			BlockDesign design) {
		super(plugin, name, blockId, metadata, design);
	}



	public Transmitter(Plugin arg0, String arg1, int arg2, int arg3,
			boolean arg4, boolean arg5, boolean arg6) {
		super(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}



	public Transmitter(Plugin plugin, String name, int blockId, int metadata,
			boolean rotate) {
		super(plugin, name, blockId, metadata, rotate);
	}



	public Transmitter(Plugin plugin, String name, int blockId, int metadata) {
		super(plugin, name, blockId, metadata);
	}



	public Transmitter(Plugin plugin, String name, int blockId) {
		super(plugin, name, blockId);
	}



	private static BlockDesign getDesign(Plugin p,int design)
	{
		if(design == 1)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{0,1,1,1,1,0});
		}
		else if(design == 0)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{3,4,4,4,4,3});
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public boolean onBlockInteract(World world, int x, int y, int z,
			SpoutPlayer player) {
		Block b = new Location(world,x,y,z).getBlock();
		SpootWifi.save.getRawChannel(b);
		if(!player.isSneaking() && SpootWifi.canInteract(player, b))
		{
			new ChooseGui(player, b);
			return true;
		}
		return super.onBlockInteract(world, x, y, z, player);
	}
	
	public void onPowered(SpoutBlock b, boolean powered)
	{
		if(powered && b.getCustomBlockData() == Byte.parseByte("0"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)1);
		}
		else if(!powered && b.getCustomBlockData() == Byte.parseByte("1"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)0);
		}
		
		SpootWifi.save.getChannel(b).update();
	}

}
