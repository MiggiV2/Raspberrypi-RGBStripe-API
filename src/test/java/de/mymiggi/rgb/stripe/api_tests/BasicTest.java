package de.mymiggi.rgb.stripe.api_tests;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class BasicTest
{

	@Test
	void test()
	{
		given()
			.when()
			.get("/rgb-stripe/mode")
			.then()
			.statusCode(200);
	}
}
