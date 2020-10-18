package com.uiTest.pages;

import com.uiTest.utils.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavBar extends PageObject {

    public NavBar(WebDriver driver) {
        super(driver);
    }

    public static final By LOGO_LINK = By.xpath("//app-header/nav[1]/a[1]");

    public static final By CONTACTS_LINK = By.linkText("Contacts");
    public static final By ADD_CONTACT_LINK = By.linkText("Add new Contact");

    public static final By ACCOUNT_BTN = By.xpath("//button[contains(text(),'Account')]");
    public static final By LOGOUT_BTN = By.xpath("//button[contains(text(),'Logout')]");
}
