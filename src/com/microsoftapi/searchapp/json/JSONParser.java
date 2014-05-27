/*
 * 
 */
package com.microsoftapi.searchapp.json;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.microsoftapi.searchapp.Constants;
import com.microsoftapi.searchapp.database.DatabaseHandler;
import com.microsoftapi.searchapp.database.FileHandler;
import com.microsoftapi.searchapp.datastructure.Publication;

// TODO: Auto-generated Javadoc
/**
 * The Class JSONParser.
 */
public class JSONParser extends Thread{

	/** The database handler. */
	DatabaseHandler databaseHandler;
	
	/** The file handler. */
	FileHandler fileHandler;
	
	String response;
	
	
	/**
	 * Instantiates a new jSON parser.
	 */
	public JSONParser() {
		// TODO Auto-generated constructor stub
	//	databaseHandler = new DatabaseHandler();
		fileHandler = new FileHandler(Constants.REQUESTSTOREPATH);
	}
	
	public ArrayList<Publication> parsePublicationResponse(String response){
	
		this.response = response;
		ArrayList<Publication> publicationList = new ArrayList<Publication>();
		
		org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
		try {
			JSONObject object = (JSONObject)parser.parse(response);
			JSONObject map= (JSONObject)object.get("d");
			HashMap result = (HashMap)map.get("Publication");
			JSONArray array = (JSONArray)result.get("Result");
			for(int i=0; i<array.size(); i++){
				HashMap publicationInfo = (HashMap)array.get(i);
				Publication publication = new Publication(publicationInfo);
				publicationList.add(publication);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return publicationList;
	}
}
