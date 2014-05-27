package com.microsoftapi.searchapp.datastructure;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.microsoftapi.searchapp.Constants;

public class Author {
		
	private Long ID;
	private Long PublicationCount;
	private Long CitationCount;
	private String FirstName;
	private String MiddleName;
	private String LastName;
	private String NativeName;
	private String HomepageURL;
	private Organization Affiliation;
	private String DisplayPhotoURL;
	private Long HIndex;
	private Long GIndex;
	private ArrayList<Domain> ResearchInterestDomain;

	public Author(HashMap map){
			this.ID = (Long)map.get(Constants.ID);
			this.PublicationCount = (Long)map.get(Constants.PUBLICATION_COUNT);
			this.CitationCount = (Long)map.get(Constants.CITATION_COUNT);
			this.FirstName = (String)map.get(Constants.FIRSTNAME);
			this.MiddleName = (String)map.get(Constants.MIDDLE_NAME);
			this.LastName = (String)map.get(Constants.LASTNAME);
			this.NativeName = (String)map.get(Constants.NATIVE_NAME);
			this.HomepageURL = (String)map.get(Constants.HOMEPAGEURL);
			JSONObject organization_ = (JSONObject)map.get(Constants.AFFILIATION);
			this.DisplayPhotoURL = (String)map.get(Constants.DISPLAY_PHOTO_URL);
			this.HIndex = (Long)map.get(Constants.HINDEX);
			this.GIndex = (Long)map.get(Constants.GINDEX);
			JSONArray researchInterestList = (JSONArray)map.get(Constants.RESEARCH_INTEREST_DOMAIN);
			if(organization_!=null)
				this.Affiliation  = new Organization(organization_);
			
			ResearchInterestDomain = new ArrayList<Domain>();
			
			if(researchInterestList!=null){
				for(Object domain: researchInterestList){
					HashMap domainmap = (HashMap)domain;
					Domain dom = new Domain(domainmap);
					ResearchInterestDomain.add(dom);
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

	public Long getCitationCount() {
		return CitationCount;
	}

	public void setCitationCount(Long citationCount) {
		CitationCount = citationCount;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getMiddleName() {
		return MiddleName;
	}

	public void setMiddleName(String middleName) {
		MiddleName = middleName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getNativeName() {
		return NativeName;
	}

	public void setNativeName(String nativeName) {
		NativeName = nativeName;
	}

	public String getHomepageURL() {
		return HomepageURL;
	}

	public void setHomepageURL(String homepageURL) {
		HomepageURL = homepageURL;
	}

	public Organization getAffiliation() {
		return Affiliation;
	}

	public void setAffiliation(Organization affiliation) {
		Affiliation = affiliation;
	}

	public String getDisplayPhotoURL() {
		return DisplayPhotoURL;
	}

	public void setDisplayPhotoURL(String displayPhotoURL) {
		DisplayPhotoURL = displayPhotoURL;
	}

	public Long getHIndex() {
		return HIndex;
	}

	public void setHIndex(Long hIndex) {
		HIndex = hIndex;
	}

	public Long getGIndex() {
		return GIndex;
	}

	public void setGIndex(Long gIndex) {
		GIndex = gIndex;
	}

	public ArrayList<Domain> getResearchInterestDomain() {
		return ResearchInterestDomain;
	}

	public void setResearchInterestDomain(ArrayList<Domain> researchInterestDomain) {
		ResearchInterestDomain = researchInterestDomain;
	}

}

