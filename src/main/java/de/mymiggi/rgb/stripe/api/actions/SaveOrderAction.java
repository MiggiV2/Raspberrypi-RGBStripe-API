package de.mymiggi.rgb.stripe.api.actions;

import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import de.mymiggi.rgb.stripe.api.actions.helpers.SaveOrderFileAction;
import de.mymiggi.rgb.stripe.api.entity.APIConfig;

public class SaveOrderAction
{
	private Logger logger = Logger.getLogger(this.getClass());

	public Response run(Map<String, String> order, APIConfig apiConfig)
	{
		int minSize = new GetAllModeNumbers().run(apiConfig).size();
		if (order.size() >= minSize)
		{
			new SaveOrderFileAction().run(order);
			return Response.ok().build();
		}
		else
		{
			logger.warn("MinSize:" + minSize + " orderSize:" + order.size() + " -> order can't be so short!");
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
}
