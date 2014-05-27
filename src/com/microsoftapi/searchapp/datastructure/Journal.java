package com.microsoftapi.searchapp.datastructure;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;

import com.microsoftapi.searchapp.Constants;

public class Journal {
	private long ID;
	private long PublicationCount;
	private long CitationCount;
	private String FullName;
	private String ShortName;
	private ArrayList<Domain> ResearchInterestDomain;
	private String ISSN;
	
	public Journal(HashMap map){
		this.ID = (Long)map.get(Constants.ID);
		this.PublicationCount = (Long)map.get(Constants.PUBLICATION_COUNT);
		this.CitationCount = (Long)map.get(Constants.CITATION_COUNT);
		this.FullName = (String)map.get(Constants.FULLNAME);
		this.ShortName = (String)map.get(Constants.SHORTNAME);
		JSONArray array = (JSONArray)map.get(Constants.RESEARCH_INTEREST_DOMAIN);
		this.ISSN = (String)map.get(Constants.ISSN);
	}
	
	public long getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public long getPublicationCount() {
		return PublicationCount;
	}

	public void setPublicationCount(int publicationCount) {
		PublicationCount = publicationCount;
	}

	public long getCitationCount() {
		return CitationCount;
	}

	public void setCitationCount(int citationCount) {
		CitationCount = citationCount;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getShortName() {
		return ShortName;
	}

	public void setShortName(String shortName) {
		ShortName = shortName;
	}

	public ArrayList<Domain> getResearchInterestDomain() {
		return ResearchInterestDomain;
	}

	public void setResearchInterestDomain(ArrayList<Domain> researchInterestDomain) {
		ResearchInterestDomain = researchInterestDomain;
	}

	public String getISSN() {
		return ISSN;
	}

	public void setISSN(String iSSN) {
		ISSN = iSSN;
	}
}
