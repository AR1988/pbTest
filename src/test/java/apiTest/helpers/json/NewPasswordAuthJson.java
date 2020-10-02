package apiTest.helpers.json;

import static apiTest.helpers.constans.ConstantsStrings.*;

public class NewPasswordAuthJson extends JsonEntity {
    private String password;

    public NewPasswordAuthJson(String password) {
        this.password = password;
    }

    @Override
    public String toJson() {
        return JSON_START +
                "password" +
                JSON_FIELD_VALUE_SEPARATOR +
                password +
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
}
