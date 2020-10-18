package com.apiTest.helpers.constans;

public abstract class ConstantsStrings {
    public static final String AUTH_HEADER_NAME = "Access-Token";

    public static final String JSON_START = "{\n  \"";
    public static final String JSON_FIELD_VALUE_SEPARATOR = "\" : \"";
    public static final String JSON_NEXT_FIELD = "\",\n  \"";
    public static final String JSON_END = "\"\n}";

    public static final String INVALID_JWT =
            "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6InBob25lLmJvb2NrLnRlc3RAZ21haWwuY29tIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MTUwMTQ5NzczOH0.9KygRXJh-OgSu2KD29j4rZJ2pTw-kHehlbtPyOqatl9D6FOY8quKcBKYMW7QLF2E_LclG66Gu82yl4oZoUWW7A";


    public static final String ERROR_USER_ALREADY_EXIST = "Error! User already exists";
    public static final String ERROR_CONTACT_NOT_IN_DB = "Error! This contact doesn't exist in our DB";
    public static final String ERROR_UNAUTHORIZED = "Unauthenticated";
    public static final String ERROR_JWT_EXPIRED_MESSAGE = "io.jsonwebtoken.ExpiredJwtException: JWT expired at 2017-07-31T12:42:18Z";
}
