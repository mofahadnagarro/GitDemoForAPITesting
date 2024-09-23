package ResourcesTestData;

import java.util.ArrayList;
import java.util.List;

import Pojo.PojoClassForSerilizationDemoTest;
import Pojo.pojoClassForLocationInDemoTest;

public class TestData
{
	public PojoClassForSerilizationDemoTest addPayload(String name, String language, String address)
	{
		PojoClassForSerilizationDemoTest p = new PojoClassForSerilizationDemoTest();
		p.setAccuracy(15);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number(1234);
		p.setWebsite("rahul");
		p.setName(name);
		//for type as its return array of list 
		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe-park");
		mylist.add("shop");
		p.setTypes(mylist);
		// for Location as its returns a class object
		pojoClassForLocationInDemoTest l = new pojoClassForLocationInDemoTest();
		l.setLat(38.83);
		l.setLng(33.3456);
		p.setLocation(l);
		return p;
	}
	
	public String deletePlacePayload(String PlaceID)
	{
return "{\r\n"
		+ "    \"place_id\":\""+PlaceID+"\"\r\n"
		+ "}";
	}

}
