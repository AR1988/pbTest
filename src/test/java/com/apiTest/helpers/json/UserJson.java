package com.apiTest.helpers.json;

import static com.apiTest.helpers.constans.ConstantsStrings.*;

public class UserJson {
    private final String userEmail;
    private final String userPassword;

    public UserJson(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public String toJson() {
        return JSON_START +
                "email" +
                JSON_FIELD_VALUE_SEPARATOR +
                userEmail +
                JSON_NEXT_FIELD +
                "password" +
                JSON_FIELD_VALUE_SEPARATOR +
                userPassword +
                JSON_END;
    }
}
