@customers
Feature: A REST client queries for a customer resource
  As a REST client
  I want to get a customer resource information
  
  Background: The system cointains two customers
  	Given the system contains the following customer:
		"""
		{
		  "name": "Mark",
		  "customerDescription": "Mark description",
		  "notes": "Customer notes"
		}
		"""
	  And the system contains the following customer:
		"""
		{
		  "name": "Rose",
		  "customerDescription": "Rose description",
		  "notes": "Customer notes"
		}
		"""
		
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
   	And the response contains a correct customer
   	And the customer description is "<customerDescription>"
  Examples:
    | id | customerDescription |
    | Mark | Mark description |
    | Rose | Rose description |

 Scenario: Customer resource query with incorrect id
   When I get a customer with id "Chris"
   Then the response status is not found
   
 Scenario Outline: Customer resource query with correct media types  
   When I get a customer with id "Mark" and accepted media type "<mediaType>"
   Then the response status is ok
   	And the response media type is "<mediaType>"
  Examples:
    | mediaType |
    | application/json |
    | application/xml |
    
 Scenario Outline: Customer resource query with not supported media types  
   When I get a customer with id "Mark" and accepted media type "<mediaType>"
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
   When I ask the options for a customer with id "Mark"
   Then the response is empty
   	And the Allow header contains "GET,PUT,DELETE"