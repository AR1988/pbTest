package apiTest.helpers.constans;

public abstract class ConstantsUrls {

    public static final String BASIC_URL = "http://localhost:8080/api/";
//    public static final String BASIC_URL = "http://dev.phonebook-1.telran-edu.de/api/";

    public static final String ENDPOINT_ADDRESS = BASIC_URL + "address";

    public static final String ENDPOINT_CONTACT = BASIC_URL + "contact";
    public static final String ENDPOINT_PROFILE = ENDPOINT_CONTACT + "/profile";
    public static final String ENDPOINT_PHONE = BASIC_URL + "phone";
    public static final String ENDPOINT_EMAIL = BASIC_URL + "email";

    public static final String ENDPOINT_USER = BASIC_URL + "user";
    public static final String ENDPOINT_USER_RECOVERY_PASS = ENDPOINT_USER + "/password/recovery";
    public static final String ENDPOINT_USER_UPDATE_PASS_AUTH_USER = BASIC_URL + "user/auth-password";

    public static final String ENDPOINT_LOGIN = BASIC_URL + "user/login";

}
