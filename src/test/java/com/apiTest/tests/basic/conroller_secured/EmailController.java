package com.apiTest.tests.basic.conroller_secured;

import com.apiTest.helpers.BasicAPITest;
import com.apiTest.helpers.constans.ConstantsHeaders;
import com.apiTest.helpers.constans.ConstantsMockEntity;
import com.apiTest.helpers.constans.ConstantsStrings;
import com.apiTest.helpers.constans.ConstantsUserSettings;
import com.apiTest.helpers.json.EmailJson;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import javax.security.auth.login.LoginException;
import java.io.IOException;

import static com.apiTest.helpers.constans.ConstantsUrls.ENDPOINT_EMAIL;
import static org.junit.Assert.assertEquals;

public class EmailController extends BasicAPITest implements ISecuredTest {

    @Test
    public void testSecuredEndpointAdd() throws IOException {
        postRequest = new HttpPost(ENDPOINT_EMAIL);
        postRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);

        EmailJson entityToSet = ConstantsMockEntity.EMAIL_JSON;
        entityToSet.setContactId(ConstantsUserSettings.TEST_USER_PROFILE_ID);

        StringEntity entity = new StringEntity(entityToSet.toJson());

        postRequest.setEntity(entity);
        response = client.execute(postRequest);
        String responseMessage = getJsonFromResponse(response).toString();

        LOGGER.info(responseMessage);
        Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        Assert.assertTrue(responseMessage.contains("Unauthorized"));
    }

    @Test
    public void testSecuredEndpointEdit() throws IOException {
        putRequest = new HttpPut(ENDPOINT_EMAIL);
        putRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);

        EmailJson entityToSet = ConstantsMockEntity.EMAIL_JSON;
        entityToSet.setContactId(ConstantsUserSettings.TEST_USER_PROFILE_ID);
        entityToSet.setEmailId(1);

        StringEntity entity = new StringEntity(entityToSet.toJson());

        putRequest.setEntity(entity);
        response = client.execute(putRequest);
        String responseMessage = getJsonFromResponse(response).toString();

        LOGGER.info(responseMessage);
        Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        Assert.assertTrue(responseMessage.contains("Unauthorized"));
    }

    @Test
    public void testSecuredEndpointGet() throws IOException {
        getRequest = new HttpGet(ENDPOINT_EMAIL + "/" + ConstantsUserSettings.CONTACT_ID);
        getRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        response = client.execute(getRequest);
        String responseMessage = getJsonFromResponse(response).toString();

        LOGGER.info(responseMessage);
        Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        Assert.assertTrue(responseMessage.contains("Unauthorized"));
    }

    @Test
    public void testSecuredEndpointDelete() throws IOException {
        deleteRequest = new HttpDelete(ENDPOINT_EMAIL + "/" + ConstantsUserSettings.CONTACT_ID);
        deleteRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        response = client.execute(deleteRequest);
        String responseMessage = getJsonFromResponse(response).toString();

        LOGGER.info(responseMessage);
        Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        Assert.assertTrue(responseMessage.contains("Unauthorized"));
    }

    @Test
    public void testSecuredEndpointGetAll() throws IOException {
        getRequest = new HttpGet(ENDPOINT_EMAIL + "/all");
        getRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        response = client.execute(getRequest);
        String responseMessage = getJsonFromResponse(response).toString();

        LOGGER.info(responseMessage);
        Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        Assert.assertTrue(responseMessage.contains("Unauthorized"));
    }

    @Test
    public void testSecuredEndpointAddExpiredToken() throws IOException {
        postRequest = new HttpPost(ENDPOINT_EMAIL);
        postRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        postRequest.addHeader(ConstantsHeaders.HEADER_AUTH_EXPIRED_TOKEN);


        StringEntity entity = new StringEntity(ConstantsMockEntity.EMAIL_JSON.toJson());

        postRequest.setEntity(entity);
        response = client.execute(postRequest);
        String responseMessage = getJsonFromResponse(response).toString();

        LOGGER.info(responseMessage);
        Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
    }

    @Test
    public void testSecuredEndpointEditExpiredToken() throws IOException {
        putRequest = new HttpPut(ENDPOINT_EMAIL);
        putRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        putRequest.addHeader(ConstantsHeaders.HEADER_AUTH_EXPIRED_TOKEN);

        EmailJson entityToSet = ConstantsMockEntity.EMAIL_JSON;
        entityToSet.setContactId(ConstantsUserSettings.TEST_USER_PROFILE_ID);
        StringEntity entity = new StringEntity(entityToSet.toEditJson());

        putRequest.setEntity(entity);
        response = client.execute(putRequest);
        String responseMessage = getJsonFromResponse(response).toString();

        LOGGER.info(responseMessage);
        Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
    }

    @Test
    public void testSecuredEndpointGetExpiredToken() throws IOException {
        getRequest = new HttpGet(ENDPOINT_EMAIL + "/" + ConstantsUserSettings.CONTACT_ID);
        getRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        getRequest.addHeader(ConstantsHeaders.HEADER_AUTH_EXPIRED_TOKEN);

        response = client.execute(getRequest);
        String responseMessage = getJsonFromResponse(response).toString();


        LOGGER.info(responseMessage);
        Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
    }

    @Test
    public void testSecuredEndpointDeleteExpiredToken() throws IOException {
        deleteRequest = new HttpDelete(ENDPOINT_EMAIL + "/" + ConstantsUserSettings.CONTACT_ID);
        deleteRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        deleteRequest.addHeader(ConstantsHeaders.HEADER_AUTH_EXPIRED_TOKEN);

        response = client.execute(deleteRequest);
        String responseMessage = getJsonFromResponse(response).toString();

        LOGGER.info(responseMessage);
        Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
    }


    @Test
    public void testSecuredEndpointGetAllExpiredToken() throws IOException {
        getRequest = new HttpGet(ENDPOINT_EMAIL + "/all");
        getRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        getRequest.addHeader(ConstantsHeaders.HEADER_AUTH_EXPIRED_TOKEN);

        response = client.execute(getRequest);
        String responseMessage = getJsonFromResponse(response).toString();

        LOGGER.info(responseMessage);
        Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
    }

    @Override
    @Test
    public void testSecuredEndpointAddToContact_contactNotExist_500() throws IOException, LoginException {
        response = login(ConstantsUserSettings.TEST_USER_EMAIL, ConstantsUserSettings.TEST_USER_PASS);
        authHeader = response.getFirstHeader(ConstantsStrings.AUTH_HEADER_NAME);

        postRequest = new HttpPost(ENDPOINT_EMAIL);
        postRequest.addHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        postRequest.addHeader(authHeader);

        EmailJson entityToSet = ConstantsMockEntity.EMAIL_JSON;
        entityToSet.setContactId(Integer.MAX_VALUE);
        StringEntity entity = new StringEntity(entityToSet.toJson());

        postRequest.setEntity(entity);
        response = client.execute(postRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
        assertEquals(false, jsonObject.isEmpty());
        Assert.assertEquals(ConstantsStrings.ERROR_CONTACT_NOT_IN_DB, jsonObject.getString("message"));
    }
}
