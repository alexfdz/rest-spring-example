Feature: A user queries for a customer resource
  As a user
  I want to get a customer resource information

 Scenario Outline: Custoemr resource query  
   When I search for a valid customer resource with id "<id>"
   Then the person customer description is "<customerDescription>"
  Examples:
    | id | customerDescription |
    | EP94 | EP94 |
    | EP18 | EP18 |
    | EP22 | EP22 |

 Scenario: Customer resource query with incorrect id
   When I search for a valid customer resource with id "W19"
   Then the response fails with a not found error
   
 Scenario Outline: Customer resource query with specific format  
   When I search for a valid customer resource with id "EP94" and format "<format>"
   Then the response is successful
  Examples:
    | format           |
    | application/json |
    | application/xml  |
 
 Scenario: Custoemr resource query with incorrect specific format  
   When I search for a valid customer resource with id "1" and format "application/html"
   Then the response fails with an internal server error