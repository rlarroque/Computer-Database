package com.excilys.computer_database.service;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.service.impl.PageService;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

public class TestPageService {

	private static PageService page;

	@BeforeClass
	public static void executeBeforeAllTests() {
		page = PageService.getInstance();
	}

	@Before
	public void executeBeforeEachTests() {
		page.setCurrentPage(1);
		page.setOffset(10);
	}

	@AfterClass
	public static void executeAfterAllTests() {
		page = null;
	}
	
	@Ignore
	@Test
	public void testNextPage() {
		assertEquals(1, page.startIndex());
		//assertEquals(10, page.last());

		page.nextPage();

		assertEquals(11, page.startIndex());
		//assertEquals(20, page.last());
	}

	@Ignore
	@Test
	public void testPreviousPage() {
		page.setCurrentPage(2);

		assertEquals(11, page.startIndex());
		//assertEquals(20, page.last());

		page.previousPage();

		assertEquals(1, page.startIndex());
		//assertEquals(10, page.last());
	}

	@Test(expected = IntegrityException.class)
	public void testPreviousPageImpossible() {
		page.previousPage();
	}

	@Test
	public void testChangeOffset() {
		page.setOffset(50);

		assertEquals(0, page.startIndex());
	}

}
