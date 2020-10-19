package com.uiTest.steps;

import io.cucumber.java8.En;

import static com.codeborne.selenide.Selenide.open;
import static com.uiTest.pages.BasePage.basicURL;

/**
 * CommonSteps
 * Class implements the common step definitions for several feature files.
 *
 * @author Violeta Abramova abramova.violetta@gmail.com
 */
public class CommonSteps implements En {

    public CommonSteps() {

        Given("I am on the {} page", (String page) -> {
            if (page.equals("Login"))
                open(basicURL + "/user/login");
            if (page.equals("Register"))
                open(basicURL + "/user/registration");
        });
    }
}
