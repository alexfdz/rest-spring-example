Feature: A user queries the Customer endpoints options
  As a user
  I want to get the OPTIONS method responses for customer collection and customer resource

 Scenario Outline: Customer OPTIONS methods 
   When I ask the options for "<endpoint>"
   Then the status is 204
   	And the response is empty
   	And the Allow header contains "<allowedMethods>"
  Examples:
    | endpoint | allowedMethods |
    | /customers | POST,GET |
    | /customers/EP94  | GET,PUT,DELETE |