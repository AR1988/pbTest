package com.apiTest.helpers.json;

import com.apiTest.helpers.constans.ConstantsStrings;

public class NewPasswordJson extends JsonEntity {
    private String password;
    private String token;

    public NewPasswordJson(String password, String token) {
        this.password = password;
        this.token = token;
    }

    @Override
    public String toJson() {
        return ConstantsStrings.JSON_START +
                "password" +
                ConstantsStrings.JSON_FIELD_VALUE_SEPARATOR +
                password +
                ConstantsStrings.JSON_NEXT_FIELD +
                "token" +
                ConstantsStrings.JSON_FIELD_VALUE_SEPARATOR +
                token +
                ConstantsStrings.JSON_END;
    }

    @Override
    public String toEditJson() {
        return null;
    }

    @Override
    public void setContactId(int contactId) {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
