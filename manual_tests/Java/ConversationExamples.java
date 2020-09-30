import com.zendesk.sunshine_conversations_client.ApiClient;
import com.zendesk.sunshine_conversations_client.ApiException;
import com.zendesk.sunshine_conversations_client.Configuration;
import com.zendesk.sunshine_conversations_client.api.ConversationsApi;
import com.zendesk.sunshine_conversations_client.auth.HttpBasicAuth;
import com.zendesk.sunshine_conversations_client.model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8091");

        // Configure HTTP basic authorization: basicAuth
        HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
        basicAuth.setUsername("YOURAPP");
        basicAuth.setPassword("YOURPASSWORD");

        ConversationsApi apiInstance = new ConversationsApi(defaultClient);

        String userExternalId = "my-user-external-id"; // String | The external Id of the user. One of userId or userExternalId is required, but not both.
        String userExternalId2 = "my-user-external-id2";
        String userId = "e78fac1e4f81ce8e7d797cc6";
        Page page = new Page();
        ConversationListFilter filter = new ConversationListFilter();

        String appId = "5ece869734a5e899a250f9ae"; // String | Identifies the app.
        try {
            ConversationCreateBody createConversationBody;
            ArrayList<ParticipantSubSchema> participants;
            ParticipantSubSchema p;
            ConversationResponse result;

            /* Personal conversations */
            //setup
            createConversationBody = new ConversationCreateBody(); // CreateConversationBody |
            createConversationBody.setType(ConversationType.PERSONAL);

            //create a personal conversation with userId
            participants = new ArrayList<ParticipantSubSchema>();
            p = new ParticipantSubSchema();
            p.setUserExternalId(userExternalId);
            participants.add(p);
            createConversationBody.setParticipants(participants);
            result = apiInstance.createConversation(createConversationBody, appId);
            System.out.println(result);

            //create a personal conversation with userExternalId
            participants = new ArrayList<ParticipantSubSchema>();
            p = new ParticipantSubSchema();
            p.setUserId(userId);
            participants.add(p);
            createConversationBody.setParticipants(participants);
            result = apiInstance.createConversation(createConversationBody, appId);
            System.out.println(result);

            //reject a conversation when no participants are supplied
            try{
                participants = new ArrayList<ParticipantSubSchema>();
                createConversationBody.setParticipants(participants);
                result = apiInstance.createConversation(createConversationBody, appId);
                System.out.println(result);
            }catch(Exception e){
                System.out.println("Caught exception: " + e.getMessage());
            }

            //reject a conversation when two participants are supplied
            try{
                participants = new ArrayList<ParticipantSubSchema>();
                p = new ParticipantSubSchema();
                p.setUserId(userId);
                participants.add(p);
                participants.add(p);
                createConversationBody.setParticipants(participants);
                result = apiInstance.createConversation(createConversationBody, appId);
                System.out.println(result);
            }catch(Exception e){
                System.out.println("Caught exception: " + e.getMessage());
            }

            //reject when both userId and userExternalId are supplied
            try{
                participants = new ArrayList<ParticipantSubSchema>();
                p = new ParticipantSubSchema();
                p.setUserId(userId);
                p.setUserExternalId(userExternalId);
                participants.add(p);
                createConversationBody.setParticipants(participants);
                result = apiInstance.createConversation(createConversationBody, appId);
                System.out.println(result);
            }catch(Exception e){
                System.out.println("Caught exception: " + e.getMessage());
            }


            //reject when no type is supplied
            try{
                createConversationBody = new ConversationCreateBody(); // CreateConversationBody |
                result = apiInstance.createConversation(createConversationBody, appId);
                System.out.println(result);
            }catch(Exception e){
                System.out.println("Caught exception: " + e.getMessage());
            }

            //create a personal conversation with a displayName
            createConversationBody = new ConversationCreateBody(); // CreateConversationBody |
            createConversationBody.setType(ConversationType.PERSONAL);
            participants = new ArrayList<ParticipantSubSchema>();
            p = new ParticipantSubSchema();
            p.setUserId(userId);
            participants.add(p);
            createConversationBody.setParticipants(participants);
            createConversationBody.setDisplayName("My friendly conversation");
            result = apiInstance.createConversation(createConversationBody, appId);
            System.out.println(result);

            /* SDK group conversations */
            createConversationBody = new ConversationCreateBody(); // CreateConversationBody |
            createConversationBody.setType(ConversationType.SDKGROUP);

            //create a group conversation with userId
            participants = new ArrayList<ParticipantSubSchema>();
            p = new ParticipantSubSchema();
            p.setUserId(userId);
            p.setSubscribeSDKClient(false);
            participants.add(p);
            createConversationBody.setParticipants(participants);
            createConversationBody.setDisplayName("My friendly conversation");
            result = apiInstance.createConversation(createConversationBody, appId);
            System.out.println(result);

            //create a group conversation with userExternalId
            participants = new ArrayList<ParticipantSubSchema>();
            p = new ParticipantSubSchema();
            p.setUserExternalId(userExternalId);
            participants.add(p);
            p.setSubscribeSDKClient(false);
            createConversationBody.setParticipants(participants);
            createConversationBody.setDisplayName("My friendly conversation");
            result = apiInstance.createConversation(createConversationBody, appId);
            System.out.println(result);

            //reject when no subscribeSDKClient
            try{
                participants = new ArrayList<ParticipantSubSchema>();
                p = new ParticipantSubSchema();
                p.setUserExternalId(userExternalId);
                participants.add(p);
                createConversationBody.setParticipants(participants);
                createConversationBody.setDisplayName("My friendly conversation");
                result = apiInstance.createConversation(createConversationBody, appId);
                System.out.println(result);
            }catch(Exception e){
                System.out.println("Caught exception: " + e.getMessage());
            }

            //create a conversation with no participants
            createConversationBody = new ConversationCreateBody(); // CreateConversationBody |
            createConversationBody.setType(ConversationType.SDKGROUP);
            result = apiInstance.createConversation(createConversationBody, appId);
            System.out.println(result);

            //reject when more than 10 participants
            try{
                participants = new ArrayList<ParticipantSubSchema>();
                p = new ParticipantSubSchema();
                p.setUserExternalId(userExternalId);
                for(int i =0; i <= 10; i++){
                    participants.add(p);
                }
                createConversationBody.setParticipants(participants);
                result = apiInstance.createConversation(createConversationBody, appId);
                System.out.println(result);
            }catch(Exception e){
                System.out.println("Caught exception: " + e.getMessage());
            }

            //accept one participant with userId and one with userExternalId
            participants = new ArrayList<ParticipantSubSchema>();
            p = new ParticipantSubSchema();
            p.setUserExternalId(userExternalId2);
            p.setSubscribeSDKClient(false);
            participants.add(p);

            p = new ParticipantSubSchema();
            p.setUserId(userId);
            p.setSubscribeSDKClient(false);
            participants.add(p);

            createConversationBody.setParticipants(participants);
            createConversationBody.setDisplayName("My friendly conversation");
            result = apiInstance.createConversation(createConversationBody, appId);
            System.out.println(result);

            //create a conversation with no participants
            createConversationBody = new ConversationCreateBody(); // CreateConversationBody |
            createConversationBody.setType(ConversationType.SDKGROUP);
            result = apiInstance.createConversation(createConversationBody, appId);
            System.out.println(result);

            //deny when one participant has both userId and userExternalId
            try{
                participants = new ArrayList<ParticipantSubSchema>();
                p = new ParticipantSubSchema();
                p.setUserExternalId(userExternalId);
                p.setUserId(userId);
                participants.add(p);

                createConversationBody.setParticipants(participants);
                createConversationBody.setDisplayName("My friendly conversation");
                result = apiInstance.createConversation(createConversationBody, appId);
                System.out.println(result);
            }catch(Exception e){
                System.out.println("Caught exception: " + e.getMessage());
            }


            //create an sdkgroup convo with displayName
            createConversationBody = new ConversationCreateBody(); // CreateConversationBody |
            createConversationBody.setType(ConversationType.SDKGROUP);
            createConversationBody.setDisplayName("Friednly name");
            result = apiInstance.createConversation(createConversationBody, appId);
            System.out.println(result);

            /* List conversations */
            //list conversations with userId in the query
            filter.setUserId(userId);
            filter.setUserExternalId(null);
            ConversationListResponse result4 = apiInstance.listConversations(appId, page, filter);
            System.out.println(result4);

            //list conversation with userExternalId in the query
            filter.setUserId(null);
            filter.setUserExternalId(userExternalId);
            result4 = apiInstance.listConversations(appId, page, filter);
            System.out.println(result4);

            //reject when neither is supplied
            try{
                filter.setUserId(null);
                filter.setUserExternalId(null);
                result4 = apiInstance.listConversations(appId, page, filter);
                System.out.println(result4);
            }catch(Exception e){
                System.out.println("Caught exception: " + e.getMessage());
            }

            //set an after and size in the query
            page.setAfter(result4.getConversations().get(0).getId());
            page.setSize(2);
            filter.setUserId(null);
            filter.setUserExternalId(userExternalId);

            result4 = apiInstance.listConversations(appId, page, filter);
            System.out.println(result4);

            /* Get conversation */
            // get a conversation by id and check that is has businessLastRead and lastUpdatedAt and type
            ConversationResponse result3 = apiInstance.getConversation(appId, result.getConversation().getId());
            System.out.println(result3);

            /* Update a conversation */
            //update the displayName of a conversation
            ConversationUpdateBody conversationUpdateBody = new ConversationUpdateBody();
            conversationUpdateBody.setDisplayName("New Conversations");
            ConversationResponse result2 = apiInstance.updateConversation(conversationUpdateBody, appId, result.getConversation().getId());
            System.out.println(result2);

            //update the metadata of a conversation
            conversationUpdateBody = new ConversationUpdateBody();
            HashMap<String, String> metadata = new HashMap<String, String>();
            metadata.put("name","MyRef");
            conversationUpdateBody.setMetadata(metadata);
            result2 = apiInstance.updateConversation(conversationUpdateBody, appId, result.getConversation().getId());
            System.out.println(result2);

            /* delete a conversation */
            Object result5 = apiInstance.deleteConversation(appId, result.getConversation().getId());
            System.out.println(result5);

        } catch (ApiException e) {
            System.err.println("Exception when calling ConversationsApi#createConversation");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
