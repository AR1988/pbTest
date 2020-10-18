package com.apiTest.tests.basic.controller_tests;

import com.apiTest.helpers.BasicAPITest;
import com.apiTest.helpers.constans.ConstantsHeaders;
import com.apiTest.helpers.constans.ConstantsMockEntity;
import com.apiTest.helpers.constans.ConstantsStrings;
import com.apiTest.helpers.constans.ConstantsUserSettings;
import com.apiTest.helpers.json.PhoneJson;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.security.auth.login.LoginException;
import java.io.IOException;

import static com.apiTest.helpers.constans.ConstantsUrls.ENDPOINT_PHONE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PhoneControllerTest extends BasicAPITest implements IControllerTest {

    private static int contactId;
    private static int phonesListSize;
    private static int phoneId;

    @BeforeClass
    public static void init_addressControllerTest() throws IOException, LoginException {
        String loginUserEmail = ConstantsUserSettings.TEST_USER_EMAIL;
        String loginUserPass = ConstantsUserSettings.TEST_USER_PASS;

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

        contactId = addContactAndGetId(ConstantsMockEntity.CONTACT_JSON, authHeader);

        LOGGER.info("Contact id: " + contactId);
    }

    @Override
    @Test
    public void test001_add_200() throws IOException {
        postRequest = new HttpPost(ENDPOINT_PHONE);
        postRequest.setHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        postRequest.setHeader(authHeader);

        PhoneJson entityToAdd = ConstantsMockEntity.PHONE_JSON;
        entityToAdd.setContactId(contactId);

        StringEntity entity = new StringEntity(entityToAdd.toJson());
        postRequest.setEntity(entity);
        response = client.execute(postRequest);

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        LOGGER.info("Entity body: " + EntityUtils.toString(entity));
    }

    @Override
    @Test
    public void test002_getList_200() throws IOException {
        getRequest = new HttpGet(ENDPOINT_PHONE + "/" + contactId + "/all");
        getRequest.setHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        getRequest.setHeader(authHeader);

        response = client.execute(getRequest);
        int responseStatusCode = response.getStatusLine().getStatusCode();

        if (responseStatusCode != 200) {
            JSONObject jsonObject = getJsonFromResponse(response);
            LOGGER.info(jsonObject.toString());
        }

        assertEquals(HttpStatus.SC_OK, responseStatusCode);

        JSONArray jsonArray = getJsonArrayFromResponse(response);

        phonesListSize = jsonArray.length();
        JSONObject lastElementInList = jsonArray.getJSONObject(phonesListSize - 1);
        phoneId = lastElementInList.getInt("id");

        LOGGER.info("Result:"
                + "\nContact id is: " + contactId
                + "\nPhone id is: " + phoneId
                + "\nList size: " + phonesListSize
                + "\nLast element in list:\n" + lastElementInList.toString());
    }

    @Override
    @Test
    public void test003_getById_200() throws IOException {
        getRequest = new HttpGet(ENDPOINT_PHONE + "/" + phoneId);
        getRequest.setHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        getRequest.setHeader(authHeader);

        response = client.execute(getRequest);

        int responseStatusCode = response.getStatusLine().getStatusCode();
        JSONObject jsonObj = getJsonFromResponse(response);

        LOGGER.info("Response obj: " + jsonObj.toString());

        assertEquals(HttpStatus.SC_OK, responseStatusCode);
        //TODO if ids added enable this
        //assertEquals(PHONE_JSON.getContactId(), jsonObj.getInt("id"));
        assertEquals(ConstantsMockEntity.PHONE_JSON.getCountryCode(), jsonObj.getString("countryCode"));
        assertEquals(ConstantsMockEntity.PHONE_JSON.getPhoneNumber(), jsonObj.getString("phoneNumber"));
    }

    @Override
    @Test
    public void test004_editItem_200() throws IOException {
        putRequest = new HttpPut(ENDPOINT_PHONE);
        putRequest.setHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        putRequest.setHeader(authHeader);

        PhoneJson jsonToEditEntity = ConstantsMockEntity.PHONE_EDIT_JSON;
        jsonToEditEntity.setContactId(contactId);
        jsonToEditEntity.setPhoneId(phoneId);

        StringEntity entity = new StringEntity(jsonToEditEntity.toEditJson());
        putRequest.setEntity(entity);
        response = client.execute(putRequest);

        LOGGER.info("Entity body: " + EntityUtils.toString(entity));

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    @Override
    @Test
    public void test005_getChangedItemById_200() throws IOException {
        getRequest = new HttpGet(ENDPOINT_PHONE + "/" + phoneId);
        getRequest.setHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        getRequest.setHeader(authHeader);

        response = client.execute(getRequest);

        int responseStatusCode = response.getStatusLine().getStatusCode();
        JSONObject jsonObj = getJsonFromResponse(response);

        LOGGER.info("Response obj: " + jsonObj.toString());

        assertEquals(HttpStatus.SC_OK, responseStatusCode);
        //TODO if ids added enable this
        //assertEquals(ADDRESS_EDIT_JSON.getContactId(), jsonObj.getInt("id"));
        assertEquals(ConstantsMockEntity.PHONE_EDIT_JSON.getCountryCode(), jsonObj.getString("countryCode"));
        assertEquals(ConstantsMockEntity.PHONE_EDIT_JSON.getPhoneNumber(), jsonObj.getString("phoneNumber"));
    }

    @Override
    @Test
    public void test006_removeById_200() throws IOException {
        deleteRequest = new HttpDelete(ENDPOINT_PHONE + "/" + phoneId);
        deleteRequest.setHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        deleteRequest.setHeader(authHeader);

        response = client.execute(deleteRequest);

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    @Override
    @Test
    public void test007_statusBeforeAndAfterTests() throws IOException {
        getRequest = new HttpGet(ENDPOINT_PHONE + "/" + contactId + "/all");
        getRequest.setHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        getRequest.setHeader(authHeader);

        response = client.execute(getRequest);
        int responseStatusCode = response.getStatusLine().getStatusCode();

        if (responseStatusCode != 200) {
            JSONObject jsonObject = getJsonFromResponse(response);
            LOGGER.info(jsonObject.toString());
        }

        assertEquals(HttpStatus.SC_OK, responseStatusCode);

        JSONArray jsonArray = getJsonArrayFromResponse(response);
        int contactsSizeAfterTests = jsonArray.length();

        if (contactsSizeAfterTests > 0) {
            JSONObject lastElementInList = jsonArray.getJSONObject(contactsSizeAfterTests);

            assertNotEquals(contactId, lastElementInList.getInt("id"));
            LOGGER.info("\nLast element in list:\n" + lastElementInList.toString());
        }

        LOGGER.info("Result:"
                + "\nList size after tests_001_add: " + phonesListSize
                + "\nList size after all tests: " + contactsSizeAfterTests);

        assertEquals(phonesListSize - 1, contactsSizeAfterTests);
    }
}
