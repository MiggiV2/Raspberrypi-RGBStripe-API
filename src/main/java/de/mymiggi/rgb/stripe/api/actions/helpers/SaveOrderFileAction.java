package de.mymiggi.rgb.stripe.api.actions.helpers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.jboss.logging.Logger;

import com.google.gson.Gson;

public class SaveOrderFileAction
{
	private Logger logger = Logger.getLogger(this.getClass());

	public void run(Map<String, String> order)
	{
		String json = new Gson().toJson(order);
		try
		{
			FileWriter myWriter = new FileWriter(System.getProperty("user.dir") + "/order.json");
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
