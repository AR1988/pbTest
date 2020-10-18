package com.uiTest;

import com.codeborne.selenide.WebDriverRunner;
import com.uiTest.driver.ChromeWebDriver;
import com.uiTest.pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

/**
 * StepsBaseTest
 * Base class for test steps implementation.
 *
 * @author Violeta Abramova abramova.violetta@gmail.com
 */
public class StepsBaseTest {

    public LoginPage loginPage;
    protected WebDriver driver;

    @Before
    public void buildDriver() {
        driver = ChromeWebDriver.getWebDriverInstance();
        initPageObjects();
        WebDriverRunner.setWebDriver(driver); //set Selenide WebDriver
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    private void initPageObjects() {
        loginPage = new LoginPage(driver);
    }
}
