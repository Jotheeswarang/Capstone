Feature: User Registration and Login
@UserRegistrationTest
  Scenario: Automate the user registration process, login, and change password
    Given User on homepage
    When User registers an account
    When User logs in with mobile number "9941207246" and password "Jone@2002"
    When User changes the password to "Jone"
    Then displays password changed successfully