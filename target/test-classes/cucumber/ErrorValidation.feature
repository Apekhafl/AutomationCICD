@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @tag2
  Scenario Outline: Submitting the oreder
    Given Logged in with username <username> and password <password>
    Then "Incorrect email or password." message is displayed as credentials are invalid

   Examples: 
      | username       | password  | productName  |
      | apex@gmail.com | Apex@1995 | ZARA COAT 3  |
