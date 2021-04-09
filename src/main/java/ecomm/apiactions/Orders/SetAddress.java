package ecomm.apiactions.Orders;

import java.util.Map;

import com.aventstack.extentreports.Status;

import ecomm.apibase.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SetAddress extends BaseTest {

	/*
	 * Method will set the shipping Address Return the version and cart total
	 */
	public String[] setAddress(String baseUri, String path, Map<String, String> pathParamValues, String token,
			Object obj) {
		String version = "";
		String centAmount = "";
		RestAssured.baseURI = baseUri;
		Response response = RestAssured.given().headers("Authorization", "Bearer " + token)
				.headers("Content-Type", "application/json").pathParams(pathParamValues).body(obj).post(path);
		// System.out.println(response.getBody().asString());
		int statusCode = response.getStatusCode();
		if (200 == statusCode) {
			logger.log(Status.PASS, " Set Address API is success and the status code is :" + statusCode);
			version = String.valueOf(response.jsonPath().getString("version"));
			centAmount = String.valueOf(response.jsonPath().getString("totalPrice.centAmount"));
			logger.log(Status.INFO, "Set Address Version: " + version);

		} else {
			logger.log(Status.FAIL, "Set Address API is failed");
		}
		return new String[] { version, centAmount };

	}
}
