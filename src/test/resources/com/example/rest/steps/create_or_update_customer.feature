@customers
Feature: A REST client creates or updates customer resources
  As a REST client
  I want to create a new customer or update an existing one

  Scenario: Create customer without required attributes 
   When I create a customer with media type "application/json" and content:
    """
    {
      "customerDescription": "A new customer",
      "notes": "Some customer notes"
    }
    """
   Then the response status is bad request
   
 Scenario: Create a correct customer 
   When I create a customer with media type "application/json" and content:
    """
    {
      "name": "Steve",
      "customerDescription": "A new customer",
      "notes": "Some customer notes"
    }
    """
   Then the response status is created
   	And the Location header is "http://localhost/customers/Steve"
   
  Scenario: Update an exisiting customer
   When I update a customer with id "EP94", media type "application/json" and content:
    """
    {
      "customerDescription": "A new description customer"
    }
    """
   Then the response status is no content
