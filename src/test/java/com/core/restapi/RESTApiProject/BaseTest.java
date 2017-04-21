package com.core.restapi.RESTApiProject;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.EofSensorInputStream;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.core.restapi.RESTApiProject.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



import com.relevantcodes.extentreports.LogStatus;


  
public class BaseTest 
{
	
	public  LinkedHashMap headerMap = new LinkedHashMap();
	public  static LinkedHashMap PropertyTransfer = new LinkedHashMap();
	public  LinkedHashMap bodyMap = new LinkedHashMap();
	public  String xmlBody = null;
	public  String url = null;
	public static int check=0;
	public Properties prop;
	public Properties envProp;
	public static HttpResponse response;
	public ExtentReports rep = ExtentManager.getInstance();
	public ExtentTest test;
	
	public void init(){
		//init the prop file
		if(prop==null){
			prop=new Properties();
			
			try {
				  
				FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//projectconfig.properties");
				prop.load(fs);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
	}
	
	/*****************************API Methods********************************/
	
	
	public void get(String url, LinkedHashMap headerMap){
		
        try {
       	 
       	
       	
       	HttpClient client = new DefaultHttpClient();
       	HttpGet request = new HttpGet(url);
       	//HttpResponse response = client.execute(request);
       	
       	Object[] keys = new String[500];
      	  	Object[] values = new String[500];
      	  		  	
      	  	if(headerMap.size() != 0){
      	  		keys = headerMap.keySet().toArray();
      	  		values = headerMap.values().toArray();
            
      	  		for(int i=0; i<keys.length;i++){
           	 
      	  			request.setHeader(keys[i].toString(), values[i].toString());
           	 
      	  		}
     
      	  	}
      	  	
      	 response = client.execute(request);
       	
      // 	System.out.println(response.toString());
       	test.log(LogStatus.INFO, "Response is  "+response.toString());

       	
       	        	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	public void post(String url, LinkedHashMap headerMap, LinkedHashMap bodyMap){
		
		//String value = null;
		
		
		
        try {
       	 
     
       	
       	HttpClient client = new DefaultHttpClient();
   	
       	HttpPost request = new HttpPost(url); 	
   	  	
   	  	Object[] keys = new String[500];
   	  	Object[] values = new String[500];
   	  		  	
   	  	if(headerMap.size() != 0){
   	  		keys = headerMap.keySet().toArray();
   	  		values = headerMap.values().toArray();
         
   	  		for(int i=0; i<keys.length;i++){
        	 
   	  			request.setHeader(keys[i].toString(), values[i].toString());
        	 
   	  		}
  
   	  	}
   	  	
   	  	for(int i=0;i<keys.length;i++)
   	  	{
   	  		keys[i] = null;
   	  		values[i] = null;
   	  	}
   	  	
   	  	List<NameValuePair> params = new ArrayList<NameValuePair>();
   	  	
   	  	if(bodyMap.size() != 0){
	  		keys = bodyMap.keySet().toArray();
	  		values = bodyMap.values().toArray();
      
	  		for(int i=0; i<keys.length;i++){
     	 
	  			params.add(new BasicNameValuePair(keys[i].toString(), values[i].toString()));
	  			
	  	   	  	request.setEntity(new UrlEncodedFormEntity(params));
     	 
	  		}

	  	}

   	  		
   			response = client.execute(request);
   	
   			test.log(LogStatus.INFO, "Response is  "+response.toString());
       	
       
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}
	
	
	
	
public void postJson(String url, LinkedHashMap headerMap, LinkedHashMap bodyMap){
		
        try {
        	String Json = null;
       
       	
       	HttpClient client = new DefaultHttpClient();
       
       	
       	HttpPost request = new HttpPost(url); 	
   	  	
   	  	Object[] keys = new String[500];
   	  	Object[] values = new String[500];
   	  		  	
   	  	if(headerMap.size() != 0){
   	  		keys = headerMap.keySet().toArray();
   	  		values = headerMap.values().toArray();
         
   	  		for(int i=0; i<keys.length;i++){
        	 
   	  			request.setHeader(keys[i].toString(), values[i].toString());
        	 
   	  		}
  
   	  	}
   	  	
   	  	for(int i=0;i<keys.length;i++)
   	  	{
   	  		keys[i] = null;
   	  		values[i] = null;
   	  	}
   	  	
   	  	List<NameValuePair> params = new ArrayList<NameValuePair>();
   	  	
   	  	if(bodyMap.size() != 0){
	  		keys = bodyMap.keySet().toArray();
	  		values = bodyMap.values().toArray();
            Json="{";
	  		for(int i=0; i<keys.length;i++){
     	 
	  			if (i<keys.length-1)
	  			{
	  		      Json=Json.concat("\""+keys[i].toString()+"\":\""+values[i].toString()+"\",");
	  			}
	  			else if (i==keys.length-1)
	  			{
	  				Json=Json.concat("\""+keys[i].toString()+"\":\""+values[i].toString()+"\"}");
	  			}
	  			
	  	   	  	
     	 
	  		}
	  
	  		test.log(LogStatus.INFO, "Json Body is  "+Json);
	  	}
   	    StringEntity entity = new StringEntity(Json);
		request.setEntity(entity);
   	  	
   			response = client.execute(request);

   			test.log(LogStatus.INFO, "Response is  "+response.toString());
   			
       	
       	
       	        	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return value;

	}
	

public String getResponseBody() throws ParseException, IOException{
	
	String line = null;
	int count=0;
	StringBuffer result = new StringBuffer();
		try {

			BufferedReader rd = new BufferedReader(
			        new InputStreamReader(response.getEntity().getContent()));
		 
			
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
            count=1;
							
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			reportFailure("Failed");
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			reportFailure("Failed");
			e.printStackTrace();
			
		}
		
		if (count==1)
		{
		test.log(LogStatus.INFO, "Response body is  "+result.toString());
		}
		return result.toString();
						
	}



	

public  String[] getResponseHeaders(){
	
	String[] responseHeader = new String[100];

	
	if(! response.getAllHeaders().equals(null)){
		
		Header [] temp = response.getAllHeaders();				
		
		for(int i=0; i<response.getAllHeaders().length; i++){
			
			responseHeader[i] = temp[i].toString();	
			test.log(LogStatus.INFO, "Response Header is  "+responseHeader[i]);		
	
			
		}
		
	}
	

return responseHeader; 
				
}


public  String getResponseCode(){
	
	test.log(LogStatus.INFO, "Response Header is  "+Integer.toString(response.getStatusLine().getStatusCode()));
	return Integer.toString(response.getStatusLine().getStatusCode());
		
			
}


/*****************************Reporting********************************/

public void reportPass(String msg){
	test.log(LogStatus.PASS, msg);
}

public void reportFailure(String msg){
	test.log(LogStatus.FAIL, msg);
	SoftAssert softAssert=new SoftAssert();
	softAssert.fail(msg);

}	








public void registerParking(String Parkingname) throws ParseException, IOException
{
	
	
	url = "http://"+prop.getProperty("IP")+":"+prop.getProperty("PORT")+"/parkinglot/register?parkingLotName="+Parkingname;
	test.log(LogStatus.INFO, "URL formed is --> "+url );
	headerMap.put("Content-Type", "application/x-www-form-urlencoded");
    post(url,headerMap, bodyMap);
  
}

public void createParking(String Parkingname, String slotnumber) throws ParseException, IOException
{
	//url="http://"+prop.getProperty("IP")+":"+prop.getProperty("PORT")+"/parkinglot/"+data.get("ParkingSlotName")+"/createParking?parkingSlot="+data.get("SlotNumber"); 
	url="http://"+prop.getProperty("IP")+":"+prop.getProperty("PORT")+"/parkinglot/"+Parkingname+"/createParking?parkingSlot="+slotnumber; 
	test.log(LogStatus.INFO, "URL formed is --> "+url );
	headerMap.put("Content-Type", "application/x-www-form-urlencoded");
    post(url,headerMap, bodyMap);
   
}

public void parAcar(String Parkingname,String registrationnumber, String color) throws ParseException, IOException
{
	
	url="http://"+prop.getProperty("IP")+":"+prop.getProperty("PORT")+"/parkinglot/"+Parkingname+"/parkCar";
	test.log(LogStatus.INFO, "URL formed is --> "+url );
	headerMap.put("Content-Type", "application/json");
	bodyMap.put("registrationNumber",registrationnumber);
	bodyMap.put("color",color);
	postJson(url,headerMap,bodyMap);   
 	
}

public void parkingStatus(String Parkingname) throws ParseException, IOException
{
	url="http://"+prop.getProperty("IP")+":"+prop.getProperty("PORT")+"/parkinglot/"+Parkingname+"/status";
	test.log(LogStatus.INFO, "URL formed is --> "+url );
	headerMap.put("Content-Type", "application/x-www-form-urlencoded");
	get(url,headerMap);
    
	
}

public void getslotNumber(String Parkingname,String registrationnumber, String color) throws ParseException, IOException
{
	url="http://"+prop.getProperty("IP")+":"+prop.getProperty("PORT")+"/parkinglot/"+Parkingname+"/slotNumber?color="+color+"&registrationNumber="+registrationnumber;
	test.log(LogStatus.INFO, "URL formed is --> "+url );
	headerMap.put("Content-Type", "application/x-www-form-urlencoded");
	
    get(url,headerMap);
   	
}

public void infoByColor(String Parkingname, String color) throws ParseException, IOException
{
	url="http://"+prop.getProperty("IP")+":"+prop.getProperty("PORT")+"/parkinglot/"+Parkingname+"/car?color="+color;
	test.log(LogStatus.INFO, "URL formed is --> "+url );
	headerMap.put("Content-Type", "application/x-www-form-urlencoded");
    get(url,headerMap);
    	
}

public void vacateParking(String Parkingname, String slotnumber) throws ParseException, IOException
{
	System.out.println("returned is ----"+PropertyTransfer.get(Parkingname));
	if (PropertyTransfer.containsKey(Parkingname)&&check!=1)
	url="http://"+prop.getProperty("IP")+":"+prop.getProperty("PORT")+"/parkinglot/"+Parkingname+"/vacateParking?parkingSlot="+PropertyTransfer.get(Parkingname);
	else if(check==0)
	url="http://"+prop.getProperty("IP")+":"+prop.getProperty("PORT")+"/parkinglot/"+Parkingname+"/vacateParking?parkingSlot=";
	else if(check==1) 
	url="http://"+prop.getProperty("IP")+":"+prop.getProperty("PORT")+"/parkinglot/"+Parkingname+"/vacateParking?parkingSlot="+slotnumber;
	test.log(LogStatus.INFO, "URL formed is --> "+url );
	headerMap.put("Content-Type", "application/x-www-form-urlencoded");
    post(url,headerMap,bodyMap);
   
}
}