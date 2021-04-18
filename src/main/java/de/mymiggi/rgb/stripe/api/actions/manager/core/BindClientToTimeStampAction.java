package de.mymiggi.rgb.stripe.api.actions.manager.core;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import de.mymiggi.rgb.stripe.api.actions.helpers.IsContainAction;

public class BindClientToTimeStampAction
{
	private static Logger logger = Logger.getLogger(BindClientToTimeStampAction.class);

	public Response run(String clientID, Map<String, String> clientIDAndTimeStampMAP, List<String> clientIDs)
	{
		String timeStamp = String.valueOf(System.currentTimeMillis());
		if (new IsContainAction().run(clientID, clientIDs))
		{
			clientIDAndTimeStampMAP.put(clientID, timeStamp);
			return Response.ok(timeStamp).build();
		}
		else
		{
			logger.warn("Client not registered");
			return Response.status(Status.FORBIDDEN).build();
		}
	}
}
