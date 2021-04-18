package de.mymiggi.rgb.stripe.api.actions;

import org.jboss.logging.Logger;

import de.mymiggi.rgb.stripe.api.entity.ClassWithAbstractMethod;

public class LoginTestLoggingAction extends ClassWithAbstractMethod
{
	private static Logger logger = Logger.getLogger(LoginTestLoggingAction.class);

	@Override
	public void run() throws Exception
	{
		logger.info("Logged in!");
	}
}
