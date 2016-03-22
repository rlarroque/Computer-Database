package com.excilys.computer_database.validator;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.model.Computer;
import com.excilys.computer_database.model.Page;
import com.excilys.computer_database.model.utils.Order;
import com.excilys.computer_database.model.utils.OrderColumn;
import com.excilys.computer_database.model.utils.OrderType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 * @author rlarroque
 */
@ContextConfiguration(locations = { "classpath:/test-core-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPageValidator {

    private Page pageToTest;

    @Before
    public void executeBeforeEachTest() {
        pageToTest = new Page();
        pageToTest.setOrder(new Order(OrderType.ASC, OrderColumn.ID));
        pageToTest.setFilter("filter");
        pageToTest.setOffset(10);
        pageToTest.setComputers(Arrays.asList(new Computer("Dummy Computer")));
        pageToTest.setStartIndex(1);
        pageToTest.setCurrentPage(1);
        pageToTest.setTotalComputer(1);
        pageToTest.setTotalPage(1);
    }

    /**
     * Test.
     */
    @Test
    public void validatePage() {
        PageValidator.validate(pageToTest);
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void validatePageNull() {
        pageToTest = null;
        PageValidator.validate(pageToTest);
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void validatePageTotalCompInvalid() {
        pageToTest.setTotalComputer(-1);
        PageValidator.validate(pageToTest);
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void validatePageCurrentPageInvalid() {
        pageToTest.setCurrentPage(-1);
        PageValidator.validate(pageToTest);
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void validatePageOffsetInvalid() {
        pageToTest.setOffset(25);
        PageValidator.validate(pageToTest);
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void validatePageStartIndexInvalid() {
        pageToTest.setStartIndex(-1);
        PageValidator.validate(pageToTest);
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void validatePageTotalPageInvalid() {
        pageToTest.setTotalPage(-1);
        PageValidator.validate(pageToTest);
    }
}
