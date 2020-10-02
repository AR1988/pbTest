package apiTest.tests.basic.conroller_secured;

import apiTest.helpers.BasicAPITest;
import apiTest.helpers.json.EmailJson;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static apiTest.helpers.constans.ConstantsHeaders.HEADER_AUTH_EXPIRED_TOKEN;
import static apiTest.helpers.constans.ConstantsHeaders.HEADER_CONTENT_TYPE;
import static apiTest.helpers.constans.ConstantsMockEntity.EMAIL_JSON;
import static apiTest.helpers.constans.ConstantsUrls.ENDPOINT_EMAIL;
import static apiTest.helpers.constans.ConstantsUserSettings.CONTACT_ID;
import static apiTest.helpers.constans.ConstantsUserSettings.TEST_USER_PROFILE_ID;

public class EmailController extends BasicAPITest implements ISecuredTest {

    @Test
    public void testSecuredEndpointAdd() throws IOException {
        postRequest = new HttpPost(ENDPOINT_EMAIL);
        postRequest.addHeader(HEADER_CONTENT_TYPE);

        EmailJson entityToSet = EMAIL_JSON;
        entityToSet.setContactId(TEST_USER_PROFILE_ID);

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
        putRequest.addHeader(HEADER_CONTENT_TYPE);

        EmailJson entityToSet = EMAIL_JSON;
        entityToSet.setContactId(TEST_USER_PROFILE_ID);
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
        getRequest = new HttpGet(ENDPOINT_EMAIL + "/" + CONTACT_ID);
        getRequest.addHeader(HEADER_CONTENT_TYPE);
        response = client.execute(getRequest);
        String responseMessage = getJsonFromResponse(response).toString();

        LOGGER.info(responseMessage);
        Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        Assert.assertTrue(responseMessage.contains("Unauthorized"));
    }

    @Test
    public void testSecuredEndpointDelete() throws IOException {
        deleteRequest = new HttpDelete(ENDPOINT_EMAIL + "/" + CONTACT_ID);
        deleteRequest.addHeader(HEADER_CONTENT_TYPE);
        response = client.execute(deleteRequest);
        String responseMessage = getJsonFromResponse(response).toString();

        LOGGER.info(responseMessage);
        Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        Assert.assertTrue(responseMessage.contains("Unauthorized"));
    }

    @Test
    public void testSecuredEndpointGetAll() throws IOException {
        getRequest = new HttpGet(ENDPOINT_EMAIL + "/all");
        getRequest.addHeader(HEADER_CONTENT_TYPE);
        response = client.execute(getRequest);
        String responseMessage = getJsonFromResponse(response).toString();

        LOGGER.info(responseMessage);
        Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        Assert.assertTrue(responseMessage.contains("Unauthorized"));
    }

    @Test
    public void testSecuredEndpointAddExpiredToken() throws IOException {
        postRequest = new HttpPost(ENDPOINT_EMAIL);
        postRequest.addHeader(HEADER_CONTENT_TYPE);
        postRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);


        StringEntity entity = new StringEntity(EMAIL_JSON.toJson());

        postRequest.setEntity(entity);
        response = client.execute(postRequest);
        String responseMessage = getJsonFromResponse(response).toString();

        LOGGER.info(responseMessage);
        Assert.assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
    }

    @Test
    public void testSecuredEndpointEditExpiredToken() throws IOException {
        putRequest = new HttpPut(ENDPOINT_EMAIL);
        putRequest.addHeader(HEADER_CONTENT_TYPE);
        putRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        EmailJson entityToSet = EMAIL_JSON;
        entityToSet.setContactId(TEST_USER_PROFILE_ID);
        StringEntity entity = new StringEntity(entityToSet.toEditJson());

        putRequest.setEntity(entity);
        response = client.execute(putRequest);
        String responseMessage = getJsonFromResponse(response).toString();

        LOGGER.info(responseMessage);
        Assert.assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
    }

    @Test
    public void testSecuredEndpointGetExpiredToken() throws IOException {
        getRequest = new HttpGet(ENDPOINT_EMAIL + "/" + CONTACT_ID);
        getRequest.addHeader(HEADER_CONTENT_TYPE);
        getRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        response = client.execute(getRequest);
        String responseMessage = getJsonFromResponse(response).toString();


        LOGGER.info(responseMessage);
        Assert.assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
    }

    @Test
    public void testSecuredEndpointDeleteExpiredToken() throws IOException {
        deleteRequest = new HttpDelete(ENDPOINT_EMAIL + "/" + CONTACT_ID);
        deleteRequest.addHeader(HEADER_CONTENT_TYPE);
        deleteRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        response = client.execute(deleteRequest);
        String responseMessage = getJsonFromResponse(response).toString();

        LOGGER.info(responseMessage);
        Assert.assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
    }


    @Test
    public void testSecuredEndpointGetAllExpiredToken() throws IOException {
        getRequest = new HttpGet(ENDPOINT_EMAIL + "/all");
        getRequest.addHeader(HEADER_CONTENT_TYPE);
        getRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        response = client.execute(getRequest);
        String responseMessage = getJsonFromResponse(response).toString();

        LOGGER.info(responseMessage);
        Assert.assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
    }
}
