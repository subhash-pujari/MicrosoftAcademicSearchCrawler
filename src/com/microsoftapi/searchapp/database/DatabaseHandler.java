package com.microsoftapi.searchapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.jws.Oneway;
import javax.swing.JFrame;

import com.microsoftapi.searchapp.Constants;
import com.microsoftapi.searchapp.datastructure.Author;
import com.microsoftapi.searchapp.datastructure.Conference;
import com.microsoftapi.searchapp.datastructure.Domain;
import com.microsoftapi.searchapp.datastructure.Journal;
import com.microsoftapi.searchapp.datastructure.Keyword;
import com.microsoftapi.searchapp.datastructure.Organization;
import com.microsoftapi.searchapp.datastructure.Publication;

// TODO: Auto-generated Javadoc
/**
 * The Class DatabaseHandler.
 */
public class DatabaseHandler {

	/** The connect. */
	static Connection connect;
	
	/** The statement. */
	Statement statement;
	
	/** The result set. */
	ResultSet resultSet;
	
	/** The prepared statement. */
	PreparedStatement preparedStatement;
	
	/*Constants for checking the database for the existence of the data*/
	
	/** The Constant PUBLICATION_CODE. */
	private static final int PUBLICATION_CODE = 0;
	
	/** The Constant AUTHOR_CODE. */
	private static final int AUTHOR_CODE = 1;
	
	/** The Constant KEYWORD_CODE. */
	private static final int KEYWORD_CODE = 2;
	
	/** The Constant CONFERENCE_CODE. */
	private static final int CONFERENCE_CODE = 3;
	
	/** The Constant JOURNAL_CODE. */
	private static final int JOURNAL_CODE = 4;
	
	/** The Constant DOMAIN_CODE. */
	private static final int DOMAIN_CODE = 5;
	
	/** The Constant ORGANISATION_CODE. */
	private static final int ORGANISATION_CODE = 6;
	

