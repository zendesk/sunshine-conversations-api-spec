import com.zendesk.sunshine_conversations_client.ApiClient;
import com.zendesk.sunshine_conversations_client.ApiException;
import com.zendesk.sunshine_conversations_client.Configuration;
import com.zendesk.sunshine_conversations_client.api.SwitchboardActionsApi;
import com.zendesk.sunshine_conversations_client.auth.HttpBasicAuth;
import com.zendesk.sunshine_conversations_client.model.*;

import java.util.HashMap;


public class SwitchboardActionExamples {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8091");

        // Configure HTTP basic authorization: basicAuth
        HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
        basicAuth.setUsername("USERNAME");
        basicAuth.setPassword("PASSWORD");


        SwitchboardActionsApi switchboardActionsApi = new SwitchboardActionsApi(defaultClient);

        String appId = "5ce6f2a7a603a78f38e344f6"; // Identifies the app
        String conversationId = "a2f3744381ed8b91e89850d3"; // Identifies the conversation
        String switchboardIntegration = "3"; // Targeted switchboard integration
        String nextSwitchboardIntegration = "4"; // Targeted switchboard integration

        HashMap<String, String> metadata = new HashMap<String, String>();
        metadata.put("patate", "frite");

        try {
            // Pass control
            System.out.println("PassControl:");
            PassControlBody passControlBody = new PassControlBody();
            passControlBody.setSwitchboardIntegration(switchboardIntegration);
            passControlBody.setMetadata(metadata);
            switchboardActionsApi.passControl(passControlBody, appId, conversationId);

            // Offer control
            System.out.println("OfferControl:");
            OfferControlBody offerControlBody = new OfferControlBody();
            offerControlBody.setSwitchboardIntegration(nextSwitchboardIntegration);
            offerControlBody.setMetadata(metadata);
            switchboardActionsApi.offerControl(offerControlBody, appId, conversationId);

            // Accept control
            System.out.println("AcceptControl:");
            AcceptControlBody acceptControlBody = new AcceptControlBody();
            acceptControlBody.setMetadata(metadata);
            switchboardActionsApi.acceptControl(acceptControlBody, appId, conversationId);
        } catch (ApiException e) {
            System.err.println("Exception when calling a Switchboard Action API");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
