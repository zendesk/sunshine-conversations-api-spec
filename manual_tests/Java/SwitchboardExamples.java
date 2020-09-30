import com.zendesk.sunshine_conversations_client.ApiClient;
import com.zendesk.sunshine_conversations_client.ApiException;
import com.zendesk.sunshine_conversations_client.Configuration;
import com.zendesk.sunshine_conversations_client.api.SwitchboardIntegrationsApi;
import com.zendesk.sunshine_conversations_client.api.SwitchboardsApi;
import com.zendesk.sunshine_conversations_client.auth.HttpBasicAuth;
import com.zendesk.sunshine_conversations_client.model.*;


public class SwitchboardExamples {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8091");

        // Configure HTTP basic authorization: basicAuth
        HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
        basicAuth.setUsername("USERNAME");
        basicAuth.setPassword("PASSWORD");


        SwitchboardsApi switchboardsApi = new SwitchboardsApi(defaultClient);
        SwitchboardIntegrationsApi switchboardIntegrationsApi = new SwitchboardIntegrationsApi(defaultClient);

        String appId = "5e99b7649e71ad2c378b328f"; // Identifies the app
        String firstIntegrationId = "5f0e0077b6b1aa5644bde8d8"; // IntegrationId used by the first switchboard integration
        String secondIntegrationId = "5f0e0083b6b1aa5644bde8d9"; // IntegrationId used by the second switchboard integration

        try {
            // Create a switchboard
            System.out.println("Creating a switchboard:");
            SwitchboardResponse switchboardResponse = switchboardsApi.createSwitchboard(appId);;
            System.out.println(switchboardResponse);

            String switchboardId = switchboardResponse.getSwitchboard().getId();

            // List switchboard
            System.out.println("Listing switchboards:");
            System.out.println(switchboardsApi.listSwitchboards(appId));

            // Create switchboard integrations
            System.out.println("Creating first switchboard integration:");
            SwitchboardIntegrationCreateBody createBody = new SwitchboardIntegrationCreateBody();
            createBody.setIntegrationId(firstIntegrationId);
            createBody.setName("first");
            createBody.setNextSwitchboardIntegrationId(null);
            SwitchboardIntegrationResponse firstSwitchboardIntegrationResponse = switchboardIntegrationsApi.createSwitchboardIntegration(createBody, appId, switchboardId);
            String firstSwitchboardIntegrationId = firstSwitchboardIntegrationResponse.getSwitchboardIntegration().getId();
            System.out.println(firstSwitchboardIntegrationResponse);

            // Create second switchboard integration
            System.out.println("Creating second switchboard integration:");
            createBody = new SwitchboardIntegrationCreateBody();
            createBody.setIntegrationId(secondIntegrationId);
            createBody.setName("second");
            createBody.setNextSwitchboardIntegrationId(firstSwitchboardIntegrationId);
            SwitchboardIntegrationResponse secondSwitchboardIntegrationResponse = switchboardIntegrationsApi.createSwitchboardIntegration(createBody, appId, switchboardId);
            String secondSwitchboardIntegrationId = secondSwitchboardIntegrationResponse.getSwitchboardIntegration().getId();

            System.out.println(secondSwitchboardIntegrationResponse);

            // Update a switchboard integration
            System.out.println("Updating first switchboard integration:");
            SwitchboardIntegrationUpdateBody updateBody = new SwitchboardIntegrationUpdateBody();
            updateBody.setName("First-updated");
            updateBody.setDeliverStandbyEvents(false);
            updateBody.setNextSwitchboardIntegrationId(secondSwitchboardIntegrationId);
            System.out.println(switchboardIntegrationsApi.updateSwitchboardIntegration(updateBody, appId, switchboardId, firstSwitchboardIntegrationId));

            // List switchboard integrations
            System.out.println("List switchboard integrations:");
            System.out.println(switchboardIntegrationsApi.listSwitchboardIntegrations(appId, switchboardId));

            // Update the switchboard
            System.out.println("Updating the switchboard:");
            SwitchboardUpdateBody switchboardUpdateBody = new SwitchboardUpdateBody();
            switchboardUpdateBody.setDefaultSwitchboardIntegrationId(firstSwitchboardIntegrationId);
            switchboardUpdateBody.setEnabled(true);
            System.out.println(switchboardsApi.updateSwitchboard(switchboardUpdateBody, appId, switchboardId));

            // Delete a switchboard integration
            System.out.println("Deleting a switchboard integration:");
            updateBody = new SwitchboardIntegrationUpdateBody();
            updateBody.setNextSwitchboardIntegrationId(null);
            switchboardIntegrationsApi.updateSwitchboardIntegration(updateBody, appId, switchboardId, firstSwitchboardIntegrationId);
            System.out.println(switchboardIntegrationsApi.deleteSwitchboardIntegration(appId, switchboardId, secondSwitchboardIntegrationId));

            // Delete the switchboard
            System.out.println("Deleting a switchboard:");
            System.out.println(switchboardsApi.deleteSwitchboard(appId, switchboardId));
        } catch (ApiException e) {
            System.err.println("Exception when calling a Switchboard API");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}