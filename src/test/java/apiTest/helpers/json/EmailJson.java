package apiTest.helpers.json;

import static apiTest.helpers.constans.ConstantsStrings.*;

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
        return JSON_START +
                "email" +
                JSON_FIELD_VALUE_SEPARATOR +
                email +
                JSON_NEXT_FIELD +
                "contactId" +
                JSON_FIELD_VALUE_SEPARATOR +
                contactId +
                JSON_END;
    }

    @Override
    public String toEditJson() {
        return JSON_START +

                "email" +
                JSON_FIELD_VALUE_SEPARATOR +
                email +

                JSON_NEXT_FIELD +
                "id" +
                JSON_FIELD_VALUE_SEPARATOR +
                emailId +

                JSON_NEXT_FIELD +
                "contactId" +
                JSON_FIELD_VALUE_SEPARATOR +
                contactId +

                JSON_END;

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
