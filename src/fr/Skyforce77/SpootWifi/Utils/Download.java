package fr.Skyforce77.SpootWifi.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Download {

	public static File dlFile(String host, String chemin)
    {
        InputStream input = null;
        FileOutputStream writeFile = null;
        chemin.substring(0, chemin.lastIndexOf("/"));
        try
        {
            URL url = new URL(host);
            URLConnection connection = url.openConnection();
            int fileLength = connection.getContentLength();
            
            if(!new File(chemin).exists())
            {
            	new File(chemin).mkdirs();
            }
 
            if (fileLength == -1)
            {
                System.out.println("Invalid URL or file.");
                return null;
            }
 
            input = connection.getInputStream();
            String fileName = url.getFile().substring(url.getFile().lastIndexOf('/') + 1);
            writeFile = new FileOutputStream(chemin+"/"+fileName);
            byte[] buffer = new byte[1024];
            int read;

            while ((read = input.read(buffer)) > 0)
                writeFile.write(buffer, 0, read);
            writeFile.flush();
           return new File(chemin+"/"+fileName);
           
           
        }
        catch (IOException e)
        {
            System.out.println("Error while trying to download the file.");
            e.printStackTrace();
            return null;
        }
        finally
        {
            try
            {
                writeFile.close();
                input.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}
