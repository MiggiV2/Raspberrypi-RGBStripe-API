package de.mymiggi.rgb.stripe.api.actions.manager;

import java.security.NoSuchAlgorithmException;

import de.mymiggi.rgb.stripe.api.actions.helpers.HashAction;

public class CreateClientIDAction
{
	public String run(int listSize) throws NoSuchAlgorithmException
	{
		String timeStamp = String.valueOf(System.currentTimeMillis());
		return new HashAction().run(timeStamp + listSize);
	}
}
