package de.mymiggi.rgb.stripe.api.actions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.mymiggi.rgb.stripe.api.actions.helpers.ReadNickNameMapAction;
import de.mymiggi.rgb.stripe.api.entity.APIConfig;
import de.mymiggi.rgb.stripe.api.entity.RGBMode;

public class GetAllModeNumbers
{
	private String filePrefix;
	private String fileSuffix;

	public List<RGBMode> run(APIConfig apiConfig)
	{
		filePrefix = apiConfig.getPythonFileNamePattern().split("%s")[0];
		fileSuffix = apiConfig.getPythonFileNamePattern().split("%s")[1];
		List<String> modeNumberList = new ArrayList<String>();
		File rootDir = new File(System.getProperty("user.dir"));
		File[] filesInRoot = rootDir.listFiles();
		for (File temp : filesInRoot)
		{
			if (!temp.isDirectory() && temp.getName().endsWith(fileSuffix) && temp.getName().startsWith(filePrefix))
			{
				modeNumberList.add(getModeNumber(temp));
			}
		}
		List<RGBMode> rgbModes = new ArrayList<RGBMode>();
		Map<String, String> nickNameMap = new ReadNickNameMapAction().run();
		for (String temp : modeNumberList)
		{
			String nickName = temp;
			if (nickNameMap.containsKey(temp))
			{
				nickName = nickNameMap.get(temp);
			}
			RGBMode mode = new RGBMode()
				.setMode(temp)
				.setNickName(nickName);
			rgbModes.add(mode);
		}
		return rgbModes;
	}

	private String getModeNumber(File temp)
	{
		String[] splite1 = temp.getName().split(filePrefix);
		return splite1[1].split(fileSuffix)[0];
	}
}
