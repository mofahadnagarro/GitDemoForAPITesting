package ResourcesTestData;

//An enum is a special class that represents a group of constants and variables(unchangable varibles)
public enum APIResources 
{
AddPlaceAPI("/maps/api/place/add/json"), //nameof the method(AddPlaceAPI) should be same as in .feature file steps
getPlaceAPI("/maps/api/place/get/json"),
deletePlaceAPI("/maps/api/place/delete/json");
private String resource;

APIResources(String resource)
{
	this.resource=resource;
}

public String getResource()
{
	return resource;
	
}

}
