package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.ExcelUtil;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateUserTest {

	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "5571d4562362c080cdd0bcb8b31d3ef4b29399b515c17557928bc6777b60140b";
	
	
	@DataProvider
	public Object[][] getUserData() {
		Object userData[][] = ExcelUtil.getTestData("userdata");
		return userData;
	}
	
	
	@Test (enabled = true, dataProvider = "getUserData")
	public void createUserAPIPostAPI(String name, String gender, String email, String status) {	
		User user = new User(name, gender, email, status);
		
		//User user = new User("Tom", "male", "Tomy@123.com", "active");
		Response response = RestClient.doPost("JSON", baseURI, basePath, token, null, true, user);
		
		JsonPath js = response.jsonPath();
		
		System.out.println(response.prettyPrint());
		System.out.println(response.statusLine());
		
		Assert.assertEquals(response.statusCode(), 200);
		
	}

}
