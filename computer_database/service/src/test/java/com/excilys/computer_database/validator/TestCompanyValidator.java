package com.excilys.computer_database.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.model.Company;

/**
 * @author rlarroque
 */
@ContextConfiguration(locations = { "classpath:/test-service-context.xml" })
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
}
