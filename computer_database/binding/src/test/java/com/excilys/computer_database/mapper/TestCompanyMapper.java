package com.excilys.computer_database.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computer_database.dto.model.CompanyDTO;
import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.model.Company;

@ContextConfiguration(locations = { "classpath:/test-binding-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCompanyMapper {
	
    @Autowired
    private CompanyMapper companyMapper;

    /**
     * Test.
     */
    @Test
    public void testCompanyToDTO() {

        Company company = new Company(1l, "Dummy Company");
        CompanyDTO dto = companyMapper.toDTO(company);

        assertEquals(dto.getName(), company.getName());
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void testCompanyToDTOWithNull() {

        Company company = null;

        @SuppressWarnings("unused")
        CompanyDTO dto = companyMapper.toDTO(company);
    }

    /**
     * Test.
     */
    @Test
    public void testDtoToCompany() {

        CompanyDTO dto = new CompanyDTO(1, "Dummy Company");
        Company company = companyMapper.toCompany(dto);

        assertEquals("Dummy Company", company.getName());
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void testDtoToCompanyWithNull() {

        CompanyDTO dto = null;

        @SuppressWarnings("unused")
        Company company = companyMapper.toCompany(dto);
    }
}