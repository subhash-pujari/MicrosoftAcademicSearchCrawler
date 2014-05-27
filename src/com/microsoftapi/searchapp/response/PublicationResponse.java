package com.microsoftapi.searchapp.response;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;

import com.microsoftapi.searchapp.Constants;
import com.microsoftapi.searchapp.datastructure.Publication;

public class PublicationResponse extends BaseResponse{

	private ArrayList<Publication> result;
	
	PublicationResponse(HashMap response){
		JSONArray array = (JSONArray)response.get(Constants.RESULT);
		result = new ArrayList<Publication>();
		
		for(Object publication : array){
			HashMap map_publication = (HashMap)publication;
			Publication publication1 = new Publication(map_publication);
			result.add(publication1);
		}
	}
	
	ArrayList<Publication> getResult(){
		return result;
	}
}
