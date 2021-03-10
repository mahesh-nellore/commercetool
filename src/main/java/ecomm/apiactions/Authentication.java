package ecomm.apiactions;


import com.aventstack.extentreports.Status;

import ecomm.apibase.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Authentication extends BaseTest{

	/*
	 * Get an Access Token
	 * Method will return an Array Of Strings
	 * access token and project_key
	 */
	public String[]  getAccessToken(String baseUri, String path, String username, String password, String paramKey, String paramValue) {
		String token = "";
		String project_key ="";
		RestAssured.baseURI = baseUri;
		Response res = RestAssured.given().auth().preemptive().basic(username, password).queryParam(paramKey, paramValue).body("").post(path);
		int statusCode = res.getStatusCode();
		if(200 == statusCode) {
			logger.log(Status.PASS, "Authentication API call is Success and the status code is: "+statusCode);
			token = res.jsonPath().getString("access_token");
			logger.log(Status.INFO, "The Access Token is: "+token);
			project_key = res.jsonPath().getString("scope").split(" ")[1].split(":")[1].replaceAll("\\s+", "");
			logger.log(Status.INFO, "The Project_key is: "+project_key);
			
		}else {
			logger.log(Status.FAIL, "Authentication API call is Success and the status code is: "+statusCode);
		}return new String[] {token, project_key};
	}

}
