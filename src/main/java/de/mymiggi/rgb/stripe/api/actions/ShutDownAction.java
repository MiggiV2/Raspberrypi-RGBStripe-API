package de.mymiggi.rgb.stripe.api.actions;

import java.io.IOException;

import de.mymiggi.rgb.stripe.api.entity.ClassWithAbstractMethod;

public class ShutDownAction extends ClassWithAbstractMethod
{
	@Override
	public void run() throws IOException
	{
		String shutdownCommand;
		String operatingSystem = System.getProperty("os.name");
		if ("Linux".equals(operatingSystem) || "Mac OS X".equals(operatingSystem))
		{
			shutdownCommand = "shutdown -h now";
		}
		else if ("Windows".equals(operatingSystem))
		{
			shutdownCommand = "shutdown.exe -s -t 0";
		}
		else
		{
			throw new RuntimeException("Unsupported operating system.");
		}
		Runtime.getRuntime().exec(shutdownCommand);
	}
}
