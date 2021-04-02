package de.mymiggi.rgb.stripe.api.actions;

import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import de.mymiggi.rgb.stripe.api.actions.helpers.ReadNickNameMapAction;
import de.mymiggi.rgb.stripe.api.actions.helpers.SaveNickNameMapAction;
import de.mymiggi.rgb.stripe.api.entity.RGBMode;

public class RenameModeAction
{
	private static Logger logger = Logger.getLogger(RenameModeAction.class);

	public Response run(RGBMode rgbMode)
	{
		if (rgbMode.getNickName() == null || rgbMode.getNickName().isBlank())
		{
			logger.warn("Nickname for mode " + rgbMode.getMode() + " is null or blank!");
			return Response.status(Status.BAD_REQUEST).build();
		}
		Map<String, String> nickNameMap = new ReadNickNameMapAction().run();
		nickNameMap.put(rgbMode.getMode(), rgbMode.getNickName());
		new SaveNickNameMapAction().run(nickNameMap);
		return Response.ok().build();
	}
}
