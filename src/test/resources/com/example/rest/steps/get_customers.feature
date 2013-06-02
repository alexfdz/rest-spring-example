Feature: A user queries for multiple customer resources
  As a user
  I want to get a customers resource information

 Scenario Outline: Customer resources query  
   When I search for all the exisiting resources and format "<format>"
   Then I should get a successful status and not empty response
  Examples:
    | format           |
    | application/json |
    | application/xml  |

 Scenario: Customer resources query with incorrect specific format  
   When I search for all the exisiting resources and format "text/html"
   Then the response fails with an Not acceptable error