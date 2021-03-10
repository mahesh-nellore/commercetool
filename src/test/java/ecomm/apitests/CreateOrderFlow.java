package ecomm.apitests;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import ecomm.apibase.BaseTest;
import ecomm.apirequestpayloads.CreateCart;
import ecomm.apirequestpayloads.AddDiscount.AddDiscount;
import ecomm.apirequestpayloads.AddDiscount.Discount;
import ecomm.apirequestpayloads.AddLineItem.Action;
import ecomm.apirequestpayloads.AddLineItem.AddLineItem;
import ecomm.apirequestpayloads.AddLineItem.DistributionChannel;
import ecomm.apirequestpayloads.AddLineItem.SupplyChannel;
import ecomm.apirequestpayloads.CreateOrder.CreateOrder;
import ecomm.apirequestpayloads.ShippingAddress.Addr_Action;
import ecomm.apirequestpayloads.ShippingAddress.Address;
import ecomm.apirequestpayloads.ShippingAddress.ShippingAddress;
import ecomm.apirequestpayloads.ShippingMethod.SetShippingMethod;
import ecomm.apirequestpayloads.ShippingMethod.ShippingMethod;
import ecomm.apirequestpayloads.ShippingMethod.ShippingMethodAction;
import ecomm.generic.GenericUtility;

public class CreateOrderFlow extends BaseTest {

	public static String token = "";
	public static String project_key = "";
	public static String cart_id = "";
	public static String product_id = "eba6b40f-f7a4-4005-9db4-916a17b4d3a1";
	public static String variant_id = "1";
	public static int quantity = 1;
	public static String supplyChannel_id = "dcf33f87-7f80-42a8-9bfd-b5c586db386c";
	public static String distributionChannel_id = "1426e64e-d6e2-4e05-8417-96f58d8c7657";
	public static String discount_code = "GET10%DISCOUNT";
	public static String order_id = "";
	public static String orderNumber = "";
	String baseUri;
	String version = "";
	/*
	 * Keys in the map are: supplyChannelId productId distributionChannelId version
	 * VariantId
	 */
	Map<String, String> productDetails = new HashMap<String, String>();

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
	public void TC_002_getProductDetails() {
		/*
		 * Get the product details
		 */
		logger = reporter.createTest("Get Product Details API");
		baseUri = apiprop.getProperty("baseUri");
		String getProductIdPath = apiprop.getProperty("getProductId");
		String productId = apiprop.getProperty("productId");
		productDetails = product.getProductDetails(baseUri, getProductIdPath, token, project_key, productId);
		version = productDetails.get("version");
		product_id = productDetails.get("productId");
		variant_id = productDetails.get("VariantId");
		supplyChannel_id = productDetails.get("supplyChannelId");
		distributionChannel_id = productDetails.get("distributionChannelId");
		Assert.assertTrue(productDetails.size() >= 5);
		logger.log(Status.PASS,
				"Get Product Details API call is Success and successfully retrived the neccesary info from the response");
	}

