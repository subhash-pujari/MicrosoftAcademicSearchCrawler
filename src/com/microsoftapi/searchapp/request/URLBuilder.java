package com.microsoftapi.searchapp.request;

import com.microsoftapi.searchapp.Constants;

public class URLBuilder {

		private Request request;
		private StringBuilder URL;
		private String authority;
		private String baseUrl;
		
		public URLBuilder(Request request){
			this.request = request;
			authority = Constants.URL;
			baseUrl = authority+Constants.APP_ID+"="+Constants.APP_ID_VALUE;
			URL = new StringBuilder(baseUrl);
		}
		
		public String getPublicationUrl(){
			
	
			
			URL.append(Constants.AND+Constants.PUBLICATION_ID+Constants.EQUAL+request.getPublicationID());
			URL.append(Constants.AND+Constants.RESULT_OBJECT+Constants.EQUAL+Constants.PUBLICATION);
			URL.append(Constants.AND+Constants.REFERENCE_TYPE+Constants.EQUAL+request.getReferenceType());
			URL.append(Constants.AND+Constants.START_ID+Constants.EQUAL+request.getStartIdx());
			URL.append(Constants.AND+Constants.END_ID+Constants.EQUAL+request.getEndIdx());
			URL.append(Constants.AND+Constants.ORDER_BY+Constants.EQUAL+Constants.YEAR);
			return URL.toString();
		}
		
		/*public String getPublicationCitationUrl(){
			URL.append(Constants.AND+Constants.PUBLICATION_ID+Constants.EQUAL+request.getPublicationID());
			URL.append(Constants.AND+Constants.RESULT_OBJECT+Constants.EQUAL+Constants.PUBLICATION);
			URL.append(Constants.AND+Constants.REFERENCE_TYPE+Constants.EQUAL+Constants.CITATION);
			URL.append(Constants.AND+Constants.START_ID+Constants.EQUAL+request.getStartIdx());
			URL.append(Constants.AND+Constants.END_ID+Constants.EQUAL+request.getEndIdx());
			URL.append(Constants.AND+Constants.ORDER_BY+Constants.EQUAL+Constants.YEAR);
			return URL.toString();
		}*/
	
}
