package com.qa.api.restfulbooker.tests;

import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.Booking;
import com.qa.api.gorest.pojo.Bookingdates;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.TestUtil;

import io.restassured.response.Response;

public class CreateBooking {

	
	@Test
	public void createBookingTest() {
		Bookingdates dates = new Bookingdates("2019-10-10", "2019-10-10");
		Booking b = new Booking("Saasha", "ertyu", 1234, false, dates, "Lunch");
		String payload = TestUtil.getSerializedJSON(b);
		
		System.out.println("Generated payload " + payload);
		
		Response res = RestClient.doPost("JSON", "https://restful-booker.herokuapp.com", "booking", null, null, true, payload);
		
		System.out.println(res.prettyPrint());
		System.out.println(res.getStatusLine());
	}
}
