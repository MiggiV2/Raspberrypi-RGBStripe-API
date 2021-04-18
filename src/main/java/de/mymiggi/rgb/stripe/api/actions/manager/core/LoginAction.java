package de.mymiggi.rgb.stripe.api.actions.manager.core;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import de.mymiggi.rgb.stripe.api.actions.helpers.IsContainAction;
import de.mymiggi.rgb.stripe.api.actions.manager.BuildTokenAction;
import de.mymiggi.rgb.stripe.api.entity.Client;

public class LoginAction
{
	private static Logger logger = Logger.getLogger(LoginAction.class);

	public Response run(Client client, List<String> tokenBlackList, List<String> clientIDs, String userName, String pw)
	{
		if (!new IsContainAction().run(client.getClientID(), clientIDs))
		{
			return Response.status(Status.UNAUTHORIZED).entity("Client not registered!").build();
		}
		if (new IsContainAction().run(client.getClientToken(), tokenBlackList))
		{
			return Response.status(Status.UNAUTHORIZED).entity("Token already used!").build();
		}
		try
		{
			String expectedToken = new BuildTokenAction().run(client, userName, pw);
			if (expectedToken.equals(client.getClientToken()))
			{
				tokenBlackList.add(expectedToken);
				return Response.ok("Logged in!").build();
			}
		}
		catch (NoSuchAlgorithmException e)
		{
			logger.error(e);
		}
		return Response.status(Status.UNAUTHORIZED).entity("Wrong username or password!").build();
	}
}
