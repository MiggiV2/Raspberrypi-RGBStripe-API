package de.mymiggi.rgb.stripe.api.actions.helpers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.jboss.logging.Logger;

import com.google.gson.Gson;

public class SaveNickNameMapAction
{
	private static Logger logger = Logger.getLogger(SaveNickNameMapAction.class);

	public void run(Map<String, String> nickNameMap)
	{
		String json = new Gson().toJson(nickNameMap);
		try
		{
			FileWriter myWriter = new FileWriter(System.getProperty("user.dir") + "/nicknames.json");
			myWriter.write(json);
			myWriter.close();
			logger.info("Successfully wrote to the file.");
		}
		catch (IOException e)
		{
			logger.error("An error occurred!");
		}
	}
}
