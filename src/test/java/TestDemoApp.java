import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

//import DemoServiceAPI;

public class TestDemoApp{
	
	@Test(groups = { "demo" })
	public void getAllAccounts_t001() throws Exception {
	
		ResponseAPI entireResponse = DemoServiceAPI.getAllAccounts();

        System.out.println("Response Status : " + entireResponse.getStatus());
        System.out.println("Response Status Message : " + entireResponse.getStatusMessage());
        System.out.println("Response Body : " + entireResponse.getResponse());
		
		Assert.assertEquals(200,entireResponse.getStatus(),"HTTP Status code was not 200");
		
		JSONArray arr = new JSONArray(entireResponse.getResponse());
		for (int i = 0; i < arr.length(); i++) {
				Long id = arr.getJSONObject(i).getLong("id");
				System.out.println("id : " + id.toString());
				String identifier = arr.getJSONObject(i).getString("identifier");
				System.out.println("identifier : " + identifier);
		}
	}
	
	@Test(groups = { "demo" })
	public void getAllBands_t001() throws Exception {
	
		ResponseAPI entireResponse = DemoServiceAPI.getAllBands();
		
		System.out.println("Response Status : " + entireResponse.getStatus());
		System.out.println("Response Status Message : " + entireResponse.getStatusMessage());
		System.out.println("Response Body : " + entireResponse.getResponse());
		
		Assert.assertEquals(200,entireResponse.getStatus(),"HTTP Status code was not 200");
		
		JSONArray arr = new JSONArray(entireResponse.getResponse());
		for (int i = 0; i < arr.length(); i++) {
				Long id = arr.getJSONObject(i).getLong("id");
				System.out.println("id : " + id.toString());
				String name = arr.getJSONObject(i).getString("name");
				System.out.println("name : " + name);
		}
	}

	@Test(groups = { "demo1" }  )
	public void computeCompoundInterest_t001() throws Exception {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("{");
        stringBuffer.append("\"accounts\": [");
        stringBuffer.append("{	\"identifier\": \"ACCOUNT-001\", \"balance\": { \"value\": \"12000\",	\"currency\": \"USD\" }, \"rate\": \"0.0200\"},");
        stringBuffer.append("{	\"identifier\": \"ACCOUNT-002\", \"balance\": { \"value\": \"10500\",	\"currency\": \"USD\" }, \"rate\": \"0.0230\"},");
        stringBuffer.append("{	\"identifier\": \"ACCOUNT-003\", \"balance\": { \"value\": \"15275\",	\"currency\": \"USD\" }, \"rate\": \"0.0180\"}");
        stringBuffer.append("],");
        stringBuffer.append("\"startDate\": \"2017-12-21T06:00:00.000Z\",");
        stringBuffer.append("\"intervals\": \"60\",");
        stringBuffer.append("\"frequency\": \"MONTHLY\",");
        stringBuffer.append("\"includeBreakdowns\": \"false\"");
        stringBuffer.append("}");

        System.out.println("Request Body : " + stringBuffer.toString());

        ResponseAPI entireResponse = DemoServiceAPI.computeCompoundInterest(stringBuffer.toString());

        System.out.println("Response Status : " + entireResponse.getStatus());
        System.out.println("Response Status Message : " + entireResponse.getStatusMessage());
        System.out.println("Response Body : " + entireResponse.getResponse());

        Assert.assertEquals(200, entireResponse.getStatus(), "HTTP Status code was not 200");

        String theStatus ;
        JSONObject jo = new JSONObject(entireResponse.getResponse());
        theStatus = jo.getString("status");

        Assert.assertEquals("OK", theStatus, "Service Status code was not OK");
    }
	
}