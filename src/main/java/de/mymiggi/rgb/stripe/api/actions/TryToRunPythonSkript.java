package de.mymiggi.rgb.stripe.api.actions;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import de.mymiggi.rgb.stripe.api.entity.APIConfig;
import de.mymiggi.rgb.stripe.api.entity.RGBMode;

public class TryToRunPythonSkript
{
	private static Logger logger = Logger.getLogger(TryToRunPythonSkript.class);

	public Response run(RGBMode mode, APIConfig apiConfig)
	{
		String fileName = String.format(apiConfig.getPythonFileNamePattern(), mode.getMode());
		File file = new File(fileName);

		if (!file.exists() || mode.getMode().length() > 3)
		{
			logger.warn("Can't find mode " + mode.getMode());
			return Response.status(Status.BAD_REQUEST).build();
		}
		try
		{
			Runtime.getRuntime().exec(apiConfig.getPythonCommand() + " " + fileName);
		}
		catch (IOException e)
		{
			logger.error(e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		logger.info("Set RBG-Mode to " + mode.getMode());
		return Response.ok().build();
	}
}
