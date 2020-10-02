package apiTest.tests.basic.conroller_secured;

import apiTest.helpers.BasicAPITest;
import apiTest.helpers.json.ContactJson;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import static apiTest.helpers.constans.ConstantsHeaders.HEADER_AUTH_EXPIRED_TOKEN;
import static apiTest.helpers.constans.ConstantsHeaders.HEADER_CONTENT_TYPE;
import static apiTest.helpers.constans.ConstantsUrls.ENDPOINT_CONTACT;
import static apiTest.helpers.constans.ConstantsUrls.ENDPOINT_PROFILE;
import static apiTest.helpers.constans.ConstantsUserSettings.CONTACT_ID;
import static org.junit.Assert.assertEquals;

public class ContactController extends BasicAPITest {

    @Test
    public void testSecuredEndpointAddContact() throws IOException {
        postRequest = new HttpPost(ENDPOINT_CONTACT);
        postRequest.addHeader(HEADER_CONTENT_TYPE);

        ContactJson entityToSet = new ContactJson(faker.name().firstName(), faker.name().lastName(), faker.hobbit().quote());
        StringEntity entity = new StringEntity(entityToSet.toJson());

        postRequest.setEntity(entity);
        response = client.execute(postRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        //assertTrue(jsonObject.getString("trace").contains(ERROR_JWT_EXPIRED_MESSAGE));
    }

    @Test
    public void testSecuredEndpointEditContact() throws IOException {
        putRequest = new HttpPut(ENDPOINT_CONTACT);
        putRequest.addHeader(HEADER_CONTENT_TYPE);

        ContactJson entityToSet = new ContactJson(faker.name().firstName(), faker.name().lastName(), faker.hobbit().quote());
        StringEntity entity = new StringEntity(entityToSet.toJson());

        putRequest.setEntity(entity);
        response = client.execute(putRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        //assertTrue(jsonObject.getString("trace").contains(ERROR_JWT_EXPIRED_MESSAGE));
    }

    @Test
    public void testSecuredEndpointGetContact() throws IOException {
        getRequest = new HttpGet(ENDPOINT_CONTACT + "/" + CONTACT_ID);
        getRequest.addHeader(HEADER_CONTENT_TYPE);
        response = client.execute(getRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
//        //assertTrue(jsonObject.getString("trace").contains(ERROR_JWT_EXPIRED_MESSAGE));
    }

    @Test
    public void testSecuredEndpointDeleteContact() throws IOException {
        deleteRequest = new HttpDelete(ENDPOINT_CONTACT + "/" + CONTACT_ID);
        deleteRequest.addHeader(HEADER_CONTENT_TYPE);
        response = client.execute(deleteRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        //assertTrue(jsonObject.getString("trace").contains(ERROR_JWT_EXPIRED_MESSAGE));
    }

    @Test
    public void testSecuredEndpointGetAllContacts() throws IOException {
        getRequest = new HttpGet(ENDPOINT_CONTACT + "/all");
        getRequest.addHeader(HEADER_CONTENT_TYPE);
        response = client.execute(getRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        //assertTrue(jsonObject.getString("trace").contains(ERROR_JWT_EXPIRED_MESSAGE));
    }

    @Test
    public void testSecuredEndpointGetProfile() throws IOException {
        getRequest = new HttpGet(ENDPOINT_PROFILE);
        getRequest.addHeader(HEADER_CONTENT_TYPE);
        response = client.execute(getRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());
        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        //assertTrue(jsonObject.getString("trace").contains(ERROR_JWT_EXPIRED_MESSAGE));
    }

    @Test
    public void testSecuredEndpointEditProfile() throws IOException {
        putRequest = new HttpPut(ENDPOINT_PROFILE);
        putRequest.addHeader(HEADER_CONTENT_TYPE);

        ContactJson entityToSet = new ContactJson(faker.name().firstName(), faker.name().lastName(), faker.hobbit().quote());
        StringEntity entity = new StringEntity(entityToSet.toJson());

        putRequest.setEntity(entity);
        response = client.execute(putRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        //assertTrue(jsonObject.getString("trace").contains(ERROR_JWT_EXPIRED_MESSAGE));
    }


    @Test
    public void testSecuredEndpointAddContactExpiredToken() throws IOException {
        postRequest = new HttpPost(ENDPOINT_CONTACT);
        postRequest.addHeader(HEADER_CONTENT_TYPE);
        postRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        ContactJson entityToSet = new ContactJson(faker.name().firstName(), faker.name().lastName(), faker.hobbit().quote());
        StringEntity entity = new StringEntity(entityToSet.toJson());

        postRequest.setEntity(entity);
        response = client.execute(postRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());
        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
        //assertTrue(jsonObject.getString("trace").contains(ERROR_JWT_EXPIRED_MESSAGE));
    }

    @Test
    public void testSecuredEndpointEditContactExpiredToken() throws IOException {
        putRequest = new HttpPut(ENDPOINT_CONTACT);
        putRequest.addHeader(HEADER_CONTENT_TYPE);
        putRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        ContactJson entityToSet = new ContactJson(faker.name().firstName(), faker.name().lastName(), faker.hobbit().quote());
        StringEntity entity = new StringEntity(entityToSet.toJson());

        putRequest.setEntity(entity);
        response = client.execute(putRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
        //assertTrue(jsonObject.getString("trace").contains(ERROR_JWT_EXPIRED_MESSAGE));
    }

    @Test
    public void testSecuredEndpointGetContactExpiredToken() throws IOException {
        getRequest = new HttpGet(ENDPOINT_CONTACT + "/" + CONTACT_ID);
        getRequest.addHeader(HEADER_CONTENT_TYPE);
        getRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        response = client.execute(getRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
        //assertTrue(jsonObject.getString("trace").contains(ERROR_JWT_EXPIRED_MESSAGE));
    }

    @Test
    public void testSecuredEndpointDeleteContactExpiredTokens() throws IOException {
        deleteRequest = new HttpDelete(ENDPOINT_CONTACT + "/" + CONTACT_ID);
        deleteRequest.addHeader(HEADER_CONTENT_TYPE);
        deleteRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        response = client.execute(deleteRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
        //assertTrue(jsonObject.getString("trace").contains(ERROR_JWT_EXPIRED_MESSAGE));
    }


    @Test
    public void testSecuredEndpointGetAllContactExpiredTokens() throws IOException {
        getRequest = new HttpGet(ENDPOINT_CONTACT + "/all");
        getRequest.addHeader(HEADER_CONTENT_TYPE);
        getRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        response = client.execute(getRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
        //assertTrue(jsonObject.getString("trace").contains(ERROR_JWT_EXPIRED_MESSAGE));
    }

    @Test
    public void testSecuredEndpointGetProfileExpiredTokens() throws IOException {
        getRequest = new HttpGet(ENDPOINT_PROFILE);
        getRequest.addHeader(HEADER_CONTENT_TYPE);
        getRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        response = client.execute(getRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
        //assertTrue(jsonObject.getString("trace").contains(ERROR_JWT_EXPIRED_MESSAGE));
    }

    @Test
    public void testSecuredEndpointEditProfileExpiredTokens() throws IOException {
        putRequest = new HttpPut(ENDPOINT_PROFILE);
        putRequest.addHeader(HEADER_CONTENT_TYPE);
        putRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        ContactJson entityToSet = new ContactJson(faker.name().firstName(), faker.name().lastName(), faker.hobbit().quote());
        StringEntity entity = new StringEntity(entityToSet.toJson());

        putRequest.setEntity(entity);
        response = client.execute(putRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
        //assertTrue(jsonObject.getString("trace").contains(ERROR_JWT_EXPIRED_MESSAGE));
    }
}
