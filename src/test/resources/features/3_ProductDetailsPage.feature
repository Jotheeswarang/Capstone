Feature: Product Details Page
@ProductDetailsPageTest
  Scenario: Validate the product title, description, price, images, and availability 
    Given User Entered the homepage
    When User login to the acc with mobile number "9941207246" and password "Jone@2002"
    When User searches for "Harry Potter" in the search bar
    When User filters
    When User validate the product
    When User adding the product to the wishlist
    When User adding the product to the cart
    Then Product is displayed in the cart