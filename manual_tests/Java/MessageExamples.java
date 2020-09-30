import com.zendesk.sunshine_conversations_client.ApiClient;
import com.zendesk.sunshine_conversations_client.ApiException;
import com.zendesk.sunshine_conversations_client.Configuration;
import com.zendesk.sunshine_conversations_client.auth.HttpBasicAuth;
import com.zendesk.sunshine_conversations_client.model.*;
import com.zendesk.sunshine_conversations_client.api.*;

import java.net.URI;
import java.net.URISyntaxException;
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
        ParticipantsApi apiInstance2 = new ParticipantsApi(defaultClient);
        MessagesApi apiInstance3 = new MessagesApi(defaultClient);

        ;


        String userExternalId = "my-user-external-id"; // String | The external Id of the user. One of userId or userExternalId is required, but not both.
        String userExternalId2 = "my-user-external-id2";
        String userId = "e78fac1e4f81ce8e7d797cc6";
        Integer limit = 25; // Integer | Limit the number of records to return.
        Integer offset = 0; // Integer | The number of initial records to skip before picking records to return.
        String appId = "5ece869734a5e899a250f9ae"; // String | Identifies the app.
        try {
            //setup
            ConversationCreateBody createConversationBody;
            ArrayList<ParticipantSubSchema> participants;
            ParticipantSubSchema p;
            ConversationResponse result;
            createConversationBody = new ConversationCreateBody(); // CreateConversationBody |
            createConversationBody.setType(ConversationType.PERSONAL);

            participants = new ArrayList<ParticipantSubSchema>();
            p = new ParticipantSubSchema();
            p.setUserId(userId);
            participants.add(p);
            createConversationBody.setParticipants(participants);

            result = apiInstance.createConversation(createConversationBody, appId);
            System.out.println(result);


            //Post with role: user and userId included
            MessagePost messagePost = new MessagePost(); // MessagePost |
            Author author = new Author();
            author.setType(Author.TypeEnum.USER);
            author.setUserId(userId);
            messagePost.setAuthor(author);

            TextMessage content = new TextMessage();
            content.setType("text");
            content.setText("Hi There!");
            messagePost.setContent(content);
            MessagePostResponse result7 = apiInstance3.postMessage(messagePost, appId, result.getConversation().getId());
            System.out.println(result7);

            //Post with role: user and userExternalId included
            content.setText("Message #2");
            messagePost.setContent(content);
            author.setUserId(null);
            author.setUserExternalId(userExternalId);
            messagePost.setAuthor(author);
            MessagePostResponse result8 = apiInstance3.postMessage(messagePost, appId, result.getConversation().getId());
            System.out.println(result8);

            //Post with error with role: user and BOTH userId and userExternalId included
            //In the response, the appUserId of the author sub-object should be displayed as userId
            try{
                content.setText("Message #2");
                messagePost.setContent(content);
                author.setUserId(userId);
                author.setUserExternalId(userExternalId);
                messagePost.setAuthor(author);
                result8 = apiInstance3.postMessage(messagePost, appId, result.getConversation().getId());
                System.out.println(result8);
            }catch(Exception e){
                System.out.println("Exception: " + e.getMessage());
            }

            //Post with a destination object (integrationId)
            author.setType(Author.TypeEnum.BUSINESS);
            author.setUserExternalId(null);
            author.setUserId(null);
            messagePost.setAuthor(author);
            IntegrationId d = new IntegrationId();
            d.setIntegrationId("5ecf1be034a5e899a250fa8a"); //my own telegram
            messagePost.setDestination(d);
            result8 = apiInstance3.postMessage(messagePost, appId, "82eac5cc27fe227294af8532"); // got the conversation ID from logs
            System.out.println(result8);

            //Post with a destination object (integrationType)
            author.setType(Author.TypeEnum.BUSINESS);
            messagePost.setAuthor(author);
            IntegrationType t = new IntegrationType();
            t.setIntegrationType("telegram"); //my own telegram
            messagePost.setDestination(t);
            result8 = apiInstance3.postMessage(messagePost, appId, "82eac5cc27fe227294af8532"); // got the conversation ID from logs
            System.out.println(result8);


            //Post with a destination object (both type & id) , it should throw an error
            //UNABLE: since destination is only one

            //Attempt to send an image message with no mediaUrl, a 400 should be returned
            try{
                ImageMessage image = new ImageMessage();
                image.setType("image");
                messagePost.setContent(image);
                messagePost.setDestination(null);
                result8 = apiInstance3.postMessage(messagePost, appId, "82eac5cc27fe227294af8532"); // got the conversation ID from logs
                System.out.println(result8);
            }catch(Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }

            //Attempt to send a file message without mediaUrl, a 400 should be returned
            try{
                FileMessage file = new FileMessage();
                file.setType("file");
                messagePost.setContent(file);
                result8 = apiInstance3.postMessage(messagePost, appId, result.getConversation().getId());
                System.out.println(result8);
            }catch(Exception e){
                System.out.println("Exception: " + e.getMessage());
            }

            //Attempt to send a message with actions: [], a 400 should be returned

            //Attempt to send a message with actions having more than 10 elements, a 400 should be returned

            //post a carousel message
            CarouselMessage carousel = new CarouselMessage();
            carousel.setType("carousel");
            Item item = new Item();
            item.setTitle("Carousel action");
            ArrayList<ActionSubset> la = new ArrayList<ActionSubset>();
            Link link = new Link();
            link.setUri(new URI("https://c402277.ssl.cf1.rackcdn.com/photos/18374/images/hero_full/Mountain_Gorilla_Silverback_WW22557.jpg?1576768689"));
            link.setText("LINKLINKLINK");
            link.setType("link");
            la.add(link);
            la.add(link);
            item.setActions(la);
            ArrayList<Item> items = new ArrayList<Item>();
            items.add(item);
            carousel.setItems(items);
            messagePost.setContent(carousel);
            result8 = apiInstance3.postMessage(messagePost, appId, "82eac5cc27fe227294af8532");
            System.out.println(result8);

            //post a list message
            ListMessage list = new ListMessage();
            list.setType("list");
            list.setItems(items);
            messagePost.setContent(list);
            result8 = apiInstance3.postMessage(messagePost, appId, "82eac5cc27fe227294af8532");
            System.out.println(result8);

            //post an image message
            ImageMessage im = new ImageMessage();
            im.setType("image");
            URI u = new URI("https://c402277.ssl.cf1.rackcdn.com/photos/18374/images/hero_full/Mountain_Gorilla_Silverback_WW22557.jpg?1576768689");
            im.setMediaUrl(u);
            messagePost.setContent(im);
            result8 = apiInstance3.postMessage(messagePost, appId, "82eac5cc27fe227294af8532");
            System.out.println(result8);

            //post a file message
            FileMessage fm = new FileMessage();
            fm.setType("file");
            fm.setMediaUrl(u);
            messagePost.setContent(fm);
            result8 = apiInstance3.postMessage(messagePost, appId, "82eac5cc27fe227294af8532");
            System.out.println(result8);

            //post with metadata
            messagePost.setContent(fm);
            HashMap<String, String> metadata = new HashMap<String, String>();
            metadata.put("ref", "1234");
            messagePost.setMetadata(metadata);
            result8 = apiInstance3.postMessage(messagePost, appId, "82eac5cc27fe227294af8532");
            System.out.println(result8);

            //post with schema at the root level ?????

            //send a message with override TODO

            //put an invalid object in override TODO


            /* List Messages */
            //list message
            MessageListResponse result9 = apiInstance3.listMessages(appId,  "82eac5cc27fe227294af8532", null);
            System.out.println(result9);

            /* Delete Messages */
            //delete a message
            Object result10 = apiInstance3.deleteMessage(appId, "82eac5cc27fe227294af8532", result8.getMessages().get(0).getId());

            //list messages
            MessageListResponse result11 = apiInstance3.listMessages(appId,  "82eac5cc27fe227294af8532", null);
            System.out.println(result11);

            /* Delete all Messages */
            //delete all messages
            apiInstance3.deleteAllMessages(appId, "82eac5cc27fe227294af8532");

            //list messages
            MessageListResponse listM = apiInstance3.listMessages(appId,  result.getConversation().getId(), null);
            System.out.println(listM);

        } catch (ApiException | URISyntaxException e) {
            System.err.println("Exception when calling ConversationsApi#createConversation");
            e.printStackTrace();
        }
    }
}
