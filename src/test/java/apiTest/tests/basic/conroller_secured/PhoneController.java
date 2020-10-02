package apiTest.tests.basic.conroller_secured;

import apiTest.helpers.BasicAPITest;
import apiTest.helpers.json.PhoneJson;
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
import static apiTest.helpers.constans.ConstantsUrls.ENDPOINT_PHONE;
import static apiTest.helpers.constans.ConstantsUserSettings.CONTACT_ID;

public class PhoneController extends BasicAPITest implements ISecuredTest {


    @Test
    public void testSecuredEndpointAdd() throws IOException {
        postRequest = new HttpPost(ENDPOINT_PHONE);
        postRequest.addHeader(HEADER_CONTENT_TYPE);

        PhoneJson entityToSet = new PhoneJson(2, "007", "123456789");
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
        putRequest = new HttpPut(ENDPOINT_PHONE);
        putRequest.addHeader(HEADER_CONTENT_TYPE);

        PhoneJson entityToSet = new PhoneJson(2, "007", "123456789");
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
        getRequest = new HttpGet(ENDPOINT_PHONE + "/" + CONTACT_ID);
        getRequest.addHeader(HEADER_CONTENT_TYPE);
        response = client.execute(getRequest);
        String responseMessage = getJsonFromResponse(response).toString();
        LOGGER.info(responseMessage);

        Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        Assert.assertTrue(responseMessage.contains("Unauthorized"));
    }

    @Test
    public void testSecuredEndpointDelete() throws IOException {
        deleteRequest = new HttpDelete(ENDPOINT_PHONE + "/" + CONTACT_ID);
        deleteRequest.addHeader(HEADER_CONTENT_TYPE);
        response = client.execute(deleteRequest);
        String responseMessage = getJsonFromResponse(response).toString();
        LOGGER.info(responseMessage);

        Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
        Assert.assertTrue(responseMessage.contains("Unauthorized"));
    }


    @Test
    public void testSecuredEndpointGetAll() throws IOException {
        getRequest = new HttpGet(ENDPOINT_PHONE + "/all");
        getRequest.addHeader(HEADER_CONTENT_TYPE);
        response = client.execute(getRequest);
        String responseMessage = getJsonFromResponse(response).toString();
        LOGGER.info(responseMessage);

        Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
    }

    @Test
    public void testSecuredEndpointAddExpiredToken() throws IOException {
        postRequest = new HttpPost(ENDPOINT_PHONE);
        postRequest.addHeader(HEADER_CONTENT_TYPE);
        postRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);


        PhoneJson entityToSet = new PhoneJson(CONTACT_ID, "007", "123456789");
        StringEntity entity = new StringEntity(entityToSet.toJson());

        postRequest.setEntity(entity);
        response = client.execute(postRequest);
        String responseMessage = getJsonFromResponse(response).toString();
        LOGGER.info(responseMessage);

        Assert.assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
    }

    @Test
    public void testSecuredEndpointEditExpiredToken() throws IOException {
        putRequest = new HttpPut(ENDPOINT_PHONE);
        putRequest.addHeader(HEADER_CONTENT_TYPE);
        putRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        PhoneJson entityToSet = new PhoneJson(CONTACT_ID, "007", "123456789");
        StringEntity entity = new StringEntity(entityToSet.toJson());

        putRequest.setEntity(entity);
        response = client.execute(putRequest);
        String responseMessage = getJsonFromResponse(response).toString();
        LOGGER.info(responseMessage);

        Assert.assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
    }

    @Test
    public void testSecuredEndpointGetExpiredToken() throws IOException {
        getRequest = new HttpGet(ENDPOINT_PHONE + "/" + CONTACT_ID);
        getRequest.addHeader(HEADER_CONTENT_TYPE);
        getRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        response = client.execute(getRequest);
        String responseMessage = getJsonFromResponse(response).toString();
        LOGGER.info(responseMessage);

        Assert.assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
    }

    @Test
    public void testSecuredEndpointDeleteExpiredToken() throws IOException {
        deleteRequest = new HttpDelete(ENDPOINT_PHONE + "/" + CONTACT_ID);
        deleteRequest.addHeader(HEADER_CONTENT_TYPE);
        deleteRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        response = client.execute(deleteRequest);
        String responseMessage = getJsonFromResponse(response).toString();
        LOGGER.info(responseMessage);

        Assert.assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
    }


    @Test
    public void testSecuredEndpointGetAllExpiredToken() throws IOException {
        getRequest = new HttpGet(ENDPOINT_PHONE + "/all");
        getRequest.addHeader(HEADER_CONTENT_TYPE);
        getRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        response = client.execute(getRequest);
        String responseMessage = getJsonFromResponse(response).toString();
        LOGGER.info(responseMessage);

        Assert.assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
    }
}
