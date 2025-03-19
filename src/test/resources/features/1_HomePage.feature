
Feature: HomePage Search Functionality
@SearchTest
  Scenario: User searches for a product
    Given User is on the Bookswagon homepage
    When User searches for a book "Harry Potter" in the search bar
    Then Search results are displayed