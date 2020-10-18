package com.apiTest.tests.basic.controller_tests;

import com.apiTest.helpers.BasicAPITest;
import com.apiTest.helpers.constans.ConstantsStrings;
import com.apiTest.helpers.json.UserJson;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.security.auth.login.LoginException;
import java.io.IOException;

import static com.apiTest.helpers.constans.ConstantsHeaders.HEADER_CONTENT_TYPE;
import static com.apiTest.helpers.constans.ConstantsUrls.ENDPOINT_USER;
import static com.apiTest.helpers.constans.ConstantsUserSettings.TEST_USER_EMAIL;
import static com.apiTest.helpers.constans.ConstantsUserSettings.TEST_USER_PASS;
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest extends BasicAPITest {

    private static int contactId;
    private static int emailsListSize;
    private static int emailId;

    @BeforeClass
    public static void init_addressControllerTest() throws IOException, LoginException {
        String loginUserEmail = TEST_USER_EMAIL;
        String loginUserPass = TEST_USER_PASS;

        response = login(loginUserEmail, loginUserPass);
        authHeader = response.getFirstHeader(ConstantsStrings.AUTH_HEADER_NAME);

        String userEmailResponse = getUserEmailFromJwt(authHeader);
        int responseStatusCode = response.getStatusLine().getStatusCode();


        String messageHeader = String.format("\n|%30s|%25s|%13s|\n",
                "User email", "User pass", "Auth header");
        String message = String.format("|%30s|%25s|%13b|",
                loginUserEmail, loginUserPass, (authHeader.getValue() != null));
        String separator = "\n_____________________________________________________________________________________";

        LOGGER.info("Init params"
                + separator
                + messageHeader + message
                + separator);

        assertEquals(HttpStatus.SC_OK, responseStatusCode);
        assertEquals(loginUserEmail, userEmailResponse);

        LOGGER.info("Login with user email: " + userEmailResponse);
    }

    @Test
    public void addUser() throws IOException {
        postRequest = new HttpPost(ENDPOINT_USER);
        postRequest.setHeader(HEADER_CONTENT_TYPE);
        postRequest.setHeader(authHeader);

        UserJson entityToAdd = new UserJson(TEST_USER_EMAIL, TEST_USER_PASS);

        StringEntity entity = new StringEntity(entityToAdd.toJson());
        postRequest.setEntity(entity);

        LOGGER.info("Entity body: " + EntityUtils.toString(entity));

        response = client.execute(postRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
        Assert.assertEquals(ConstantsStrings.ERROR_USER_ALREADY_EXIST, jsonObject.getString("message"));
    }

    @Test
    public void activateUser() throws IOException {

    }

    @Test
    public void changePassword() throws IOException {

    }

    @Test
    public void getUser() throws IOException {

    }

    @Test
    public void changePasswordAuth() throws IOException {

    }

}
