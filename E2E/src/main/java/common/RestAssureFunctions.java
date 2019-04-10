package common;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

//import utility.CryptoFunctions;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssureFunctions {
	
	//***********Declaration**********
	public ExtentTest logger;
	public String secretkey = "9A9E8861A9AA034CEB3EE9ED14832B03";
	
	public String SecretKey = "123"; //key for MSA Application
	
	//*********Constructor*********
	 public RestAssureFunctions(ExtentTest logger) {
	   this.logger = logger;
	 }
	
	
	public RequestSpecification requestURL(String srtURL){
		RestAssured.baseURI = srtURL;
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification httpRequest = RestAssured.given();
		logger.log(Status.PASS, "API URL : "+srtURL);
		return httpRequest;
	}
	
	
	public RequestSpecification requestHeader(RequestSpecification httpRequest, HashMap<String, String> strHeader){
		List<Header> list = new ArrayList<Header>();
		for ( Map.Entry<String, String> entry : strHeader.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    Header h= new Header(key, value);
		    list.add(h);
		}
	    Headers header = new Headers(list);
	    logger.log(Status.INFO, "Header : " + list);
	    httpRequest.headers(header);
	    return httpRequest;
	}
	
	public RequestSpecification requestBody(RequestSpecification httpRequest, String reqBody){
		httpRequest.body(reqBody);
		logger.log(Status.INFO, "Request Body : "+reqBody);
	    return httpRequest;
	}
	
	
	//Upload File
	public RequestSpecification uploadFile(RequestSpecification httpRequest, String path){
		httpRequest.given().multiPart("file", new File(path), "text/xlsx");
		return httpRequest;
	}
	
	public Response execute(RequestSpecification httpRequest, String reqType){
			Response response = null;
			switch (reqType) {
				case "get":
					System.out.println("GET Request");
					response = httpRequest.request(Method.GET);
					logger.log(Status.PASS, "Performed : GET Request");
					break;
				case "post":
					System.out.println("POST Request");
					response = httpRequest.request(Method.POST);
					logger.log(Status.PASS, "Performed : POST Request");
					break;
				default:
					logger.log(Status.WARNING, "NOT FOUND");
					System.out.println("NOT FOUND");
			}
			return response;
	}
	
	public String getResponse(Response response){
		String getResponseBody = response.getBody().asString();
		logger.log(Status.INFO, "Get Response : " + getResponseBody);
		return getResponseBody;
	}
	
	
	
	
	// Encrypted Responses
	public String getEncryptedResponse(String resBody) throws Exception{
		JSONObject myObject = new JSONObject(resBody);
		String getResponse = (String) myObject.get("response");
		logger.log(Status.INFO, "Get only the encrypted response : " + getResponse);
		return getResponse;
	}
	
	
//	//Body Encrypt
//	
//	public String encryptResponse(String reqBody) throws Exception{
//		CryptoFunctions crypto = new CryptoFunctions();
//		String encryptRes = crypto.encrypt(reqBody, secretkey);
//		System.out.println("Encrypted Value: "+encryptRes);
//		logger.log(Status.INFO, "Encrypted data : " + encryptRes);
//		JSONObject encryptedObject = new JSONObject();
//		encryptedObject.put("request", encryptRes);
//		logger.log(Status.INFO, "Encrypted request object : " + encryptedObject.toString());
//		return encryptedObject.toString();
//	}
//	
//	//Body Decrypt
//	
//	public String decryptResponse(String resBody) throws Exception{
//		JSONObject myObject = new JSONObject(resBody);
//		String reqBody = (String) myObject.get("response");
//		CryptoFunctions crypto = new CryptoFunctions();
//		String decryptRes = crypto.decrypt(reqBody, secretkey);
//		System.out.println("Decrypted Value: "+decryptRes);
//		logger.log(Status.INFO, "Decrypted data : " + decryptRes);
//		return decryptRes;
//	}
	
	
	//Validate Responses
	public void validateResponse(String responseText){
		
		JSONObject myObject = new JSONObject(responseText);
		String reqBody = (String) myObject.get("transactionRemarks");
		
		if(reqBody.contains("Transaction success")){
			logger.log(Status.PASS, "Successful Transaction : " + reqBody);
		}
		
		if(reqBody.contains("Transaction fail")){
			logger.log(Status.FAIL, "Transaction Failed : " + reqBody);
		}
		
		System.out.println(responseText);
	}
	
	
	
	
//	//Prepare Header from Previous Response
//	 public HashMap<String, String> getHeaderbyAPI(HashMap<String, String> strHeader, Response response) throws Exception{
//		 	JSONObject myObject = new JSONObject(decryptResponse(getResponse(response)));
//	        Iterator<String> keysItr = myObject.keys();
//	        while (keysItr.hasNext()) {
//	            String key = keysItr.next();
//	            String value = (String) myObject.get(key);
//	            strHeader.put(key, value);
//	        }
//			return strHeader;    
//	 }
	
    
}
