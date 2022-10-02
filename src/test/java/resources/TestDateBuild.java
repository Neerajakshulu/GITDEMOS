package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDateBuild {
	public AddPlace addPlacePayload(String name,String language,String address) {
		AddPlace p=new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		
		p.setLanguage(language);
		p.setPhone_number("9123456780");
		p.setWebsite("www.gmail.com");
		p.setName(name);
		List<String> myList=new ArrayList<String>();
		myList.add("abc");
		myList.add("xyz");
		
		p.setTypes(myList);
		Location l=new Location();
		l.setLat(23);
		l.setLng(234);
		
		return p;
		
	}
	public String deletePlacePayload(String placeID) {
		return "{\r\n   \"place_id\":\""+placeID+"\"\r\n}";
	}

}
