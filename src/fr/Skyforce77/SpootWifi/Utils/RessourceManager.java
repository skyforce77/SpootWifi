package fr.Skyforce77.SpootWifi.Utils;

import java.io.File;

import fr.Skyforce77.SpootWifi.SpootWifi;

public class RessourceManager {

	public static String getTexture(String texturename)
	{
		try
		{
			File dir = new File(SpootWifi.plugin.getDataFolder(), "Ressources/Textures");
			if (!dir.exists()) dir.mkdirs();
			
			String copy = new File("Ressources/Textures", texturename).getPath();
			SpootWifi.plugin.saveResource(copy, true);
			
			System.out.print(SpootWifi.plugin.getDataFolder()+"/Ressources/Textures/"+texturename);
			return SpootWifi.plugin.getDataFolder()+"/Ressources/Textures/"+texturename;
		}
		catch(Exception e)
		{
			return "";
		}
	}

}
