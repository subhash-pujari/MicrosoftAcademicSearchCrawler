package com.microsoftapi.searchapp.database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

	
	private static java.sql.Connection connect = null;
	String databasename;
	
	java.sql.Connection getConnection(String DatabaseName){
		
		if(!this.databasename.equals(DatabaseName)){
			try {
				connect = DriverManager.getConnection("jdbc:mysql://localhost/"+databasename+"?"
				          + "user=tiger&password=user@123");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(connect == null){
			try {
				connect = DriverManager.getConnection("jdbc:mysql://localhost/"+databasename+"?"
				          + "user=tiger&password=user@123&dontTrackOpenResources=true");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		return connect;
	}
	
}
