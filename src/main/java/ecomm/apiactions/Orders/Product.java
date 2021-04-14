package ecomm.apiactions.Orders;

import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.aventstack.extentreports.Status;
import ecomm.apibase.BaseTest;
import io.restassured.RestAssured;

public class Product extends BaseTest {

	public Map<String, String> getProductDetails(String baseUri, String path, String token, String projectKey,
			String productId) {
		RestAssured.baseURI = baseUri;
		Map<String, String> map = new HashMap<String, String>();

		try {
			HttpGet request = new HttpGet("https://api.australia-southeast1.gcp.commercetools.com/" + projectKey
					+ "/product-projections/key=" + productId);
			request.addHeader("Authorization", "Bearer " + token);
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = httpClient.execute(request);
			int statusCode = response.getStatusLine().getStatusCode();
			if (200 == statusCode) {
				logger.log(Status.PASS,
						"Get the Product Details API calls is Success and the status code is: " + statusCode);
				String result = EntityUtils.toString(response.getEntity());
				JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(result);
				map.put("productId", (String) json.get("id"));
				map.put("version", String.valueOf(json.get("version")));
				JSONObject mastervariant = (JSONObject) json.get("masterVariant");
				map.put("VariantId", String.valueOf(mastervariant.get("id")));
				JSONObject availability = (JSONObject) mastervariant.get("availability");
				JSONObject channelAvailability = (JSONObject) availability.get("channels");
				for (Object object : channelAvailability.keySet()) {
					map.put("supplyChannelId", (String) object);
				}
				JSONArray price = (JSONArray) mastervariant.get("prices");
				JSONObject distributionChannelObject = (JSONObject) price.get(0);
				JSONObject channelObj = (JSONObject) distributionChannelObject.get("channel");
				map.put("distributionChannelId", (String) channelObj.get("id"));
				logger.log(Status.INFO, "Got the below mentioned details from the API response:");
				map.forEach((k, v) -> logger.log(Status.INFO, k + "                                      : " + v));
			} else {
				logger.log(Status.FAIL,
						"Get the product details API call is Failed and the status code is: " + statusCode);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return map;

	}

}
