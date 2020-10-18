package com.apiTest.helpers.json;

import com.apiTest.helpers.constans.ConstantsStrings;

public class EmailJson extends JsonEntity {

    private int contactId;
    private String email;
    private int emailId;

    public EmailJson(int contactId, String email) {
        this.contactId = contactId;
        this.email = email;
    }

    @Override
    public String toJson() {
        return ConstantsStrings.JSON_START +
                "email" +
                ConstantsStrings.JSON_FIELD_VALUE_SEPARATOR +
                email +
                ConstantsStrings.JSON_NEXT_FIELD +
                "contactId" +
                ConstantsStrings.JSON_FIELD_VALUE_SEPARATOR +
                contactId +
                ConstantsStrings.JSON_END;
    }

    @Override
    public String toEditJson() {
        return ConstantsStrings.JSON_START +

                "email" +
                ConstantsStrings.JSON_FIELD_VALUE_SEPARATOR +
                email +

                ConstantsStrings.JSON_NEXT_FIELD +
                "id" +
                ConstantsStrings.JSON_FIELD_VALUE_SEPARATOR +
                emailId +

                ConstantsStrings.JSON_NEXT_FIELD +
                "contactId" +
                ConstantsStrings.JSON_FIELD_VALUE_SEPARATOR +
                contactId +

                ConstantsStrings.JSON_END;

    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContactId() {
        return contactId;
    }

    public String getEmail() {
        return email;
    }

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }
}
