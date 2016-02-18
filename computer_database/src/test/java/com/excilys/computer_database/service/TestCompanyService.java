package com.excilys.computer_database.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.excilys.computer_database.dao.impl.CompanyDAOImpl;
import com.excilys.computer_database.persistence.model.Company;
import com.excilys.computer_database.service.impl.CompanyServiceImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CompanyDAOImpl.class)
public class TestCompanyService {

	private static CompanyDAOImpl compDAO = Mockito.mock(CompanyDAOImpl.class);
	private static CompanyService compService;
	
	@BeforeClass
	public static void executeBeforeTests() {
        Mockito.when(compDAO.getAll()).thenReturn(new ArrayList<Company>());
		
		PowerMockito.mockStatic(CompanyDAOImpl.class);
		PowerMockito.when(CompanyDAOImpl.getInstance()).thenReturn(compDAO);
		
		compService = CompanyServiceImpl.getInstance();
	}
	
	@AfterClass
	public static void executeAfterTests(){
		compService = null;
	}

	@Test
	public void testGetCompanies() {
		assertEquals(0, compService.getAll().size());
	}
}
