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
import ecomm.apirequestpayloads.AddPaymentToCart.AddPaymentToCartAction;
import ecomm.apirequestpayloads.AddPaymentToCart.AddpaymentToCart;
import ecomm.apirequestpayloads.AddPaymentToCart.Payment;
import ecomm.apirequestpayloads.CreateOrder.CreateOrder;
import ecomm.apirequestpayloads.CreatePayment.AmountPlanned;
import ecomm.apirequestpayloads.CreatePayment.CreatePayment;
import ecomm.apirequestpayloads.CreatePayment.Custom;
import ecomm.apirequestpayloads.CreatePayment.Fields;
import ecomm.apirequestpayloads.CreatePayment.PaymentMethodInfo;
import ecomm.apirequestpayloads.CreatePayment.Type;
import ecomm.apirequestpayloads.SetShippingMethodTaxAmount.ExternalTaxAmount;
import ecomm.apirequestpayloads.SetShippingMethodTaxAmount.SetShippingMethodTaxAmountAction;
import ecomm.apirequestpayloads.SetShippingMethodTaxAmount.ShippingMethodTaxAmount;
import ecomm.apirequestpayloads.SetShippingMethodTaxAmount.TaxRate;
import ecomm.apirequestpayloads.SetShippingMethodTaxAmount.TotalGross;
import ecomm.apirequestpayloads.ShippingAddress.Addr_Action;
import ecomm.apirequestpayloads.ShippingAddress.Address;
import ecomm.apirequestpayloads.ShippingAddress.ShippingAddress;
import ecomm.apirequestpayloads.ShippingMethod.SetShippingMethod;
import ecomm.apirequestpayloads.ShippingMethod.ShippingMethod;
import ecomm.apirequestpayloads.ShippingMethod.ShippingMethodAction;
import ecomm.generic.ExcelUtility;
import ecomm.generic.GenericUtility;

public class CreateOrderFlow extends BaseTest {

