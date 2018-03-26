package org.fasttrackit.steps;

import cucumber.api.java.en.Given;
import org.fasttrackit.AppConfig;
import org.fasttrackit.TestBase;

public class HomePageSteps extends TestBase {

    @Given("^I open the homepage$")
    public void iOpenTheHomepage() {
        driver.get(AppConfig.getSiteUrl());
    }

}
