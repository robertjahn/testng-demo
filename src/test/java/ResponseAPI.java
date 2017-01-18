import org.json.JSONArray;
import java.util.Scanner;

public class ResponseAPI {
    private int status;
    private String statusMessage;
    private String response;
	private JSONArray responseJSONArray;
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int new_status) {
        status = new_status;
    }
	
	public String getStatusMessage() {
        return statusMessage;
    }
    
    public void setStatusMessage(String new_statusMessage) {
        statusMessage = new_statusMessage;
    }

	public String getResponse() {
        return response;
    }
    
    public void setResponse(String new_response) {
        response = new_response;
    }

}