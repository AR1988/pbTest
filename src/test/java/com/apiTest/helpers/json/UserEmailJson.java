package com.apiTest.helpers.json;

import com.apiTest.helpers.constans.ConstantsStrings;

public class UserEmailJson {

    private final String email;

    public UserEmailJson(String email) {
        this.email = email;
    }

    public String toJson() {
        return ConstantsStrings.JSON_START +
                "email" +
                ConstantsStrings.JSON_FIELD_VALUE_SEPARATOR +
                email +
                ConstantsStrings.JSON_END;
    }
}
