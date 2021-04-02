package de.mymiggi.rgb.stripe.api.actions.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.jboss.logging.Logger;

import com.google.gson.Gson;

public class ReadNickNameMapAction
{
	private static Logger logger = Logger.getLogger(ReadNickNameMapAction.class);

	@SuppressWarnings("unchecked")
	public Map<String, String> run()
	{
		try
		{
			String json = readFile();
			Map<String, String> nicknameMap = new Gson().fromJson(json, Map.class);
			return nicknameMap;
		}
		catch (Exception e)
		{
			if (e.getMessage() != null && !e.getMessage().equals("No nickname file!"))
			{
				logger.error("An error occurred!", e);
			}
			return new HashMap<String, String>();
		}
	}

	private String readFile() throws Exception
	{
		File nickNameFile = new File(System.getProperty("user.dir") + "/nicknames.json");
		String json = "";
		if (nickNameFile.exists())
		{
			try
			{
				Scanner myReader = new Scanner(nickNameFile);
				while (myReader.hasNextLine())
				{
					json += myReader.nextLine() + "\r\n";
				}
				myReader.close();
			}
			catch (FileNotFoundException e)
			{
				logger.error("An error occurred!", e);
			}
			return json;
		}
		else
		{
			nickNameFile.createNewFile();
			new SaveNickNameMapAction().run(new HashMap<String, String>());
			logger.warn("Can't finde nicknames file! Created file!");
			throw new Exception("No nickname file!");
		}
	}
}
