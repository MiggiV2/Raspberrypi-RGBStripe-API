package de.mymiggi.rgb.stripe.api.actions.manager;

import java.security.NoSuchAlgorithmException;

import de.mymiggi.rgb.stripe.api.actions.helpers.HashAction;
import de.mymiggi.rgb.stripe.api.entity.Client;

public class BuildTokenAction
{
	public String run(Client client, String userName, String pw) throws NoSuchAlgorithmException
	{
		String registerTimeStamp = client.getRegisterTimeStamp();
		String part1 = new HashAction().run(registerTimeStamp + userName);
		String part2 = new HashAction().run(pw + registerTimeStamp);
		String hashedPart1 = new HashAction().run(part1 + client.getClientID());
		String hashedPart2 = new HashAction().run(part2 + client.getClientID());
		return hashedPart1 + hashedPart2;
	}
}
