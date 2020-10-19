package com.uiTest.steps;

import com.codeborne.selenide.Selenide;
import com.uiTest.pages.NavBar;
import com.uiTest.pages.user.PassRecoveryPage;
import com.uiTest.pages.user.RegistrationPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.uiTest.pages.user.LoginPage.*;

public class LoginSteps implements En {
    public LoginSteps() {
        When("I enter user credentials:", (DataTable table) -> {
            List<Map<String, String>> credentials = table.asMaps();
            String username = credentials.get(0).get("username");
            String password = credentials.get(0).get("password");
            $(EMAIL_INPUT).setValue(username);
            $(PASS_INPUT).setValue(password);
        });

        When("I click on Login button", () -> {
            $(LOGIN_FORM).submit();
        });

        Then("I see logout button", () -> {
            Selenide.$(NavBar.LOGOUT_BTN).shouldBe(visible);
        });

        Then("I observe error message {}", (String error) -> {

            switch (error) {
                case "Email is required.":
                    $(INVALID_EMAIL_REQUIRED).shouldHave(text(error));
                    break;
                case "Password must be no longer than 20 characters.":
                    $(INVALID_PASS_MAX_L).shouldHave(text(error));
                    break;
                case "Password must be at least 8 characters.":
                    $(INVALID_PASS_MIN_L).shouldHave(text(error));
                    break;
                case "Password is required.":
                    $(INVALID_PASS_REQUIRED).shouldHave(text(error));
                    break;
            }
        });

        And("Submit btn is disabled", () -> {
            $(LOGIN_BTN).shouldBe(disabled);
        });

        When("I click on email input filed", () -> {
            $(EMAIL_INPUT).click();
            $(PASS_INPUT).click();
        });

        When("I click on password input filed", () -> {
            $(PASS_INPUT).click();
            $(EMAIL_INPUT).click();
        });

        When("I click the link {}", (String link) -> {
            if (link.equals("Register new Account"))
                $(REGISTER_LINK).click();
            if (link.equals("Forgot password?"))
                $(FORGOT_PASSWORD_LINK).click();
        });

        Then("I see the text {}", (String pageName) -> {
            if (pageName.equals("Register new Account"))
                $(RegistrationPage.PAGE_NAME).shouldHave(text(pageName));
            if (pageName.equals("Forgot password?"))
                $(PassRecoveryPage.PAGE_NAME).shouldHave(text(pageName));
        });
    }
}
