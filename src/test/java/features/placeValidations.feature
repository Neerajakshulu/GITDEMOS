Feature: Validationg Place APIs 

@addPlace @Regression
Scenario Outline: Verify if place is successfully added using AddPlaceAPI
    Given Add Place payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"
    
Examples:
     | name    | language | address              |
     | aahouse |  English | World class          | 
     | bbhouse | spanish  | CCross Centre        |

@deletePlace @Regression 
Scenario: Verify if Delete Place functionality is working
    Given DeletePlace payload
    When user calls "deletePlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    