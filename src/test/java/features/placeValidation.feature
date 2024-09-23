Feature: Validating Place API

@AddAndGetPlaceAPI
Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
Given Add Place Payload with "<name>" "<language>" "<address>"
When user callls "AddPlaceAPI" with "POST" http request
Then API call is success wit status code 200 
And "status" in response body is "OK"
And Verify Place_Id created in AddplaceAPI maps with "<name>" using "getPlaceAPI"

Examples:
|name |language|address   |
|Test|English |Karol Bagh|
# |Test2|Hindi   |PunjaiBagh|

@DeletePlaceAPI
Scenario: Verify Delete Place API functionality is working 
Given DeletePlace Payload
When user callls "deletePlaceAPI" with "POST" http request
Then API call is success wit status code 200
And "status" in response body is "OK"
