package com.uiTest.pages;

import com.uiTest.utils.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageObject {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public static final By EMAIL_INPUT = By.name("email");
    public static final By PASS_INPUT = By.name("password");

    public static final By LOGIN_BTN = By.tagName("button");
    public static final By LOGIN_FORM = By.id("login-form");

    public static final By INVALID_EMAIL_MSG_INVALID = By.id("email-error-invalid");
    public static final By INVALID_EMAIL_MSG_REQUIRED = By.id("email-error-required");
    public static final By INVALID_EMAIL_MSG = By.id("email-error-invalid");

    public static final By INVALID_PASS_MSG_MIN_L = By.id("password-error-minlength");
    public static final By INVALID_PASS_MSG_MAX_L = By.id("password-error-maxlength");
}
