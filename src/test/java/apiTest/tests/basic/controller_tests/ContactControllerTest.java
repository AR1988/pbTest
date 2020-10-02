package apiTest.tests.basic.controller_tests;

import apiTest.helpers.BasicAPITest;
import apiTest.helpers.json.ContactJson;
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

import static apiTest.helpers.constans.ConstantsHeaders.HEADER_CONTENT_TYPE;
import static apiTest.helpers.constans.ConstantsMockEntity.CONTACT_EDIT_JSON;
import static apiTest.helpers.constans.ConstantsMockEntity.CONTACT_JSON;
import static apiTest.helpers.constans.ConstantsStrings.AUTH_HEADER_NAME;
import static apiTest.helpers.constans.ConstantsUrls.ENDPOINT_CONTACT;
import static apiTest.helpers.constans.ConstantsUserSettings.TEST_USER_EMAIL;
import static apiTest.helpers.constans.ConstantsUserSettings.TEST_USER_PASS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContactControllerTest extends BasicAPITest implements IControllerTest {

    private static int contactId;
    private static int contactsSize;

    @BeforeClass
    public static void init_addsToContact() throws IOException, LoginException {
        String loginUserEmail = TEST_USER_EMAIL;
        String loginUserPass = TEST_USER_PASS;

        response = login(loginUserEmail, loginUserPass);
        authHeader = response.getFirstHeader(AUTH_HEADER_NAME);

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

    @Override
    @Test
    public void test001_add_200() throws IOException {
        postRequest = new HttpPost(ENDPOINT_CONTACT);
        postRequest.setHeader(HEADER_CONTENT_TYPE);

        postRequest.setHeader(authHeader);

        StringEntity entity = new StringEntity(CONTACT_JSON.toJson());
        postRequest.setEntity(entity);
        response = client.execute(postRequest);

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        LOGGER.info("Entity body: " + EntityUtils.toString(entity));
    }

    @Override
    @Test
    public void test002_getList_200() throws IOException {
        getRequest = new HttpGet(ENDPOINT_CONTACT);
        getRequest.setHeader(HEADER_CONTENT_TYPE);
        getRequest.setHeader(authHeader);

        response = client.execute(getRequest);
        int responseStatusCode = response.getStatusLine().getStatusCode();

        if (responseStatusCode != 200) {
            JSONObject jsonObject = getJsonFromResponse(response);
            LOGGER.info(jsonObject.toString());
        }

        assertEquals(HttpStatus.SC_OK, responseStatusCode);

        JSONArray jsonArray = getJsonArrayFromResponse(response);

        contactsSize = jsonArray.length();
        JSONObject lastElementInList = jsonArray.getJSONObject(contactsSize - 1);
        contactId = lastElementInList.getInt("id");

        LOGGER.info("Result:"
                + "\nContact id is: " + contactId
                + "\nList list size: " + contactsSize
                + "\nLast element in list:\n" + lastElementInList.toString());

    }

    @Override
    @Test
    public void test003_getById_200() throws IOException {
        getRequest = new HttpGet(ENDPOINT_CONTACT + "/" + contactId);
        getRequest.setHeader(HEADER_CONTENT_TYPE);
        getRequest.setHeader(authHeader);

        response = client.execute(getRequest);

        int responseStatusCode = response.getStatusLine().getStatusCode();
        JSONObject jsonObj = getJsonFromResponse(response);

        LOGGER.info("Response obj: " + jsonObj.toString());

        assertEquals(HttpStatus.SC_OK, responseStatusCode);
        //TODO if ids added enable this
        //assertEquals(CONTACT_EDIT_JSON.getContactId(), jsonObj.getInt("id"));
        assertEquals(CONTACT_JSON.getFirstName(), jsonObj.getString("firstName"));
        assertEquals(CONTACT_JSON.getLastName(), jsonObj.getString("lastName"));
        assertEquals(CONTACT_JSON.getDescription(), jsonObj.getString("description"));
    }

    @Override
    @Test
    public void test004_editItem_200() throws IOException {
        putRequest = new HttpPut(ENDPOINT_CONTACT);
        putRequest.setHeader(HEADER_CONTENT_TYPE);
        putRequest.setHeader(authHeader);

        ContactJson contactJson = CONTACT_EDIT_JSON;
        contactJson.setContactId(contactId);

        StringEntity entity = new StringEntity(contactJson.toEditJson());
        putRequest.setEntity(entity);
        response = client.execute(putRequest);

        LOGGER.info("Entity body: " + EntityUtils.toString(entity));

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    @Override
    @Test
    public void test005_getChangedItemById_200() throws IOException {
        getRequest = new HttpGet(ENDPOINT_CONTACT + "/" + contactId);
        getRequest.setHeader(HEADER_CONTENT_TYPE);
        getRequest.setHeader(authHeader);

        response = client.execute(getRequest);

        int responseStatusCode = response.getStatusLine().getStatusCode();
        JSONObject jsonObj = getJsonFromResponse(response);

        LOGGER.info("Response obj: " + jsonObj.toString());

        assertEquals(HttpStatus.SC_OK, responseStatusCode);
        //TODO if ids added enable this
        //assertEquals(CONTACT_EDIT_JSON.getContactId(), jsonObj.getInt("id"));
        assertEquals(CONTACT_EDIT_JSON.getFirstName(), jsonObj.getString("firstName"));
        assertEquals(CONTACT_EDIT_JSON.getLastName(), jsonObj.getString("lastName"));
        assertEquals(CONTACT_EDIT_JSON.getDescription(), jsonObj.getString("description"));
    }

    @Override
    @Test
    public void test006_removeById_200() throws IOException {
        deleteRequest = new HttpDelete(ENDPOINT_CONTACT + "/" + contactId);
        deleteRequest.setHeader(HEADER_CONTENT_TYPE);
        deleteRequest.setHeader(authHeader);

        response = client.execute(deleteRequest);

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    @Override
    @Test
    public void test007_statusBeforeAndAfterTests() throws IOException {
        getRequest = new HttpGet(ENDPOINT_CONTACT);

        getRequest.setHeader(HEADER_CONTENT_TYPE);
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
            JSONObject lastElementInList = jsonArray.getJSONObject(contactsSizeAfterTests - 1);

            assertNotEquals(contactId, lastElementInList.getInt("id"));
            LOGGER.info("\nLast element in list:\n" + lastElementInList.toString());
        }

        LOGGER.info("Result:"
                + "\nList size after tests_001_add: " + contactsSize
                + "\nList size after tests: " + contactsSizeAfterTests);

        assertEquals(contactsSize - 1, contactsSizeAfterTests);
    }
}
