package com.microsoftapi.searchapp.datastructure;

import java.util.HashMap;

import com.microsoftapi.searchapp.Constants;

public class Domain {

	
		private long DomainID;
		private long SubDomainID;
		private long PublicationCount;
		private long CitationCount;
		private String Name;
		
		public Domain(HashMap map){
			this.DomainID = (Long)map.get(Constants.DOMAIN_ID);
			this.SubDomainID = (Long)map.get(Constants.SUBDOMAIN_ID);
			this.PublicationCount = (Long)map.get(Constants.PUBLICATION_COUNT);
			this.CitationCount = (Long)map.get(Constants.CITATION_COUNT);
			this.Name = (String)map.get(Constants.NAME);
		}

		public long getDomainID() {
			return DomainID;
		}

		public void setDomainID(int domainID) {
			DomainID = domainID;
		}

		public long getSubDomainID() {
			return SubDomainID;
		}

		public void setSubDomainID(int subDomainID) {
			SubDomainID = subDomainID;
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

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}
}
