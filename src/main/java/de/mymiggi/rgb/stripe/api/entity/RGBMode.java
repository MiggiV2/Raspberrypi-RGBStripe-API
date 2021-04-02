package de.mymiggi.rgb.stripe.api.entity;

public class RGBMode
{
	private String mode;
	private String nickName;

	public String getMode()
	{
		return mode;
	}

	public RGBMode setMode(String mode)
	{
		this.mode = mode;
		return this;
	}

	public String getNickName()
	{
		return nickName;
	}

	public RGBMode setNickName(String nickName)
	{
		this.nickName = nickName;
		return this;
	}
}
