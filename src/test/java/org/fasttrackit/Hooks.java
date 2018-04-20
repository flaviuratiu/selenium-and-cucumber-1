package org.fasttrackit;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @Before
    public void setup() {
        String browser = System.getProperty("browser", "chrome");

        DriverFactory.initWebDriver(browser);

        DriverFactory.getDriver().get(AppConfig.getSiteUrl());
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.BYTES);

            scenario.embed(screenshot, "image/png");
        }

        DriverFactory.getDriver().quit();
    }
}
