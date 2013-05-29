package fr.Skyforce77.SpootWifi.Entity;

import java.lang.reflect.Method;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_5_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_5_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import fr.Skyforce77.SpootWifi.Effects.CustomEffect;
import fr.Skyforce77.SpootWifi.Effects.ParticleType;

import net.minecraft.server.v1_5_R3.EntityItem;
import net.minecraft.server.v1_5_R3.EntityTypes;

public class SWEntityItem extends EntityItem{

	public SWEntityItem(World world, Location loc, ItemStack itemstack) {
		super(((CraftWorld)world).getHandle(), loc.getX(), loc.getY(), loc.getZ(), CraftItemStack.asNMSCopy(itemstack));
		((CraftWorld)world).getHandle().addEntity(this);
	}
	
	@Override
	public void l_() {
		CustomEffect.createEffect(ParticleType.WITCH_MAGIC, this.getBukkitEntity().getLocation(), new Vector(0.1,0.1,0.1), 0.1F, 1, 1);
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
