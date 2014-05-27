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
import java.util.Random;

import com.microsoftapi.searchapp.database.DatabaseHandler;
import com.microsoftapi.searchapp.datastructure.Publication;
import com.microsoftapi.searchapp.http.HttpClient;
import com.microsoftapi.searchapp.json.JSONParser;
import com.microsoftapi.searchapp.request.Request;
import com.microsoftapi.searchapp.request.URLBuilder;

public class RandomWalkPublicationNetwork extends Thread{

	
		Random randomGenerator;
		ArrayList<Publication> refListCurrent;
		ArrayList<Publication> citListCurrent;
		DatabaseHandler handler;
		long currentPublication;
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			Publication publication = null;
			handler = new DatabaseHandler("academicsearch");
			
			//final int root = 1712314;
/*			Request requestCitation = new Request();
			requestCitation.setPublicationID(publication.getID());
			requestCitation.setStartIdx(0);
			requestCitation.setEndIdx(100);
			requestCitation.setReferenceType(Constants.CITATION);*/
			
			/*Request requestReference = new Request();
			requestReference.setPublicationID(publication.getID());
			requestReference.setStartIdx(0);
			requestReference.setEndIdx(100);
			requestReference.setReferenceType(Constants.REFERENCE);*/
			
			/*URLBuilder builderCitation= new URLBuilder(requestCitation);
			String citationUrl = builderCitation.getPublicationUrl();*/
			
		//	URLBuilder builderReference = new URLBuilder(requestReference);
			//String referenceUrl = builderReference.getPublicationUrl();
			
			
			randomGenerator = new Random();
		//	randomGenerator.nextInt(2);
			
			
			
			
			int i=0;
			publication = getCurrentPublication();
			currentPublication = publication.getID();
			while(i<300000){
			
				//get the publication which we want to search
				
				if(currentPublication == 0){
					break;
				}
				
				publication.setID(currentPublication);
				handler.updatePublicationReferencesChecked(currentPublication);
				//get the random list for the citation and reference
				citListCurrent = getRandomPublicationList(publication, 10, Constants.CITATION);
				refListCurrent = getRandomPublicationList(publication, 10, "Reference");
			
				//save the list to the database
				saveListToDatabase(citListCurrent);
				saveListToDatabase(refListCurrent);
				
				//save the list to files
				//saveListToFile(citListCurrent, 0);
				//saveListToFile(refListCurrent, 1);
				
				//save the list to database i publication and reference section
				savelisttodatabasereferencecitation(citListCurrent, 0);
				savelisttodatabasereferencecitation(refListCurrent, 1);
				
				//writeToCurrentPublication();
				currentPublication = selectNextRandomNode(currentPublication);
			}
			
			
			
