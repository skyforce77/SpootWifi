package fr.Skyforce77.SpootWifi.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

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
			
			if(SpootWifi.plugin.getConfig().contains("texturepack") && !SpootWifi.plugin.getConfig().getString("texturepack").equals("default"))
			{
				File tps = new File(SpootWifi.plugin.getDataFolder(), "TexturePacks");
				if (!tps.exists()) tps.mkdirs();
				File texturepack = new File(tps,SpootWifi.plugin.getConfig().getString("texturepack")+".zip");
				
				if(texturepack.exists())
				{
					ZipFile file = new ZipFile(texturepack);
					ZipEntry entry = file.getEntry(texturename);
					if(entry != null)
					{
						FileOutputStream fileoutputstream = new FileOutputStream(SpootWifi.plugin.getDataFolder()+"/Ressources/Textures/"+texturename);             
						InputStream is = file.getInputStream(entry);
						while (is.available() > 0) {fileoutputstream.write(is.read());}	
						fileoutputstream.close(); 
						is.close();
					}
				}
			}
			
			return SpootWifi.plugin.getDataFolder()+"/Ressources/Textures/"+texturename;
		}
		catch(Exception e)
		{
			SpootWifi.plugin.getLogger().log(Level.WARNING, "Error occured while trying to load "+texturename);
			return "";
		}
	}
	
	public static void LoadConfig()
	{
		if(!new File(SpootWifi.plugin.getDataFolder()+"/config.yml").exists())
		{
			SpootWifi.plugin.getLogger().log(Level.CONFIG, "Generating configuration...");
			SpootWifi.plugin.getConfig().addDefault("texturepack", "default");
			SpootWifi.plugin.getConfig().options().copyDefaults(true);
			SpootWifi.plugin.saveConfig();
		}
	}

}
