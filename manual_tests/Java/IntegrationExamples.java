import com.zendesk.sunshine_conversations_client.ApiClient;
import com.zendesk.sunshine_conversations_client.ApiException;
import com.zendesk.sunshine_conversations_client.Configuration;
import com.zendesk.sunshine_conversations_client.api.IntegrationsApi;
import com.zendesk.sunshine_conversations_client.auth.HttpBasicAuth;
import com.zendesk.sunshine_conversations_client.model.*;

public class IntegrationsExample {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8091");

        // Configure HTTP basic authorization: basicAuth
        HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
        basicAuth.setUsername("<YOUR_APP>");
        basicAuth.setPassword("<YOUR_PASSWORD>");

        IntegrationsApi apiInstance = new IntegrationsApi(defaultClient);
        String appId = "5de13f9761f0801d8e022dbd"; // String | Identifies the app.

        try {
            // Create an integration telegram
            Telegram integration = new Telegram(); // Integration |
            integration.setToken("<YOUR_TOKEN>");
            integration.setType("telegram");
            IntegrationResponse result = apiInstance.createIntegration(integration, appId);
            System.out.println(result);
            String integrationId = ((Integration)result.getIntegration()).getId();

            // Create an integration android
            // Create an integration custom
            // Create an integration ios
            // Create an integration messenger
            // Create an integration line
            // Create an integration mailgun
            // Create an integration messagebird
            // Create an integration twilio
            // Create an integration viber
            // Create an integration wechat
            // Create an integration whatsApp

            // Get integration
            IntegrationResponse result2 = apiInstance.getIntegration(appId, integrationId);
            System.out.println(result2);

            // Update integration
            TelegramUpdate tupdate = new TelegramUpdate();
            tupdate.setDisplayName("New Name");
            IntegrationResponse result3 = apiInstance.updateIntegration(tupdate, appId, integrationId);
            System.out.println(result3);

            // List integrations
            IntegrationListResponse result4 = apiInstance.listIntegrations(appId, null, null);
            System.out.println(result4);

            // List integrations with pagination
            Page page = new Page();
            page.setAfter(((Integration)result4.getIntegrations().get(0)).getId());
            page.setSize(2);
            IntegrationListResponse result5 = apiInstance.listIntegrations(appId, page, null);
            System.out.println(result5);

            // List integrations with type filtering
            IntegrationListFilter filter = new IntegrationListFilter();
            filter.setTypes((result4.getIntegrations().get(0)).getType());
            IntegrationListResponse result6 = apiInstance.listIntegrations(appId, null, filter);
            System.out.println(result6);

            // Delete integration
            Object result7 = apiInstance.deleteIntegration(appId, integrationId);
            System.out.println(result7);
        } catch (ApiException e) {
            System.err.println("Exception when calling IntegrationsApi#createIntegration");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}