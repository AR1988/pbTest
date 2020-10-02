package apiTest.tests.basic.conroller_secured;

import apiTest.helpers.BasicAPITest;
import apiTest.helpers.json.AddressJson;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import org.junit.Test;

import javax.security.auth.login.LoginException;
import java.io.IOException;

import static apiTest.helpers.constans.ConstantsHeaders.HEADER_AUTH_EXPIRED_TOKEN;
import static apiTest.helpers.constans.ConstantsHeaders.HEADER_CONTENT_TYPE;
import static apiTest.helpers.constans.ConstantsMockEntity.ADDRESS_JSON;
import static apiTest.helpers.constans.ConstantsStrings.AUTH_HEADER_NAME;
import static apiTest.helpers.constans.ConstantsStrings.ERROR_CONTACT_NOT_IN_DB;
import static apiTest.helpers.constans.ConstantsUrls.*;
import static apiTest.helpers.constans.ConstantsUserSettings.*;
import static org.junit.Assert.assertEquals;

public class AddressController extends BasicAPITest implements ISecuredTestContact {

    @Override
    @Test
    public void testSecuredEndpointAdd() throws IOException {
        postRequest = new HttpPost(ENDPOINT_ADDRESS);
        postRequest.addHeader(HEADER_CONTENT_TYPE);

        AddressJson entityToSet = ADDRESS_JSON;
        entityToSet.setContactId(CONTACT_ID);
        StringEntity entity = new StringEntity(entityToSet.toJson());

        postRequest.setEntity(entity);
        response = client.execute(postRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
    }

    @Override
    @Test
    public void testSecuredEndpointEdit() throws IOException {
        putRequest = new HttpPut(ENDPOINT_ADDRESS);
        putRequest.addHeader(HEADER_CONTENT_TYPE);

        AddressJson entityToSet = ADDRESS_JSON;
        entityToSet.setContactId(CONTACT_ID);
        StringEntity entity = new StringEntity(entityToSet.toJson());

        putRequest.setEntity(entity);
        response = client.execute(putRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
    }

    @Override
    @Test
    public void testSecuredEndpointGet() throws IOException {
        getRequest = new HttpGet(ENDPOINT_ADDRESS + "/" + CONTACT_ID);
        getRequest.addHeader(HEADER_CONTENT_TYPE);

        response = client.execute(getRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
    }

    @Override
    @Test
    public void testSecuredEndpointDelete() throws IOException {
        deleteRequest = new HttpDelete(ENDPOINT_ADDRESS + "/" + CONTACT_ID);
        deleteRequest.addHeader(HEADER_CONTENT_TYPE);

        response = client.execute(deleteRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
    }

    @Override
    @Test
    public void testSecuredEndpointGetAll() throws IOException {
        getRequest = new HttpGet(ENDPOINT_ADDRESS + "/all");
        getRequest.addHeader(HEADER_CONTENT_TYPE);

        response = client.execute(getRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
    }

    @Override
    @Test
    public void testSecuredEndpointAddExpiredToken() throws IOException {
        postRequest = new HttpPost(ENDPOINT_CONTACT);
        postRequest.addHeader(HEADER_CONTENT_TYPE);
        postRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        AddressJson entityToSet = ADDRESS_JSON;
        entityToSet.setContactId(CONTACT_ID);
        StringEntity entity = new StringEntity(entityToSet.toJson());

        postRequest.setEntity(entity);
        response = client.execute(postRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
    }

    @Override
    @Test
    public void testSecuredEndpointEditExpiredToken() throws IOException {
        putRequest = new HttpPut(ENDPOINT_CONTACT);
        putRequest.addHeader(HEADER_CONTENT_TYPE);
        putRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        AddressJson entityToSet = ADDRESS_JSON;
        entityToSet.setContactId(CONTACT_ID);
        StringEntity entity = new StringEntity(entityToSet.toJson());

        putRequest.setEntity(entity);
        response = client.execute(putRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
    }

    @Override
    @Test
    public void testSecuredEndpointGetExpiredToken() throws IOException {
        getRequest = new HttpGet(ENDPOINT_CONTACT + "/" + CONTACT_ID);
        getRequest.addHeader(HEADER_CONTENT_TYPE);
        getRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        response = client.execute(getRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
    }

    @Override
    @Test
    public void testSecuredEndpointDeleteExpiredTokens() throws IOException {
        deleteRequest = new HttpDelete(ENDPOINT_CONTACT + "/" + CONTACT_ID);
        deleteRequest.addHeader(HEADER_CONTENT_TYPE);
        deleteRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        response = client.execute(deleteRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
    }


    @Override
    @Test
    public void testSecuredEndpointGetAllExpiredTokens() throws IOException {
        getRequest = new HttpGet(ENDPOINT_CONTACT + "/all");
        getRequest.addHeader(HEADER_CONTENT_TYPE);
        getRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        response = client.execute(getRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
    }

    @Override
    @Test
    public void testSecuredEndpointGetProfileExpiredTokens() throws IOException {
        getRequest = new HttpGet(ENDPOINT_PROFILE);
        getRequest.addHeader(HEADER_CONTENT_TYPE);
        getRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        response = client.execute(getRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
    }

    @Override
    @Test
    public void testSecuredEndpointEditProfileExpiredTokens() throws IOException {
        putRequest = new HttpPut(ENDPOINT_PROFILE);
        putRequest.addHeader(HEADER_CONTENT_TYPE);
        putRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        AddressJson entityToSet = ADDRESS_JSON;
        entityToSet.setContactId(CONTACT_ID);
        StringEntity entity = new StringEntity(entityToSet.toJson());

        putRequest.setEntity(entity);
        response = client.execute(putRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
    }

    @Override
    @Test
    public void testSecuredEndpointAddToContact_contactNotExist_500() throws IOException, LoginException {
        response = login(TEST_USER_EMAIL, TEST_USER_PASS);
        authHeader = response.getFirstHeader(AUTH_HEADER_NAME);

        postRequest = new HttpPost(ENDPOINT_ADDRESS);
        postRequest.addHeader(HEADER_CONTENT_TYPE);
        postRequest.addHeader(authHeader);

        AddressJson entityToSet = ADDRESS_JSON;
        entityToSet.setContactId(Integer.MAX_VALUE);
        StringEntity entity = new StringEntity(entityToSet.toJson());

        postRequest.setEntity(entity);
        response = client.execute(postRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
        assertEquals(false, jsonObject.isEmpty());
        assertEquals(ERROR_CONTACT_NOT_IN_DB, jsonObject.getString("message"));
    }
}
