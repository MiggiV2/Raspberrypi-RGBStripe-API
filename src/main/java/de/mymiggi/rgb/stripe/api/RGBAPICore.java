package de.mymiggi.rgb.stripe.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.mymiggi.rgb.stripe.api.actions.GetAllModeNumbers;
import de.mymiggi.rgb.stripe.api.actions.RenameModeAction;
import de.mymiggi.rgb.stripe.api.actions.TryToRunPythonSkript;
import de.mymiggi.rgb.stripe.api.entity.APIConfig;
import de.mymiggi.rgb.stripe.api.entity.RGBMode;

@Path("/rgb-stripe")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RGBAPICore
{
	private APIConfig apiConfig = new ConfigBuilder().build();

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
		Response response = new TryToRunPythonSkript().run(mode, apiConfig);
		return response;
	}

	@Path("mode-rename")
	@POST
	public Response rename(RGBMode mode)
	{
		Response response = new RenameModeAction().run(mode);
		return response;
	}
}
