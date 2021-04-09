package ecomm.apitests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import ecomm.apibase.BaseTest;
import ecomm.apirequestpayloads.CreateCart;

public class AddMultipleOrder extends BaseTest{
	
	public static String token = "";
	public static String cart_id = "";
	public static String project_key = "";
	public static String baseUri;
	String version = "";
	@Test(priority = 1)
	public void TC_001_getAccessToken() {
		/*
		 * Get An Access Token
		 */
		logger = reporter.createTest("Get access Token API");
		String authUri = apiprop.getProperty("authUri");
		String tokenpath = apiprop.getProperty("accessToken");
		String username = apiprop.getProperty("authUsername");
		String password = apiprop.getProperty("authPassword");
		String paramKey = apiprop.getProperty("queryParamKey");
		String paramValue = apiprop.getProperty("queryParamValue");

		String values[] = authentication.getAccessToken(authUri, tokenpath, username, password, paramKey, paramValue);
		token = values[0];
		project_key = values[1];
		Assert.assertTrue(values.length > 0);
		logger.log(Status.PASS,
				"Get Access Token API call is Success and successfully retrived the neccesary info from the response");
	}
	@Test(priority = 2)
	public void TC_003_CreateCart() {
		logger = reporter.createTest("Create a cart API");
		baseUri = apiprop.getProperty("baseUri");
		String path = apiprop.getProperty("createCart");
		CreateCart createcart = new CreateCart();
		createcart.setCurrency("USD");
		String values[] = cart.createCart(baseUri, path, project_key, token, createcart);
		cart_id = values[0];
		version = values[1];
		Assert.assertTrue(values.length > 0);
		logger.log(Status.PASS,
				"Create Cart API call is Success and successfully retrived the neccesary info from the response");
	}
	


}