			super.run();
			
			
		}
		
		void savelisttodatabasereferencecitation(ArrayList<Publication> list, int type){
			
			for(Object object: list ){
				Publication pub = (Publication)object;
				handler.insertIntoPublicationReferenceCitation(currentPublication, pub.getID(), type);
			}
		}
		
		long selectNextRandomNode(long currentId){
				
			
			ArrayList<Publication> citList = null;
			ArrayList<Publication> refList = null;

			/*citList = citeListFilterNodes(citList);
			refList = citeListFilterNodes(refList);*/
			
			long newCitationId;
				
				while(true){
					//get the citation list from the database
					
					//get reference list from the database
				
					citList = handler.getRelatedForPublication(currentId, true);
					refList = handler.getRelatedForPublication(currentId, false);
					
					citList = citeListFilterNodes(citList);
					refList = citeListFilterNodes(refList);
					
					//randomly select between the citation and the references
					int choice = randomGenerator.nextInt(1);
					
					
					if(choice == 0){
							if(citList.size() == 0){
								if(refList.size() == 0){
									//get the predecessor of current
									currentId = handler.getPredecessor(currentId);
								}else{
									//randomly select from not referenced publication
									newCitationId = refList.get(getRandomIndex(refList)).getID();
									break;
								}
							}else{
								newCitationId = citList.get(getRandomIndex(citList)).getID();
								break;
							}
					}else{
						if(refList.size() == 0){
								if(citList.size() == 0){
									currentId = handler.getPredecessor(currentId);
								}else{
									newCitationId = citList.get(getRandomIndex(citList)).getID();
									break;

								}
							}else{
								newCitationId = refList.get(getRandomIndex(refList)).getID();
								break;
							}
					}
					
					
					
				}
				//
				//citListCurrent = citeListFilterNodes(citListCurrent);
				handler.insertIntoTraverse(newCitationId, currentId);
				return newCitationId;
		
		}
		
		int getRandomIndex(ArrayList<Publication> list){
			
			int random = randomGenerator.nextInt(list.size());
			return random;
		}
		
		ArrayList<Publication> citeListFilterNodes(ArrayList<Publication> list){
				
				ArrayList<Publication> tmplist = new ArrayList<Publication>();
				
				for(Object object:list){
					Publication pub = (Publication)object;
					if(!handler.alreadyTraversed(pub)){
						tmplist.add(pub);
					}
				}
				return tmplist;
				
		}
		
		
		
		void writeToCurrentPublication(){
			File file = new File("currentPub.txt");
			FileWriter writer;
			Publication pub = getNextRandomPublication();
			
			try {
				writer = new FileWriter(file, true);
				BufferedWriter br = new BufferedWriter(writer); 
				br.write(Long.toString(pub.getID())+"\n");
				br.flush();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		Publication getCurrentPublication(){
			
			/*File file = new File("currentPub.txt");
			FileReader reader;
			Publication pub = null;
			
			try {
				reader = new FileReader(file);
				BufferedReader br = new BufferedReader(reader); 
				String id = br.readLine();
				String prev = id;
				while((id = br.readLine())!=null){
					prev = id;
				}
				
				Long pubId = Long.parseLong(prev);
				pub = new Publication(pubId);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return pub;*/
			
			return new Publication(handler.getLastItem());
		}
		
		Publication getNextRandomPublication(){
			
			int choice = randomGenerator.nextInt(1);
			
			switch(choice){
				case 0:
					//get random publication from citation
					
					int i = randomGenerator.nextInt(citListCurrent.size());
					return citListCurrent.get(i);
					
					
				case 1: 
					//get random publication from reference
					int j  =randomGenerator.nextInt(refListCurrent.size());
					return refListCurrent.get(j);
			}
			
			return null;
		}
		
		void saveListToFile(ArrayList list, int type){
			
			String pathCitation = "/home/subhash/citationData/random/citation";
			String pathReference = "/home/subhash/citationData/random/reference";
			File file = null;
			
			switch(type){
				case 0:
				file = new File(pathCitation+"/"+Long.toString(currentPublication)+".txt");
					break;
				
				case 1:
				file = new File(pathReference+"/"+Long.toString(currentPublication)+".txt");
					break;
			}
			
			
			if(file.exists()){
				return ;
			}
				
			FileWriter fw;
			BufferedWriter bw = null;
			try {
				fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			for(Object object: list){
				Publication pub = (Publication)object;
				try {
					bw.write(Long.toString(pub.getID())+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				bw.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		void saveListToDatabase(ArrayList list){
			for(Object object: list){
				Publication pub = (Publication)object;
				handler.insertPublication(pub);
			}
		}
		
		ArrayList<Publication> getRandomPublicationList(Publication publication, int count, String Type){
			
			String citationResponse = getResponseForPublication(publication, Type);
			JSONParser parser = new JSONParser();
			ArrayList<Publication> citationList = parser.parsePublicationResponse(citationResponse);
			;
			ArrayList<Publication> randomCitationList  = new ArrayList<Publication>();
			//Publication publication;
			
			if(citationList.size()<=count){
				randomCitationList = citationList;
			}else{
				while(randomCitationList.size()< count){
					int size = citationList.size();
					int index = randomGenerator.nextInt(citationList.size()-1);
					publication = citationList.get(index);
					/*if(!isDuplicate(randomCitationList, publication)){
						
					}*/
					randomCitationList.add(publication);
					citationList.remove(index);
				}
			}
			
			
			return randomCitationList;
		}
		
		boolean isDuplicate(ArrayList randomCitationList, Publication other){
			
			for(Object object: randomCitationList){
				Publication publication = (Publication)object;
				if(other.equals(publication)){
					return true;
				}
			}
			
			return false;
		}
		
		String getResponseForPublication(Publication publication, String type){
			Long publicationId = publication.getID();
			Request request = new Request();
			request.setPublicationID(publicationId);
			request.setStartIdx(0);
			request.setEndIdx(100);
			request.setReferenceType(type);
			URLBuilder builder = new URLBuilder(request);
			String Url = builder.getPublicationUrl();
			HttpClient client = new HttpClient();
			InputStream stream = client.getResponseInputStream(Url);
			return getStringOutOfInputStream(stream);
		}
		
		String getStringOutOfInputStream(InputStream response){
			File tmp = new File("tmp.txt");
			BufferedInputStream bis = new BufferedInputStream(response);
			FileOutputStream fos;
			StringBuilder builder = null;
			
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
			builder = new StringBuilder();
			
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
