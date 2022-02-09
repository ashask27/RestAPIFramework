package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.TestUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;

@Epic("get user gorest API feature")
@Feature("get api tests")
public class GetUserTest {
	
	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "5571d4562362c080cdd0bcb8b31d3ef4b29399b515c17557928bc6777b60140b";
	
	@Test
	public void getAllUserListAPITest() {
		Response response = RestClient.doGet("JSON", baseURI, basePath, token, null, true);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}

	
	@Test
	public void getUserWithQueryParamsTest() {
		
		Map<String, String> params = new HashMap<String, String>(); 
		params.put("name", "Subha");
		params.put("gender", "female");
		
		Response response = RestClient.doGet("JSON", baseURI, basePath, token, params, true);
	
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}

}
