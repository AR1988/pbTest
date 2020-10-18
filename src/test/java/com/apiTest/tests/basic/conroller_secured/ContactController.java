package com.apiTest.tests.basic.conroller_secured;

import com.apiTest.helpers.BasicAPITest;
import com.apiTest.helpers.constans.ConstantsHeaders;
import com.apiTest.helpers.constans.ConstantsUserSettings;
import com.apiTest.helpers.json.ContactJson;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import static com.apiTest.helpers.constans.ConstantsUrls.ENDPOINT_CONTACT;
import static com.apiTest.helpers.constans.ConstantsUrls.ENDPOINT_PROFILE;
import static org.junit.Assert.assertEquals;

public class ContactController extends BasicAPITest {

    @Test
    public void testSecuredEndpointAddContact() throws IOException {
        postRequest = new HttpPost(ENDPOINT_CONTACT);
        postRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);

        ContactJson entityToSet = new ContactJson(faker.name().firstName(), faker.name().lastName(), faker.hobbit().quote());
        StringEntity entity = new StringEntity(entityToSet.toJson());

        postRequest.setEntity(entity);
        response = client.execute(postRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        assertEquals("Unauthenticated", jsonObject.getString("message"));
    }

    @Test
    public void testSecuredEndpointEditContact() throws IOException {
        putRequest = new HttpPut(ENDPOINT_CONTACT);
        putRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);

        ContactJson entityToSet = new ContactJson(faker.name().firstName(), faker.name().lastName(), faker.hobbit().quote());
        StringEntity entity = new StringEntity(entityToSet.toJson());

        putRequest.setEntity(entity);
        response = client.execute(putRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        assertEquals("Unauthenticated", jsonObject.getString("message"));
    }

    @Test
    public void testSecuredEndpointGetContact() throws IOException {
        getRequest = new HttpGet(ENDPOINT_CONTACT + "/" + ConstantsUserSettings.CONTACT_ID);
        getRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        response = client.execute(getRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
//        assertEquals("Unauthenticated", jsonObject.getString("message"));
    }

    @Test
    public void testSecuredEndpointDeleteContact() throws IOException {
        deleteRequest = new HttpDelete(ENDPOINT_CONTACT + "/" + ConstantsUserSettings.CONTACT_ID);
        deleteRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        response = client.execute(deleteRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        assertEquals("Unauthenticated", jsonObject.getString("message"));
    }

    @Test
    public void testSecuredEndpointGetAllContacts() throws IOException {
        getRequest = new HttpGet(ENDPOINT_CONTACT + "/all");
        getRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        response = client.execute(getRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        assertEquals("Unauthenticated", jsonObject.getString("message"));
    }

    @Test
    public void testSecuredEndpointGetProfile() throws IOException {
        getRequest = new HttpGet(ENDPOINT_PROFILE);
        getRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        response = client.execute(getRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());
        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        assertEquals("Unauthenticated", jsonObject.getString("message"));
    }

    @Test
    public void testSecuredEndpointEditProfile() throws IOException {
        putRequest = new HttpPut(ENDPOINT_PROFILE);
        putRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);

        ContactJson entityToSet = new ContactJson(faker.name().firstName(), faker.name().lastName(), faker.hobbit().quote());
        StringEntity entity = new StringEntity(entityToSet.toJson());

        putRequest.setEntity(entity);
        response = client.execute(putRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        assertEquals("Unauthenticated", jsonObject.getString("message"));
    }


    @Test
    public void testSecuredEndpointAddContactExpiredToken() throws IOException {
        postRequest = new HttpPost(ENDPOINT_CONTACT);
        postRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        postRequest.addHeader(ConstantsHeaders.HEADER_AUTH_EXPIRED_TOKEN);

        ContactJson entityToSet = new ContactJson(faker.name().firstName(), faker.name().lastName(), faker.hobbit().quote());
        StringEntity entity = new StringEntity(entityToSet.toJson());

        postRequest.setEntity(entity);
        response = client.execute(postRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());
        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        assertEquals("Unauthenticated", jsonObject.getString("message"));
    }

    @Test
    public void testSecuredEndpointEditContactExpiredToken() throws IOException {
        putRequest = new HttpPut(ENDPOINT_CONTACT);
        putRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        putRequest.addHeader(ConstantsHeaders.HEADER_AUTH_EXPIRED_TOKEN);

        ContactJson entityToSet = new ContactJson(faker.name().firstName(), faker.name().lastName(), faker.hobbit().quote());
        StringEntity entity = new StringEntity(entityToSet.toJson());

        putRequest.setEntity(entity);
        response = client.execute(putRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        assertEquals("Unauthenticated", jsonObject.getString("message"));
    }

    @Test
    public void testSecuredEndpointGetContactExpiredToken() throws IOException {
        getRequest = new HttpGet(ENDPOINT_CONTACT + "/" + ConstantsUserSettings.CONTACT_ID);
        getRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        getRequest.addHeader(ConstantsHeaders.HEADER_AUTH_EXPIRED_TOKEN);

        response = client.execute(getRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        assertEquals("Unauthenticated", jsonObject.getString("message"));
    }

    @Test
    public void testSecuredEndpointDeleteContactExpiredTokens() throws IOException {
        deleteRequest = new HttpDelete(ENDPOINT_CONTACT + "/" + ConstantsUserSettings.CONTACT_ID);
        deleteRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        deleteRequest.addHeader(ConstantsHeaders.HEADER_AUTH_EXPIRED_TOKEN);

        response = client.execute(deleteRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        assertEquals("Unauthenticated", jsonObject.getString("message"));
    }


    @Test
    public void testSecuredEndpointGetAllContactExpiredTokens() throws IOException {
        getRequest = new HttpGet(ENDPOINT_CONTACT + "/all");
        getRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        getRequest.addHeader(ConstantsHeaders.HEADER_AUTH_EXPIRED_TOKEN);

        response = client.execute(getRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        assertEquals("Unauthenticated", jsonObject.getString("message"));
    }

    @Test
    public void testSecuredEndpointGetProfileExpiredTokens() throws IOException {
        getRequest = new HttpGet(ENDPOINT_PROFILE);
        getRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        getRequest.addHeader(ConstantsHeaders.HEADER_AUTH_EXPIRED_TOKEN);

        response = client.execute(getRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        assertEquals("Unauthenticated", jsonObject.getString("message"));
    }

    @Test
    public void testSecuredEndpointEditProfileExpiredTokens() throws IOException {
        putRequest = new HttpPut(ENDPOINT_PROFILE);
        putRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        putRequest.addHeader(ConstantsHeaders.HEADER_AUTH_EXPIRED_TOKEN);

        ContactJson entityToSet = new ContactJson(faker.name().firstName(), faker.name().lastName(), faker.hobbit().quote());
        StringEntity entity = new StringEntity(entityToSet.toJson());

        putRequest.setEntity(entity);
        response = client.execute(putRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        assertEquals("Unauthenticated", jsonObject.getString("message"));
    }
}
