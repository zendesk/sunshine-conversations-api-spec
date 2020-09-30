import com.zendesk.sunshine_conversations_client.ApiClient;
import com.zendesk.sunshine_conversations_client.ApiException;
import com.zendesk.sunshine_conversations_client.Configuration;
import com.zendesk.sunshine_conversations_client.api.ApiKeysApi;
import com.zendesk.sunshine_conversations_client.api.IntegrationsApi;
import com.zendesk.sunshine_conversations_client.auth.HttpBasicAuth;
import com.zendesk.sunshine_conversations_client.model.*;

import java.util.ArrayList;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8091");

        // Configure HTTP basic authorization: basicAuth
        HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
        basicAuth.setUsername("YOUR_APP");
        basicAuth.setPassword("YOUR_KEY");

        IntegrationsApi apiInstance = new IntegrationsApi(defaultClient);
        Custom integration = new Custom(); // Integration |
        String appId = "5e7269cc501e42fa198ce9a1"; // String | Identifies the app.
        try {
            // create custom integration
            Webhook webhook = new Webhook();
            webhook.setTarget("https://webhook.site/c18a93a8-cf3c-4e25-8c82-1e9e1780297a");
            ArrayList<String> list = new ArrayList<String>();
            list.add("conversation:read");
            webhook.setTriggers(list);
            integration.setWebhook(webhook);
            integration.setType("custom");
            IntegrationResponsePost result = apiInstance.createIntegration(integration, appId);
            System.out.println(result);

            String integrationId = ((Custom)result.getIntegration()).getId();

            // create custom integration api key
            ApiKeysApi apiInstance2 = new ApiKeysApi(defaultClient);
            IntegrationApiKey integrationApiKey = new IntegrationApiKey();
            integrationApiKey.setDisplayName("new key");
            IntegrationApiKeyResponse result2 = apiInstance2.createCustomIntegrationKey(integrationApiKey, appId, integrationId);
            String keyId = result2.getKey().getId();

            // get the custom integration api key
            IntegrationApiKeyResponse result3 = apiInstance2.getCustomIntegrationKey(appId, integrationId, keyId);

            // list the custom integration api keys
            IntegrationApiKeyListResponse result4 = apiInstance2.listCustomIntegrationKeys(appId, integrationId);

            // delete the keys
            Object result5 = apiInstance2.deleteCustomIntegrationKey(appId, integrationId, keyId);

            // list the keys
            IntegrationApiKeyListResponse result6 = apiInstance2.listCustomIntegrationKeys(appId, integrationId);


        } catch (ApiException e) {
            System.err.println("Exception when calling IntegrationsApi#createIntegration");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}