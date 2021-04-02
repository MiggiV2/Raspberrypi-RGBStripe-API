package de.mymiggi.rgb.stripe.api.entity;

public class APIConfig
{
	private String pythonCommand = "python3";
	private String pythonFileNamePattern = "stripe-mode%s.py";
	private int maxModeDigitLength = 3;

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
}
