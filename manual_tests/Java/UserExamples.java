import io.smooch.v2.client.ApiClient;
import io.smooch.v2.client.ApiException;
import io.smooch.v2.client.Configuration;
import io.smooch.v2.client.auth.HttpBasicAuth;
import io.smooch.v2.client.model.*;
import io.smooch.v2.client.api.*;

import java.net.URI;
import java.util.Collections;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8091");

        // Configure HTTP basic authorization: basicAuth
        HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
        basicAuth.setUsername("USERNAME");
        basicAuth.setPassword("PASSWORD");

        UsersApi userApi = new UsersApi(defaultClient);

        String appId = "5ebee0975ac5304b664a12fa"; // Identifies the app

        try {
            // Create User
            System.out.println("Creating user:");
            UserCreateBody userCreateBody = new UserCreateBody();
            Profile createProfile = new Profile();
            createProfile.givenName("Jane");
            createProfile.surname("Doe");
            createProfile.email("jane.doe@gmail.com");
            createProfile.avatarUrl(URI.create("https://s3.amazonaws.com/avatar.jpg"));
            userCreateBody.setExternalId("customId1");
            userCreateBody.signedUpAt("2020-05-21T15:53:30.197Z");
            userCreateBody.profile(createProfile);
            userCreateBody.metadata(Collections.singletonMap("lang", "en-ca"));
            UserResponse createdUserResponse = userApi.createUser(userCreateBody, appId);
            System.out.println(createdUserResponse);

            // Update User
            System.out.println("Updating user:");
            UserUpdateBody userUpdateBody = new UserUpdateBody();
            Profile updateProfile = new Profile();
            updateProfile.givenName("Clark");
            updateProfile.surname("Kent");
            updateProfile.email("clark.kent@gmail.com");
            updateProfile.avatarUrl(URI.create("https://s3.amazonaws.com/avatar.png"));
            userUpdateBody.signedUpAt("2020-07-14T12:07:48.156Z");
            userUpdateBody.profile(updateProfile);
            userUpdateBody.metadata(Collections.singletonMap("lang", "fr-ca"));
            UserResponse updatedUserResponse = userApi.updateUser(userUpdateBody, appId, createdUserResponse.getUser().getId());
            System.out.println(updatedUserResponse);

            // Get User
            System.out.println("Fetching user:");
            UserResponse getUserResponse = userApi.getUser(appId, createdUserResponse.getUser().getId());
            System.out.println(getUserResponse);

            // Delete User Personal Information
            System.out.println("Deleting user personal information:");
            System.out.println(userApi.deleteUserPersonalInformation(appId, createdUserResponse.getUser().getId()));

            // Delete User
            System.out.println("Deleting user:");
            System.out.println(userApi.deleteUser(appId, createdUserResponse.getUser().getId()));
        } catch (ApiException e) {
            System.err.println("Exception when calling a User endpoint");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}