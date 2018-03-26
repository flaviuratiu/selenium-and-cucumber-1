package org.fasttrackit.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.fasttrackit.TestBase;
import org.fasttrackit.webviews.Header;

public class HeaderSteps extends TestBase {

    private Header header = initElements(Header.class);

    @And("^I search a product by \"(.+)\"$")
    public void iSearchAProductBy(String keyword) {
        header.search(keyword);
    }


}
