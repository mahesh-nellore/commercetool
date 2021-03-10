package ecomm.apiactions.Orders;

import java.util.Map;

import com.aventstack.extentreports.Status;

import ecomm.apibase.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SetMethod_Shipping extends BaseTest{
	
	/*
	 * Method will set the shipping Method
	 * Return the version and the Order ID
	 */
	public String setShippingMethod(String baseUri, String path, Map<String, String> pathParamValues, String token,
			Object obj) {
		String version = "";
		
		RestAssured.baseURI = baseUri;
		Response response = RestAssured.given().headers("Authorization", "Bearer " + token)
				.headers("Content-Type", "application/json").pathParams(pathParamValues).body(obj).post(path);
		int statusCode = response.getStatusCode();
		if (200 == statusCode) {
			logger.log(Status.PASS, " Set Shipping Method API is success and the status code is :" + statusCode);
			version = String.valueOf(response.jsonPath().getString("version"));
			logger.log(Status.INFO, "Set Shipping Method: " + version);
			
		} else {
			logger.log(Status.FAIL, "Set Shipping Method API is failed");
		}
		return version;

	}

}
