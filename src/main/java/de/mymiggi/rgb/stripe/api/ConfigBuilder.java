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
		APIConfig apiConfig = new APIConfig();
		try
		{
			configMap = buildMap();
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
			apiConfig.setUserName(configMap.get("user-name"));
			apiConfig.setPassword(configMap.get("user-password"));
		}
		catch (Exception e1)
		{
		}
		logger.info("Username: " + apiConfig.getUserName());
		logger.info("Password: " + apiConfig.getPassword());
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
				String value = keyAndValue[1];
				for (int n = 2; n < keyAndValue.length; n++)
				{
					value += "=" + keyAndValue[n];
				}
				configMap.put(keyAndValue[0], value);
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
					String line = myReader.nextLine();
					if (!line.startsWith("#"))
					{
						configLines.add(line);
					}
				}
				myReader.close();
			}
			catch (FileNotFoundException e)
			{
				logger.error("An error occurred!", e);
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
