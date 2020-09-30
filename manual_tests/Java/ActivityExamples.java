import com.zendesk.sunshine_conversations_client.ApiClient;
import com.zendesk.sunshine_conversations_client.ApiException;
import com.zendesk.sunshine_conversations_client.Configuration;
import com.zendesk.sunshine_conversations_client.api.ActivitiesApi;
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

        ActivitiesApi apiInstance = new ActivitiesApi(defaultClient);
        ActivityPost activityPost = new ActivityPost(); // ActivityPost |


        String appId = "5ece869734a5e899a250f9ae"; // String | Identifies the app.
        try {
            Author author = new Author();
            author.setRole(Author.RoleEnum.USER);
            author.setUserId("d11fd080bec04c16eac47850");
            activityPost.setAuthor(author);
            activityPost.setType(ActivityPost.TypeEnum.CONVERSATION_READ);
            apiInstance.postActivity(activityPost, appId, "82eac5cc27fe227294af8532");

        } catch (ApiException e) {
            System.err.println("Exception when calling ConversationsApi#createConversation");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}