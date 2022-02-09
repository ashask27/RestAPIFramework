package com.qa.api.gorest.restclient;

import java.io.File;
import java.util.Map;

import com.qa.api.gorest.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * This class has all http methods which will call the APIs and has generic methods for getting 
 * the generic response and fetch the values from response
 * @author asha.koli
 *
 */
public class RestClient {

	
	private static boolean setBaseURI(String baseURI) {
		
		if (baseURI != null && !baseURI.isEmpty()) {
			RestAssured.baseURI = baseURI;
			return true;
		} else {
			return false;
		}
		
	}
	
	private static RequestSpecification createRequest(String contentType, String token, Map<String, String> queryParams, boolean log) {
		RequestSpecification request;
		if (log) {
			request = RestAssured.given().relaxedHTTPSValidation().log().all();
		} else {
			request = RestAssured.given().relaxedHTTPSValidation();
		}
		
		if (token != null) {
			request.header("Authorization", "Bearer " + token);
		}
		
		if (contentType != null) {
			if (contentType.equalsIgnoreCase("JSON")) {
				request.contentType(ContentType.JSON);
			} else if (contentType.equalsIgnoreCase("XML")) {
				request.contentType(ContentType.XML);
			} else if (contentType.equalsIgnoreCase("TEXT")) {
				request.contentType(ContentType.XML);
			} else if (contentType.equalsIgnoreCase("multipart")) {
				request.multiPart("image", new File("C:\\Users\\asha.koli\\Downloads\\image.png"));
			}
		}
		
		if (!(queryParams==null)) {
			request.queryParams(queryParams);
		}
		
		return request;
	}
	
	
	
	private static void addRequestPayload(RequestSpecification request, Object obj) {
		
		if (obj instanceof Map) {
			request.formParams((Map<String, String>) obj);
		} else {
			String jsonString = TestUtil.getSerializedJSON(obj);
			request.body(jsonString);
		}
	}
	
	
	
	private static Response getResponse(String httpMethod, RequestSpecification request, String basePath) {
		return executeAPI(httpMethod, request, basePath);
	}
	
	
	
	
	private static Response executeAPI(String httpMethod, RequestSpecification request, String basePath) {
		Response response = null;
		switch(httpMethod) {
		case "GET":
			response = request.get(basePath);
			break;
		case "POST":
			response = request.post(basePath);
			break;
		case "DELETE":
			response = request.delete(basePath);
			break;
		case "PUT":
			response = request.put(basePath);
			break;
		default:
			System.out.println("Invalid HTTP method");
				break;
		}
		
		return response;
	}
	
	// CRUD - GET, POST, PUT , DELETE
	
	/**
	 * This method returns response of the GET call 
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param queryParams
	 * @param log
	 * @return Response - Get call response
	 */
	public static Response doGet(String contentType, String baseURI, String basePath, String token, 
			Map<String, String> queryParams, boolean log) {
		
		if (setBaseURI(baseURI)) {
			RequestSpecification request = createRequest(contentType, token, queryParams, log);
			return getResponse("GET", request, basePath);
		} else {
			return null;
		}		
	}
	


	/**
	 * This is a method for sending POST API request
	 * @param contentType
	 * @param baseURI
	 * @param basePath
	 * @param token
	 * @param paramsMap
	 * @param log
	 * @param payload
	 * @return
	 */
	public static Response doPost(String contentType, String baseURI, String basePath, String token, 
			Map<String, String> paramsMap,  boolean log, Object obj) {
		
		if (setBaseURI(baseURI)) {
			RequestSpecification request = createRequest(contentType, token, paramsMap, log);
			addRequestPayload(request, obj);
			return getResponse("POST", request, basePath);
		} else {
			return null;
		}		
	}

	
	
}
