package com.microsoftapi.searchapp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.text.AbstractDocument.BranchElement;

import com.microsoftapi.searchapp.database.DatabaseHandler;
import com.microsoftapi.searchapp.datastructure.Publication;
import com.microsoftapi.searchapp.http.HttpClient;
import com.microsoftapi.searchapp.json.JSONParser;
import com.microsoftapi.searchapp.request.Request;
import com.microsoftapi.searchapp.request.URLBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class ManagerBFS which search 
 * the citation tree in breadth first search fashion making
 *  search for references as well citation of a publication.
 */
public class ManagerBFS extends Thread{
	
	/** The builder object to create urls. */
	URLBuilder builder;
	
	
	/** The database handler object to handle the database request. It act
	 *  as interface  to the database.
	 *  */
	DatabaseHandler handler;
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 * This is the overridden run method of the thread.
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.print("manager started");
		int i=0;
		
		// database handler initialised
		handler= new DatabaseHandler("acda_bfs");
	//	ArrayList<Publication> publicationList = handler.getPublicationList(2000);
		JSONParser parser = new JSONParser();
		ArrayList<Publication> publicationList;
		ArrayList<Publication> publicationListCitation;
		ArrayList<Publication> publicationListReference;
		
		//response string that contains the response of the 
		//	citation url request for a given publication
		String responseCitation;
		
		//response string that contains the response of the 
		// reference url request for a given publication
		String responseReference;
		
		// A given publication for which we are maing the request
		Publication publication;
		
		//make the loop for the 3000 iteration
		while(i<3000){
			
			
			i++;
			
			// get the given number of publication from database 
			//for which the reference have already been not checked
			publicationList = handler.getPublicationList(2000);
			
			// in initial case initialize publication list with seed publication
			if(publicationList.isEmpty()){
				publicationList.add(new Publication(1712314));
			}
			
			// run while the publication list is not empty
			while(!publicationList.isEmpty()){
			
			publication = publicationList.get(0);
			
			//handler.insertPublication(publication);
			// get the response for the citation query
			responseCitation = getResponseForPublication(publication, Constants.CITATION);
			
			// get the response for the reference query
			responseReference = getResponseForPublication(publication, Constants.REFERENCE);
			
			// parse the citation query and get the citation publication list
			publicationListCitation = parser.parsePublicationResponse(responseCitation);
			
			// parse the citation query and get the citation publication list
			publicationListReference = parser.parsePublicationResponse(responseReference);
			
			
			//insert citations into database
			postQuery(publicationListCitation, publication.getID(), Constants.CITATION);
			
			//insert references into database
			postQuery(publicationListReference, publication.getID(), Constants.REFERENCE);
			
			// remove the current publication for which we have queried
			// the citation and references from the publication list
			publicationList.remove(0);
			
						
			
			//parser.
			//now we have the input stream for the query 
			//the thing remains is providing the JSON parsing for the data
			
			}
		}	
		super.run();
	}
	
	/**
	 * Gets the response for publication.
	 *
	 * @param publication the publication
	 * @param Type the type
	 * @return the response for publication
	 */
	String getResponseForPublication(Publication publication, String Type){
		Long publicationId = publication.getID();
		
		//initialise a request object
		Request request = new Request();
		
		//set the publication id for the request
		request.setPublicationID(publicationId);
		request.setStartIdx(0);
		request.setEndIdx(100);
		request.setReferenceType(Type);
		
		// url builder object
		URLBuilder builder = new URLBuilder(request);
		
		// get the publication url for the given builder and request object
		String Url = builder.getPublicationUrl();
		
		//initialise the http client
		HttpClient client = new HttpClient();
		
		//initialise the input stream from the http client
		InputStream stream = client.getResponseInputStream(Url);
		
		// get string from the input stream and return it as string object.
		return getStringOutOfInputStream(stream);
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#interrupt()
	 */
	@Override
	public void interrupt() {
		// TODO Auto-generated method stub
		super.interrupt();
		
		System.out.println("Thread interrupted");
	}

	/**
	 * Search for default publication.
	 */
	private void searchForDefaultPublication(){
		Publication publication = new Publication(694978);
		handler.insertPublication(publication);
	//	String responseCitation = getResponseForPublication(publication);
		JSONParser parser = new JSONParser();
		//ArrayList<Publication> publicationList = parser.parsePublicationResponse(response);
		//postQuery(publicationList, 694978);
	}
	
	
	/**
	 * Post query.
	 *
	 * @param arrayList the array list
	 * @param currentPublicationId the current publication id
	 * @param type the type
	 */
	void postQuery(ArrayList<Publication> arrayList, long currentPublicationId, String type){
		
		//save the references
		//ArrayList<Long> referencePublicationIdList = new ArrayList<Long>();
		
		handler.insertIntoPublicationReferenceCitation(currentPublicationId, arrayList, type);
		
		/*if(type.equals(Constants.CITATION)){
			for(Publication publication : arrayList){
	//			handler.insertIntoPublicationReferenceCitation(currentPublicationId, publication.getID(), 0);		
				handler.insertIntoPublicationReferenceCitation(currentPublicationId, arrayList, type);
			}
			
			
		}else if(type.equals(Constants.REFERENCE)){
			for(Publication publication : arrayList){
				handler.insertIntoPublicationReferenceCitation(currentPublicationId, arrayList, type);		
			}
		}*/
		
		
		//saveToFile(referencePublicationIdList, currentPublicationId);
		//save the Publications into database
		for(Publication publication: arrayList){
			handler.insertPublication(publication);
		}
		
		//update the reference of the publication have been successfully saved into the database
		handler.updatePublicationReferencesChecked(currentPublicationId);
		
	}
	
	/**
	 * Save to file.
	 *
	 * @param referencePublicationIdList the reference publication id list
	 * @param currentPublicationId the current publication id
	 */
	void saveToFile(ArrayList<Long> referencePublicationIdList, long currentPublicationId){
		File file = new File("/home/subhash/citationData/"+currentPublicationId+".txt");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			for(Long id: referencePublicationIdList){
				bw.write(Long.toString(id)+"\n");
			}
			
			bw.close();
			fw.close();
			
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Gets the string out of input stream.
	 *
	 * @param response the response
	 * @return the string out of input stream
	 */
	String getStringOutOfInputStream(InputStream response){
		File tmp = new File("tmp.txt");
		BufferedInputStream bis = new BufferedInputStream(response);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(new File("tmp.txt"));
		
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		
		byte[] bytes = new byte[1024];
		int read = 0;
		
		while((read=bis.read(bytes))!=-1){
			bos.write(bytes, 0, read);
		}
		bos.flush();
		FileReader fr = new FileReader("tmp.txt");
		BufferedReader br = new BufferedReader(fr);
		String str = null;
		StringBuilder builder = new StringBuilder();
		
		while((str = br.readLine())!=null){
			builder.append(str);
		}
		
		br.close();
		fr.close();
		fos.close();
		bos.close();
		bis.close();
		
		return	builder.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return	builder.toString();
	}
}
