package ecomm.apiactions.Orders;

import java.util.Map;
import com.aventstack.extentreports.Status;
import ecomm.apibase.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Cart extends BaseTest {
	/*
	 * Method will create a cart returns cart id and the version in an Array
	 */
	public String[] createCart(String baseUri, String path, String pathParamValue, String token, Object obj) {
		String cartId = "";
		String version = "";
		RestAssured.baseURI = baseUri;
		Response response = RestAssured.given().headers("Authorization", "Bearer " + token)
				.headers("Content-Type", "application/json").pathParam("project-key", pathParamValue).body(obj)
				.post(path);
		int statusCode = response.getStatusCode();
		if (201 == statusCode) {
			logger.log(Status.PASS, " Create Cart API is success and the status code is :" + statusCode);
			cartId = response.jsonPath().getString("id");
			logger.log(Status.INFO, "Cart Id is: " + cartId);
			version = String.valueOf(response.jsonPath().getString("version"));
			logger.log(Status.INFO, "Cart Id Version: " + version);

		} else {
			logger.log(Status.FAIL, "Create cart API call is failed");
			logger.log(Status.INFO,"Status Code: "+ statusCode);
			logger.log(Status.INFO,"Response : "+ response.getBody().asString());
		}
		return new String[] { cartId, version };

	}

	/*
	 * Method will add an item to the Cart Returns the Version
	 */
	public String addLineItemToCart(String baseUri, String path, Map<String, String> pathParamValues, String token,
			Object obj) {
		String version = "";
		RestAssured.baseURI = baseUri;
		Response response = RestAssured.given().headers("Authorization", "Bearer " + token)
				.headers("Content-Type", "application/json").pathParams(pathParamValues).body(obj).post(path);
		int statusCode = response.getStatusCode();
		if (200 == statusCode) {
			logger.log(Status.PASS, " Add line item API is success and the status code is :" + statusCode);
			version = String.valueOf(response.jsonPath().getString("version"));
			logger.log(Status.INFO, "Add line item to cart Version: " + version);

		} else {
			logger.log(Status.FAIL, "Add line item API is failed");
			logger.log(Status.INFO,"Status Code: "+ statusCode);
			logger.log(Status.INFO,"Response : "+ response.getBody().asString());
		}
		return version;
	}
	
	/*
	 * Method will return the cart items & Details
	 */
	public String[] getCartById(String baseUri, String path, Map<String, String> pathParamValues, String token) {
		String version = "";
		String amount = "";
		RestAssured.baseURI = baseUri;
		Response response = RestAssured.given().headers("Authorization", "Bearer " + token)
				.headers("Content-Type", "application/json").pathParams(pathParamValues).get(path);
		int statusCode = response.getStatusCode();
		if (200 == statusCode) {
			logger.log(Status.PASS, " Add line item API is success and the status code is :" + statusCode);
			version = String.valueOf(response.jsonPath().getString("version"));
			logger.log(Status.INFO, "Add line item to cart Version: " + version);
			amount = String.valueOf(response.jsonPath().getString("totalPrice.centAmount"));
			

		} else {
			logger.log(Status.FAIL, "Add line item API is failed");
			logger.log(Status.INFO,"Status Code: "+ statusCode);
			logger.log(Status.INFO,"Response : "+ response.getBody().asString());
		}
		return new String[] {version, amount};
	}

	/*
	 * Method will add a discount to the Cart Returns the Version
	 */
	public String[] addDiscountToTheCart(String baseUri, String path, Map<String, String> pathParamValues, String token,
			Object obj) {
		String version = "";
		String price = "";
		String discountedPrice = "";
		RestAssured.baseURI = baseUri;
		Response response = RestAssured.given().headers("Authorization", "Bearer " + token)
				.headers("Content-Type", "application/json").pathParams(pathParamValues).body(obj).post(path);
		int statusCode = response.getStatusCode();
		if (200 == statusCode) {
			logger.log(Status.PASS, " Add discount to the cart API is success and the status code is :" + statusCode);
			version = String.valueOf(response.jsonPath().getString("version"));
			logger.log(Status.INFO, "Add discount to the cart Version: " + version);
			price = response.jsonPath().getString("lineItems[0].price.value.centAmount");
			discountedPrice = response.jsonPath().getString("lineItems[0].discountedPrice.value.centAmount");
			logger.log(Status.INFO, "Discounted Amount: " + discountedPrice);
			logger.log(Status.INFO, " Price Amount: " + price);
			/*
			 * String price = "1600"; String discountedPrice =
			 * String.valueOf(Integer.parseInt(price) - Integer.parseInt(price)*0.1);
			 * System.out.println(discountedPrice);
			 */

		} else {
			logger.log(Status.FAIL, "Add discount to the cart API is failed");
			logger.log(Status.INFO,"Status Code: "+ statusCode);
			logger.log(Status.INFO,"Response : "+ response.getBody().asString());
		}
		return new String[] {version, price, discountedPrice};

	}

}
