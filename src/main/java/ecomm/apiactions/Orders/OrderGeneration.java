package ecomm.apiactions.Orders;

import java.util.Map;

import com.aventstack.extentreports.Status;

import ecomm.apibase.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OrderGeneration extends BaseTest{
	
	/*
	 * Method will set the shipping Method
	 * Return the version
	 */
	public String[] orderGeneration(String baseUri, String path, Map<String, String> pathParamValues, String token,
			Object obj) {
		String version = "";
		String order_id = "";
		RestAssured.baseURI = baseUri;
		Response response = RestAssured.given().headers("Authorization", "Bearer " + token)
				.headers("Content-Type", "application/json").pathParams(pathParamValues).body(obj).post(path);
		int statusCode = response.getStatusCode();	
		if (201 == statusCode) {
			logger.log(Status.PASS, " Create Order API call is success and the status code is :" + statusCode);
			version = String.valueOf(response.jsonPath().getString("version"));
			logger.log(Status.INFO, "Set Address Version: " + version);
			order_id = response.jsonPath().getString("id");
			logger.log(Status.INFO, "Generated Order_id: " + order_id);

		} else {
			logger.log(Status.FAIL, "Create Order API call is failed");
			logger.log(Status.INFO,"Status Code: "+ statusCode);
			logger.log(Status.INFO,"Response : "+ response.getBody().asString());
		}
		return new String[] {version, order_id};

	}
	
	public String getOrderNumber(String baseUri, String path, Map<String, String> pathParamValues, String token) {
		String orderNumber = "";
		RestAssured.baseURI = baseUri;
		Response response = RestAssured.given().headers("Authorization", "Bearer " + token)
				.headers("Content-Type", "application/json").pathParams(pathParamValues).get(path);
		int statusCode = response.getStatusCode();
		if (200 == statusCode) {
			logger.log(Status.PASS, " Get Order API call is success and the status code is :" + statusCode);
			orderNumber = response.jsonPath().getString("orderNumber");
			logger.log(Status.INFO, "Order Number is:" + orderNumber);
		} else {
			logger.log(Status.FAIL, "Get Order API call is failed");
			logger.log(Status.INFO,"Status Code: "+ statusCode);
			logger.log(Status.INFO,"Response : "+ response.getBody().asString());
		}return orderNumber;
	}

}
