package com.apiTest.helpers.json;

import com.apiTest.helpers.constans.ConstantsStrings;

public class ContactJson extends JsonEntity {

    private String firstName;
    private String lastName;
    private String description;
    private int contactId;

    public ContactJson(String firstName, String lastName, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
    }

    public String toJson() {
        return ConstantsStrings.JSON_START +
                "firstName" +
                ConstantsStrings.JSON_FIELD_VALUE_SEPARATOR +
                firstName +
                ConstantsStrings.JSON_NEXT_FIELD +
                "lastName" +
                ConstantsStrings.JSON_FIELD_VALUE_SEPARATOR +
                lastName +
                ConstantsStrings.JSON_NEXT_FIELD +
                "description" +
                ConstantsStrings.JSON_FIELD_VALUE_SEPARATOR +
                description +
                ConstantsStrings.JSON_END;
    }

    public String toEditJson() {
        return ConstantsStrings.JSON_START +
                "firstName" +
                ConstantsStrings.JSON_FIELD_VALUE_SEPARATOR +
                firstName +

                ConstantsStrings.JSON_NEXT_FIELD +
                "lastName" +
                ConstantsStrings.JSON_FIELD_VALUE_SEPARATOR +
                lastName +

                ConstantsStrings.JSON_NEXT_FIELD +
                "id" +
                ConstantsStrings.JSON_FIELD_VALUE_SEPARATOR +
                contactId +

                ConstantsStrings.JSON_NEXT_FIELD +
                "description" +
                ConstantsStrings.JSON_FIELD_VALUE_SEPARATOR +
                description +
                ConstantsStrings.JSON_END;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
}
