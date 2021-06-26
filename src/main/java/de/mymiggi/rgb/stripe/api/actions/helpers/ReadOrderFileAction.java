package de.mymiggi.rgb.stripe.api.actions.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import org.jboss.logging.Logger;

public class ReadOrderFileAction
{
	private Logger logger = Logger.getLogger(this.getClass());

	public String run() throws Exception
	{
		File nickNameFile = new File(System.getProperty("user.dir") + "/order.json");
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
			new SaveOrderFileAction().run(new HashMap<String, String>());
			throw new Exception("No order file!");
		}
	}
}
