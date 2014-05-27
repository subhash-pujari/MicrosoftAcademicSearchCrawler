package com.microsoftapi.searchapp.database;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.microsoftapi.searchapp.datastructure.Publication;

public class DatabaseHandlerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsertPublication() {
		fail("Not yet implemented");
	//	DatabaseHandler handler = new DatabaseHandler();
		
		Publication publication  = new Publication(11111);
		
		
		//handler.insertPublication(publication);
	}
	
	@Ignore
	@Test
	public void testInsertAuthor() {
		fail("Not yet implemented");
	}
	
	@Ignore
	@Test
	public void testInsertConference() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testInsertJournal() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testInsertOrganisation() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testInsertDomain() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testInsertKeyword() {
		fail("Not yet implemented");
	}

}
