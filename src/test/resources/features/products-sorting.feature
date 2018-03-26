Feature: Products Sorting
  As a customer
  I want to sort products by different criteria
  So that I can easily find what I need

  Scenario: Sort by price
    Given I open the homepage
    And I search a product by "vase"
    When I sort the products by "Price"
    And I sort the products in descending order
    Then products are sorted by "Price" in descending order
