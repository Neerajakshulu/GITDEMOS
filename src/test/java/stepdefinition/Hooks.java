package stepdefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeSecenario() throws IOException {
		StepDefinition m=new StepDefinition();
		if(StepDefinition.place_Id==null) {
		m.add_place_payload_with("Neeraj", "India", "Asia");
		m.user_calls_with_http_request("AddPlaceAPI", "POST");
		m.verify_place_Id_created_maps_to_using("Neeraj", "getPlaceAPI");
	}
	}

}
