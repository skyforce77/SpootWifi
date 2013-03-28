package fr.Skyforce77.SpootWifi.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.getspout.spoutapi.SpoutManager;

import fr.Skyforce77.SpootWifi.SpootWifi;

public class RessourceManager {

	public static String getTexture(String texturename)
	{
		try
		{
			File file;
			File dir = new File(SpootWifi.plugin.getDataFolder(), "Ressources/Textures");
			if (!dir.exists()) dir.mkdirs();
			
			String copy = new File("Ressources/Textures", texturename).getPath();
			SpootWifi.plugin.saveResource(copy, true);
			file = new File(SpootWifi.plugin.getDataFolder()+"/Ressources/Textures/"+texturename);
			
			if(SpootWifi.plugin.getConfig().contains("texturepack") && !SpootWifi.plugin.getConfig().getString("texturepack").equals("default"))
			{
				File tps = new File(SpootWifi.plugin.getDataFolder(), "TexturePacks");
				if (!tps.exists()) tps.mkdirs();
				File texturepack = new File(tps,SpootWifi.plugin.getConfig().getString("texturepack")+".zip");
				
				if(texturepack.exists())
				{
					ZipFile tfile = new ZipFile(texturepack);
					ZipEntry entry = tfile.getEntry(texturename);
					if(entry != null)
					{
						file = new File(SpootWifi.plugin.getDataFolder()+"/Ressources/Textures/"+SpootWifi.plugin.getConfig().getString("texturepack")+"-"+texturename);
						FileOutputStream fileoutputstream = new FileOutputStream(file);             
						InputStream is = tfile.getInputStream(entry);
						while (is.available() > 0) {fileoutputstream.write(is.read());}	
						fileoutputstream.close(); 
						is.close();
					}
				}
			}
			
			SpoutManager.getFileManager().addToPreLoginCache(SpootWifi.plugin, file);
			
			return file.toString();
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
