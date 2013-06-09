Feature: A user queries for a customer resource
  As a user
  I want to get a customer resource information

 Scenario Outline: Customer list query  
   When I get "/customers" and accepted media type "<mediaType>"
   Then the status is 200
   	And the response is not empty
  Examples:
    | mediaType           |
    | application/json |
    | application/xml  |

 Scenario: Customer resources query with incorrect specific format  
   When I get "/customers" and accepted media type "text/html"
   Then the status is 406
   
 Scenario Outline: Customer resource query  
   When I get "/customers/<id>"
   Then the status is 200
   	And the person customer description is "<customerDescription>"
  Examples:
    | id | customerDescription |
    | EP94 | description EP94 |
    | EP18 | description EP18 |

 Scenario: Customer resource query with incorrect id
   When I get "/customers/W19"
   Then the status is 404
   
 Scenario Outline: Customer resource query with correct media types  
   When I get "/customers/EP94" and accepted media type "<mediaType>"
   Then the status is 200
   	And the response media type is "<mediaType>"
  Examples:
    | mediaType |
    | application/json |
    | application/xml |
    
 Scenario Outline: Customer resource query with not supported media types  
   When I get "/customers/EP94" and accepted media type "<mediaType>"
   Then the status is 406
  Examples:
    | mediaType |
    | text/html |
    | text/xml |