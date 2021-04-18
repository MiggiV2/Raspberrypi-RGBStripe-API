package de.mymiggi.rgb.stripe.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import de.mymiggi.rgb.stripe.api.actions.manager.core.BindClientToTimeStampAction;
import de.mymiggi.rgb.stripe.api.actions.manager.core.LoginAction;
import de.mymiggi.rgb.stripe.api.actions.manager.core.RegisterClientAction;
import de.mymiggi.rgb.stripe.api.entity.APIConfig;
import de.mymiggi.rgb.stripe.api.entity.ClassWithAbstractMethod;
import de.mymiggi.rgb.stripe.api.entity.Client;

public class ClientManager
{
	private Map<String, String> clientIDAndTimeStampMAP = new HashMap<String, String>();
	private List<String> clientIDs = new ArrayList<String>();
	private List<String> tokenBlackList = new ArrayList<String>();
	private static Logger logger = Logger.getLogger(ClientManager.class);

	private String userName = "admin";
	private String pw = "thuSw?=r9t!onaH";

	public ClientManager(APIConfig apiConfig)
	{
		this.userName = apiConfig.getUserName();
		this.pw = apiConfig.getPassword();
	}

	public Response loginAndRun(Client client, ClassWithAbstractMethod abstractMethod)
	{
		Response response = new LoginAction().run(client, tokenBlackList, clientIDs, userName, pw);
		if (response.getStatus() == 200)
		{
			try
			{
				abstractMethod.run();
			}
			catch (Exception e)
			{
				logger.error(e);
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			}
		}
		else
		{
			logger.warn("Failed to login!");
		}
		return response;
	}

	public Response registerClient()
	{
		return new RegisterClientAction().run(clientIDs);
	}

	public Response binde(String clientID)
	{
		return new BindClientToTimeStampAction().run(clientID, clientIDAndTimeStampMAP, clientIDs);
	}
}
