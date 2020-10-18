package com.apiTest.helpers;

import com.apiTest.helpers.json.ContactJson;
import com.apiTest.helpers.json.JsonEntity;
import com.apiTest.helpers.json.UserJson;
import com.github.javafaker.Faker;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.logging.Logger;

import static com.apiTest.helpers.constans.ConstantsHeaders.HEADER_CONTENT_TYPE;
import static com.apiTest.helpers.constans.ConstantsUrls.ENDPOINT_CONTACT;
import static com.apiTest.helpers.constans.ConstantsUrls.ENDPOINT_LOGIN;

public class BasicAPITest {

    public static HttpPost postRequest;
    public static HttpPut putRequest;
    public static HttpGet getRequest;
    public static HttpDelete deleteRequest;

    public static HttpResponse response;
    public static CloseableHttpClient client;

    public static Header authHeader;

    public static Faker faker = new Faker();

    public final static Logger LOGGER = Logger.getLogger(BasicAPITest.class.getName());

    public static HttpResponse login(String userEmail, String userPassword) throws IOException, LoginException {
        client = HttpClientBuilder.create().build();

        postRequest = new HttpPost(ENDPOINT_LOGIN);
        postRequest.setHeader(HEADER_CONTENT_TYPE);

        UserJson loginUser = new UserJson(userEmail, userPassword);
        StringEntity entity = new StringEntity(loginUser.toJson());

        postRequest.setEntity(entity);
        response = client.execute(postRequest);

        if (response.getStatusLine().getStatusCode() == 401)
            throw new LoginException("Check login credential");

        return response;
    }

    public static Header getTokenFromResponse(HttpResponse response, String headerName) {
        Header authHeaderFromResponse;

        try {
            authHeaderFromResponse = response.getFirstHeader(headerName);
        } catch (NullPointerException ex) {
            LOGGER.warning("Header with name: " + headerName + " not found");
            return null;
        }
        return authHeaderFromResponse;
    }

    public static String getUserEmailFromJwt(Header authHeader) {
        Base64 decoder = new Base64(true);

        String jwtToken;
        try {
            jwtToken = authHeader.getValue();
        } catch (NullPointerException ex) {
            LOGGER.warning("Header is null");
            return null;
        }

        String jwtPayloadString = jwtToken.split("\\.")[1];
        byte[] decodedPayloadToBytes = decoder.decode(jwtPayloadString);

        String id = new String(decodedPayloadToBytes);

        return id.split("\"")[3];
    }

    public static JSONObject getJsonFromResponse(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        if (responseString.equals(""))
            return new JSONObject().put("!---Response string---", "---is empty---!");
        if (responseString.startsWith("[{"))
            return new JSONObject().put("!---Response string---", "---is Array---!");
        return new JSONObject(responseString);
    }

    public static JSONArray getJsonArrayFromResponse(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        return new JSONArray(responseString);
    }

    public static int addContactAndGetId(ContactJson contactJson, Header authHeader) throws IOException {
        addEntity(ENDPOINT_CONTACT, contactJson, authHeader);
        JSONArray jsonArray = getContactsList(authHeader);
        return jsonArray.getJSONObject(jsonArray.length() - 1).getInt("id");
    }

    public static int getIdLastContactFromContactsList(Header authHeader) throws IOException {
        JSONArray jsonArray = getContactsList(authHeader);
        return jsonArray.getJSONObject(jsonArray.length() - 1).getInt("id");
    }

    public static void addEntity(String endpoint, JsonEntity jsonEntity, Header authHeader) throws IOException {
        postRequest = new HttpPost(endpoint);
        postRequest.setHeader(HEADER_CONTENT_TYPE);
        postRequest.setHeader(authHeader);

        StringEntity entity = new StringEntity(jsonEntity.toJson());
        postRequest.setEntity(entity);
        response = client.execute(postRequest);
    }

    public static int removeEntity(String endpoint, int entityIdToRemove, Header authHeader) throws IOException {
        deleteRequest = new HttpDelete(endpoint + "/" + entityIdToRemove);
        deleteRequest.setHeader(HEADER_CONTENT_TYPE);
        deleteRequest.setHeader(authHeader);

        response = client.execute(deleteRequest);

        return response.getStatusLine().getStatusCode();
    }

    public static int getListSizeByUser(String endpoint, int contactId, Header authHeader) throws IOException {
        return getListEntity(endpoint, contactId, authHeader).length();
    }

    public static JSONObject getLastElementFromList(String endpoint, int contactId, Header authHeader) throws IOException {
        JSONArray jsonArray = getListEntity(endpoint, contactId, authHeader);
        return jsonArray.getJSONObject(jsonArray.length() - 1);
    }

    private static JSONArray getListEntity(String endpoint, int contactId, Header authHeader) throws IOException {
        getRequest = new HttpGet(endpoint + "/" + contactId + "/all");
        getRequest.setHeader(HEADER_CONTENT_TYPE);
        getRequest.setHeader(authHeader);

        response = client.execute(getRequest);
        return getJsonArrayFromResponse(response);
    }

    private static JSONArray getContactsList(Header authHeader) throws IOException {
        getRequest = new HttpGet(ENDPOINT_CONTACT);
        getRequest.setHeader(HEADER_CONTENT_TYPE);
        getRequest.setHeader(authHeader);

        response = client.execute(getRequest);
        return getJsonArrayFromResponse(response);
    }

    @Before
    public void init() {
        client = HttpClientBuilder.create().build();
    }

    @After
    public void after() throws IOException {
        client.close();
    }
}
