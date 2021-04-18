package de.mymiggi.rgb.stripe.api.actions.helpers;

import java.util.List;

public class IsContainAction
{
	public boolean run(String stringToCheck, List<String> stringList)
	{
		for (String tempToken : stringList)
		{
			if (tempToken.equals(stringToCheck))
			{
				return true;
			}
		}
		return false;
	}
}
