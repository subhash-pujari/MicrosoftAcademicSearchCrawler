package com.microsoftapi.searchapp.response;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;

import com.microsoftapi.searchapp.Constants;
import com.microsoftapi.searchapp.datastructure.Author;
import com.microsoftapi.searchapp.datastructure.Publication;

public class AuthorResponse extends BaseResponse{

	private ArrayList<Author> result;
	
	AuthorResponse(HashMap response){
		JSONArray array = (JSONArray)response.get(Constants.RESULT);
		result = new ArrayList<Author>();
		
		for(Object author : array){
			HashMap map_author = (HashMap)author;
			Author author1 = new Author(map_author);
			result.add(author1);
		}
	}
	
	ArrayList<Author> getResult(){
		return result;
	}
}