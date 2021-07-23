package de.mymiggi.rgb.stripe.api_tests;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import de.mymiggi.rgb.stripe.api.actions.manager.BuildTokenAction;
import de.mymiggi.rgb.stripe.api.entity.Client;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class LoginTest
{

	private String userName = "admin";
	private String pw = "1234";
	private final String baseURL = "http://localhost:8081/";

	@Test
	void test() throws NoSuchAlgorithmException, IOException, InterruptedException
	{
		Client client = new Client();
		getClientID(client);
		getTimeStamp(client);
		login(client);
		try
		{
			login(client);
			fail();
		}
		catch (NoSuchAlgorithmException | IOException e)
		{
		}
		pw = "12345";
		try
		{
			login(client);
			fail();
		}
		catch (NoSuchAlgorithmException | IOException e)
		{
		}
	}

	private void getClientID(Client client) throws IOException
	{
		OkHttpClient httpClient = new OkHttpClient();
		Request request = new Request.Builder()
			.url(baseURL + "/rgb-stripe/get-clientID")
			.build();
		Response response = httpClient.newCall(request).execute();
		if (response.code() != 200)
		{
			throw new IOException();
		}
		client.setClientID(response.body().string());
	}

	private void getTimeStamp(Client client) throws IOException
	{
		OkHttpClient httpClient = new OkHttpClient();
		RequestBody body = RequestBody.create(new Gson().toJson(client), MediaType.parse("application/json; charset=utf-8"));
		Request request = new Request.Builder()
			.url(baseURL + "/rgb-stripe/get-timestamp")
			.put(body)
			.build();
		Response response = httpClient.newCall(request).execute();
		if (response.code() != 200)
		{
			throw new IOException();
		}
		client.setRegisterTimeStamp(response.body().string());
	}

	private void login(Client client) throws IOException, NoSuchAlgorithmException
	{
		String clientToken = new BuildTokenAction().run(client, userName, pw);
		client.setClientToken(clientToken);
		OkHttpClient httpClient = new OkHttpClient();
		RequestBody body = RequestBody.create(new Gson().toJson(client), MediaType.parse("application/json; charset=utf-8"));
		Request request = new Request.Builder()
			.url(baseURL + "rgb-stripe/login")
			.post(body)
			.build();
		Response response = httpClient.newCall(request).execute();
		System.err.println("Code: " + response.code() + "\r\n" + response.body().string() + "\r\n");
		if (response.code() != 200)
		{
			throw new IOException();
		}
	}
}