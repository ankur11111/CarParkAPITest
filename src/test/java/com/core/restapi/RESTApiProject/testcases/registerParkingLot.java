package com.core.restapi.RESTApiProject.testcases;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedHashMap;

import org.apache.http.HttpResponse;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.core.restapi.RESTApiProject.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;
import com.core.restapi.RESTApiProject.DataUtil;
import com.core.restapi.RESTApiProject.BaseTest;





public class registerParkingLot extends BaseTest
{
	String testCaseName="registerParkingLot";
	Xls_Reader xls;
	@Test(dataProvider="getData")
	public void registerParkingTest(Hashtable<String,String> data) throws IOException
	{

		test = rep.startTest("registerParkingTest");
		test.log(LogStatus.INFO, data.toString());
		if(!DataUtil.isRunnable(testCaseName, xls) ||  data.get("Runmode").equalsIgnoreCase("N")){
			test.log(LogStatus.SKIP, "Skipping the test as runmode is N");
			throw new SkipException("Skipping the test as runmode is N");
		}
		
		url = "http://34.205.255.27:8080/parkinglot/register?parkingLotName="+data.get("ParkingSlotName");
		test.log(LogStatus.INFO, "URL formed is --> "+url );
		headerMap.put("Content-Type", "application/x-www-form-urlencoded");
		
	
	//	bodyMap.put("registrationNumber", "12");
	//	bodyMap.put("color", "Black");
	//	postJson(url,headerMap,bodyMap);
	   post(url,headerMap, bodyMap);
	    String Body=getResponseBody();
	    System.out.println(Body);
		if (Body.equalsIgnoreCase("true") && getResponseCode().equalsIgnoreCase("200"))
		{
			reportPass("Passed");
		}
		else reportFailure("Failed");
	
		
		
}
	

	
	
	
	
	@AfterMethod
	public void repoquit(){
		if(rep!=null){
			rep.endTest(test);
			rep.flush();
		}}
	@DataProvider
	public Object[][] getData(){
		super.init();
	
		xls = new Xls_Reader(prop.getProperty("xlspath"));
		Object[][] data= DataUtil.getTestData(xls, testCaseName);
		return data;
		
	}
}
