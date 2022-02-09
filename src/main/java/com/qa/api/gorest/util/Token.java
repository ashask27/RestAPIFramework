package com.qa.api.gorest.util;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;


public class Token {

	public static Map<Object, Object> getAccessToken() {
		
		Map<String, String> paramsMap = new HashMap<String, String>();
		
		paramsMap.put("refresh_token", "8d2af47eac7ba58c53dc40eabde0734ec5e77c52");
		paramsMap.put("client_id", "14fceb55be09f7e");
		paramsMap.put("client_secret", "e2cb8a0bc2685881c5c958643b702ce27a1b559e");
		paramsMap.put("grant_type", "refresh_token");
		
		
		JsonPath token = 
		given().relaxedHTTPSValidation().log().all()
		.formParams(paramsMap)
		.when().log().all()
		.post("https://api.imgur.com/oauth2/token")
		.then().log().all()
		.extract().jsonPath();
		
		System.out.println(token.getMap(""));
		return  token.getMap("");
				
	}
	
}
