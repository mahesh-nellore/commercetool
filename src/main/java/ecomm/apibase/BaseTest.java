package ecomm.apibase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import ecomm.apiactions.Authentication;
import ecomm.apiactions.Orders.Cart;
import ecomm.apiactions.Orders.CreatePayment;
import ecomm.apiactions.Orders.OrderGeneration;
import ecomm.apiactions.Orders.Product;
import ecomm.apiactions.Orders.SetAddress;
import ecomm.apiactions.Orders.SetMethod_Shipping;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class BaseTest {

	public static ExtentReports reporter;
	public static ExtentTest logger;
	public static Properties apiprop;
	public static Authentication authentication = null;;
	public static Product product = null;
	public static Cart cart = null;
	public static SetAddress address = null;
	public static SetMethod_Shipping shipMethod = null;
	public static OrderGeneration ordgen = null;
	public static CreatePayment createpaymentmethod = null;
	@BeforeSuite
	public static void initBaseConfig() {
		ExtentHtmlReporter extreporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + File.separator + "Reports" + File.separator + "ecomm.html");
		reporter = new ExtentReports();
		reporter.attachReporter(extreporter);		
		apiprop = new Properties();
		try {
			apiprop.load(new FileInputStream(
					System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "data.properties"));
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
		authentication = new Authentication();
		product = new Product();
		cart = new Cart();
		address = new SetAddress();
		shipMethod = new SetMethod_Shipping();
		ordgen = new OrderGeneration();
		createpaymentmethod = new CreatePayment();
	}

	@AfterMethod
	public void teardown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {

			logger.fail("Test Case Failed is: " + result.getTestName());
		}
		reporter.flush();
	}
	
	public static void main(String[] args) {
		System.out.println(System.getenv());
		
	}

}