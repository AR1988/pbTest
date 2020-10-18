package com.apiTest.helpers.constans;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;

public abstract class ConstantsHeaders {
    public static final Header HEADER_CONTENT_TYPE = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    public static final Header HEADER_AUTH_EXPIRED_TOKEN = new BasicHeader(ConstantsStrings.AUTH_HEADER_NAME, ConstantsStrings.INVALID_JWT);

}