	public static String token = "";
	public static String project_key = "";
	public static String cart_id = "";
	public static String product_id = "";
	public static String variant_id = "1";
	public static int quantity = 1;
	public static String supplyChannel_id = "";
	public static String distributionChannel_id = "";
	public static String discount_code = "";
	public static String order_id = "";
	public static String orderNumber = "";
	public static int cartTotal = 0;
	public static String paymentId = "";
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
		//String productId = apiprop.getProperty("productId");
		String productId = ExcelUtility.getCellValue(excelFilePath, 0, 1, 0);
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
		createcart.setTaxMode("ExternalAmount");
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
				"Add line Item to the Cart API call is Success and the version values is: "+version);
	}
	
	@Test(priority = 5)
	public void TC_005_getCartById() {
		logger = reporter.createTest("Get the Cart by ID");
		String path = apiprop.getProperty("addLineItemOrDiscount");
		Map<String, String> map = new HashMap<String, String>();
		map.put("project-key", project_key);
		map.put("cart-id", cart_id);
		String [] values = cart.getCartById(baseUri, path, map, token);
		version = values[0];
		Assert.assertTrue(version != "" || version != null);
		logger.log(Status.PASS,
				"Get Cart API call is Success and successfully retrived the version from the response and the version is: "+version);
		String total = ExcelUtility.getCellValue(excelFilePath, 0, 1, 1);
		Assert.assertEquals(values[1].trim(), total);
		logger.log(Status.PASS, "The Actual cart total  is: "+values[1]+" And the Expected Cart total  is: "+total);
	}
	
	

	@Test(priority = 6)
	public void TC_006_AddDiscountToCart() {
		logger = reporter.createTest("Add Dsicount to the cart");
		String path = apiprop.getProperty("addLineItemOrDiscount");
		discount_code = ExcelUtility.getCellValue(excelFilePath, 0, 1, 2);
		AddDiscount adddiscount = new AddDiscount();
		adddiscount.setVersion(Integer.parseInt(version));
		Discount discount = new Discount();
		discount.setCode(discount_code);
		adddiscount.setActions(Arrays.asList(discount));
		Map<String, String> map = new HashMap<String, String>();
		map.put("project-key", project_key);
		map.put("cart-id", cart_id);
		String[] values = cart.addDiscountToTheCart(baseUri, path, map, token, adddiscount);
		version = values[0];
		Assert.assertTrue(version != "" || version != null);		
		logger.log(Status.PASS,
				"Add Discount to the Cart API call is Success and successfully retrived the version from the response and the version is: "+version);
		String discountAmount = ExcelUtility.getCellValue(excelFilePath, 0, 1, 3);
		Assert.assertEquals(values[2].trim(), discountAmount);
		logger.log(Status.PASS, "The Actual cart total after discount is: "+values[2]+" And the Expected Cart total after discount is: "+discountAmount);
		
	}
	
	@Test(priority = 7)
	public void TC_007_SetShippingAddress() {
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
		String values[] = address.setAddress(baseUri, path, map, token, shippingAddr);
		version = values[0];
		cartTotal = Integer.parseInt(values[1]);
		Assert.assertTrue(version != "" || version != null);
		logger.log(Status.PASS,
				"Set Shipping Address API call is Success and successfully retrived and version is: "+version);
		String expectedTaxRate = ExcelUtility.getCellValue(excelFilePath, 0, 1, 4).trim();
		String expectedAmountIncludingTax = ExcelUtility.getCellValue(excelFilePath, 0, 1, 5).trim();
		String actualTaxRate = values[2].trim();
		String actualAmountIncludingTax = values[3].trim();
		Assert.assertEquals(actualTaxRate, expectedTaxRate);
		logger.log(Status.PASS,
				"Actual Tax rate is: "+actualTaxRate+" And the Expected Tax Rate is: "+expectedTaxRate);
		Assert.assertEquals(actualAmountIncludingTax, expectedAmountIncludingTax);
		logger.log(Status.PASS,
				"Actual Total Amount including Tax: "+actualAmountIncludingTax+" And the Expected Total Amount including Tax: "+expectedAmountIncludingTax);
	}
	
	@Test(priority = 8)
	public void TC_008_SetBillingAddress() {
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
		String values[] = address.setAddress(baseUri, path, map, token, shippingAddr);
		version = values[0];
		Assert.assertTrue(version != "" || version != null);
		logger.log(Status.PASS,
				"Set Billing Address API call is Success and successfully retrived the neccesary info from the response");
	}
	
	@Test(priority = 9)
	public void TC_009_SetShippingMethod() {
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
	
	@Test(priority = 10)
	public void TC_010_SetShippingMethodTaxAmount() {
		//System.out.println("--SetShippingMethodTaxAmount--(Cart Total):"+cartTotal);
		logger = reporter.createTest("Set ShippingMethod TaxAmount");
		String path = apiprop.getProperty("addLineItemOrDiscount");
		TotalGross totalgross = new TotalGross();
		totalgross.setCentAmount(cartTotal);
		TaxRate taxrate = new TaxRate();
		ExternalTaxAmount externaltaxamount = new ExternalTaxAmount();
		externaltaxamount.setTaxRate(taxrate);
		externaltaxamount.setTotalGross(totalgross);
		SetShippingMethodTaxAmountAction action = new SetShippingMethodTaxAmountAction();
		action.setExternalTaxAmount(externaltaxamount);		
		ShippingMethodTaxAmount taxamount = new ShippingMethodTaxAmount();		
		taxamount.setVersion(Integer.parseInt(version));		
		taxamount.setActions(Arrays.asList(action));
		Map<String, String> map = new HashMap<String, String>();
		map.put("project-key", project_key);
		map.put("cart-id", cart_id);
		version = shipMethod.setShippingMethodTaxAmount(baseUri, path, map, token, taxamount);
	}
	
	@Test(priority = 11)
	public void TC_011_CreatePayment() {
		logger = reporter.createTest("Create Payment");
		String path = apiprop.getProperty("createpayment");
		Map<String, String> map = new HashMap<String, String>();
        map.put("project-key", "breville-trial");
        Type type = new Type();
        Fields fields = new Fields(cartTotal);
        fields.setCartIdReference(cart_id);
        Custom custom = new Custom();
        custom.setType(type);
        custom.setFields(fields);
        AmountPlanned amountPlanned = new AmountPlanned();
        amountPlanned.setCentAmount(cartTotal);
        PaymentMethodInfo paymentMethodInfo = new PaymentMethodInfo();
        CreatePayment crt_payment=new CreatePayment();
        crt_payment.setAmountPlanned(amountPlanned);
        crt_payment.setPaymentMethodInfo(paymentMethodInfo);
        crt_payment.setCustom(custom);
        paymentId = createpaymentmethod.createPaymentMethod(baseUri, path, map, token, crt_payment);     
       
	}
	
	@Test(priority = 12)
	public void TC_012_addCarttopayment() {
		logger = reporter.createTest("Add cart payment to cart");
		String path = apiprop.getProperty("addcartpayment");
		Map<String, String> map = new HashMap<String, String>();		
		AddPaymentToCartAction action = new AddPaymentToCartAction();
		 Payment payment = new Payment();
		payment.setId(paymentId);		
		AddpaymentToCart add_paymenttocart = new AddpaymentToCart();
		add_paymenttocart.setVersion(Integer.parseInt(version));
		action.setPayment(payment);
	    add_paymenttocart.setActions(Arrays.asList(action));
	    map.put("project-key", project_key);
		map.put("cart-id", cart_id);	    
		version = createpaymentmethod.addPaymentToCart(baseUri, path, map, token, add_paymenttocart);	    
	}
	
	@Test(priority = 13)
	public void TC_013_CreateAnOrder() {
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
	@Test(priority = 14)
	public void TC_014_GetAnOrder() {
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
