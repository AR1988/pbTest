package com.uiTest.steps;


import com.codeborne.selenide.Selenide;
import com.uiTest.pages.NavBar;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;

import java.util.List;
import java.util.Map;

import static com.apiTest.helpers.BasicAPITest.LOGGER;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.uiTest.pages.LoginPage.*;

/**
 * LoginSteps
 * Class implements the step definitions for testing the Login page.
 *
 * @author Violeta Abramova abramova.violetta@gmail.com
 */
public class LoginSteps implements En {

    public LoginSteps() {

        When("I enter user credentials:", (DataTable table) -> {
            List<Map<String, String>> credentials = table.asMaps();

            String username = credentials.get(0).get("username");
            String password = credentials.get(0).get("password");

            LOGGER.info("usename: " + username + " password: " + password);

            $(EMAIL_INPUT).setValue(username);
            $(PASS_INPUT).setValue(password);
        });

        When("I click on Login button", () -> {
            $(LOGIN_FORM).submit();
        });

        Then("I see logout button", () -> {
            Selenide.$(NavBar.LOGOUT_BTN).shouldBe(visible);
        });

        //Negative tests
        When("I set invalid user email: {}", (String email) -> {
            $(EMAIL_INPUT).setValue(email);

        });
        Then("I observe error message: {}", (String errorMessage) -> {
            Selenide.$(INVALID_EMAIL_MSG).shouldHave(text(errorMessage));
        });

        Then("The login button is disabled", () -> {
            Selenide.$(LOGIN_BTN).shouldBe(disabled);
        });

        When("I click on email input filed", () -> {
            $(EMAIL_INPUT).click();
            $(PASS_INPUT).click();
        });

        Then("I observe error message because i don't set value: {}", (String errorMessage) -> {
            Selenide.$(INVALID_EMAIL_MSG_REQUIRED).shouldHave(text(errorMessage));
        });


        When("I set invalid too long password {}", (String longPassword) -> {
            $(PASS_INPUT).setValue(longPassword);
        });

        Then("I observe password error message: {}", (String errorMessage) -> {
            if (errorMessage.equals("Password must be no longer than 20 characters."))
                Selenide.$(INVALID_PASS_MSG_MAX_L).shouldHave(text(errorMessage));
            else
                Selenide.$(INVALID_PASS_MSG_MIN_L).shouldHave(text(errorMessage));

        });
    }
}
