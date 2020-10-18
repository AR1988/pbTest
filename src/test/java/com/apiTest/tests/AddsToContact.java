package com.apiTest.tests;

import com.apiTest.helpers.BasicAPITest;
import com.apiTest.helpers.constans.ConstantsHeaders;
import com.apiTest.helpers.constans.ConstantsStrings;
import com.apiTest.helpers.constans.ConstantsUserSettings;
import com.apiTest.helpers.json.AddressJson;
import com.apiTest.helpers.json.ContactJson;
import com.apiTest.helpers.json.EmailJson;
import com.apiTest.helpers.json.PhoneJson;
import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.apiTest.helpers.constans.ConstantsUrls.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

//@Ignore
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddsToContact extends BasicAPITest {

    private static int contactId;

    private final Faker FAKER = new Faker();

    private List<ContactJson> contactJsonList;
    private List<AddressJson> addressJsonList;
    private List<EmailJson> emailJsonList;
    private List<PhoneJson> phoneJsonList;

    private static Header authHeader;

    @BeforeClass
    public static void init_addsToContact() throws IOException, LoginException {

        String loginUserEmail = ConstantsUserSettings.USER_EMAIL;
        String loginUserPass = ConstantsUserSettings.USER_PASS;

        response = login(loginUserEmail, loginUserPass);
        authHeader = response.getFirstHeader(ConstantsStrings.AUTH_HEADER_NAME);

        String userEmailResponse = getUserEmailFromJwt(authHeader);
        int responseStatusCode = response.getStatusLine().getStatusCode();

        assertEquals(HttpStatus.SC_OK, responseStatusCode);
        assertEquals(loginUserEmail, userEmailResponse);

        String separator = "\n_____________________________________________________________________________________";

        String messageHeaderTable1 = String.format("\n|%30s|%25s|%13s|\n",
                "User email", "User pass", "Auth header");
        String messageTable1 = String.format("|%30s|%25s|%13b|",
                loginUserEmail, loginUserPass, (authHeader.getValue() != null));

        String messageHeaderTable2 = String.format("\n|%30s|%10s|%10s|%10s|%10s|\n",
                "Base Url", "Contacts", "Addresses", "Emails", "Phones");
        String messageTable2 = String.format("|%30s|%10s|%10s|%10s|%10s|",
                BASIC_URL, ConstantsUserSettings.ADDS_CONTACTS, ConstantsUserSettings.ADDS_ADDRESSES, ConstantsUserSettings.ADDS_EMAILS, ConstantsUserSettings.ADDS_PHONES);

        LOGGER.info("Init params"
                + separator
                + messageHeaderTable1 + messageTable1
                + separator
                + messageHeaderTable2
                + messageTable2
                + separator);
    }

    @Test
    public void test001_loopAddContact() throws IOException {
        fillContactArray();
        for (ContactJson contactJson : contactJsonList)
            addContactsToUser(contactJson);
    }

    @Test
    public void test002_loopAddAddresses() throws IOException {
        fillAddressList();
        for (AddressJson addressJson : addressJsonList)
            addAddressesToUser(addressJson);
    }

    @Test
    public void test003_loopAddEmails() throws IOException {
        fillEmailList();
        for (EmailJson emailJson : emailJsonList)
            addEmailsToUser(emailJson);
    }


    @Test
    public void test004_loopAddPhones() throws IOException {
        fillPhonesList();
        for (PhoneJson phoneJson : phoneJsonList)
            addPhonesToUser(phoneJson);
    }


    private void addAddressesToUser(AddressJson addressJson) throws IOException {
        postRequest = new HttpPost(ENDPOINT_ADDRESS);
        postRequest.setHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        postRequest.setHeader(authHeader);

        StringEntity entity = new StringEntity(addressJson.toJson());

        postRequest.setEntity(entity);
        response = client.execute(postRequest);

        LOGGER.info("Contact id: " + contactId);

        int responseStatusCode = response.getStatusLine().getStatusCode();
        if (responseStatusCode != 200) {
            LOGGER.warning("Error " + responseStatusCode + ". Message from server: " + getJsonFromResponse(response).toString());
            fail();
        } else
            assertEquals(HttpStatus.SC_OK, responseStatusCode);
    }

    private void addContactsToUser(ContactJson contactJson) throws IOException {
        postRequest = new HttpPost(ENDPOINT_CONTACT);
        postRequest.setHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        postRequest.setHeader(authHeader);

        StringEntity entity = new StringEntity(contactJson.toJson());
        postRequest.setEntity(entity);
        response = client.execute(postRequest);


        LOGGER.info("Contact id: " + contactId);

        LOGGER.info(getJsonFromResponse(response).toString());
        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        contactId = getIdLastContactFromContactsList(authHeader);

        LOGGER.info("contactId: " + contactId);
    }

    private void addEmailsToUser(EmailJson emailJson) throws IOException {
        postRequest = new HttpPost(ENDPOINT_EMAIL);
        postRequest.setHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        postRequest.setHeader(authHeader);

        StringEntity entity = new StringEntity(emailJson.toJson());
        postRequest.setEntity(entity);
        response = client.execute(postRequest);


        LOGGER.info("Contact id: " + contactId);

        int responseStatusCode = response.getStatusLine().getStatusCode();
        if (responseStatusCode != 200) {
            LOGGER.warning("Error " + responseStatusCode + ". Message from server: " + getJsonFromResponse(response).toString());
            fail();
        } else
            assertEquals(HttpStatus.SC_OK, responseStatusCode);
    }

    private void addPhonesToUser(PhoneJson phoneJson) throws IOException {
        postRequest = new HttpPost(ENDPOINT_PHONE);
        postRequest.setHeader(ConstantsHeaders.HEADER_CONTENT_TYPE);
        postRequest.setHeader(authHeader);

        StringEntity entity = new StringEntity(phoneJson.toJson());
        postRequest.setEntity(entity);
        response = client.execute(postRequest);


        LOGGER.info("Contact id: " + contactId);

        int responseStatusCode = response.getStatusLine().getStatusCode();
        if (responseStatusCode != 200) {
            LOGGER.warning("Error " + responseStatusCode + ". Message from server: " + getJsonFromResponse(response).toString());
            fail();
        } else
            assertEquals(HttpStatus.SC_OK, responseStatusCode);
    }

    private void fillAddressList() {
        addressJsonList = new ArrayList<>();

        for (int i = 0; i < ConstantsUserSettings.ADDS_ADDRESSES; i++) {
            Faker faker2 = new Faker(new Locale("en", "us"));
            Address address = faker2.address();
            String streetAddress = address.streetAddress();
            String zipCode = address.zipCode();
            String cityName = address.city();
            String country = address.country();

            addressJsonList.add(new AddressJson(contactId, streetAddress, zipCode, cityName, country));
        }
    }

    private void fillContactArray() {
        contactJsonList = new ArrayList<>();

        for (int i = 0; i < ConstantsUserSettings.ADDS_CONTACTS; i++) {
            String fullName = FAKER.name().fullName();
            String firstName = FAKER.name().firstName();
            String lastName = FAKER.name().lastName();
            String description = FAKER.hobbit().quote();

            contactJsonList.add(new ContactJson(firstName, lastName, fullName + ". " + description));
        }

    }

    private void fillEmailList() {
        emailJsonList = new ArrayList<>();
        for (int i = 0; i < ConstantsUserSettings.ADDS_EMAILS; i++) {
            String firstName = FAKER.name().firstName();
            String lastName = FAKER.name().lastName();
            emailJsonList.add(new EmailJson(contactId, firstName + "_" + lastName + "@gmail.com"));
        }
    }

    private void fillPhonesList() {
        phoneJsonList = new ArrayList<>();
        for (int i = 0; i < ConstantsUserSettings.ADDS_PHONES; i++) {
            String countryCode = FAKER.phoneNumber().extension();
            String phoneNumber = FAKER.phoneNumber().cellPhone();
            phoneJsonList.add(new PhoneJson(contactId, countryCode, phoneNumber));
        }
    }
}
