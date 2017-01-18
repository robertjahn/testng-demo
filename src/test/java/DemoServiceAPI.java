import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;


public class DemoServiceAPI{

	private static ResponseAPI response;
	
	public static ResponseAPI getAllAccounts() throws Exception {
		ResponseAPI theResponse = callAPIGET("http://dtdemoapp.us-east-1.elasticbeanstalk.com/service/getAllAccounts",null);
		return theResponse;		
	}	
	
	public static ResponseAPI getAllBands() throws Exception {
		ResponseAPI theResponse = callAPIGET("http://dtdemoapp.us-east-1.elasticbeanstalk.com/service/getAllBands",null);
		return theResponse;		
	}

	public static ResponseAPI computeCompoundInterest(String theRequestBody) throws Exception {
		ResponseAPI theResponse = callAPIPOST("http://dtdemoapp.us-east-1.elasticbeanstalk.com/calculate/computeCompoundInterest",theRequestBody);
		return theResponse;
	}

	public static ResponseAPI callAPIPOST(String theUrl, String theRequest) throws Exception {
	try {
			response = new ResponseAPI();

			URL url = new URL(theUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches (false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Content-Length", "" + 	Integer.toString(theRequest.getBytes().length));
			conn.setRequestProperty("Content-Language", "en-US");

			DataOutputStream os = new DataOutputStream(conn.getOutputStream());
			os.writeBytes(theRequest);

			os.flush();
			os.close();

			InputStream is = conn.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer responseSB = new StringBuffer();
			while((line = rd.readLine()) != null) {
				responseSB.append(line);
			}
			rd.close();

			response.setResponse(responseSB.toString());
			response.setStatus(conn.getResponseCode());
			response.setStatusMessage(conn.getResponseMessage());

			conn.disconnect();

			return response;

		} catch (MalformedURLException e) {
			if (response.getStatusMessage() == null) {
				response.setStatusMessage("MalformedURLException Failure" + e.getMessage());
			}
			response.setResponse(e.getMessage());
			return response;

		} catch (IOException e) {
			if (response.getStatusMessage() == null) {
				response.setStatusMessage("IOException Failure" + e.getMessage());
			}
			response.setResponse(e.getMessage());
			return response;
		}

	}

	public static ResponseAPI callAPIGET(String theUrl, String theRequest) throws Exception {
	try {
			response = new ResponseAPI();

			URL url = new URL(theUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");

			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");

			Scanner scan = new Scanner(url.openStream());

			String entireResponse = new String();
			while (scan.hasNext())
				entireResponse += scan.nextLine();

			response.setStatus(conn.getResponseCode());
			response.setStatusMessage(conn.getResponseMessage());

			scan.close();
			conn.disconnect();

			response.setResponse(entireResponse);

			return response;

		} catch (MalformedURLException e) {
			if (response.getStatusMessage() == null) {
				response.setStatusMessage("MalformedURLException Failure" + e.getMessage());
			}
			response.setResponse(e.getMessage());
			return response;

		} catch (IOException e) {
			if (response.getStatusMessage() == null) {
				response.setStatusMessage("IOException Failure" + e.getMessage());
			}
			response.setResponse(e.getMessage());
			return response;
		}

	}
}

/*



http://dtdemoapp.us-east-1.elasticbeanstalk.com/calculate/computeCompoundInterest
monthly_3_acct_no_breakdowns

String input = "{ \"snippet\": {\"playlistId\": \"WL\",\"resourceId\": {\"videoId\": \""+videoId+"\",\"kind\": \"youtube#video\"},\"position\": 0}}";

{
	"accounts": [
		{	"identifier": "ACCOUNT-001",
			"balance": {
				"value": "12000",
				"currency": "USD"
			},
			"rate": "0.0200"
		},{
			"identifier": "ACCOUNT-002",
			"balance": {
				"value": "10500.00",
				"currency": "USD"
			},
			"rate": "0.0230"
		},{
			"identifier": "ACCOUNT-003",
			"balance": {
				"value": "15275.00",
				"currency": "USD"
			},
			"rate": "0.0180"
		}
	],
	"startDate": "2017-12-21T06:00:00.000Z",
	"intervals": "60",
	"frequency": "MONTHLY",
	"includeBreakdowns": "false"
}


response
{
   "status": "OK",
   "message": null,
   "version": "v2",
   "elapsedTimeMs": 1693,
   "results":    [
            {
         "accountIdentifier": "ACCOUNT-001",
         "interestRate": 0.02,
         "startDate": 1513836000000,
         "intervals": 60,
         "frequency": "MONTHLY",
         "startBalance":          {
            "value": 12000,
            "currency": "USD"
         },
         "endBalance":          {
            "value": 40159.816849592644,
            "currency": "USD"
         },
         "results": null
      },
            {
         "accountIdentifier": "ACCOUNT-002",
         "interestRate": 0.023,
         "startDate": 1513836000000,
         "intervals": 60,
         "frequency": "MONTHLY",
         "startBalance":          {
            "value": 10500,
            "currency": "USD"
         },
         "endBalance":          {
            "value": 42034.21637885735,
            "currency": "USD"
         },
         "results": null
      },
            {
         "accountIdentifier": "ACCOUNT-003",
         "interestRate": 0.018,
         "startDate": 1513836000000,
         "intervals": 60,
         "frequency": "MONTHLY",
         "startBalance":          {
            "value": 15275,
            "currency": "USD"
         },
         "endBalance":          {
            "value": 45351.91990226393,
            "currency": "USD"
         },
         "results": null
      }
   ]
}


 */