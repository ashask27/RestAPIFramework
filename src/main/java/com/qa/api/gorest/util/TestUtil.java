package com.qa.api.gorest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {

	/**
	 * This method is used to convert POJO Object to JSON String
	 * @param obj
	 * @return JSON String
	 */
	
	public static String getSerializedJSON(Object obj) {
		//Convert POJO to JSON
		String userJson = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			userJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return userJson;
	}
}
