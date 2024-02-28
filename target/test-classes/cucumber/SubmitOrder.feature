@tag
Feature: Title of your feature
  I want to use this template for my feature file

Background:
Given I laned on Ecommerce platform

  @Regression
  Scenario Outline: Submitting the oreder
    Given Logged in with username <username> and password <password>
    When I add product <productName> to the cart 
    And Enter <countryName> Checkout and submit the order
    Then "THANK YOU FOR YOUR ORDER." message is displayed on Confirmation Page

    Examples: 
      | username       | password  | productName  | countryName |
      | apex@gmail.com | Apex@1995 | ZARA COAT 3  | India       |
