package fr.Skyforce77.SpootWifi.Entity;

import java.lang.reflect.Method;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_5_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_5_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import fr.Skyforce77.SpootWifi.Effects.CustomEffect;
import fr.Skyforce77.SpootWifi.Effects.ParticleType;
import fr.Skyforce77.SpootWifi.Saves.SWStorage;

import net.minecraft.server.v1_5_R3.EntityItem;
import net.minecraft.server.v1_5_R3.EntityTypes;

public class SWEntityItem extends EntityItem{

	DyeColor color = DyeColor.WHITE;
	
	public SWEntityItem(World world, Location loc, ItemStack itemstack) {
		super(((CraftWorld)world).getHandle(), loc.getX(), loc.getY(), loc.getZ(), CraftItemStack.asNMSCopy(itemstack));
		((CraftWorld)world).getHandle().addEntity(this);
		SWStorage storage = new SWStorage(itemstack);
		color = DyeColor.getByWoolData(storage.getByte("WoolColor"));
	}
	
	@Override
	public void l_() {
		if(!color.equals(DyeColor.WHITE)) {
			CustomEffect.createEffectTileCrack(35, color.getWoolData(), this.getBukkitEntity().getLocation(), new Vector(0.1,0.1,0.1), 0.001F, 1);
		} else {
			CustomEffect.createEffect(ParticleType.PORTAL, this.getBukkitEntity().getLocation(), new Vector(0.2,0.2,0.2), 0.01F, 2, 1);
		}
		super.l_();
	}

	public static void create()
	{
		try {
            @SuppressWarnings("rawtypes")
            Class[] args = new Class[3];
            args[0] = Class.class;
            args[1] = String.class;
            args[2] = int.class;
 
            Method a = EntityTypes.class.getDeclaredMethod("a", args);
            a.setAccessible(true);
 
            a.invoke(a, SWEntityItem.class, "SWEntityItem", 1);
        }catch (Exception e){
            e.printStackTrace();
        }
	}
}
