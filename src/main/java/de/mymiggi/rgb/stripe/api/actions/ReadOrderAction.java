package de.mymiggi.rgb.stripe.api.actions;

import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import com.google.gson.Gson;

import de.mymiggi.rgb.stripe.api.actions.helpers.ReadOrderFileAction;

public class ReadOrderAction
{
	private Logger logger = Logger.getLogger(this.getClass());

	public Response run()
	{
		try
		{
			String json = new ReadOrderFileAction().run();
			@SuppressWarnings("unchecked")
			Map<String, String> order = new Gson().fromJson(json, Map.class);
			if (order.isEmpty())
			{
				return Response.status(Status.NO_CONTENT).build();
			}
			else
			{
				return Response.ok(order).build();
			}
		}
		catch (Exception e)
		{
			logger.warn("Failed to read order.json -> " + e.getMessage());
			return Response.status(Status.NO_CONTENT).build();
		}
	}
}
