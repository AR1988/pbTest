package com.uiTest.pages.user;

import com.uiTest.utils.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PassRecoveryPage extends PageObject {

    public PassRecoveryPage(WebDriver driver) {
        super(driver);
    }


    public static final By EMAIL_INPUT = By.name("email");
    public static final By PASS_INPUT = By.name("password");

    public static final By LOGIN_BTN = By.tagName("button");
    public static final By LOGIN_FORM = By.id("login-form");

    public static final By INVALID_EMAIL_INVALID = By.id("email-error-invalid");
    public static final By INVALID_EMAIL_REQUIRED = By.id("email-error-required");

    public static final By INVALID_PASS_MIN_L = By.id("password-error-minlength");
    public static final By INVALID_PASS_MAX_L = By.id("password-error-maxlength");
    public static final By INVALID_PASS_REQUIRED = By.id("password-error-required");

    public static final By REGISTER_LINK = By.linkText("Register new Account");
    public static final By FORGOT_PASSWORD_LINK = By.linkText("Forgot password?");

    public static final By PAGE_NAME = By.xpath("//h3[contains(text(),'Password recovery')]");

}
