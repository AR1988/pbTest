package apiTest.tests.basic.conroller_secured;

import apiTest.helpers.BasicAPITest;
import apiTest.helpers.json.NewPasswordAuthJson;
import apiTest.helpers.json.UserEmailJson;
import apiTest.helpers.json.UserJson;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import static apiTest.helpers.constans.ConstantsHeaders.HEADER_AUTH_EXPIRED_TOKEN;
import static apiTest.helpers.constans.ConstantsHeaders.HEADER_CONTENT_TYPE;
import static apiTest.helpers.constans.ConstantsStrings.ERROR_USER_ALREADY_EXIST;
import static apiTest.helpers.constans.ConstantsUrls.*;
import static apiTest.helpers.constans.ConstantsUserSettings.TEST_USER_EMAIL;
import static apiTest.helpers.constans.ConstantsUserSettings.TEST_USER_PASS;
import static org.junit.Assert.assertEquals;

public class UserController extends BasicAPITest {

    @Test
    public void testSecuredEndpoint_changePassword_401() throws IOException {
        putRequest = new HttpPut(ENDPOINT_USER_UPDATE_PASS_AUTH_USER);
        putRequest.addHeader(HEADER_CONTENT_TYPE);

        NewPasswordAuthJson entityToSet = new NewPasswordAuthJson("NewPassword");
        StringEntity entity = new StringEntity(entityToSet.toJson());

        putRequest.setEntity(entity);
        response = client.execute(putRequest);

        assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatusLine().getStatusCode());
    }

    @Test
    public void testSecuredEndpoint_changePasswordExpiredToken_500() throws IOException {
        putRequest = new HttpPut(ENDPOINT_USER_UPDATE_PASS_AUTH_USER);
        putRequest.addHeader(HEADER_CONTENT_TYPE);
        putRequest.addHeader(HEADER_AUTH_EXPIRED_TOKEN);

        NewPasswordAuthJson entityToSet = new NewPasswordAuthJson("NewPassword");
        StringEntity entity = new StringEntity(entityToSet.toJson());

        putRequest.setEntity(entity);
        response = client.execute(putRequest);

        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusLine().getStatusCode());
    }

    @Test
    public void testAddUserJson_caseInsensitive_400() throws IOException {
        postRequest = new HttpPost(ENDPOINT_USER);
        postRequest.addHeader(HEADER_CONTENT_TYPE);

        UserJson entityToSet = new UserJson(TEST_USER_EMAIL.toUpperCase(), TEST_USER_PASS);
        StringEntity entity = new StringEntity(entityToSet.toJson());

        postRequest.setEntity(entity);
        response = client.execute(postRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
        assertEquals(ERROR_USER_ALREADY_EXIST, jsonObject.getString("message"));
    }

    @Test
    public void testChangePasswordRequest_userCaseInsensitive_ok() throws IOException {
        postRequest = new HttpPost(ENDPOINT_USER_RECOVERY_PASS);
        postRequest.addHeader(HEADER_CONTENT_TYPE);

        UserEmailJson entityToSet = new UserEmailJson(TEST_USER_EMAIL.toUpperCase());
        StringEntity entity = new StringEntity(entityToSet.toJson());

        postRequest.setEntity(entity);
        response = client.execute(postRequest);

        JSONObject responseMessage = getJsonFromResponse(response);
        LOGGER.info(responseMessage.toString());

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }
}
