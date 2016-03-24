package com.excilys.computer_database.validator;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.model.Company;
import com.excilys.computer_database.model.Computer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

/**
 * @author rlarroque
 */
@ContextConfiguration(locations = { "classpath:/test-service-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestComputerValidator {

    /**
     * Test.
     */
    @Test
    public void validateComputer() {
        Computer computer = new Computer("Dummy computer", LocalDate.ofYearDay(2012, 65), LocalDate.ofYearDay(2012, 89), new Company(1l, "Dummy company"));
        ComputerValidator.validate(computer);
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void validateComputerNull() {
        Computer computer = null;
        ComputerValidator.validate(computer);
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void validateComputerNameInvalid() {
        Computer computer = new Computer(null);
        ComputerValidator.validate(computer);
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void validateComputerDiscontinuedInvalid() {
        Computer computer = new Computer("Dummy computer", null, LocalDate.ofYearDay(2012, 89), new Company(1l, "Dummy company"));
        ComputerValidator.validate(computer);
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void validateComputerDatesInvalid() {
        Computer computer = new Computer("Dummy computer", LocalDate.ofYearDay(2012, 187), LocalDate.ofYearDay(2012, 89), new Company(1l, "Dummy company"));
        ComputerValidator.validate(computer);
    }
}
