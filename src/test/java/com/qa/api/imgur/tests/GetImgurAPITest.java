package com.qa.api.imgur.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.Token;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetImgurAPITest {

	Map<Object, Object> tokenMap;
	String accessToken;
	String accountUsername;
	
	
	@BeforeMethod
	public void setUp() {
		tokenMap = Token.getAccessToken();
		accessToken = tokenMap.get("access_token").toString();
		accountUsername = tokenMap.get("account_username").toString();
	}
	
	@Test
	public void getAccountBlockStatusTest() {

		Response response = RestClient.doGet(null, "https://api.imgur.com/", "account/v1/"+accountUsername+"/block", accessToken, null, true);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	

	@Test
	public void getAccountSettingsTest() {

		Response response = RestClient.doGet(null, "https://api.imgur.com/", "3/account/me/settings", accessToken, null, true);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	
	@Test
	public void uploadImagePostAPITest() {
		
		Map<String, String> formMap = new HashMap<String, String>();
		formMap.put("title", "test image api");
		formMap.put("description", "test description API");
		Response response =RestClient.doPost("multipart", "https://api.imgur.com/", "3/upload", accessToken, null, true, formMap); 
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}

}
