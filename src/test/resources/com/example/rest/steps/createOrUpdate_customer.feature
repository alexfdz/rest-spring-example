Feature: A user creates a customer resource
  As a user
  I want to create a new customer

  Scenario: Create customer without required attributes 
   When I post to "/customers" and media type "application/json" with:
    """
    {
      "customerDescription": "A new customer",
      "notes": "Some customer notes"
    }
    """
   Then the status is 400
   
 Scenario: Create a correct customer 
   When I post to "/customers" and media type "application/json" with:
    """
    {
      "name": "Steve",
      "customerDescription": "A new customer",
      "notes": "Some customer notes"
    }
    """
   Then the status is 201
   	And the "Location" header is "http://localhost/customers/Steve"
   
  Scenario: Update an exisiting customer
   When I put to "/customers/EP94" and media type "application/json" with:
    """
    {
      "customerDescription": "A new description customer"
    }
    """
   Then the status is 204   
