package apiTest.helpers.json;

import static apiTest.helpers.constans.ConstantsStrings.*;

public class NewPasswordJson extends JsonEntity {
    private String password;
    private String token;

    public NewPasswordJson(String password, String token) {
        this.password = password;
        this.token = token;
    }

    @Override
    public String toJson() {
        return JSON_START +
                "password" +
                JSON_FIELD_VALUE_SEPARATOR +
                password +
                JSON_NEXT_FIELD +
                "token" +
                JSON_FIELD_VALUE_SEPARATOR +
                token +
                JSON_END;
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
