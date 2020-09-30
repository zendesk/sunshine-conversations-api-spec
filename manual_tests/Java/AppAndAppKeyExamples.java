import com.zendesk.sunshine_conversations_client.ApiClient;
import com.zendesk.sunshine_conversations_client.ApiException;
import com.zendesk.sunshine_conversations_client.Configuration;
import com.zendesk.sunshine_conversations_client.auth.HttpBasicAuth;
import com.zendesk.sunshine_conversations_client.api.AppsApi;
import com.zendesk.sunshine_conversations_client.api.AppKeysApi;
import com.zendesk.sunshine_conversations_client.model.*;

import java.util.HashMap;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8091");

        // Configure HTTP basic authorization: basicAuth
        HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
        basicAuth.setUsername("YOUR USERNAME");
        basicAuth.setPassword("YOUR PASSWORD");
        String serviceAccountId = new String("YOUR SERVICE ACCOUNT ID");

        AppsApi apiInstance = new AppsApi(defaultClient);
        AppCreateBody appCreateBody = new AppCreateBody();
        AppSettings appSettings = new AppSettings();
        HashMap<String, String> metadata = new HashMap<String, String>();
        HashMap<String, String> metadataPartialUpdate = new HashMap<String, String>();
        HashMap<String, String> metadataUnsetValueUpdate = new HashMap<String, String>();
        HashMap<String, String> metadataUnsetUpdate = null;
        AppUpdateBody appUpdateBody = new AppUpdateBody();
        AppUpdateBody appUnsetMetadataKeyUpdateBody = new AppUpdateBody();
        AppUpdateBody appUnsetMetadataUpdateBody = new AppUpdateBody();
        Page page = new Page();
        Page pageCombined = new Page();
        AppListFilter filter = new AppListFilter();

        AppKeysApi akApiInstance = new AppKeysApi();
        akApiInstance.setApiClient(defaultClient);
        AppKeyCreateBody akCreateBody = new AppKeyCreateBody();

        try {
            // create an app
            System.out.println("CREATE APP");
            appCreateBody.setDisplayName("Java Wrapper - Test App");
            appSettings.setMultiConvoEnabled(true);
            metadata.put("metadataKey", "metadataValue");
            metadata.put("anotherMetadataKey", "anotherMetadataValue");
            appCreateBody.setSettings(appSettings);
            appCreateBody.setMetadata(metadata);
            AppResponse creationResponse = apiInstance.createApp(appCreateBody);
            System.out.println(creationResponse);
            String appId = ((App)creationResponse.getApp()).getId();

            // get app
            System.out.println("CET APP");
            AppResponse getResponse = apiInstance.getApp(appId);
            System.out.println(getResponse);

            // update app
            System.out.println("UPDATE APP");
            appUpdateBody.setDisplayName("Updated displayName");
            AppResponse updateResponse = apiInstance.updateApp(appUpdateBody, appId);
            System.out.println(updateResponse);
            metadataPartialUpdate.put("anotherMetadataKey", "changedValue");
            metadataPartialUpdate.put("newMetadataKey", "newValue");
            appUpdateBody.setMetadata(metadataPartialUpdate);
            AppResponse metadataPartialUpdateResponse = apiInstance.updateApp(appUpdateBody, appId);
            System.out.println(metadataPartialUpdateResponse);
            metadataUnsetValueUpdate.put("metadataKey", null);
            appUnsetMetadataKeyUpdateBody.setMetadata(metadataUnsetValueUpdate);
            AppResponse metadataUnsetValueUpdateResponse = apiInstance.updateApp(appUnsetMetadataKeyUpdateBody, appId);
            System.out.println(metadataUnsetValueUpdateResponse);
            appUnsetMetadataUpdateBody.setMetadata(metadataUnsetUpdate);
            AppResponse metadataUnsetUpdateResponse = apiInstance.updateApp(appUnsetMetadataUpdateBody, appId);
            System.out.println(metadataUnsetUpdateResponse);

            // list apps
            System.out.println("LIST APPS");
            AppListResponse listResponse = apiInstance.listApps(null, null);
            System.out.println(listResponse);
            filter.setServiceAccountId(serviceAccountId);
            AppListResponse listFilterResponse = apiInstance.listApps(null, filter);
            System.out.println(listFilterResponse);
            page.setBefore(appId);
            AppListResponse listBeforeAfterResponse = apiInstance.listApps(page, null);
            System.out.println(listBeforeAfterResponse);
            pageCombined.setAfter(appId);
            AppListResponse listPageFilterResponse = apiInstance.listApps(pageCombined, filter);
            System.out.println(listPageFilterResponse);

            // create app keys
            System.out.println("CREATE APP KEYS");
            akCreateBody.setDisplayName("Java Wrapper - Test App Keys");
            AppKeyResponse akCreationResponse = akApiInstance.createAppKey(akCreateBody, appId);
            System.out.println(akCreationResponse);

            // get app keys
            System.out.println("GET APP KEYS");
            String appKeyId = ((AppKey)akCreationResponse.getKey()).getId();
            AppKeyResponse akGetResponse = akApiInstance.getAppKey(appId, appKeyId);
            System.out.println(akGetResponse);

            // list app keys
            System.out.println("LIST APP KEYS");
            AppKeyListResponse akListResponse = akApiInstance.listAppKeys(appId);
            System.out.println(akListResponse);

            // delete app keys
            System.out.println("DELETE APP KEYS");
            Object akDeleteResponse = akApiInstance.deleteAppKey(appId, appKeyId);
            System.out.println(akDeleteResponse);

            // delete app
            System.out.println("DELETE APP");
            Object deleteResponse = apiInstance.deleteApp(appId);
            System.out.println(deleteResponse);
        } catch (ApiException e) {
            System.err.println("Exception when running the tests");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}