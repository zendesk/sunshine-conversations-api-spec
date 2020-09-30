import com.zendesk.sunshine_conversations_client.ApiClient;
import com.zendesk.sunshine_conversations_client.ApiException;
import com.zendesk.sunshine_conversations_client.Configuration;
import com.zendesk.sunshine_conversations_client.api.WebhooksApi;
import com.zendesk.sunshine_conversations_client.auth.HttpBasicAuth;
import com.zendesk.sunshine_conversations_client.model.*;

import java.util.List;


public class WebhooksExample {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8091");

        // Configure HTTP basic authorization: basicAuth
        HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
        basicAuth.setUsername("USERNAME");
        basicAuth.setPassword("PASSWORD");


        WebhooksApi webhooksApi = new WebhooksApi(defaultClient);

        String appId = "5de13f9761f0801d8e022dbd"; // Identifies the app
        String integrationId = "5f33f8edc1a58fed8000944c"; // Custom integration id

        try {
            // Create a webhook
            WebhookCreateBody webhookCreateBody = new WebhookCreateBody();
            webhookCreateBody.setTarget("https://www.example.com/111");
            webhookCreateBody.addTriggersItem("conversation:postback");
            webhookCreateBody.addTriggersItem("conversation:message:delivery:channel");
            webhookCreateBody.addTriggersItem("conversation:message:delivery:failure");
            webhookCreateBody.addTriggersItem("conversation:message:delivery:user");

            System.out.println("Creating a new webhook:");
            try {
                WebhookResponse webhookResponse = webhooksApi.createWebhook(webhookCreateBody, appId, integrationId);
            } catch (ApiException e) {
                // Currently limited to 1 webhook
                System.out.println(e);
            }

            // List webhooks
            System.out.println("Listing webhooks:");
            WebhookListResponse webhookListResponse = webhooksApi.listWebhooks(appId, integrationId);
            System.out.println(webhookListResponse);

            // Retrieve a webhook
            String webhookId = webhookListResponse.getWebhooks().get(0).getId();
            System.out.println("Get webhook:");
            System.out.println(webhooksApi.getWebhook(appId, integrationId, webhookId));

            // Update a webhook
            System.out.println("Update webhook:");
            WebhookBody webhookUpdateBody = new WebhookBody();
            webhookUpdateBody.setTarget("https://www.example.com/222");
            System.out.println(webhooksApi.updateWebhook(webhookUpdateBody, appId, integrationId, webhookId));

            // Delete a webhook
            try {
                System.out.println("Deleting a webhook:");
                System.out.println(webhooksApi.deleteWebhook(appId, integrationId, webhookId));
            } catch (ApiException e) {
                // Currently limited to 1 webhook
                System.out.println(e);
            }
        } catch (ApiException e) {
            System.err.println("Exception when calling the Webhooks API");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}