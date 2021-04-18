package de.mymiggi.rgb.stripe.api.actions.helpers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashAction
{
	public String run(String stringToHash) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] messageDigest = md.digest(stringToHash.getBytes());
		BigInteger no = new BigInteger(1, messageDigest);
		String hashtext = no.toString(16);
		while (hashtext.length() < 32)
		{
			hashtext = "0" + hashtext;
		}
		return hashtext;
	}
}
