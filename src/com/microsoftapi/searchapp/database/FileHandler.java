package com.microsoftapi.searchapp.database;

import java.io.File;
import java.util.ArrayList;

public class FileHandler {

		private String path;
		private File file;
		public FileHandler(String path){
			this.path = path;
			file = new File(this.path);
		}
		
		public boolean isDirectory(){
			return file.isDirectory();
		}
		
		public String[] getFileList(){
			File[] files = file.listFiles();
			ArrayList<String> list = new ArrayList<String>();
			for(File file : files){
				list.add(file.getName());
			}
			return (String[]) list.toArray();
		}
}
