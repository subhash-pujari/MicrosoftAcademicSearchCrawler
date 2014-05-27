package com.microsoftapi.searchapp.request;

public class Request {

		private String Version;
		private String AppID;
		private long PublicationID;
		private long AuthorID; 
		private long ConferenceID;
		private long JournalID;
		private long OrganisationID;
		private long DomainID;
		private long SubDomainID;
		private long KeywordID;
		private String TitleQuery;
		private String AuthorQuery;
		private String ConferenceQuery;
		private String JournalQuery;
		private String FullTextQuery;
		private String ResultObjectType;
		private String ReferenceType;
		private String PublicationContent;
		private String OrderBy;
		private short YearStart;
		private short YearEnd;
		private int StartIdx;
		private int EndIdx;
		
		public Request() {
			// TODO Auto-generated constructor stub
			this.AppID = AppID;
			this.Version = Version;
		}
		
		public String getVersion() {
			return Version;
		}
		public void setVersion(String version) {
			Version = version;
		}
		public String getAppID() {
			return AppID;
		}
		public void setAppID(String appID) {
			AppID = appID;
		}
		public long getPublicationID() {
			return PublicationID;
		}
		public void setPublicationID(long publicationID) {
			PublicationID = publicationID;
		}
		public long getAuthorID() {
			return AuthorID;
		}
		public void setAuthorID(int authorID) {
			AuthorID = authorID;
		}
		public long getConferenceID() {
			return ConferenceID;
		}
		public void setConferenceID(int conferenceID) {
			ConferenceID = conferenceID;
		}
		public long getJournalID() {
			return JournalID;
		}
		public void setJournalID(int journalID) {
			JournalID = journalID;
		}
		public long getOrganisationID() {
			return OrganisationID;
		}
		public void setOrganisationID(int organisationID) {
			OrganisationID = organisationID;
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
		public long getKeywordID() {
			return KeywordID;
		}
		public void setKeywordID(int keywordID) {
			KeywordID = keywordID;
		}
		public String getTitleQuery() {
			return TitleQuery;
		}
		public void setTitleQuery(String titleQuery) {
			TitleQuery = titleQuery;
		}
		public String getAuthorQuery() {
			return AuthorQuery;
		}
		public void setAuthorQuery(String authorQuery) {
			AuthorQuery = authorQuery;
		}
		public String getConferenceQuery() {
			return ConferenceQuery;
		}
		public void setConferenceQuery(String conferenceQuery) {
			ConferenceQuery = conferenceQuery;
		}
		public String getJournalQuery() {
			return JournalQuery;
		}
		public void setJournalQuery(String journalQuery) {
			JournalQuery = journalQuery;
		}
		public String getFullTextQuery() {
			return FullTextQuery;
		}
		public void setFullTextQuery(String fullTextQuery) {
			FullTextQuery = fullTextQuery;
		}
		public String getResultObjectType() {
			return ResultObjectType;
		}
		public void setResultObjectType(String resultObjectType) {
			ResultObjectType = resultObjectType;
		}
		public String getReferenceType() {
			return ReferenceType;
		}
		public void setReferenceType(String referenceType) {
			ReferenceType = referenceType;
		}
		public String getPublicationContent() {
			return PublicationContent;
		}
		public void setPublicationContent(String publicationContent) {
			PublicationContent = publicationContent;
		}
		public String getOrderBy() {
			return OrderBy;
		}
		public void setOrderBy(String orderBy) {
			OrderBy = orderBy;
		}
		public short getYearStart() {
			return YearStart;
		}
		public void setYearStart(short yearStart) {
			YearStart = yearStart;
		}
		public short getYearEnd() {
			return YearEnd;
		}
		public void setYearEnd(short yearEnd) {
			YearEnd = yearEnd;
		}
		public int getStartIdx() {
			return StartIdx;
		}
		public void setStartIdx(int startIdx) {
			StartIdx = startIdx;
		}
		public int getEndIdx() {
			return EndIdx;
		}
		public void setEndIdx(int endIdx) {
			EndIdx = endIdx;
		}
		
}
