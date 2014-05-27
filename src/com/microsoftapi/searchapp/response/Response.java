package com.microsoftapi.searchapp.response;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.microsoftapi.searchapp.Constants;
import com.microsoftapi.searchapp.datastructure.Publication;

public class Response {
	
		private String Version;
		private int ResultCode;
		private HashMap d;
		private HashMap Publication;
		private HashMap Author;
		private HashMap Conference;
		private HashMap Journal;
		private HashMap Organisation;
		private HashMap Domain;
		private HashMap Keyword;
		private String response;
		private int Type;
		private JSONObject json;
		
		public static final int TYPE_PUBLICATION = 0;
		public static final int TYPE_AUTHOR = 1;
		public static final int TYPE_CONFERENCE = 2;
		public static final int TYPE_JOURNAL = 3;
		public static final int TYPE_ORGANISATION = 4;
		public static final int TYPE_KEYWORD = 5;
		
		public Response(String response) {
			// TODO Auto-generated constructor stub
			this.response = response;
			try {
				json = (JSONObject)new JSONParser().parse(response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			d = (HashMap)json.get("d");
			Publication = (HashMap)d.get(Constants.PUBLICATION);
			Author = (HashMap)d.get(Constants.AUTHOR);
			Conference = (HashMap)d.get(Constants.CONFERENCE);
			Journal = (HashMap)d.get(Constants.JOURNAL);
			Organisation = (HashMap)d.get(Constants.ORGANISATION);
		//	map.get(Constants.DOMAIN);
			Keyword = (HashMap)d.get(Constants.KEYWORD);
			
			if(Publication!=null){
				this.Type = TYPE_PUBLICATION;
			}
			
			if(Author!=null){
				this.Type = TYPE_AUTHOR;
			}
			
			if(Conference!=null){
				this.Type = TYPE_CONFERENCE;
			}
			
			if(Journal!=null){
				this.Type = TYPE_JOURNAL;
			}
			
			if(Organisation!=null){
				this.Type = TYPE_ORGANISATION;
			}
		
		}
		
		public int responseType(){
			return Type;
		}
		
		public boolean isResponsePublication(){
			if(Type == this.TYPE_PUBLICATION){
				return true;
			}else{
				return false;
			}
		}
}
