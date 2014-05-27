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

public class Manager extends Thread{
	
	URLBuilder builder;
	DatabaseHandler handler;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.print("manager started");
		int i=0;
		handler= new DatabaseHandler("test");
	//	ArrayList<Publication> publicationList = handler.getPublicationList(2000);
		JSONParser parser = new JSONParser();
		ArrayList<Publication> publicationListCurrentPublication;
		ArrayList<Publication> publicationList;
		String response;
		Publication publication;
		while(i<3000){
			i++;
			publicationList = handler.getPublicationList(200);
			if(publicationList.isEmpty()){
				searchForDefaultPublication();
			}
			while(!publicationList.isEmpty()){
			publication = publicationList.get(0);
			//handler.insertPublication(publication);
			response = getResponseForPublication(publication);
			
			publicationListCurrentPublication = parser.parsePublicationResponse(response);
			postQuery(publicationListCurrentPublication, publication.getID());
			publicationList.remove(0);
			
						
			
			//parser.
			//now we have the input stream for the query 
			//the thing remains is providing the JSON parsing for the data
			
			}
		}	
		super.run();
	}
	
	String getResponseForPublication(Publication publication){
		Long publicationId = publication.getID();
		Request request = new Request();
		request.setPublicationID(publicationId);
		request.setStartIdx(0);
		request.setEndIdx(100);
		request.setReferenceType(Constants.REFERENCE);
		URLBuilder builder = new URLBuilder(request);
		String Url = builder.getPublicationUrl();
		HttpClient client = new HttpClient();
		InputStream stream = client.getResponseInputStream(Url);
		return getStringOutOfInputStream(stream);
	}
	
	
	@Override
	public void interrupt() {
		// TODO Auto-generated method stub
		super.interrupt();
		
		System.out.println("Thread interrupted");
	}

	private void searchForDefaultPublication(){
		Publication publication = new Publication(694978);
		handler.insertPublication(publication);
		String response = getResponseForPublication(publication);
		JSONParser parser = new JSONParser();
		ArrayList<Publication> publicationList = parser.parsePublicationResponse(response);
		postQuery(publicationList, 694978);
	}
	
	
	void postQuery(ArrayList<Publication> arrayList, long currentPublicationId){
		
		//save the references
		ArrayList<Long> referencePublicationIdList = new ArrayList<Long>();
		
		for(Publication publication : arrayList){
			referencePublicationIdList.add(publication.getID());
		}
		
		saveToFile(referencePublicationIdList, currentPublicationId);
		//save the Publications into database
		for(Publication publication: arrayList){
			handler.insertPublication(publication);
		}
		
		//update for the current publication reference
		handler.updatePublicationReferencesChecked(currentPublicationId);
		
	}
	
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
