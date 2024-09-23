package stepDefination;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks
{

	@Before("@DeletePlaceAPI")
	public void beforeScenario() throws IOException
	
	{
		stepsDefination obj = new stepsDefination();
		if(stepsDefination.place_id==null) //As place id is declared as static so it will associated with class only
		{
		obj.add_place_payload_with("Fahad", "French", "FranceEurope");
		obj.user_callls_with_http_request("AddPlaceAPI", "POST");
		obj.verify_place_id_created_in_addplace_api_maps_with_using("Fahad", "getPlaceAPI");
		}
	}
}
