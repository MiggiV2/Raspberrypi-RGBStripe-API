package de.mymiggi.rgb.stripe.api.actions.manager.core;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import de.mymiggi.rgb.stripe.api.actions.manager.CreateClientIDAction;

public class RegisterClientAction
{
	private static Logger logger = Logger.getLogger(BindClientToTimeStampAction.class);

	public Response run(List<String> clientIDs)
	{
		try
		{
			String clientID = new CreateClientIDAction().run(clientIDs.size());
			clientIDs.add(clientID);
			return Response.ok(clientID).build();
		}
		catch (NoSuchAlgorithmException e)
		{
			logger.error(e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
