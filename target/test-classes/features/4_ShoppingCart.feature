Feature: Shopping cart Page
@ShoppingCartTest
  Scenario: Automate adding items to the cart, removing them, updating quantities, and proceeding to checkout
    Given User Entered homepage
    When User logged in to the acc with mobile number "9941207246" and password "Jone@2002"
    When User searches "Harry Potter" adds other products to the cart
    When User removes the product and updates the quantities
    When User proceeds to the checkout
    Then Checkout page is displayed