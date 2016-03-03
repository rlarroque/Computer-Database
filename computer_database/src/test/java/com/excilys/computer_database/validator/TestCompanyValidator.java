/**
 * 
 */
package com.excilys.computer_database.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.persistence.model.Company;
import com.excilys.computer_database.persistence.model.validator.CompanyValidator;
import com.excilys.computer_database.webapp.dto.CompanyDTO;
import com.excilys.computer_database.webapp.dto.validator.CompanyDTOValidator;

/**
 * @author rlarroque
 */
@ContextConfiguration(locations = { "classpath:/test-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCompanyValidator {
    
    /**
     * Test.
     */
    @Test
    public void validateCompany() {
        Company company = new Company(1l , "Dummy company");
        CompanyValidator.validate(company);
    }
    
    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void validateCompanyNull() {
        Company company = null;
        CompanyValidator.validate(company);
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void validateCompanyIdInvalid() {
        Company company = new Company(-1l , "Dummy company");
        CompanyValidator.validate(company);
    }
    
    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void validateCompanyNameInvalid() {
        Company company = new Company(1l , null);
        CompanyValidator.validate(company);
    }
    
    /**
     * Test.
     */
    @Test
    public void validateDTO() {
        CompanyDTO dto = new CompanyDTO(1l , "Dummy company");
        CompanyDTOValidator.validate(dto);
    }
    
    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void validateDTONull() {
        CompanyDTO dto = null;
        CompanyDTOValidator.validate(dto);
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void validateDTOIdInvalid() {
        CompanyDTO dto = new CompanyDTO(-1l , "Dummy company");
        CompanyDTOValidator.validate(dto);
    }
    
    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void validateDTONameInvalid() {
        CompanyDTO dto = new CompanyDTO(1l , null);
        CompanyDTOValidator.validate(dto);
    }
}
