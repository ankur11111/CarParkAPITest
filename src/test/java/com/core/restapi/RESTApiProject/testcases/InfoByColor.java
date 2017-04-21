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





public class InfoByColor extends BaseTest
{
	String testCaseName="InfoByColor";
	Xls_Reader xls;
	@Test(dataProvider="getData")
	public void registerParkingTest(Hashtable<String,String> data) throws IOException
	{

		test = rep.startTest("InfoByColorTest");
		test.log(LogStatus.INFO, data.toString());
		if(!DataUtil.isRunnable(testCaseName, xls) ||  data.get("Runmode").equalsIgnoreCase("N")){
			test.log(LogStatus.SKIP, "Skipping the test as runmode is N");
			throw new SkipException("Skipping the test as runmode is N");
		}

		infoByColor(data.get("ParkingSlotName"),data.get("color"));
		String Body=getResponseBody();
	    System.out.println(Body);
	    if (Body.contains("color")&& Body.contains("registrationNumber")&& getResponseCode().equalsIgnoreCase("200"))
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
		xls = new Xls_Reader(System.getProperty("user.dir")+prop.getProperty("xlspath"));
		//xls = new Xls_Reader(prop.getProperty("xlspath"));
		Object[][] data= DataUtil.getTestData(xls, testCaseName);
		return data;
		
	}
}
