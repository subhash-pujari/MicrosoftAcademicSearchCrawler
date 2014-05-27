package com.microsoftapi.searchapp.datastructure;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.microsoftapi.searchapp.Constants;

public class Publication {
		
		private Long ID;
		private Long CitationCount;
		private Long ReferenceCount;
		private Long PublicationType;
		private Long Year;
		private String Title;
		private String Abstract;
		private Conference conference;
		private Journal journal;
		private ArrayList<Keyword> keywords;
		private ArrayList<Author> Authors;
		private String DOI;
		private ArrayList<String> FullVersionURL;
		private ArrayList<Long> citations;
		
		
		public Publication(long id){
			this.ID = id;
		}
		

		public Publication(HashMap object){
			
			this.keywords = new ArrayList<Keyword>();
			this.Authors = new ArrayList<Author>();		
			this.citations = new ArrayList<Long>();
			JSONArray keywords = (JSONArray)object.get(Constants.KEYWORD);
			this.PublicationType = (Long)object.get(Constants.TYPE);
			JSONArray author_list = (JSONArray)object.get(Constants.AUTHOR);
			this.CitationCount = (Long)object.get(Constants.CITATION_COUNT);
			this.Title = (String)object.get(Constants.TITLE);
			this.Year = (Long)object.get(Constants.YEAR);
			this.ReferenceCount = (Long)object.get(Constants.REFERENCE_COUNT);
			this.ID = (Long)object.get(Constants.ID);
			this.DOI = (String)object.get(Constants.DOI);
			this.Abstract = (String)object.get(Constants.ABSTRACT);
			HashMap Conference = (HashMap)object.get(Constants.CONFERENCE);
			HashMap Journal = (HashMap)object.get(Constants.JOURNAL);
			
			if(Conference!=null){
				this.conference = new Conference(Conference);
			}
			
			if(Journal!=null){
				this.journal = new Journal(Journal);
			}
			for(Object simpleobject: keywords){
				HashMap jsonobject = (HashMap)simpleobject;
				Keyword keyword1 = new Keyword(jsonobject);
				this.keywords.add(keyword1);
			}
			
			for(Object simpleobject: author_list){
				HashMap jsonobject = (HashMap)simpleobject;
				Author author = new Author(jsonobject);
				this.Authors.add(author);
			}
		}
		
		@Override
		public boolean equals(Object obj) {
		// TODO Auto-generated method stub
			Publication pub = (Publication)obj;
			if(pub.ID == this.ID){
				return true;
			}else{
				return false;
			}
		}
		
		
		public Long getID() {
			return ID;
		}

		public void setID(Long iD) {
			ID = iD;
		}

		public Long getCitationCount() {
			return CitationCount;
		}

		public void setCitationCount(Long citationCount) {
			CitationCount = citationCount;
		}

		public Long getReferenceCount() {
			return ReferenceCount;
		}

		public void setReferenceCount(Long referenceCount) {
			ReferenceCount = referenceCount;
		}

		public Long getPublicationType() {
			return PublicationType;
		}

		public void setPublicationType(Long publicationType) {
			PublicationType = publicationType;
		}

		public Long getYear() {
			return Year;
		}

		public void setYear(Long year) {
			Year = year;
		}

		public String getTitle() {
			return Title;
		}

		public void setTitle(String title) {
			Title = title;
		}

		public String getAbstract() {
			return Abstract;
		}

		public void setAbstract(String abstract1) {
			Abstract = abstract1;
		}

		public ArrayList<Author> getAuthors() {
			return Authors;
		}

		public void setAuthors(ArrayList<Author> authors) {
			Authors = authors;
		}

		public Conference getConference() {
			return conference;
		}

		public void setConference(Conference conference) {
			this.conference = conference;
		}

		public Journal getJournal() {
			return journal;
		}

		public void setJournal(Journal journal) {
			this.journal = journal;
		}

		public ArrayList<Keyword> getKeywords() {
			return keywords;
		}

		public void setKeywords(ArrayList<Keyword> keywords) {
			this.keywords = keywords;
		}

		public String getDOI() {
			return DOI;
		}

		public void setDOI(String dOI) {
			DOI = dOI;
		}

		public ArrayList<String> getFullVersionURL() {
			return FullVersionURL;
		}

		public void setFullVersionURL(ArrayList<String> fullVersionURL) {
			FullVersionURL = fullVersionURL;
		}

		public ArrayList<Long> getCitations() {
			return citations;
		}

		public void setCitations(ArrayList<Long> citations) {
			this.citations = citations;
		}

		
		public boolean equals(Publication other){
			
			if(this.ID == other.ID){
				return true;
			}else{
				return false;
			}

		}
}
