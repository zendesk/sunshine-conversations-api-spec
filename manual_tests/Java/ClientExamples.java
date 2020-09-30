import com.zendesk.sunshine_conversations_client.ApiClient;
import com.zendesk.sunshine_conversations_client.ApiException;
import com.zendesk.sunshine_conversations_client.Configuration;
import com.zendesk.sunshine_conversations_client.auth.HttpBasicAuth;
import com.zendesk.sunshine_conversations_client.model.*;
import com.zendesk.sunshine_conversations_client.api.*;

import java.util.ArrayList;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8091");

        // Configure HTTP basic authorization: basicAuth
        HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
        basicAuth.setUsername("YOURAPP");
        basicAuth.setPassword("YOURPASSWORD");


        ConversationCreateBody createConversationBody = new ConversationCreateBody(); // CreateConversationBody |
        createConversationBody.setType(ConversationType.SDKGROUP);

        String userExternalId = "my-user-external-id1"; // String | The external Id of the user. One of userId or userExternalId is required, but not both.
        String userExternalId2 = "my-user-external-id2";
        String userId = "88b28e6f2fb4f3628f94dac9";
        Page page = new Page();

        String appId = "5e7269cc501e42fa198ce9a1"; // String | Identifies the app.
        String conversationId = "8b2a8f63dcb4a83278463b2d"; //personal convo created before
        try {
            // create a client with userId
            ClientsApi apiInstanceClient = new ClientsApi(defaultClient);
            ClientCreate clientCreate = new ClientCreate(); // ClientCreate |
            Target target = new Target();
            target.setConversationId(conversationId);
            Confirmation confirmation = new Confirmation();
            confirmation.setType(Confirmation.TypeEnum.IMMEDIATE);
            MatchCriteria match = new MatchCriteria();
            match.setType(MatchCriteria.TypeEnum.WHATSAPP);
            match.setPhoneNumber("+15550001234");
            match.setIntegrationId("5e985b4d3ab215b6dc7dd357");
            clientCreate.setConfirmation(confirmation);
            clientCreate.setMatchCriteria(match);
            clientCreate.setTarget(target);
            ClientResponse result = apiInstanceClient.createClient(clientCreate, appId, userId);
            System.out.println(result.getClient());

            // create another client with externalId
            //TODO add externalId parameter
            //target.setConversationId(conversationId);
            //clientCreate.setTarget(target);
            //ClientResponse result2 = apiInstanceClient.createClient(clientCreate, appId, userId);
            //System.out.println(result2.getClient());

            // get list of clients with userId
            //ClientList result3 = apiInstanceClient.listClients(appId, userId, page);
            //System.out.println(result3);

            // get list of clients with 
            //TODO remove date-form 
            //Object result3 = apiInstanceClient.listClients(appId, userId, page);
            //System.out.println(result3);

            // delete client
            Object result4 =  apiInstanceClient.removeClient(appId, "6e67928ca5a03f335aa455f5", "5e8e2e21ed4df50d6bd3dd2a"); //took directly out of db
            System.out.println(result4);

            // get list of clients
            //TODO remove date-form 
            //ClientList result5 = apiInstanceClient.listClients(appId, userId, page);
            //System.out.println(result5);

            // get list of clients
            ClientListResponse result6 = apiInstanceClient.listClients(appId, userId, page);
            System.out.println(result6);

            // get list of clients using cursor
            page.setAfter(result6.getClients().get(0).getId());
            page.setSize(2);
            ClientListResponse result7 = apiInstanceClient.listClients(appId, userId, page);
            System.out.println(result7);

        } catch (ApiException e) {
            System.err.println("Exception when calling ConversationsApi#createConversation");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}