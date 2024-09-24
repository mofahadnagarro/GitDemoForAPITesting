package stepDefination;

import ResourcesTestData.APIResources;
import ResourcesTestData.TestData;
import ResourcesTestData.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.junit.Assert.*;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class stepsDefination extends Utils {
	RequestSpecification reqSpec;
	ResponseSpecification responseSpec;
	Response responseObj;
	TestData data = new TestData();
	static String place_id;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		
	    reqSpec = given().spec(requestSpecification()).body(data.addPayload(name, language, address));
	}

	@When("user callls {string} with {string} http request")
	public void user_callls_with_http_request(String resource, String httpMethod) {
//constructor in Enum class will be called once  we called valueOf(resource) method.
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if (httpMethod.equalsIgnoreCase("POST"))
		responseObj= reqSpec.when().post(resourceAPI.getResource());
		else if (httpMethod.equalsIgnoreCase("GET")) 
			responseObj= reqSpec.when().get(resourceAPI.getResource());			
		
	}
	@Then("API call is success wit status code {int}")
	public void api_call_is_success_wit_status_code(Integer int1) {
	  int statusCode =  responseObj.getStatusCode();
	  assertEquals(200, statusCode);
	  
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		//String respBody = responseObj.asString();
		  assertEquals(getJsonPath(responseObj,key), value);
	}
	
	@Then("Verify Place_Id created in AddplaceAPI maps with {string} using {string}")
	public void verify_place_id_created_in_addplace_api_maps_with_using(String expectedName, String resource) throws IOException {
		place_id = getJsonPath(responseObj, "place_id");
	    reqSpec = given().spec(requestSpecification()).queryParam("place_id",place_id);
	    user_callls_with_http_request(resource, "GET");
	    String actualName = getJsonPath(responseObj,"name");
	    System.out.println(actualName);
	    assertEquals(actualName,expectedName);
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		reqSpec = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}
}
