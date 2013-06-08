Feature: A user queries for multiple customer resources
  As a user
  I want to get a customers resource information

 Scenario Outline: Customer resources query  
   When I search for all the exisiting resources and format "<format>"
   Then the status is 200
   Then the response is not empty
  Examples:
    | format           |
    | application/json |
    | application/xml  |

 Scenario: Customer resources query with incorrect specific format  
   When I search for all the exisiting resources and format "text/html"
   Then the status is 406