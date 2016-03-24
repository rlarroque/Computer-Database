package com.excilys.computer_database.validator;

import com.excilys.computer_database.validator.dto.CompanyDTOValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computer_database.dto.CompanyDTO;
import com.excilys.computer_database.exception.IntegrityException;

/**
 * @author rlarroque
 */
@ContextConfiguration(locations = { "classpath:/test-binding-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCompanyDtoValidator {
    
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
