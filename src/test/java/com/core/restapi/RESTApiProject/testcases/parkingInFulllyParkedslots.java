package com.core.restapi.RESTApiProject.testcases;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

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





public class parkingInFulllyParkedslots extends BaseTest
{
	String testCaseName="parkingInFulllyParkedslots";
	Xls_Reader xls;
	@Test(dataProvider="getData")
	public void parkingInFulllyParkedslotsTest(Hashtable<String,String> data) throws IOException
	{
        check=1;
		test = rep.startTest("parkingInFulllyParkedslotsTest");
		test.log(LogStatus.INFO, data.toString());
		if(!DataUtil.isRunnable(testCaseName, xls) ||  data.get("Runmode").equalsIgnoreCase("N"))
		{
			test.log(LogStatus.SKIP, "Skipping the test as runmode is N");
			throw new SkipException("Skipping the test as runmode is N");
		}
		test.log(LogStatus.INFO, "Registering the Parking "+data.get("ParkingSlotName"));
		registerParking(data.get("ParkingSlotName"));
		String Body=getResponseBody();
	    System.out.println(Body);
		if (Body.equalsIgnoreCase("true") && getResponseCode().equalsIgnoreCase("200"))
		{
			test.log(LogStatus.INFO, "Registered the parking name");
			
		}
		test.log(LogStatus.INFO, "Registered the Parking "+data.get("ParkingSlotName"));
		test.log(LogStatus.INFO, "Creating the Parking for "+data.get("ParkingSlotName")+"with slots="+data.get("TotalSlotNumber"));
		createParking(data.get("ParkingSlotName"),data.get("TotalSlotNumber"));
		if (getResponseCode().equalsIgnoreCase("200"))
		{
			test.log(LogStatus.INFO, "Parking is created");
		}
		else reportFailure("Failed");
		test.log(LogStatus.INFO, "Created the Parking for "+data.get("ParkingSlotName")+"with slots="+data.get("TotalSlotNumber"));
		test.log(LogStatus.INFO, "Parking the cars at "+data.get("ParkingSlotName"));
		List<String> listRegistrationNumber = Arrays.asList(data.get("registrationNumber").split(","));
		List<String> listcolors = Arrays.asList(data.get("color").split(","));
		System.out.println("size of reg="+listRegistrationNumber.size());
		System.out.println("size of color="+listcolors.size());
		for (int i=0;i<Integer.parseInt(data.get("TotalSlotNumber"));i++)
		{
			
		parAcar(data.get("ParkingSlotName"),listRegistrationNumber.get(i),listcolors.get(i));
		if (getResponseCode().equalsIgnoreCase("200"))
		{
			test.log(LogStatus.INFO, "Parked the car at "+data.get("ParkingSlotName"));
		}
		else reportFailure("Failed");
		}
		
		parAcar(data.get("ParkingSlotName"),listRegistrationNumber.get(0),listcolors.get(0));
		if ( getResponseCode().equalsIgnoreCase("204"))
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
		Object[][] data= DataUtil.getTestData(xls, testCaseName);
		return data;
		
	}
}
