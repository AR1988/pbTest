package com.uiTest.pages.user;

import com.uiTest.utils.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationConfirmationPage extends PageObject {
    public RegistrationConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "/html/body/app-root/app-activate-email/main/section/div/h4")
    private WebElement confirmationText;

    public String getConfirmationPageText() {
        return confirmationText.getText();
    }
}
