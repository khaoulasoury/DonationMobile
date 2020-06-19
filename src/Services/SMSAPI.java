
package Services;

import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import static com.codename1.ui.events.ActionEvent.Type.Response;
import com.codename1.util.Base64;
import java.util.Map;


public class SMSAPI {
    String accountSID = "ACf9f1d802766269a8c0a2d41f";
    String authToken = "5e8da9b0e4d73264130277376e4f";
    String fromPhone = "+12056277837";
    
    public SMSAPI(String message, String to){
        
        Response <Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + accountSID + "/Messages.json").
        queryParam("To", to).
        queryParam("From", fromPhone).
        queryParam("Body", message).
        header("Authorization", "Basic " + Base64.encodeNoNewline((accountSID + ":" + authToken).getBytes())).
        getAsJsonMap();
        System.out.println(result.getResponseData());
        
        
        if(result.getResponseData() != null) {
            String error = (String)result.getResponseData().get("error_message");
        if(error != null) {
            System.out.println(error);
        }
        } else {
            //ToastBar.showErrorMessage("Error sending SMS: " + result.getResponseCode());
        }
        }
    
}
