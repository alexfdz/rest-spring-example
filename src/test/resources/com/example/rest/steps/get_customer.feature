Feature: A user queries for a customer resource
  As a user
  I want to get a customer resource information

 Scenario Outline: Customer resource query  
   When I search for a valid customer resource with id "<id>"
   Then the status is 200
   Then the person customer description is "<customerDescription>"
  Examples:
    | id | customerDescription |
    | EP94 | description EP94 |
    | EP18 | description EP18 |

 Scenario: Customer resource query with incorrect id
   When I search for a valid customer resource with id "W19"
   Then the status is 404
   
 Scenario Outline: Customer resource query with specific format  
   When I search for a valid customer resource with id "EP94" and format "<format>"
   Then the status is 200
   Then the response format is "<format>"
  Examples:
    | format           |
    | application/json |
    | application/xml  |
 
 Scenario: Customer resource query with incorrect specific format  
   When I search for a valid customer resource with id "EP94" and format "text/html"
   Then the status is 406