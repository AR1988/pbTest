package apiTest.tests;

import apiTest.helpers.BasicAPITest;
import apiTest.helpers.json.*;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.security.auth.login.LoginException;
import java.io.IOException;

import static apiTest.helpers.constans.ConstantsHeaders.HEADER_CONTENT_TYPE;
import static apiTest.helpers.constans.ConstantsMockEntity.*;
import static apiTest.helpers.constans.ConstantsStrings.AUTH_HEADER_NAME;
import static apiTest.helpers.constans.ConstantsStrings.ERROR_USER_ALREADY_EXIST;
import static apiTest.helpers.constans.ConstantsUrls.*;
import static apiTest.helpers.constans.ConstantsUserSettings.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserTest extends BasicAPITest {

    private static final String loginUserEmail = TEST_USER_EMAIL;
    private static final String loginUserPass = TEST_USER_PASS;
    private static final String newPass = "Hello_World";

    private static int phoneId;
    private static int contactId;
    private static int emailId;
    private static int addressId;

    private static int contactsSize;
    private static int phonesListSize;

    @BeforeClass
    public static void init_addsToContact() throws IOException, LoginException {

        response = login(loginUserEmail, loginUserPass);
        authHeader = response.getFirstHeader(AUTH_HEADER_NAME);

        String userEmailResponse = getUserEmailFromJwt(authHeader);

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        assertEquals(loginUserEmail, userEmailResponse);

        String messageHeader = String.format("\n|%30s|%25s|%13s|\n",
                "UserJson email", "UserJson pass", "Auth header");
        String message = String.format("|%30s|%25s|%13b|",
                loginUserEmail, loginUserPass, (authHeader.getValue() != null));
        String separator = "\n_____________________________________________________________________________________";

        LOGGER.info("Init params"
                + separator
                + messageHeader + message
                + "\nLogin with user email: " + userEmailResponse
                + separator);
    }

    @Test
    public void test001_TestUser_login_200() throws IOException, LoginException {
        response = login(loginUserEmail, loginUserPass);

        authHeader = response.getFirstHeader(AUTH_HEADER_NAME);

        String userEmail = getUserEmailFromJwt(authHeader);
        LOGGER.info(userEmail);

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        assertEquals(userEmail, loginUserEmail);
    }

    @Test
    public void test002_TestUser_createNewTestUser_400() throws IOException {
        postRequest = new HttpPost(ENDPOINT_USER);
        postRequest.setHeader(HEADER_CONTENT_TYPE);

        UserJson loginUser = new UserJson(loginUserEmail, loginUserPass);
        StringEntity entity = new StringEntity(loginUser.toJson());

        postRequest.setEntity(entity);
        response = client.execute(postRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
        assertEquals(ERROR_USER_ALREADY_EXIST, jsonObject.getString("message"));
    }

    @Test
    public void test003_TestUser_changePasswordWithAuth_ok() throws IOException, LoginException {

        //region change password
        //---------------------------------------------------------------------------------------
        putRequest = new HttpPut(ENDPOINT_USER_UPDATE_PASS_AUTH_USER);
        putRequest.setHeader(HEADER_CONTENT_TYPE);
        putRequest.setHeader(authHeader);

        NewPasswordAuthJson newPassJson = new NewPasswordAuthJson(newPass);
        StringEntity entity = new StringEntity(newPassJson.toJson());

        putRequest.setEntity(entity);
        response = client.execute(putRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        LOGGER.info("Change password");

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        //---------------------------------------------------------------------------------------
        //endregion

        //region login with new password
        //---------------------------------------------------------------------------------------
        response = login(loginUserEmail, newPass);

        jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        LOGGER.info("Login with new password");

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        //---------------------------------------------------------------------------------------
        //endregion

        //region change password to default password
        //---------------------------------------------------------------------------------------

        putRequest = new HttpPut(ENDPOINT_USER_UPDATE_PASS_AUTH_USER);
        putRequest.setHeader(HEADER_CONTENT_TYPE);
        putRequest.setHeader(authHeader);

        newPassJson = new NewPasswordAuthJson(loginUserPass);
        entity = new StringEntity(newPassJson.toJson());

        putRequest.setEntity(entity);
        response = client.execute(putRequest);

        jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        LOGGER.info("Change password to default test user pass");
        //---------------------------------------------------------------------------------------
        //endregion

        //region login with default password
        //---------------------------------------------------------------------------------------
        response = login(loginUserEmail, loginUserPass);
        LOGGER.info("Login with default test user password");
        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        //endregion
    }


    @Test
    public void test004_TestUser_getProfile_ok() throws IOException {
        getRequest = new HttpGet(ENDPOINT_PROFILE);
        getRequest.addHeader(HEADER_CONTENT_TYPE);
        getRequest.addHeader(authHeader);

        response = client.execute(getRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    @Test
    public void test005_TestUser_addContact() throws IOException {
        postRequest = new HttpPost(ENDPOINT_CONTACT);
        postRequest.setHeader(HEADER_CONTENT_TYPE);

        postRequest.setHeader(authHeader);

        StringEntity entity = new StringEntity(CONTACT_JSON.toJson());
        postRequest.setEntity(entity);
        response = client.execute(postRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }


    @Test
    public void test006_TestUser_getContacts_ok() throws IOException {
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

        contactId = getIdLastContactFromContactsList(authHeader);

        LOGGER.info("Result:"
                + "\nContact id is: " + contactId);

    }

    @Test
    public void test007_TestUser_editContact_ok() throws IOException {
        putRequest = new HttpPut(ENDPOINT_CONTACT);
        putRequest.setHeader(HEADER_CONTENT_TYPE);
        putRequest.setHeader(authHeader);

        ContactJson contactJson = CONTACT_EDIT_JSON;
        contactJson.setContactId(contactId);
        StringEntity entity = new StringEntity(contactJson.toEditJson());

        putRequest.setEntity(entity);
        response = client.execute(putRequest);

        JSONObject jsonObj = getJsonFromResponse(response);
        LOGGER.info("Response obj: " + jsonObj.toString());

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    @Test
    public void test008_TestUser_getContactByContactId_ok() throws IOException {
        getRequest = new HttpGet(ENDPOINT_CONTACT + "/" + contactId);
        getRequest.setHeader(HEADER_CONTENT_TYPE);
        getRequest.setHeader(authHeader);

        response = client.execute(getRequest);
        JSONObject jsonObj = getJsonFromResponse(response);

        LOGGER.info("response obj: " + jsonObj.toString());

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        //TODO if ids added enable this
        //assertEquals(CONTACT_EDIT_JSON.getContactId(), jsonObj.getInt("id"));
        assertEquals(CONTACT_EDIT_JSON.getFirstName(), jsonObj.getString("firstName"));
        assertEquals(CONTACT_EDIT_JSON.getLastName(), jsonObj.getString("lastName"));
        assertEquals(CONTACT_EDIT_JSON.getDescription(), jsonObj.getString("description"));
    }

    @Test
    public void test009_addAddressesToUser() throws IOException {
        postRequest = new HttpPost(ENDPOINT_ADDRESS);
        postRequest.setHeader(HEADER_CONTENT_TYPE);
        postRequest.setHeader(authHeader);

        AddressJson addressJson = ADDRESS_JSON;
        addressJson.setContactId(contactId);
        StringEntity entity = new StringEntity(addressJson.toJson());

        LOGGER.info("Entity: " + addressJson.toJson());

        postRequest.setEntity(entity);
        response = client.execute(postRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    @Test
    public void test010_addEmailsToUser() throws IOException {
        postRequest = new HttpPost(ENDPOINT_EMAIL);
        postRequest.setHeader(HEADER_CONTENT_TYPE);
        postRequest.setHeader(authHeader);

        EmailJson emailJson = EMAIL_JSON;
        emailJson.setContactId(contactId);
        StringEntity entity = new StringEntity(emailJson.toJson());

        LOGGER.info("Entity: " + emailJson.toJson());

        postRequest.setEntity(entity);
        response = client.execute(postRequest);

        JSONObject jsonObject = getJsonFromResponse(response);
        LOGGER.info(jsonObject.toString());

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    @Test
    public void test011_addPhonesToUser() throws IOException {
        postRequest = new HttpPost(ENDPOINT_PHONE);
        postRequest.setHeader(HEADER_CONTENT_TYPE);
        postRequest.setHeader(authHeader);

        PhoneJson phoneJson = PHONE_JSON;
        phoneJson.setContactId(contactId);
        StringEntity entity = new StringEntity(phoneJson.toJson());

        postRequest.setEntity(entity);
        response = client.execute(postRequest);

        LOGGER.info("Contact id: " + CONTACT_ID);
        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }


    @Test
    public void test012_TestUser_getPhonesListByContactId_ok() throws IOException {
        getRequest = new HttpGet(ENDPOINT_PHONE + "/" + contactId + "/all");

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
        phoneId = jsonArray.getJSONObject(jsonArray.length() - 1).getInt("id");
        phonesListSize = jsonArray.length();

        LOGGER.info("Get last phone id and phones list size:"
                + "\nContact id is: " + contactId
                + "\nPhone id is: " + phoneId
                + "\nPhones list size: " + phonesListSize
                + "\nPhones list\n" + jsonArray.toString());


    }

    @Test
    public void test013_TestUser_editPhone_ok() throws IOException {
        putRequest = new HttpPut(ENDPOINT_PHONE);
        putRequest.setHeader(HEADER_CONTENT_TYPE);

        putRequest.setHeader(authHeader);

        PhoneJson contactJson = PHONE_EDIT_JSON;
        contactJson.setPhoneId(phoneId);

        StringEntity entity = new StringEntity(contactJson.toEditJson());
        putRequest.setEntity(entity);
        response = client.execute(putRequest);
        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    @Test
    public void test014_TestUser_getPhoneByPhoneId_ok() throws IOException {
        getRequest = new HttpGet(ENDPOINT_PHONE + "/" + phoneId);
        getRequest.setHeader(HEADER_CONTENT_TYPE);
        getRequest.setHeader(authHeader);

        response = client.execute(getRequest);
        JSONObject jsonObj = getJsonFromResponse(response);

        LOGGER.info("Response obj: " + jsonObj.toString());

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        //TODO if ids added enable this
        //assertEquals(ADDRESS_EDIT_JSON.getContactId(), jsonObj.getInt("id"));
        assertEquals(PHONE_EDIT_JSON.getCountryCode(), jsonObj.getString("countryCode"));
        assertEquals(PHONE_EDIT_JSON.getPhoneNumber(), jsonObj.getString("phoneNumber"));
    }

    @Test
    public void test015_TestUser_removePhone_ok() throws IOException {
        deleteRequest = new HttpDelete(ENDPOINT_PHONE + "/" + phoneId);
        deleteRequest.setHeader(HEADER_CONTENT_TYPE);
        deleteRequest.setHeader(authHeader);

        response = client.execute(deleteRequest);
        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    @Test
    public void test016_TestUser_checkThatPhoneRemoved_getPhonesListByContactId_ok() throws IOException {
        getRequest = new HttpGet(ENDPOINT_PHONE + "/" + contactId + "/all");
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
            JSONObject lastElementInList = jsonArray.getJSONObject(contactsSizeAfterTests);

            assertNotEquals(contactId, lastElementInList.getInt("id"));
            LOGGER.info("\nLast element in list:\n" + lastElementInList.toString());
        }

        LOGGER.info("Result:"
                + "\nList size after all tests: " + contactsSizeAfterTests);

        assertEquals(phonesListSize - 1, contactsSizeAfterTests);
    }


    @Test
    public void test999_testUser_removeContact_ok() throws IOException {
        deleteRequest = new HttpDelete(ENDPOINT_CONTACT + "/" + contactId);
        deleteRequest.setHeader(HEADER_CONTENT_TYPE);
        deleteRequest.setHeader(authHeader);

        response = client.execute(deleteRequest);

        String responseMessage = getJsonFromResponse(response).toString();
        LOGGER.info(responseMessage);

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }
}
