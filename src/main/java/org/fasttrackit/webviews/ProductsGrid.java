package org.fasttrackit.webviews;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductsGrid {

    @FindBy(css = ".product-name a")
    private List<WebElement> productNames;

    @FindBy(css = ".sort-by select")
    private WebElement sortByList;

    @FindBy(className = "sort-by-switcher")
    private WebElement sortDirectionLink;

    @FindBy(xpath = "//div[@class='price-box']//*[@class!='old-price']//*[@class='price']")
    private List<WebElement> productPrices;

    public List<WebElement> getProductPrices() {
        return productPrices;
    }

    public WebElement getSortDirectionLink() {
        return sortDirectionLink;
    }

    public Select getSortByList() {
        return new Select(sortByList);
    }

    public List<WebElement> getProductNames() {
        return productNames;
    }
}
