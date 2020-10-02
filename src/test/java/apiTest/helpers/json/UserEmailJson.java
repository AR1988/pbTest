package apiTest.helpers.json;

import static apiTest.helpers.constans.ConstantsStrings.*;

public class UserEmailJson {

    private final String email;

    public UserEmailJson(String email) {
        this.email = email;
    }

    public String toJson() {
        return JSON_START +
                "email" +
                JSON_FIELD_VALUE_SEPARATOR +
                email +
                JSON_END;
    }
}
