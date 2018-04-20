Feature: Products Sorting
  As a customer
  I want to sort products by different criteria
  So that I can easily find what I need

  Scenario Outline: Sort by price
    Given I open the homepage
    And I search a product by "<keyword>"
    When I sort the products by "<sort criteria>"
    And I sort the products in <sort direction> order
    Then products are sorted by "<sort criteria>" in <sort direction> order

    Examples:
      | keyword | sort criteria | sort direction |
      | vase    | Prices        | descending     |
      | shirt   | Name          | ascending      |