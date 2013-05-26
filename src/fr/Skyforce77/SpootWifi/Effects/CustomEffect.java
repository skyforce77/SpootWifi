package fr.Skyforce77.SpootWifi.Effects;

import java.lang.reflect.Field;

import net.minecraft.server.v1_5_R3.Packet63WorldParticles;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_5_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class CustomEffect {

	public static void createEffect(CraftPlayer player, String effect, float playersX,
            float playersY, float playersZ, float xOffset, float yOffset,
            float zOffset, float effectSpeed, int amountOfParticles) {
		
        Packet63WorldParticles sPacket = new Packet63WorldParticles();
        for (Field field : sPacket.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                String fieldName = field.getName();
                switch (fieldName) {
                case "a":
                    field.set(sPacket, effect);
                    break;
                case "b":
                    field.setFloat(sPacket, playersX);
                    break;
                case "c":
                    field.setFloat(sPacket, playersY);
                    break;
                case "d":
                    field.setFloat(sPacket, playersZ);
                    break;
                case "e":
                    field.setFloat(sPacket, xOffset);
                    break;
                case "f":
                    field.setFloat(sPacket, yOffset);
                    break;
                case "g":
                    field.setFloat(sPacket, zOffset);
                    break;
                case "h":
                    field.setFloat(sPacket, effectSpeed);
                    break;
                case "i":
                    field.setInt(sPacket, amountOfParticles);
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        player.getHandle().playerConnection.sendPacket(sPacket);
    }
	
	public static void createEffect(Player p, ParticleType type, Location loc, Vector v, float speed, int amount, int data) {
		if(type.equals(ParticleType.TILE_CRACK)) {
			createEffectTileCrack(p, data, 0, loc, v, speed, amount);
		} else if(type.equals(ParticleType.ICON_CRACK)) {
			createEffectIconCrack(p, data, loc, v, speed, amount);
		} else {
			createEffect((CraftPlayer)p, type.getParticleName(), (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), (float)v.getX(), (float)v.getY(), (float)v.getZ(), speed, amount);
		}
	}
	
	public static void createEffect(ParticleType type, Location loc, Vector v, float speed, int amount, int data) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			createEffect((CraftPlayer)p, type, loc, v, speed, amount, data);
		}
	}
	
	public static void createEffectTileCrack(Player p, int id, int data, Location loc, Vector v, float speed, int amount) {
		createEffect((CraftPlayer)p, "tilecrack_"+id+"_"+data, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), (float)v.getX(), (float)v.getY(), (float)v.getZ(), speed, amount);
	}
	
	public static void createEffectTileCrack(int id, int data, Location loc, Vector v, float speed, int amount) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			createEffectTileCrack(p, id, data, loc, v, speed, amount);
		}
	}
	
	public static void createEffectIconCrack(Player p, int id, Location loc, Vector v, float speed, int amount) {
		createEffect((CraftPlayer)p, "iconcrack_"+id, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), (float)v.getX(), (float)v.getY(), (float)v.getZ(), speed, amount);
	}
	
	public static void createEffectIconCrack(int id, Location loc, Vector v, float speed, int amount) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			createEffectIconCrack(p, id, loc, v, speed, amount);
		}
	}
}
