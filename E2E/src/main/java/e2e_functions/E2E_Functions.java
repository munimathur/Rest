package e2e_functions;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Properties;

import org.json.JSONObject;
import org.testng.Assert;

//import tool.RestAssureFunctions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.RestAssureFunctions;



public class E2E_Functions {

	public Properties prop=new Properties();
	FileOutputStream fileOut = null;
	FileInputStream  fileIn = null;
	
	//CUID 
	public String apiCUID(ExtentTest report){
		RestAssureFunctions oRAF = new RestAssureFunctions(report);
		HashMap<String, String> strHeader;
		Response response;
		JSONObject reqBody = new JSONObject();
		RequestSpecification httpRequest;
		String cuidCode = null;
		//*************PAGE METHODS*******************
		try{
			
			//API 1  ###########################################
			
			
		    //REQUEST URL
			httpRequest = oRAF.requestURL("http://openapi.inoapi.in.infra:8001/customer/hc-oauth2/authorize");
			
			
			//HEADER
			strHeader = new HashMap<String, String>();
			strHeader.put("Content-Type", "application/json");
			httpRequest = oRAF.requestHeader(httpRequest, strHeader);
			
			//BODY 
//			CUID_RequestBody getReqBody = new CUID_RequestBody();
//			getReqBody.setClientId("testid");
//			getReqBody.setResponseType("code");
//			getReqBody.setScope("customer");
//			getReqBody.setRedirectUri("http://in-vx109.uat.homecredit.in/Angulartest/profile/oauth");
//			getReqBody.setProvisionKey("6697cfc162b3416197a45b88c7944254");
//			getReqBody.setAuthenticatedUserid("459932");

			reqBody.put("client_id", "testid");
			reqBody.put("response_type", "code");
			reqBody.put("scope", "customer");
			reqBody.put("redirect_uri", "http://in-vx109.uat.homecredit.in/Angulartest/profile/oauth");
			reqBody.put("provision_key", "6697cfc162b3416197a45b88c7944254");
			reqBody.put("authenticated_userid", "459932");
			httpRequest = oRAF.requestBody(httpRequest, reqBody.toString());
			
		    //EXECUTE
			response = oRAF.execute(httpRequest, "post");
			
			JSONObject getObj = new JSONObject(response.asString());
			System.out.println(response.asString());
			if(response.getStatusCode() == 200){
				Assert.assertEquals(response.getStatusCode(), 200);
				report.log(Status.PASS, "Acutal Status Code : " + response.getStatusCode() + "# Expected Status Code : 200");
				String redirectURL = getObj.getString("redirect_uri");
				cuidCode = redirectURL.split("=")[1];
			}else{
				throw new Exception("Status Code :"+response.getStatusCode());
			}
			
		}catch(Exception e){
			System.out.println("Test");
		}
		return cuidCode;
	}
	
	
	public String apiToken(ExtentTest report, String cuidcode){
		RestAssureFunctions oRAF = new RestAssureFunctions(report);
		HashMap<String, String> strHeader;
		Response response;
		JSONObject reqBody = new JSONObject();
		RequestSpecification httpRequest;
		String accessToken = null;
		
		//*************PAGE METHODS*******************
		try{
			
			//API 1  ###########################################
			
			
		    //REQUEST URL
			httpRequest = oRAF.requestURL("http://openapi.inoapi.in.infra:8000/customer/hc-oauth2/token");
			
			
			//HEADER
			strHeader = new HashMap<String, String>();
			strHeader.put("Content-Type", "application/json");
			httpRequest = oRAF.requestHeader(httpRequest, strHeader);
			
			//BODY  
			reqBody.put("grant_type", "authorization_code");
			reqBody.put("client_id", "testid");
			reqBody.put("client_secret", "testsecret");
			reqBody.put("code", cuidcode);
			httpRequest = oRAF.requestBody(httpRequest, reqBody.toString());
			
		    //EXECUTE
			response = oRAF.execute(httpRequest, "post");
			
			System.out.println(response.asString());
			JSONObject getObj = new JSONObject(response.asString());
			if(response.getStatusCode() == 200){
				Assert.assertEquals(response.getStatusCode(), 200);
				report.log(Status.PASS, "Acutal Status Code : " + response.getStatusCode() + "# Expected Status Code : 200");
				accessToken = getObj.getString("access_token");
				loadParam("accessToken", accessToken);
			}
		}catch(Exception e){
			System.out.println("Test");
		}
		return accessToken;
	}
	
	// Write to Properties
	public void loadParam(String key, String value) throws Exception {
		//Properties prop = new Properties();
		fileOut=new FileOutputStream("D:\\config.properties");
		prop.setProperty(key, value);
		prop.store(fileOut, null);
	}

		
		
	// Read Properties
	public String readProp(String key) throws Exception {
		Properties prop = new Properties();
		fileIn=new FileInputStream("D:\\config.properties");
		prop.load(fileIn);
		return prop.getProperty(key);
	}
	
	
	// Contract Code
		public String contractCode(ExtentTest report) throws Exception {
			RestAssureFunctions oRAF = new RestAssureFunctions(report);
			HashMap<String, String> strHeader;
			Response response;
			//JSONObject reqBody = new JSONObject();
			RequestSpecification httpRequest;
			String contractCode = null;

			try {

				String getCUIDCode = apiCUID(report);
				String getToken = apiToken(report, getCUIDCode);

				// Request URL
				httpRequest = oRAF.requestURL("http://openapi.inoapi.in.infra:8000/customer/v1/my/contracts");

				// Header
				strHeader = new HashMap<String, String>();

				// String ab=readProp("accessToken");
				strHeader.put("Content-Type", "application/json");
				strHeader.put("Authorization", "Bearer " + getToken);
				httpRequest = oRAF.requestHeader(httpRequest, strHeader);

				// Execute
				response = oRAF.execute(httpRequest, "post");

				System.out.println(response.asString());
				JSONObject getObj = new JSONObject(response.asString());
				if (response.getStatusCode() == 200) {
					Assert.assertEquals(response.getStatusCode(), 200);
					report.log(Status.PASS,"Acutal Status Code : " + response.getStatusCode() + "# Expected Status Code : 200");
					contractCode = getObj.getString("contractCode");
					report.log(Status.INFO, contractCode);
					loadParam("contractCode", contractCode);
				}
			} catch (Exception e) {
				System.out.println("Test");
			}
			return contractCode;
		}
	
	
}
