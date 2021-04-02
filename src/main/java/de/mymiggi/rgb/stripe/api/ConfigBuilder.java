package de.mymiggi.rgb.stripe.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.jboss.logging.Logger;

import de.mymiggi.rgb.stripe.api.entity.APIConfig;

public class ConfigBuilder
{
	private static Logger logger = Logger.getLogger(ConfigBuilder.class);

	public APIConfig build()
	{
		Map<String, String> configMap = new HashMap<String, String>();
		try
		{
			configMap = buildMap();
		}
		catch (Exception e1)
		{
		}
		APIConfig apiConfig = new APIConfig();
		if (configMap.containsKey("MaxModeDigitLength"))
		{
			try
			{
				apiConfig.setMaxModeDgitLength(Integer.parseInt(configMap.get("MaxModeDigitLength")));
			}
			catch (Exception e)
			{
				logger.error("Not an number!", e);
			}
		}
		if (configMap.containsKey("Python-Command"))
		{
			apiConfig.setPythonCommand(configMap.get("Python-Command"));
		}
		if (configMap.containsKey("Python-FileNamePattern") && configMap.get("Python-FileNamePattern").contains("%s"))
		{
			apiConfig.setPythonFileNamePattern(configMap.get("Python-FileNamePattern"));
		}
		return apiConfig;
	}

	private Map<String, String> buildMap() throws Exception
	{
		List<String> configLines = readFile();
		Map<String, String> configMap = new HashMap<String, String>();
		for (int i = 0; i < configLines.size(); i++)
		{
			if (configLines.get(i).contains("="))
			{
				String[] keyAndValue = configLines.get(i).split("=");
				configMap.put(keyAndValue[0], keyAndValue[1]);
			}
			else
			{
				logger.warn("Problem in config! Ignore line " + i + " -> " + configLines.get(i));
			}
		}
		return configMap;
	}

	private List<String> readFile() throws Exception
	{
		File configFile = new File(System.getProperty("user.dir") + "/api.config");
		List<String> configLines = new ArrayList<String>();
		if (configFile.exists())
		{
			try
			{
				Scanner myReader = new Scanner(configFile);
				while (myReader.hasNextLine())
				{
					if (!myReader.nextLine().startsWith("#"))
					{
						configLines.add(myReader.nextLine());
					}
				}
				myReader.close();
			}
			catch (FileNotFoundException e)
			{
				logger.error("An error occurred!", e);
				e.printStackTrace();
			}
			return configLines;
		}
		else
		{
			logger.warn("Can't finde config file! Use deafult config!");
			throw new Exception("No configFile!");
		}
	}
}
