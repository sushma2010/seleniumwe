
@tag
Feature: Purchase order from ecommerce website
  I want to use this template for my feature file

Background:
Given I landed on EcommercePage

  @Regression
  Scenario Outline: Positive test of Submitting the order
    Given Logged in with username <name> and password is <password>
    When I add product <ProductName>  to cart
    And Checkout <ProductName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on conformationPage

    Examples: 
      | name  | password | ProductName|
      | suhani@gmail.com | Suhani@2010|ADIDAS ORIGINAL |
      
