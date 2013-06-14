@customers
Feature: A REST client wants to send a message to an existing customer
  As a REST client
  I want to send a message to an existing customer

  Background: The system cointains a customer
    Given the system contains the following customer:
      """
      {
        "name": "Mark",
        "customerDescription": "Mark description",
        "notes": "Customer notes"
      }
      """

  Scenario: Send a message to a customer
    When I post to "/customers/Mark/sendMessage" and media type "application/json" with:
      """
      {
        "text": "Hello Mark!"
      }
      """
    Then the response status is ok
    
  Scenario: Send an empty message to a customer
    When I post to "/customers/Mark/sendMessage" and media type "application/json" with:
      """
      """
    Then the response status is bad request