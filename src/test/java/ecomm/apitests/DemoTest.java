package ecomm.apitests;

import java.util.HashMap;
import java.util.Map;

import ecomm.apirequestpayloads.CreatePayment.AmountPlanned;
import ecomm.apirequestpayloads.CreatePayment.CreatePayment;
import ecomm.apirequestpayloads.CreatePayment.Custom;
import ecomm.apirequestpayloads.CreatePayment.Fields;
import ecomm.apirequestpayloads.CreatePayment.PaymentMethodInfo;
import ecomm.apirequestpayloads.CreatePayment.Type;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DemoTest {
	
	public static void main(String[] args) {
		
		int cartTotal = 1400;
		RestAssured.baseURI = "https://api.australia-southeast1.gcp.commercetools.com";
		String path = "/{project-key}/payments";
		Map<String, String> map = new HashMap<String, String>();
		map.put("project-key", "breville-trial");
		Type type = new Type();
		Fields fields = new Fields(cartTotal);
		Custom custom = new Custom();
		custom.setType(type);
		custom.setFields(fields);
		AmountPlanned amountPlanned = new AmountPlanned();
		amountPlanned.setCentAmount(cartTotal);
		PaymentMethodInfo paymentMethodInfo = new PaymentMethodInfo();
		CreatePayment createPayment = new CreatePayment();
		createPayment.setAmountPlanned(amountPlanned);
		createPayment.setPaymentMethodInfo(paymentMethodInfo);
		createPayment.setCustom(custom);
		Response response = RestAssured.given().log().all().headers("Authorization", "Bearer " + "jiOEstQhiMoxz8p4Q3VopubNGfcDdooD")
				.headers("Content-Type", "application/json").pathParams(map).body(createPayment).post(path);
		System.out.println("Status Code: "+response.getStatusCode());
		System.out.println("Response: "+response.getBody().asString());
	}

}
