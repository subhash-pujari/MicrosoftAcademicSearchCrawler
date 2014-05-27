package com.microsoftapi.searchapp.datastructure;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;

import com.microsoftapi.searchapp.Constants;

public class Organization {
	private Long ID;
	private Long PublicationCount;
	private Long AuthorCount;
	private Long CitationCount;
	private String Name;
	private String HomepageURL;
	private ArrayList<Domain> ResearchInterestDomain;
		
	public Organization(HashMap map){
			this.ID = (Long)map.get(Constants.ID);
			this.PublicationCount = (Long)map.get(Constants.PUBLICATION_COUNT);
			this.AuthorCount = (Long)map.get(Constants.AUTHOR_COUNT);
			this.CitationCount = (Long)map.get(Constants.CITATION_COUNT);
			this.Name = (String)map.get(Constants.NAME);
			this.HomepageURL = (String)map.get(Constants.HOMEPAGEURL);
			JSONArray array = (JSONArray)map.get(Constants.RESEARCH_INTEREST_DOMAIN);
			
			ResearchInterestDomain = new ArrayList<Domain>();
			if(array!=null){
				for(Object domain: array){
					HashMap domainMap = (HashMap)domain;
					Domain domain1 = new Domain(domainMap);
					ResearchInterestDomain.add(domain1);
				}
			}
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public Long getPublicationCount() {
		return PublicationCount;
	}

	public void setPublicationCount(Long publicationCount) {
		PublicationCount = publicationCount;
	}

	public Long getAuthorCount() {
		return AuthorCount;
	}

	public void setAuthorCount(Long authorCount) {
		AuthorCount = authorCount;
	}

	public Long getCitationCount() {
		return CitationCount;
	}

	public void setCitationCount(Long citationCount) {
		CitationCount = citationCount;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getHomepageURL() {
		return HomepageURL;
	}

	public void setHomepageURL(String homepageURL) {
		HomepageURL = homepageURL;
	}

	public ArrayList<Domain> getResearchInterestDomain() {
		return ResearchInterestDomain;
	}

	public void setResearchInterestDomain(ArrayList<Domain> researchInterestDomain) {
		ResearchInterestDomain = researchInterestDomain;
	}
}
