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
@ContextConfiguration(locations = { "classpath:/test-service-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPageValidator {

    private Page pageToTest;

    @Before
    public void executeBeforeEachTest() {
        pageToTest = new Page.PageBuilder()
                             .currentPage(1)
                             .offset(10)
                             .order(new Order(OrderType.ASC, OrderColumn.ID))
                             .filter("filter")
                             .startIndex(1)
                             .totalPage(1)
                             .computers(Arrays.asList(new Computer("Dummy Computer")))
                             .build();
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
