package ecomm.apiactions.Orders;

import java.util.Map;

import com.aventstack.extentreports.Status;

import ecomm.apibase.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CreatePayment extends BaseTest{
	
	/*
	 * Method will do Create Payment
	 */
	public String createPaymentMethod(String baseUri, String path, Map<String, String> pathParamValues, String token,
			Object obj) {
		String version = "";
		String paymentID="";
		RestAssured.baseURI = baseUri;
		Response response = RestAssured.given().headers("Authorization", "Bearer " + token)
				.headers("Content-Type", "application/json").pathParams(pathParamValues).body(obj).post(path);
		int statusCode = response.getStatusCode();
		
		System.out.println("Status code is :"+statusCode);
		System.out.println(response.getBody().asString());
		
		if (201 == statusCode) {
			logger.log(Status.PASS, " Create Payment Method API is success and the status code is :" + statusCode);
			version = String.valueOf(response.jsonPath().getString("version"));
			System.out.println("Ekada Version emi ante "+version);
			paymentID=response.jsonPath().getString("id");
			logger.log(Status.INFO, "Create Payment Method: " + version);
			
		} else {
			logger.log(Status.FAIL, "Create Payment Method API is failed");
		}
		return paymentID;

	}
	
	public String addPaymentToCart(String baseUri, String path, Map<String, String> pathParamValues, String token,
			Object obj) {
		String version = "";
		RestAssured.baseURI = baseUri;
		Response response = RestAssured.given().headers("Authorization", "Bearer " + token)
				.headers("Content-Type", "application/json").pathParams(pathParamValues).body(obj).post(path);
		int statusCode = response.getStatusCode();
		
		System.out.println("Status code is :"+statusCode);
		System.out.println(response.getBody().asString());
		
		if (200 == statusCode) {
			logger.log(Status.PASS, " Create Payment Method API is success and the status code is :" + statusCode);
			version = String.valueOf(response.jsonPath().getString("version"));
			System.out.println("Ekada Version emi ante "+version);
			logger.log(Status.INFO, "Create Payment Method: " + version);
			
		} else {
			logger.log(Status.FAIL, "Create Payment Method API is failed");
		}
		return version;

	}

}