	@Test(priority = 3)
	public void TC_003_CreateCart() {
		logger = reporter.createTest("Create a cart API");
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

	@Test(priority = 4)
	public void TC_004_AddLineItemToCart() {
		logger = reporter.createTest("Add line item to the cart");
		String path = apiprop.getProperty("addLineItemOrDiscount");
		AddLineItem addlineitem = new AddLineItem();
		addlineitem.setVersion(Integer.parseInt(version));
		Action action = new Action();
		action.setProductId(product_id);
		action.setVariantId(Integer.parseInt(variant_id));
		action.setQuantity(quantity);
		DistributionChannel distributionChannel = new DistributionChannel();
		distributionChannel.setId(distributionChannel_id);
		action.setDistributionChannel(distributionChannel);
		SupplyChannel supplyChannel = new SupplyChannel();
		supplyChannel.setId(supplyChannel_id);
		action.setSupplyChannel(supplyChannel);
		addlineitem.setActions(Arrays.asList(action));
		Map<String, String> map = new HashMap<String, String>();
		map.put("project-key", project_key);
		map.put("cart-id", cart_id);
		version = cart.addLineItemToCart(baseUri, path, map, token, addlineitem);
		Assert.assertTrue(version != "" || version != null);
		logger.log(Status.PASS,
				"Add line Item to the Cart API call is Success and successfully retrived the neccesary info from the response");
	}

	@Test(priority = 5)
	public void TC_005_AddDiscountToCart() {
		logger = reporter.createTest("Add Dsicount to the cart");
		String path = apiprop.getProperty("addLineItemOrDiscount");
		AddDiscount adddiscount = new AddDiscount();
		adddiscount.setVersion(Integer.parseInt(version));
		Discount discount = new Discount();
		discount.setCode(discount_code);
		adddiscount.setActions(Arrays.asList(discount));
		Map<String, String> map = new HashMap<String, String>();
		map.put("project-key", project_key);
		map.put("cart-id", cart_id);
		version = cart.addDiscountToTheCart(baseUri, path, map, token, adddiscount);
		Assert.assertTrue(version != "" || version != null);
		logger.log(Status.PASS,
				"Add Discount to the Cart API call is Success and successfully retrived the neccesary info from the response");
	}
	
	@Test(priority = 6)
	public void TC_006_SetShippingAddress() {
		logger = reporter.createTest("Set Shipping Address");
		String path = apiprop.getProperty("addLineItemOrDiscount");
		Address addr = new Address();
		addr.setStreetName("West Adams Street");
		addr.setStreetNumber("111");
		addr.setCity("Houston");
		addr.setState("TX");
		Addr_Action addrAction = new Addr_Action();
		addrAction.setAction("setShippingAddress");
		addrAction.setAddress(addr);
		ShippingAddress shippingAddr = new ShippingAddress();
		shippingAddr.setVersion(Integer.parseInt(version));
		shippingAddr.setActions(Arrays.asList(addrAction));
		Map<String, String> map = new HashMap<String, String>();
		map.put("project-key", project_key);
		map.put("cart-id", cart_id);
		version = address.setAddress(baseUri, path, map, token, shippingAddr);		
		Assert.assertTrue(version != "" || version != null);
		logger.log(Status.PASS,
				"Set Shipping Address API call is Success and successfully retrived the neccesary info from the response");
	}
	
	@Test(priority = 7)
	public void TC_007_SetBillingAddress() {
		logger = reporter.createTest("Set Billing Address");
		String path = apiprop.getProperty("addLineItemOrDiscount");
		Address addr = new Address();
		addr.setStreetName("South Las Vegas Boulevard");
		addr.setStreetNumber("3900");
		addr.setCity("Las Vegas");
		addr.setState("NV");
		Addr_Action addrAction = new Addr_Action();
		addrAction.setAction("setBillingAddress");
		addrAction.setAddress(addr);
		ShippingAddress shippingAddr = new ShippingAddress();
		shippingAddr.setVersion(Integer.parseInt(version));
		shippingAddr.setActions(Arrays.asList(addrAction));
		Map<String, String> map = new HashMap<String, String>();
		map.put("project-key", project_key);
		map.put("cart-id", cart_id);
		version = address.setAddress(baseUri, path, map, token, shippingAddr);		
		Assert.assertTrue(version != "" || version != null);
		logger.log(Status.PASS,
				"Set Billing Address API call is Success and successfully retrived the neccesary info from the response");
	}
	
	@Test(priority = 8)
	public void TC_008_SetShippingMethod() {
		logger = reporter.createTest("Set Shipping Method");
		String path = apiprop.getProperty("addLineItemOrDiscount");
		SetShippingMethod shipping_method = new SetShippingMethod();
		shipping_method.setVersion(Integer.parseInt(version));
		ShippingMethod shippingmethod = new ShippingMethod();
		shippingmethod.setId("c15b4894-4eb2-42ea-9819-1a611e5c8f01");
		ShippingMethodAction shippingMethodAction = new ShippingMethodAction();
		shippingMethodAction.setShippingMethod(shippingmethod);
		shipping_method.setActions(Arrays.asList(shippingMethodAction));		
		Map<String, String> map = new HashMap<String, String>();
		map.put("project-key", project_key);
		map.put("cart-id", cart_id);
		version = shipMethod.setShippingMethod(baseUri, path, map, token, shipping_method);	
		Assert.assertTrue(version != "" || version != null);
		logger.log(Status.PASS,
				"Set Shipping Method API call is Success and successfully retrived the neccesary info from the response");
	}
	
	@Test(priority = 9)
	public void TC_009_CreateAnOrder() {
		logger = reporter.createTest("Create An Order");
		String path = apiprop.getProperty("createOrder");
		orderNumber = GenericUtility.getRandomNumberString();
		CreateOrder createOrder = new CreateOrder();
		createOrder.setId(cart_id);
		createOrder.setVersion(Integer.parseInt(version));
		createOrder.setOrderNumber(orderNumber);
		Map<String, String> map = new HashMap<String, String>();
		map.put("project-key", project_key);
		String values[] = ordgen.orderGeneration(baseUri, path, map, token, createOrder);
		version = values[0];
		order_id = values[1];
		Assert.assertTrue(values.length>0);
		logger.log(Status.PASS,
				"Create An Order API call is Success and successfully retrived the neccesary info from the response");
	}
	@Test(priority = 10)
	public void TC_010_GetAnOrder() {
		logger = reporter.createTest("Get Created Order");
		String path = apiprop.getProperty("getOrderId");
		Map<String, String> map = new HashMap<String, String>();
		map.put("project-key", project_key);
		map.put("orderId", order_id);
		String ordNumber = ordgen.getOrderNumber(baseUri, path, map, token);
		Assert.assertTrue(orderNumber.trim().equals(ordNumber.trim()));
		logger.log(Status.PASS,
				"Get Created Order API call is Success and successfully retrived the neccesary info from the response");
	}

}
