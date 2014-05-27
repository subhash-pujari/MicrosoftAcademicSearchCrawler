package com.microsoftapi.searchapp;

import java.io.Console;
import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * This is the Main class for this program which runs the main loop for it.
 */
public class Main {
	
		/**
		 * The main loop of the crawler
		 *
		 * @param args the string arguments passed from the command line
		 */
		public static void main(String[] args){
			
			// Select the thread user needs to run
			// Flag for Manager thread
			boolean IS_MANAGER = false;
			
			// Flag for the thread which can make a random walk over citation graph
			boolean IS_RANDOM = false;
			
			// Flag to enable breadth 
			boolean IS_BFS = true;
			
			// Manager thread of our program, this thread is not used in this program
			Manager manager = new Manager();
			
			// Thread to make a random walk over the citation network
			RandomWalkPublicationNetwork random = new RandomWalkPublicationNetwork();
		
			// Thread to make the breadth first search on the citation network
			ManagerBFS managerBFS = new ManagerBFS();
			
			while(true){
				
				//command to start the thread
				System.out.print("type the command:");
				byte[] b = new byte[1024];
				try {
					System.in.read(b);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String choice = new String(b);
				choice = choice.substring(0, choice.indexOf('\n'));
				
				
				if(choice.equals("stop")){
					if(manager.isAlive()){
						manager.stop();
						System.out.println("thread stopped");
					}
				}else if(choice.equals("start")){
					
					if(IS_MANAGER){
						if(!manager.isAlive()){
							manager = new Manager();
							manager.start();
						}else{
							System.out.print("download thread running");
						}
					}
					
					
					if(IS_RANDOM){
						if(!random.isAlive()){
							random = new RandomWalkPublicationNetwork();
							random.start();
						}else{
							System.out.print("download thread running");
						}
					}
					
					if(IS_BFS){
						if(!managerBFS.isAlive()){
							managerBFS = new ManagerBFS();
							managerBFS.start();
						}else{
							System.out.print("download thread running");
						}
					}
				}else if(choice.equals("showrecord")){
					
				}else if(choice.equals("exit")){
					manager.stop();
					break;
				}
				
			}
            		
			/*
			String TAG = "";
			long publication_id = 1;
			URL url = null;
			ArrayList<Long> arrayListPublication = new ArrayList<Long>();
			HashMap<Long, Publication> publication_map = new HashMap<Long, Publication>();
			arrayListPublication.add((long) 694978);
			int count = 0;
			int maximumCount = 20000;
			
			while(count != maximumCount || arrayListPublication.isEmpty()){
				
				try {
					
					
					publication_id = arrayListPublication.remove(0);
					String strurl = Constants.URL + 
							Constants.APP_ID + Constants.EQUAL + Constants.APP_ID_VALUE +
							Constants.AND + Constants.PUBLICATION_ID + Constants.EQUAL + publication_id +
							Constants.AND + Constants.RESULT_OBJECT + Constants.EQUAL + Constants.PUBLICATION +
							Constants.AND + Constants.REFERENCE_TYPE + Constants.EQUAL + Constants.CITATION +
							Constants.AND + Constants.START_ID + Constants.EQUAL + "1" + Constants.AND + Constants.END_ID + Constants.EQUAL + "10" +
							Constants.AND + Constants.ORDER_BY + Constants.EQUAL + Constants.YEAR;
					
					
					url = new URL(strurl);
					HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
					httpConn.setRequestMethod("GET");
					httpConn.connect();
					
					InputStream response = (InputStream)httpConn.getContent();
					File tmp = new File("tmp.txt");
					BufferedInputStream bis = new BufferedInputStream(response);
					FileOutputStream fos = new FileOutputStream(new File("tmp.txt"));
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
					
					JSONObject json;
					try {
							json = (JSONObject)new JSONParser().parse(builder.toString());
							HashMap map = (HashMap)json.get("d");
							map = (HashMap)map.get("Publication");
							JSONArray array = (JSONArray)map.get("Result");
							
							for(Object publication: array){
								HashMap map_publication = (HashMap)publication;
								Publication publication1 = new Publication(map_publication);
							//	publication1.citations.add(publication_id);
								publication_map.put(publication_id, publication1);
							//	arrayListPublication.add(publication1.ID);
							}
							
							
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
				count++;
			}*/
			
			
		}

}
