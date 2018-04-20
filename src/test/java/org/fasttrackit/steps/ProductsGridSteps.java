package org.fasttrackit.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fasttrackit.TestBase;
import org.fasttrackit.webviews.ProductsGrid;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductsGridSteps extends TestBase {

    private ProductsGrid productsGrid = initElements(ProductsGrid.class);

    @When("^I sort the products by \"([^\"]*)\"$")
    public void iSortTheProductsBy(String criteria) {
        productsGrid.getSortByList().selectByVisibleText(criteria);
    }

    public static void main(String[] args) {
        // regex demo

        String priceText = "Starting at: 100,00 RON";

        Pattern pattern = Pattern.compile(".* (\\d+,\\d+).*");
        Matcher matcher = pattern.matcher(priceText);

        Double price = null;

        if (matcher.find()) {
            String parsablePriceText = matcher.group(1);

            price = Double.parseDouble(parsablePriceText.replace(",", "."));
        }

        // Use the parsed price in assertions or in any way you need to.
        System.out.println(price);
    }

    @And("^I sort the products in (.+) order$")
    public void iSortTheProductsInDescendingOrder(String sortDirection) {
        String classValue = productsGrid.getSortDirectionLink().getAttribute("class");

        String expectedPartialClassValue;
        if (sortDirection.equals("descending")) {
            expectedPartialClassValue = "--asc";
        } else {
            expectedPartialClassValue = "--desc";
        }

        if (classValue.contains(expectedPartialClassValue)) {
            productsGrid.getSortDirectionLink().click();
        }
    }

    @Then("^products are sorted by \"([^\"]*)\" in (.+) order$")
    public void productsAreSortedByInDescendingOrder(String criteria, String sortDirection) throws Exception {
        if (criteria.equals("Price")) {
            verifySortingByPrice(sortDirection);
        } else if (criteria.equals("Name")) {
            verifySortingByName(sortDirection);
        } else {
            throw new Exception("Step implemented only for Price and Name criteria");
        }
    }

    private void verifySortingByPrice(String sortDirection) {
        List<Double> actualPrices = new ArrayList<>();

        for (WebElement priceContainer : productsGrid.getProductPrices()) {
            String priceText = priceContainer.getText();

            String priceTextWithoutCurrency = priceText.split(" ")[0];

            priceTextWithoutCurrency = priceTextWithoutCurrency.replace(",", ".");

            double price = Double.parseDouble(priceTextWithoutCurrency);

            actualPrices.add(price);
        }

        List<Double> sortedPrices = new ArrayList<>(actualPrices);

        Comparator<Double> comparator;

        if (sortDirection.equals("descending")) {
            comparator = Comparator.reverseOrder();
        } else {
            comparator = Comparator.naturalOrder();
        }

        sortedPrices.sort(comparator);

        assertThat("Products are not sorted correctly.", actualPrices, equalTo(sortedPrices));
    }

    private void verifySortingByName(String sortDirection) {
        List<String> actualNames = new ArrayList<>();

        for (WebElement nameContainer : productsGrid.getProductNames()) {
            String name = nameContainer.getText();

            actualNames.add(name);
        }

        List<String> sortedNames = new ArrayList<>(actualNames);

        Comparator<String> comparator;

        if (sortDirection.equals("descending")) {
            comparator = Comparator.reverseOrder();
        } else {
            comparator = Comparator.naturalOrder();
        }

        sortedNames.sort(comparator);

        assertThat("Products are not sorted correctly.", actualNames, equalTo(sortedNames));
    }


}
