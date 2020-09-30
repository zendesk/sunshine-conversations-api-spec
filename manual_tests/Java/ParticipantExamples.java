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

        ConversationsApi apiInstance = new ConversationsApi(defaultClient);
        ParticipantsApi apiInstance2 = new ParticipantsApi(defaultClient);
        MessagesApi apiInstance3 = new MessagesApi(defaultClient);

        ConversationCreateBody createConversationBody = new ConversationCreateBody(); // CreateConversationBody |
        createConversationBody.setType(ConversationType.SDKGROUP);


        String userExternalId = "my-user-external-id"; // String | The external Id of the user. One of userId or userExternalId is required, but not both.
        String userExternalId2 = "my-user-external-id2";
        String userId = "e78fac1e4f81ce8e7d797cc6";
        Page page = new Page();

        String appId = "5ece869734a5e899a250f9ae"; // String | Identifies the app.
        try {
            //setup
            ConversationResponse result = apiInstance.createConversation(createConversationBody, appId);
            System.out.println(result);

            /* Participant endpoints */
            //join a conversation with userID
            ParticipantJoinBody participantJoinBody = new ParticipantJoinBody(); // ParticipantJoinBody |
            participantJoinBody.setUserId(userId);
            participantJoinBody.setSubscribeSDKClient(false);
            ParticipantResponse participant = apiInstance2.joinConversation(participantJoinBody, appId, result.getConversation().getId());
            System.out.println(participant);

            //join a conversation with userExternalId
            ParticipantJoinBody participantJoinBody2 = new ParticipantJoinBody(); // ParticipantJoinBody |
            participantJoinBody2.setUserExternalId(userExternalId2);
            participantJoinBody2.setSubscribeSDKClient(false);
            ParticipantResponse participant2 = apiInstance2.joinConversation(participantJoinBody2, appId, result.getConversation().getId());
            System.out.println(participant2);

            //list participants
            ParticipantListResponse result3 = apiInstance2.listParticipants(appId, result.getConversation().getId(), page);
            System.out.println(result3);

            //set an after and size in the query
            page.setAfter(result3.getParticipants().get(0).getId());
            page.setSize(2);
            ParticipantListResponse result4 = apiInstance2.listParticipants(appId, result.getConversation().getId(), page);
            System.out.println(result4);

            //deny joining a conversation when both userId and userExternalId is supplied
            try{
                result = apiInstance.createConversation(createConversationBody, appId);
                participantJoinBody = new ParticipantJoinBody(); // ParticipantJoinBody |
                participantJoinBody.setUserId(userId);
                participantJoinBody.setUserExternalId(userExternalId);
                participantJoinBody.setSubscribeSDKClient(false);
                participant2 = apiInstance2.joinConversation(participantJoinBody, appId, result.getConversation().getId());
            }catch(Exception e){
                System.out.println("Exception: " + e.getMessage());
            }

            //leave a conversation with userId
            apiInstance2.joinConversation(participantJoinBody2, appId, result.getConversation().getId());
            ParticipantLeaveBodyUserId participantLeaveBody = new ParticipantLeaveBodyUserId(); // ParticipantLeaveBody |
            participantLeaveBody.setUserId(participant2.getParticipant().getUserId());
            Object result5 = apiInstance2.leaveConversation(participantLeaveBody, appId, result.getConversation().getId());

            //leave a conversation with userExternalId
            apiInstance2.joinConversation(participantJoinBody2, appId, result.getConversation().getId());
            ParticipantLeaveBodyUserExternalId participantLeaveBody2 = new ParticipantLeaveBodyUserExternalId(); // ParticipantLeaveBody |
            participantLeaveBody2.setUserExternalId(participant2.getParticipant().getUserExternalId());
            result5 = apiInstance2.leaveConversation(participantLeaveBody, appId, result.getConversation().getId());

            //leave a conversation using participantId
            apiInstance2.joinConversation(participantJoinBody2, appId, result.getConversation().getId());
            ParticipantLeaveBodyParticipantId participantLeaveBody3 = new ParticipantLeaveBodyParticipantId(); // ParticipantLeaveBody |
            participantLeaveBody3.setParticipantId(participant2.getParticipant().getUserExternalId());
            result5 = apiInstance2.leaveConversation(participantLeaveBody, appId, result.getConversation().getId());

            //reject when more than one option is specified
        } catch (ApiException e) {
            System.err.println("Exception when calling ConversationsApi#createConversation");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}