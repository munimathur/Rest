package e2e;

import java.util.HashMap;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.RestAssureFunctions;
import e2e_framework.ExtentTestManager;
import e2e_framework.ParallelClassesBase;
import e2e_functions.E2E_Functions;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.ParaMethod;
import utility.PropertyFile;

public class E2E_TESTSCENARIOS extends ParallelClassesBase {

	ExtentTest report;
	@Test(description="special offer about E2E API", dataProvider="Testcase1", dataProviderClass=ParaMethod.class)
	public void special_offer(String getsubURL) {
		
		//*************PAGE INSTANTIATIONS*************
	       report = ExtentTestManager.createTest("E2E API - TestCase 1 - Special Offer");
	      RestAssureFunctions oRAF = new RestAssureFunctions(report);
	      HashMap<String, String> strHeader;
	      Response response;
	      //JSONObject reqBody = new JSONObject();
	      RequestSpecification httpRequest;
	      E2E_Functions objE2E = new E2E_Functions();
	      PropertyFile prop = new PropertyFile();
	      
	      //*************PAGE METHODS*******************
	      try{
	            //Pre Token Calls
	            
	            String getCUIDCode = objE2E.apiCUID(report);
	            String getToken = objE2E.apiToken(report, getCUIDCode);
	            
	            //API 1  ###########################################
	            
	          //REQUEST URL
	            if(getsubURL.contains("KONG")) {
	            	httpRequest = oRAF.requestURL(prop.readPropFile("kongURL")+ getsubURL.split("=")[1]);
	            }else {
	            	httpRequest = oRAF.requestURL(prop.readPropFile("atlURL")+ getsubURL.split("=")[1]);
	            }
	            
	            
	            
	            //HEADER
	            strHeader = new HashMap<String, String>();
	            strHeader.put("Content-Type", "application/json");
	            strHeader.put("Authorization", "Bearer "+getToken);
	            httpRequest = oRAF.requestHeader(httpRequest, strHeader);
	            
	            //BODY - No
	            
	          //EXECUTE
	            response = oRAF.execute(httpRequest, "get");
	            
	            System.out.println(response.asString());
	            
	            
	            //RootObject specialOfferObj = response.as(RootObject.class);
	            if(response.getStatusCode() == 200){
	                 // specialOfferObj.getMaxLoanAmount();
	                  
	            	System.out.println("Pass");
	            }else {
	            	System.out.println("Fail");
	            }
	            

	            }catch(Exception | AssertionError err){
	                  System.out.println("Failed in API Call");
	            report.log(Status.FAIL, "E2E API - TestCase 1 - Special Offer");
	      }

		
	}
	
	
	@Test(description="Marketing offer about E2E API", dataProvider="Testcase2", dataProviderClass=ParaMethod.class)
	public void marketing_offer(String getsubURL) {
		
		//*************PAGE INSTANTIATIONS*************
	       report = ExtentTestManager.createTest("E2E API - TestCase 2 - Marketing Offer");
	      RestAssureFunctions oRAF = new RestAssureFunctions(report);
	      HashMap<String, String> strHeader;
	      Response response;
	      //JSONObject reqBody = new JSONObject();
	      RequestSpecification httpRequest;
	      E2E_Functions objE2E = new E2E_Functions();
	      PropertyFile prop = new PropertyFile();
	      
	      //*************PAGE METHODS*******************
	      try{
	            //Pre Token Calls
	            
	            String getCUIDCode = objE2E.apiCUID(report);
	            String getToken = objE2E.apiToken(report, getCUIDCode);
	            
	            //API 1  ###########################################
	            
	          //REQUEST URL
	            if(getsubURL.contains("KONG")) {
	            	httpRequest = oRAF.requestURL(prop.readPropFile("kongURL")+ getsubURL.split("=")[1]);
	            }else {
	            	httpRequest = oRAF.requestURL(prop.readPropFile("atlURL")+ getsubURL.split("=")[1]);
	            }
	            
	            
	            
	            //HEADER
	            strHeader = new HashMap<String, String>();
	            strHeader.put("Content-Type", "application/json");
	            strHeader.put("Authorization", "Bearer "+getToken);
	            httpRequest = oRAF.requestHeader(httpRequest, strHeader);
	            
	            //BODY - No
	            
	          //EXECUTE
	            response = oRAF.execute(httpRequest, "get");
	            
	            System.out.println(response.asString());
	            
	            
	            //RootObject specialOfferObj = response.as(RootObject.class);
	            if(response.getStatusCode() == 200){
	                 // specialOfferObj.getMaxLoanAmount();
	                  
	            	System.out.println("Pass");
	            }else {
	            	System.out.println("Fail");
	            }
	            

	            }catch(Exception | AssertionError err){
	                  System.out.println("Failed in API Call");
	            report.log(Status.FAIL, "E2E API - TestCase 1 - Special Offer");
	      }

		
	}
	
	
	
	@Test(description="Marketing offer about E2E API", dataProvider="Testcase3", dataProviderClass=ParaMethod.class)
	public void marketing_offerscalculation(String getsubURL) {
		
		//*************PAGE INSTANTIATIONS*************
	       report = ExtentTestManager.createTest("E2E API - TestCase 3-Marketing Offers Calculation");
	      RestAssureFunctions oRAF = new RestAssureFunctions(report);
	      HashMap<String, String> strHeader;
	      Response response;
	      JSONObject reqBody = new JSONObject();
	      RequestSpecification httpRequest;
	      E2E_Functions objE2E = new E2E_Functions();
	      PropertyFile prop = new PropertyFile();
	      
	      
	      //*************PAGE METHODS*******************
	      try{
	            //Pre Token Calls
	            
	            String getCUIDCode = objE2E.apiCUID(report);
	            String getToken = objE2E.apiToken(report, getCUIDCode);
	            
	            //API 3 POST MARKETING OFFER CALCLUATION  ###########################################
	            
	          //REQUEST URL
	            if(getsubURL.contains("KONG")) {
	            	httpRequest = oRAF.requestURL(prop.readPropFile("kongURL")+ getsubURL.split("=")[1]);
	            }else {
	            	httpRequest = oRAF.requestURL(prop.readPropFile("atlURL")+ getsubURL.split("=")[1]);
	            }
	            
	            
	            
	            //HEADER
	            strHeader = new HashMap<String, String>();
	            strHeader.put("Content-Type", "application/json");
	            strHeader.put("Authorization", "Bearer "+getToken);
	            httpRequest = oRAF.requestHeader(httpRequest, strHeader);
	            
	            //BODY - 
	            

				reqBody.put("income", "30000");
				reqBody.put("salaryPaymentMethod", "CASH");
				
				httpRequest = oRAF.requestBody(httpRequest, reqBody.toString());
	            
	          //EXECUTE
	            response = oRAF.execute(httpRequest, "post");
	            
	            System.out.println(response.asString());
	            
	            
	            //RootObject specialOfferObj = response.as(RootObject.class);
	            if(response.getStatusCode() == 200){
	                 // specialOfferObj.getMaxLoanAmount();
	                  
	            	System.out.println("Pass");
	            }else {
	            	System.out.println("Fail");
	            }
	            

	            }catch(Exception | AssertionError err){
	                  System.out.println("Failed in API Call");
	            report.log(Status.FAIL, "E2E API - TestCase 3- MArketingoffer Calculation");
	      }

		
	}


	
}
