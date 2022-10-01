package stepdefinition;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDateBuild;
import resources.Utils;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class StepDefinition extends Utils{
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	
	TestDateBuild data=new TestDateBuild();
	static String place_Id;
	@Given("Add Place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		
		 resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 res=given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address)).log().all();
		
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
	    // Write code here that turns the phrase above into concrete actions
		APIResources ressp=APIResources.valueOf(resource);
		System.out.println(ressp.getResource());
		 resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(httpMethod.equalsIgnoreCase("POST")) 
		 response = res.when().post(ressp.getResource());
		else if(httpMethod.equalsIgnoreCase("GET")) 
			 response = res.when().get(ressp.getResource());
		

	}
	@Then("the API call is success with status code {int}")
	public void the_api_call_got_success_with_status_code1(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
         assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions  
	   assertEquals(getJSONPath(response,keyValue), Expectedvalue);
	}
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName,String resource) {
	    // Write code here that turns the phrase above into concrete actions
		 place_Id=getJSONPath(response,"place_Id");
         res=given().spec(requestSpecification).queryParam("place_Id", place_Id);
         user_calls_with_http_request(resource, "GET");
         String actualName=getJSONPath(response, "name");
         assertEquals(actualName, expectedName);
	   
	}
	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException {
	 given().spec(requestSpecification()).body(data.deletePlacePayload(place_Id));
	}
	



}
