package com.microsoftapi.searchapp.datastructure;

import java.util.HashMap;

import com.microsoftapi.searchapp.Constants;

public class Keyword {
			
	
			private Long ID;
			private Long PublicationCount;
			private Long CitationCount;
			private String Name;

			public Keyword(HashMap object){
				this.ID = (Long)object.get(Constants.ID);
				this.PublicationCount = (Long)object.get(Constants.PUBLICATION_COUNT);
				this.CitationCount = (Long)object.get(Constants.CITATION_COUNT);
				this.Name = (String)object.get(Constants.NAME);
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

			public String getName() {
				return Name;
			}

			public void setName(String name) {
				Name = name;
			}

}
