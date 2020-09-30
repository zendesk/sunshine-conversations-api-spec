import com.zendesk.sunshine_conversations_client.ApiClient;
import com.zendesk.sunshine_conversations_client.ApiException;
import com.zendesk.sunshine_conversations_client.Configuration;
import com.zendesk.sunshine_conversations_client.api.AttachmentsApi;
import com.zendesk.sunshine_conversations_client.auth.HttpBasicAuth;
import com.zendesk.sunshine_conversations_client.model.*;

import java.io.File;

public class AttachmentsExamples {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8091");

        // Configure HTTP basic authorization: basicAuth
        HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
        basicAuth.setUsername("YOURAPP");
        basicAuth.setPassword("YOURPASSWORD");

        AttachmentsApi apiInstance = new AttachmentsApi(defaultClient);
        File file = new File("path-to-file");

        String appId = "5ec41c54fe13cc5ac404bedc"; // String | Identifies the app.
        String access = "public";
        String forMessage = "message";
        String conversationId = "c616a583e4c240a871818541";

        try {
            // Upload an attachment
            AttachmentResponse result = apiInstance.uploadAttachment(file, appId, access, forMessage, conversationId);
            System.out.println(result);

            // Delete attachment
            AttachmentDeleteBody attachmentDeleteBody = new AttachmentDeleteBody();
            attachmentDeleteBody.setMediaUrl(result.getAttachment().getMediaUrl());
            apiInstance.deleteAttachment(attachmentDeleteBody, appId);

            // Generate media token
            AttachmentMediaTokenBody attachmentMediaTokenBody = new AttachmentMediaTokenBody();
            attachmentMediaTokenBody.addPathsItem("/apps/5ec41c54fe13cc5ac404bedc");
            AttachmentMediaTokenResponse attachmentMediaTokenResponse = apiInstance
                    .generateMediaJsonWebToken(attachmentMediaTokenBody, appId);
        } catch (ApiException e) {
            System.err.println("Exception when calling AttachmentsApi#uploadAttachment");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }

    }
}
