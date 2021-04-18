package de.mymiggi.rgb.stripe.api.entity;

public class APIConfig
{
	private String pythonCommand = "python3";
	private String pythonFileNamePattern = "stripe-mode%s.py";
	private int maxModeDigitLength = 3;
	private String userName = "admin";
	private String password = "4RGB1sN0ICe";
	/*
	 * Default user and pw if nothing is set in config file
	 */

	public String getPythonCommand()
	{
		return pythonCommand;
	}

	public APIConfig setPythonCommand(String pythonCommand)
	{
		this.pythonCommand = pythonCommand;
		return this;
	}

	public int getMaxModeDigitLength()
	{
		return maxModeDigitLength;
	}

	public APIConfig setMaxModeDgitLength(int maxModeDigitLength)
	{
		this.maxModeDigitLength = maxModeDigitLength;
		return this;
	}

	public String getPythonFileNamePattern()
	{
		return pythonFileNamePattern;
	}

	public APIConfig setPythonFileNamePattern(String pythonFileNamePattern)
	{
		this.pythonFileNamePattern = pythonFileNamePattern;
		return this;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}
}
