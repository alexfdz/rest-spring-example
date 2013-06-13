@customers
Feature: A REST client queries for a customer resource
  As a REST client
  I want to get a customer resource information

 Scenario Outline: Customer list query  
   When I get the list of customers and accepted media type "<mediaType>"
   Then the response status is ok
   	And the response is not empty
  Examples:
    | mediaType			|
    | application/json	|
    | application/xml	|

 Scenario: Customer resources query with incorrect specific format  
   When I get the list of customers and accepted media type "text/html"
   Then the response status is not acceptable
   
 Scenario Outline: Customer resource query  
   When I get a customer with id "<id>"
   Then the response status is ok
   	And the person customer description is "<customerDescription>"
  Examples:
    | id | customerDescription |
    | EP94 | description EP94 |
    | EP18 | description EP18 |

 Scenario: Customer resource query with incorrect id
   When I get a customer with id "W19"
   Then the response status is not found
   
 Scenario Outline: Customer resource query with correct media types  
   When I get a customer with id "EP94" and accepted media type "<mediaType>"
   Then the response status is ok
   	And the response media type is "<mediaType>"
  Examples:
    | mediaType |
    | application/json |
    | application/xml |
    
 Scenario Outline: Customer resource query with not supported media types  
   When I get a customer with id "EP94" and accepted media type "<mediaType>"
   Then the response status is not acceptable
  Examples:
    | mediaType |
    | text/html |
    | text/xml |

 Scenario: Customers list OPTIONS query 
   When I ask the options for customers
   Then the response is empty
   	And the Allow header contains "POST,GET"
   	
  Scenario: Customer entity OPTIONS query 
   When I ask the options for a customer with id "EP94"
   Then the response is empty
   	And the Allow header contains "GET,PUT,DELETE"