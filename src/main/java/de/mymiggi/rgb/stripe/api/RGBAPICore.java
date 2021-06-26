package de.mymiggi.rgb.stripe.api;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.mymiggi.rgb.stripe.api.actions.GetAllModeNumbers;
import de.mymiggi.rgb.stripe.api.actions.LoginTestLoggingAction;
import de.mymiggi.rgb.stripe.api.actions.ReadOrderAction;
import de.mymiggi.rgb.stripe.api.actions.RenameModeAction;
import de.mymiggi.rgb.stripe.api.actions.SaveOrderAction;
import de.mymiggi.rgb.stripe.api.actions.ShutDownAction;
import de.mymiggi.rgb.stripe.api.actions.TryToRunPythonSkript;
import de.mymiggi.rgb.stripe.api.entity.APIConfig;
import de.mymiggi.rgb.stripe.api.entity.Client;
import de.mymiggi.rgb.stripe.api.entity.RGBMode;

@Path("/rgb-stripe")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RGBAPICore
{
	private APIConfig apiConfig = new ConfigBuilder().build();
	private RGBMode lastMode;
	private ClientManager clientIDManager = new ClientManager(apiConfig);

	@GET
	public Response getModes()
	{
		List<RGBMode> modeNumberList = new GetAllModeNumbers().run(apiConfig);
		return Response.ok(modeNumberList).build();
	}

	@Path("mode")
	@PUT
	public Response runMode(RGBMode mode)
	{
		Response response = new TryToRunPythonSkript().run(mode, apiConfig, lastMode);
		this.lastMode = mode;
		return response;
	}

	@Path("mode-rename")
	@POST
	public Response rename(RGBMode mode)
	{
		Response response = new RenameModeAction().run(mode);
		return response;
	}

	@Path("order")
	@POST
	public Response saveOrder(Map<String, String> order)
	{
		Response response = new SaveOrderAction().run(order, apiConfig);
		return response;
	}

	@Path("order")
	@GET
	public Response getOrder()
	{
		Response response = new ReadOrderAction().run();
		return response;
	}

	/*
	 * Create a ClientID
	 */
	@Path("get-clientID")
	@GET
	public Response createID()
	{
		return clientIDManager.registerClient();
	}

	/*
	 * Use ClientID to get a TimeStamp
	 */
	@Path("get-timestamp")
	@PUT
	public Response getTimeStamp(Client client)
	{
		return clientIDManager.binde(client.getClientID());
	}

	/*
	 * login
	 */
	@Path("login")
	@POST
	public Response login(Client client)
	{
		return clientIDManager.loginAndRun(client, new LoginTestLoggingAction());
	}

	/*
	 * need a client with token like login above!
	 */
	@Path("shutdown")
	@POST
	public Response shutdown(Client client)
	{
		return clientIDManager.loginAndRun(client, new ShutDownAction());
	}
}
