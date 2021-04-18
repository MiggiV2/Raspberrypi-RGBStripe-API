package de.mymiggi.rgb.stripe.api.entity;

public class Client
{
	private String clientID;
	private String clientToken;
	private String registerTimeStamp;

	public String getClientID()
	{
		return clientID;
	}

	public void setClientID(String clientID)
	{
		this.clientID = clientID;
	}

	public String getClientToken()
	{
		return clientToken;
	}

	public void setClientToken(String clientToken)
	{
		this.clientToken = clientToken;
	}

	public String getRegisterTimeStamp()
	{
		return registerTimeStamp;
	}

	public void setRegisterTimeStamp(String registerTimeStamp)
	{
		this.registerTimeStamp = registerTimeStamp;
	}
}