	/**
	 * Instantiates a new database handler.
	 */
	public DatabaseHandler(String databaseName) {
		// TODO Auto-generated constructor stub
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			//to close access connection to the database we used "&dontTrackOpenResources=true"
	//change for memory issue  - start 
			connect = DriverManager
				      .getConnection("jdbc:mysql://localhost/"+databaseName+"?"
				          + "user=user_name&password=(passwordofuser)&dontTrackOpenResources=true");
			
	//change for memory issue  - end
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Insert publication.
	 *
	 * @param publication the publication
	 * @return the int
	 */
	public int insertPublication(Publication publication){
		
		ArrayList<Author> authors = publication.getAuthors();
		ArrayList<Keyword> keywords = publication.getKeywords();
		ArrayList<String> fullVersionUrl = publication.getFullVersionURL();
		long publicationId = publication.getID();
		long citationCount;
		if(publication.getCitationCount()!= null){
			citationCount = publication.getCitationCount();
		}else{
			citationCount  = 0;
		}
		
		
		
		long reference ;
		if(publication.getCitationCount()!= null){
			reference = publication.getReferenceCount();
		}else{
			reference  = 0;
		}
		
		long type = 0;
		long year = 0;
		String title = null;
		String _abstract = null;
		long conference = 0;
		long journal = 0;
		String doi;
		int referenceChecked = 0;
		
		if(publication.getConference()!=null){
			insertConference(publication.getConference());
			conference = publication.getConference().getID();
		}else{
			conference = 0;
		}
		
		if(publication.getJournal()!=null){
			insertJournal(publication.getJournal());
			journal = publication.getJournal().getID();
		}else{
			journal = 0;
		}
		
		if(publication.getDOI()!=null){
			doi = "'"+publication.getDOI()+"'";
		}else{
			doi = "NULL";
		}
		if(publication.getTitle()!=null){
			title = removePunctuation(publication.getTitle());
			title = "'"+title+"'";
		}
		if(publication.getAbstract()!=null){
			_abstract = removePunctuation(publication.getAbstract());
			_abstract = "'"+_abstract+"'";
		}
		
		Long[] AuthorIds = new Long[7];
			
		if(authors!=null){
			int i = 0;
			for(Author author: authors){
				
				
				if(i==7){
					break;
				}
				insertAuthor(author);
				AuthorIds[i++] = author.getID();
			}
		}	
		Long[] keywordIds = new Long[15];
		if(keywords!=null){
				int i = 0;
				for(Keyword keyword: keywords){
					if(i==15){
						break;
					}
					
					insertKeyword(keyword);
					keywordIds[i++] = keyword.getID();
				}
		}
		
		String[] fullversionUrl = new String[6];
			
			if(fullVersionUrl!=null){
				int i=0;
				for(String url: fullVersionUrl){
					if(i==6){
						break;
					}
					if(url!=null){
						url = "'"+url+"'";
					}
					fullversionUrl[i++] = url;
				}
			}
			
			
		
		if(alreadyExist(publication.getID(), PUBLICATION_CODE)){
			return 0;
		}
		
		String str = "insert into Publication("+Constants.ID+", "+Constants.CITATION_COUNT+", "+Constants.REFERENCE_COUNT+", "
 				+Constants.TYPE+", "+Constants.YEAR+", "+Constants.TITLE+", "+Constants.ABSTRACT+", "+Constants.AUTHOR+"1, "
 						+Constants.AUTHOR+"2, "+Constants.AUTHOR+"3, "+Constants.AUTHOR+"4, "+Constants.AUTHOR+"5, "+Constants.AUTHOR+"6, " 
 						+Constants.AUTHOR+"7, "+Constants.CONFERENCE+", "+Constants.JOURNAL+", "+Constants.KEYWORD+"1, "
 						+Constants.KEYWORD+"2, "+Constants.KEYWORD+"3, "+Constants.KEYWORD+"4, "+Constants.KEYWORD+"5, "+Constants.KEYWORD+"6, "
 						+Constants.KEYWORD+"7, "+Constants.KEYWORD+"8, "
 						+Constants.KEYWORD+"9, "+Constants.KEYWORD+"10, "+Constants.KEYWORD+"11, "+Constants.KEYWORD+"12, "+Constants.KEYWORD+"13, "
 						+Constants.KEYWORD+"14, "+Constants.KEYWORD+"15, "/*+Constants.DOI+", "*/+Constants.FULL_VERSION_URL+"1, "+Constants.FULL_VERSION_URL+"2, "+Constants.FULL_VERSION_URL+"3, "
 						+Constants.FULL_VERSION_URL+"4, "+Constants.FULL_VERSION_URL+"5, "+Constants.FULL_VERSION_URL+"6, "+Constants.REFERENCECHECKED+") values ("
 			+publication.getID()+", "+publication.getCitationCount()+", "+publication.getReferenceCount()+", "+type+", "+year+", "+title+", "+_abstract+", "+AuthorIds[0]+", "+AuthorIds[1]
 					+", "+AuthorIds[2]+", "+AuthorIds[3]+", "+AuthorIds[4]+", "+AuthorIds[5]+", "+AuthorIds[6]+", "+conference+", "+journal+", "+keywordIds[0]
 							+", "+keywordIds[1]+", "+keywordIds[2]+", "+keywordIds[3]+", "+keywordIds[4]+", "+keywordIds[5]+", "+keywordIds[6]+", "+keywordIds[7]
 		 							+", "+keywordIds[8]+", "+keywordIds[9]+", "+keywordIds[10]+", "+keywordIds[11]+", "+keywordIds[12]+", "+keywordIds[13]+", "+keywordIds[14]+", "+/*doi+", "+*/fullversionUrl[0]
 									+", "+fullversionUrl[1]+", "+fullversionUrl[2]+", "+fullversionUrl[3]+", "+fullversionUrl[4]+", "+fullversionUrl[5]+", 0);";
		
		try {
			PreparedStatement insert = connect.prepareStatement(str);
			int rowsAffected = insert.executeUpdate();
			insert.close();
			if(rowsAffected > 0){
				return rowsAffected;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * Already exist.
	 *
	 * @param id the id
	 * @param queryType the query type
	 * @return true, if successful
	 */
	boolean alreadyExist(long id, int queryType){
		
		String tablename = "";
		switch(queryType){
		case PUBLICATION_CODE:
			tablename = Constants.PUBLICATION;
			break;
		case AUTHOR_CODE:
			tablename = Constants.AUTHOR;
			break;
		case KEYWORD_CODE:
			tablename = Constants.KEYWORD;
			break;
			
		case DOMAIN_CODE:
			tablename = "Domain";
			try {
				Statement statement = connect.createStatement();
				ResultSet set = statement.executeQuery("select * from "+tablename+" where "+Constants.ID+"="+id+";");
			
				while(set.next()){
					return true;
				}
				return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case JOURNAL_CODE:
			tablename = Constants.JOURNAL;
			break;
		case CONFERENCE_CODE:
			tablename = Constants.CONFERENCE;
			break;
		case ORGANISATION_CODE:
			tablename = Constants.ORGANISATION;
			break;
		}
	
		try {
			Statement statement = connect.createStatement();
			ResultSet set = statement.executeQuery("select * from "+tablename+" where "+Constants.ID+"="+id+";");
		
			while(set.next()){
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Insert author.
	 *
	 * @param author the author
	 * @return the int
	 */
	int insertAuthor(Author author){
		
		long affiliation = 0;
		String firstname = null;
		String middlename= null;
		String lastname= null;
		String nativename= null;
		String homepageUrl= null;
		String displayphotourl= null;
		Organization organization = author.getAffiliation();
		
		if(organization != null){
			affiliation = organization.getID();
			insertOrganisation(organization);
		}
		
		if(alreadyExist(author.getID(), AUTHOR_CODE)){
			return 0;
		}
		
		if(author.getFirstName()!=null){
			firstname = removePunctuation(author.getFirstName());
			firstname = "'"+firstname+"'";
		}
		
		if(author.getMiddleName()!=null){
			middlename  = removePunctuation(author.getMiddleName());
			middlename = "'"+middlename +"'";
		}
		
		if(author.getLastName()!=null){
			lastname = removePunctuation(author.getLastName());
			lastname = "'"+lastname+"'";
		}
		
		if(author.getNativeName()!=null){
			nativename = removePunctuation(author.getNativeName());
			nativename = "'"+nativename+"'";
		}
		
		if(author.getHomepageURL()!=null){
			homepageUrl = "'"+author.getHomepageURL()+"'";
		}
		
		if(author.getDisplayPhotoURL()!=null){
			displayphotourl = "'"+author.getDisplayPhotoURL()+"'";
		}
		
				
		ArrayList<Domain> researchdomainlist = author.getResearchInterestDomain();
		
		long[] domainArray = new long[10];
		
		if(researchdomainlist!=null){
			int i=0;
			for(Domain domain: researchdomainlist){
				
				insertDomain(domain);
				
				if(i>9){
					break;
				}
				
				domainArray[i] = domain.getDomainID();
				i++;
			}
		}
		
		String sqlStatement = "insert into "+Constants.AUTHOR+"( "+Constants.ID+", "+Constants.PUBLICATION_COUNT 
				+", "+Constants.CITATION_COUNT+", "+Constants.FIRSTNAME+", "+Constants.MIDDLE_NAME+", "+Constants.LASTNAME
				+", "+Constants.NATIVE_NAME+", "+Constants.HOMEPAGEURL+", "+Constants.AFFILIATION+", "+Constants.DISPLAY_PHOTO_URL+", "+
				Constants.HINDEX+", "+Constants.GINDEX+", "+Constants.RESEARCH_INTEREST_DOMAIN+"1, "+
				Constants.RESEARCH_INTEREST_DOMAIN+"2, "+Constants.RESEARCH_INTEREST_DOMAIN+"3, "+Constants.RESEARCH_INTEREST_DOMAIN+"4, "+
				Constants.RESEARCH_INTEREST_DOMAIN+"5, "+Constants.RESEARCH_INTEREST_DOMAIN+"6, "+Constants.RESEARCH_INTEREST_DOMAIN+"7, "+
				Constants.RESEARCH_INTEREST_DOMAIN+"8, "+Constants.RESEARCH_INTEREST_DOMAIN+"9, "+Constants.RESEARCH_INTEREST_DOMAIN+"10 "+
				") values ("+author.getID()+", "+author.getPublicationCount()+", "+author.getCitationCount()	+", "+firstname+
				", "+middlename+", "+lastname+", "+nativename+", "+homepageUrl+", "+
				affiliation+", "+displayphotourl+", "+author.getHIndex()+", "+author.getGIndex()+
				", "+domainArray[0]+", "+domainArray[1]+", "+domainArray[2]+", "+domainArray[3]+", "+domainArray[4]+", "+
				domainArray[5]+", "+domainArray[6]+", "+domainArray[7]+", "+domainArray[8]+", "+domainArray[9]+
				");";
		
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connect.prepareStatement(sqlStatement);
			int rowsAffected = preparedStatement.executeUpdate();
		
			if(rowsAffected > 0){
				preparedStatement.close();
				return rowsAffected;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	

	String removePunctuation(String str){
		String newstr = str;
		newstr  = newstr.replace("\'", " ");
		newstr = newstr.replace("`", " ");
		newstr = newstr.replace("\"", " ");
		return newstr;
	}
	
	
	/**
	 * Insert conference.
	 *
	 * @param conference the conference
	 * @return the int
	 */
	int insertConference(Conference conference){
	
		String fullname = null;
		String shortname = null;
		
		if(conference.getFullName()!=null){
			fullname = removePunctuation(conference.getFullName());
			fullname = "'"+fullname+"'";
		}
		
		if(conference.getShortName()!=null){
			shortname = removePunctuation(conference.getShortName());
			shortname = "'"+shortname+"'";
		}
		
		if(alreadyExist(conference.getID(), CONFERENCE_CODE)){
			return 0;
		}
		
		ArrayList<Domain> researchdomainlist = conference.getResearchInterestDomain();
		

		long[] domainArray = new long[10];
		
		if(researchdomainlist!=null){
			int i=0;
			for(Domain domain: researchdomainlist){
				
				insertDomain(domain);
				
				if(i>9){
					break;
				}
				
				domainArray[i] = domain.getDomainID();
				i++;
			}
		}
		
		String sql = "insert into "+Constants.CONFERENCE+"("+Constants.ID+", "+Constants.PUBLICATION_COUNT+", "+Constants.CITATION_COUNT+", "+
		Constants.FULLNAME+", "+Constants.SHORTNAME+", "+Constants.RESEARCH_INTEREST_DOMAIN+"1, "+
		Constants.RESEARCH_INTEREST_DOMAIN+"2, "+Constants.RESEARCH_INTEREST_DOMAIN+"3, "+Constants.RESEARCH_INTEREST_DOMAIN+"4, "+
		Constants.RESEARCH_INTEREST_DOMAIN+"5, "+Constants.RESEARCH_INTEREST_DOMAIN+"6, "+Constants.RESEARCH_INTEREST_DOMAIN+"7, "+
		Constants.RESEARCH_INTEREST_DOMAIN+"8, "+Constants.RESEARCH_INTEREST_DOMAIN+"9, "+Constants.RESEARCH_INTEREST_DOMAIN+"10 )" +
				"values("+conference.getID()+", "+conference.getPublicationCount()+","+conference.getCitationCount()+", "+fullname+
				", "+shortname+", "+domainArray[0]+", "+domainArray[1]+", "+domainArray[2]+", "+domainArray[3]+", "+
				domainArray[4]+", "+domainArray[5]+", "+domainArray[6]+", "+domainArray[7]+", "+domainArray[8]+", "+domainArray[9]+");";
		
		PreparedStatement prepare;
		try {
			prepare = connect.prepareStatement(sql);
			int rowsAffected = prepare.executeUpdate();
			prepare.close();
			return rowsAffected;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	/**
	 * Insert journal.
	 *
	 * @param journal the journal
	 * @return the int
	 */
	int insertJournal(Journal journal){
	
		String fullname = null;
		String shortname = null;
		
		if(journal.getFullName()!=null){
			fullname = removePunctuation(journal.getFullName());
			fullname = "'"+fullname+"'";		}
		
		if(journal.getShortName()!=null){
			shortname = removePunctuation(journal.getShortName());
			shortname = "'"+shortname+"'";
		}
		
		if(alreadyExist(journal.getID(), JOURNAL_CODE)){
			return 0;
		}
		
		ArrayList<Domain> researchdomainlist = journal.getResearchInterestDomain();
		
		
		

		long[] domainArray = new long[10];
		
		if(researchdomainlist!=null){
			int i=0;
			for(Domain domain: researchdomainlist){
				
				insertDomain(domain);
				
				if(i>9){
					break;
				}
				
				domainArray[i] = domain.getDomainID();
				i++;
			}
		}
		
		String sql = "insert into "+Constants.JOURNAL+"("+Constants.ID+", "+Constants.PUBLICATION_COUNT+", "+Constants.CITATION_COUNT+", "+
		Constants.FULLNAME+", "+Constants.SHORTNAME+", "+Constants.RESEARCH_INTEREST_DOMAIN+"1, "+
		Constants.RESEARCH_INTEREST_DOMAIN+"2, "+Constants.RESEARCH_INTEREST_DOMAIN+"3, "+Constants.RESEARCH_INTEREST_DOMAIN+"4, "+
		Constants.RESEARCH_INTEREST_DOMAIN+"5, "+Constants.RESEARCH_INTEREST_DOMAIN+"6, "+Constants.RESEARCH_INTEREST_DOMAIN+"7, "+
		Constants.RESEARCH_INTEREST_DOMAIN+"8, "+Constants.RESEARCH_INTEREST_DOMAIN+"9, "+Constants.RESEARCH_INTEREST_DOMAIN+"10 )" +
				"values("+journal.getID()+", "+journal.getPublicationCount()+","+journal.getCitationCount()+", "+fullname+
				", "+shortname+", "+domainArray[0]+", "+domainArray[1]+", "+domainArray[2]+", "+domainArray[3]+", "+
				domainArray[4]+", "+domainArray[5]+", "+domainArray[6]+", "+domainArray[7]+", "+domainArray[8]+", "+domainArray[9]+");";
		
		PreparedStatement prepare;
		try {
			prepare = connect.prepareStatement(sql);
		
			int rowsAffected = prepare.executeUpdate();
			prepare.close();
			return rowsAffected;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			System.out.print(sql);
		}
		
		return 0;
	}
	
	/**
	 * Insert organisation.
	 *
	 * @param organisation the organisation
	 * @return the int
	 */
	int insertOrganisation(Organization organisation){
		
		String name = null;
		String homepageUrl = null;
		
		if(organisation.getName()!=null){
			name = "'"+organisation.getName()+"'";
		}
		
		if(organisation.getHomepageURL()!=null){
			homepageUrl = "'"+organisation.getHomepageURL()+"'";
		}
		
		if(alreadyExist(organisation.getID(), ORGANISATION_CODE)){
			return 0;
		}
		
		String sql = "insert into "+Constants.ORGANISATION+"("+Constants.ID+", "+Constants.PUBLICATION_COUNT+", "+Constants.AUTHOR_COUNT+
				", "+Constants.CITATION_COUNT+", "+Constants.NAME+", "+Constants.HOMEPAGEURL+") values ("+organisation.getID()+", "+
				organisation.getPublicationCount()+", "+organisation.getAuthorCount()+", "+organisation.getCitationCount()+", "+
				name+", "+homepageUrl+");";
		
		
		PreparedStatement prepare;
		try {
			prepare = connect.prepareStatement(sql);
			
			int rowsAffected = prepare.executeUpdate();
			prepare.close();
			return rowsAffected;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * Insert domain.
	 *
	 * @param domain the domain
	 * @return the int
	 */
	int insertDomain(Domain domain){
		
		String name = null;
		
		if(alreadyExist(domain.getDomainID(), DOMAIN_CODE)){
			return 0;
		}
		
		if(domain.getName()!=null){
			name = "'"+domain.getName()+"'";
		}
		
		String sql = "insert into "+"Domain"+"("+Constants.ID+", "+Constants.SUBDOMAIN_ID+", "+Constants.PUBLICATION_COUNT+", "+Constants.AUTHOR_COUNT+
				", "+Constants.CITATION_COUNT+", "+Constants.NAME+", "+Constants.HOMEPAGEURL+") values ("+domain.getDomainID()+", "+
				domain.getSubDomainID()+", "+	domain.getPublicationCount()+", "+domain.getCitationCount()+", "+name+");";
		
		
		PreparedStatement prepare;
		try {
			prepare = connect.prepareStatement(sql);
			
			int rowsAffected = prepare.executeUpdate();
			prepare.close();
			return rowsAffected;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * Insert keyword.
	 *
	 * @param keyword the keyword
	 * @return the int
	 */
	int insertKeyword(Keyword keyword){
	
		String name = null;
		
		if(alreadyExist(keyword.getID(), KEYWORD_CODE)){
			return 0;
		}
		
		if(keyword.getName()!=null){
			name = removePunctuation(keyword.getName());
			name = "'"+name+"'";
		}
		
		String sql = "insert into "+Constants.KEYWORD+"("+Constants.ID+", "+Constants.PUBLICATION_COUNT+", "+
		Constants.CITATION_COUNT+", "+Constants.NAME+") values ("+keyword.getID()+", "+
				keyword.getPublicationCount()+", "+	keyword.getCitationCount()+", "+name+");";
		
		
		PreparedStatement prepare;
		try {
			prepare = connect.prepareStatement(sql);
			
			int rowsAffected = prepare.executeUpdate();
			prepare.close();
			return rowsAffected;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * Gets the publication.
	 *
	 * @param id the id
	 * @return the publication
	 */
	ResultSet getPublication(int id){
		
		
		
		return null;
	}
	
	/**
	 * Gets the publication list.
	 *
	 * @param the number of publication object required as return or
	 *  less in case that publication are not there 
	 * @return Publication List with publication number equal to the number 
	 * of publication requested. 
	 */
	public ArrayList<Publication> getPublicationList(int k){
		
		ArrayList<Publication> publicationList = new ArrayList<Publication>();
		ResultSet set = null ;
		
		try {
			Statement statement = connect.createStatement();
			set = statement.executeQuery("select * from Publication where "+Constants.REFERENCECHECKED+"=0 order by _id limit "+k+";");
			//set.first();
			int count = 0;
			
			while(set.next() && count < k){
				long publicationId = set.getLong(Constants.ID);
				Publication publication = new Publication(publicationId);
				publicationList.add(publication);
				count++;
			}
//			set.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(set!=null){
				try{
					set.close();
				}catch(SQLException e){
					
				};
			}
		}
		
		return publicationList;
	}
	
	/**
	 * Update publication references checked.
	 *
	 * @param long publicationId for which we wants to update the publication as
	 *  it has been queried in the database
	 * @return int as the result of the request made
	 */
	public int updatePublicationReferencesChecked(long publicationId){
		
		String updateString = "update "+Constants.PUBLICATION+" set "+Constants.REFERENCECHECKED+"=1 where "
		+Constants.ID+"="+publicationId;
		try {
			PreparedStatement preparedStatement = connect.prepareStatement(updateString);
			int value = preparedStatement.executeUpdate();
			return value;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public long getLastItem(){
		
		Statement statement = null;
		ResultSet set = null;
		try {
			statement = connect.createStatement();
			set = statement.executeQuery("Select "+Constants.PUBLICATION_ID+" from "+Constants.PUBLICATION_TRAVERSAL+" order by "+Constants.ID+" desc limit 1");
			set.first();
			return set.getLong("publicationid");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(set!=null){
				try {
					set.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return 0;
	}
	
	public boolean alreadyTraversed(Publication pub){
		
		Statement statement = null;
		ResultSet set = null;
		try {
			statement = connect.createStatement();
			String str = "Select "+Constants.REFERENCECHECKED+" from "+Constants.PUBLICATION+" where "+
			Constants.ID+"="+pub.getID()+" ;";
			set = statement.executeQuery(str);
			if(set.first()){
				long value = set.getLong(Constants.REFERENCECHECKED);
				if(value == 1){
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(set!=null){
				try {
					set.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public int insertIntoPublicationReferenceCitation(long publicationId, long citationreference,int type){
		
		String statement = "insert into "+Constants.PUBLICATION_REFERENCE_CITATION+"("+Constants.PUBLICATION_ID+
				","+Constants.CITATION_REFERENCE+","+Constants.DOC_TYPE+") values ("+ publicationId+
				", "+citationreference+", "+type+");";
		PreparedStatement prepare = null;
		try {
			prepare = connect.prepareStatement(statement);
			int updatedrow = prepare.executeUpdate();
			prepare.close();
			return updatedrow;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(prepare!=null){
				try {
					prepare.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return 0;
	}
	
	public int insertIntoPublicationReferenceCitation(long currentPub, ArrayList<Publication> list, String Type){
		
		StringBuilder statement = new StringBuilder();
		
		if(list.isEmpty()){
			return 0;
		}
		int isReference = 0;
		if(Type.equals(Constants.CITATION)){
			isReference = 0;
		}
		
		if(Type.equals(Constants.REFERENCE)){
			isReference = 1;
		}
		
		statement.append("insert into "+Constants.PUBLICATION_REFERENCE_CITATION+"("+Constants.PUBLICATION_ID+
				","+Constants.CITATION_REFERENCE+","+Constants.DOC_TYPE+") values ");
		
			for(Publication pub : list){
				statement.append("("+ currentPub+","+pub.getID()+","+isReference+"),");
			}
		
			statement = new StringBuilder(statement.substring(0, statement.toString().length()-1));
			statement.append(";");
		
		PreparedStatement prepare = null;
		try {
			prepare = connect.prepareStatement(statement.toString());
			int updatedrow = prepare.executeUpdate();
			prepare.close();
			return updatedrow;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(prepare!=null){
				try {
					prepare.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return 0;
	}
	
	public int insertIntoTraverse(long publicationId, long predecessor){
		
		String statement = "insert into "+Constants.PUBLICATION_TRAVERSAL+"("+Constants.PUBLICATION_ID+","+Constants.PREDECESSOR+") values ("+ publicationId+
				", "+predecessor+");";
		PreparedStatement prepare = null;
		try {
			prepare = connect.prepareStatement(statement);
			int updatedrow = prepare.executeUpdate();
			prepare.close();
			return updatedrow;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(prepare!=null){
				try {
					prepare.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
	
	public long getPredecessor(long currentId){
		String str = "Select "+Constants.PREDECESSOR+" from "+ Constants.PUBLICATION_TRAVERSAL+ " where "+Constants.PUBLICATION_ID+"="+currentId+";";
	
		Statement statement = null;
		ResultSet set = null;
		try {
			statement = connect.createStatement();
			set = statement.executeQuery(str);
			
			if(set.first()){
				return set.getLong(Constants.PREDECESSOR);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(set!=null){
				try {
					set.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return 0;
	}
	
	public ArrayList<Publication> getRelatedForPublication(long currentId, boolean isCitation){
		
		ArrayList<Publication> list = new ArrayList<Publication>();
		String query;
		
		//is citation
		if(isCitation){
			query = "select "+Constants.CITATION_REFERENCE+" from PublicationReferenceCitation where "+Constants.PUBLICATION_ID+" = "+currentId+" and type = 1;";
		}else{
			query = "select "+Constants.CITATION_REFERENCE+" from PublicationReferenceCitation where "+Constants.PUBLICATION_ID+" = "+currentId+" and type = 0;";
		}
		
		Statement statement = null;
		ResultSet set = null;
		try {
			statement = connect.createStatement();
			set = statement.executeQuery(query);
			
			while(set.next()){
				list.add(new Publication(set.getLong(Constants.CITATION_REFERENCE)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(set!=null){
				try {
					set.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}
