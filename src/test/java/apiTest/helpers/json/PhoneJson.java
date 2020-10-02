package apiTest.helpers.json;

import static apiTest.helpers.constans.ConstantsStrings.*;

public class PhoneJson extends JsonEntity {
    private int contactId;
    private String countryCode;
    private String phoneNumber;
    private int phoneId;

    public PhoneJson(int contactId, String countryCode, String phoneNumber) {
        this.contactId = contactId;
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
    }

    public String toJson() {
        return JSON_START +

                "countryCode" +
                JSON_FIELD_VALUE_SEPARATOR +
                countryCode +

                JSON_NEXT_FIELD +
                "phoneNumber" +
                JSON_FIELD_VALUE_SEPARATOR +
                phoneNumber +

                JSON_NEXT_FIELD +
                "contactId" +
                JSON_FIELD_VALUE_SEPARATOR +
                contactId +

                JSON_END;
    }

    public String toEditJson() {
        return JSON_START +

                "countryCode" +
                JSON_FIELD_VALUE_SEPARATOR +
                countryCode +

                JSON_NEXT_FIELD +
                "id" +
                JSON_FIELD_VALUE_SEPARATOR +
                phoneId +

                JSON_NEXT_FIELD +
                "phoneNumber" +
                JSON_FIELD_VALUE_SEPARATOR +
                phoneNumber +

                JSON_NEXT_FIELD +
                "contactId" +
                JSON_FIELD_VALUE_SEPARATOR +
                contactId +

                JSON_END;
    }

    public int getContactId() {
        return contactId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getPhoneId() {
        return phoneId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }
}
