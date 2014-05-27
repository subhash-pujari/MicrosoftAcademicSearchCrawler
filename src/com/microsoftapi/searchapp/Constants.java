package com.microsoftapi.searchapp;

public class Constants {

		//publication query string
		public static final String URL = "http://academic.research.microsoft.com/json.svc/search?";
		public static final String APP_ID = "AppId";
		public static final String APP_ID_VALUE = "AppId_value (to be filled by the user)";
		public static final String RESULT_OBJECT = "ResultObjects";
		public static final String PUBLICATION = "Publication";
		public static final String PUBLICATION_CONTENT = "PublicationContent";
		public static final String ALL_INFO = "AllInfo";
		public static final String PUBLICATION_ID = "PublicationID";
		public static final String START_ID = "StartIdx";
		public static final String END_ID = "EndIdx";
		public static final String AND = "&";
		public static final String EQUAL = "=";
		public static final String ORDER_BY = "OrderBy";
		public static final String REQUESTSTOREPATH = "";
		public static final String RESULT = "Result	";
		
	
		//string for publication
		public static final String ID = "ID";
		public static final String KEYWORD = "Keyword";
		public static final String TYPE = "Type";
		public static final String AUTHOR = "Author";
		public static final String CITATION_COUNT = "CitationCount";
		public static final String DOI = "DOI";
		public static final String TITLE = "Title";
		public static final String YEAR = "Year";
		public static final String REFERENCE_COUNT = "ReferenceCount";
		public static final String JOURNAL = "Journal";
		public static final String CONFERENCE = "Conference";
		public static final String CITATION_CONTEXT = "CitationContext";
		public static final String ABSTRACT = "Abstract";
		public static final String FULL_VERSION_URL = "FullVersionURL";
		public static final String REFERENCECHECKED = "ReferenceChecked";
		public static final int publication_id = 694978;
		
		//string for author queries
		public static final String AFFILIATION = "Affiliation";
		public static final String ORGANISATION = "Organisation";
		public static final String PUBLICATION_COUNT = "PublicationCount";
		public static final String RESEARCH_INTEREST_DOMAIN = "ResearchInterestDomain";
		public static final String GINDEX  = "GIndex";
		public static final String HINDEX = "HIndex";
		public static final String HOMEPAGEURL = "HomepageURL";
		public static final String REFERENCE_TYPE = "ReferenceType";
		public static final String CITATION = "Citation";
		public static final String REFERENCE = "Reference";
		public static final String FIRSTNAME = "FirstName";
		public static final String LASTNAME = "LastName";
		public static final String MIDDLE_NAME = "MiddleName";
		public static final String NATIVE_NAME = "NativeName";
		public static final String DISPLAY_PHOTO_URL = "DisplayPhotoURL";
		
		//string for the citation queries
		
		
		//string for organisation 
		public static final String AUTHOR_COUNT = "AuthorCount";
		
		//string for domain
		public static final String DOMAIN_ID = "DomainID";
		public static final String SUBDOMAIN_ID = "SubDomainID";
		public static final String NAME = "Name";
		
		//string for conference and journal
		public static final String FULLNAME = "FullName";
		public static final String SHORTNAME = "ShortName";
		public static final String ISSN = "ISSN";
		
		
		// new tables publicationreferencecitation and publicationtraversal
		public static final String PUBLICATION_REFERENCE_CITATION = 
				"PublicationReferenceCitation";
	//	public static final String PUBLICATION_ID = "publicationid";
		public static final String CITATION_REFERENCE = "citationreference";
		public static final String DOC_TYPE = "type";
		
	
		public static final String PUBLICATION_TRAVERSAL = "PublicationTraverse";
		public static final String PREDECESSOR = "predecessor";
		
		
		//queries
		/*public static String PublicationQuery = URL +
				APP_ID + EQUAL + APP_ID_VALUE + 
				AND + RESULT_OBJECT + EQUAL + PUBLICATION + 
				AND + PUBLICATION_CONTENT + EQUAL + ALL_INFO +
				AND +PUBLICATION_ID+"="+publication_id+
				AND + START_ID +"=1" +
				AND + END_ID+"=1";*/
						
		public static String PublicationCitationQuery = URL + 
				APP_ID + EQUAL + APP_ID_VALUE +
				AND + PUBLICATION_ID + EQUAL + publication_id +
				AND + RESULT_OBJECT + EQUAL + PUBLICATION +
				AND + REFERENCE_TYPE + EQUAL + CITATION +
				AND + START_ID + EQUAL + "1" + AND + END_ID + EQUAL + "10" +
				AND + ORDER_BY + EQUAL + YEAR;
		
		public static String str = "http://academic.research.microsoft.com/json.svc/search?" +
				"AppId=ea3c29c5-2fa3-4e99-aae6-86d88d1121cb" +
				"&PublicationID=694978" +
				"&ResultObjects=Publication" +
				"&ReferenceType=Citation" +
				"&StartIdx=1" +
				"&EndIdx=10" +
				"&OrderBy=Year";
}
