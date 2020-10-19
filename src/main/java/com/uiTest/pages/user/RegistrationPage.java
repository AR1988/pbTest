package com.uiTest.pages.user;

import com.uiTest.utils.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends PageObject {

    public static final By EMAIL_INPUT = By.name("email");
    public static final By PASS_INPUT = By.name("password");
    public static final By CONFIRM_PASS_INPUT = By.name("confirm-password");

    public static final By REGISTER_FORM = By.id("registration-form");

    public static final By ERROR_PASS_NOT_MATCH = By.id("confirm-password-error-matcher");
    public static final By ERROR_MSG_EMAIL_REQUIED = By.id("email-error-required");
    public static final By ERROR_MSG_EMAIL_INVALID = By.id("email-error-invalid");
    public static final By SIGN_UP_BTN = By.tagName("button");

    public static final By ERROR_MSG_USER_EXIST = By.cssSelector("#error-message");

    public static final By PAGE_NAME = By.xpath("//h3[contains(text(),'Registration page')]");


    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

}
