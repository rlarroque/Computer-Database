package com.excilys.computer_database.validator;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.model.Page;

/**
 * Interface with static methods used to validate the integrity of a Page.
 * @author rlarroque
 */
public interface PageValidator {

    /** 	
     * Validate the integrity of a page.
     * @param page page to validate
     * @throws IntegrityException thrown if the integrity is not respected
     */
    static void validate(Page page) throws IntegrityException {

        if (page == null) {
            throw new IntegrityException("The Page is null");
        }

        if (page.getCurrentPage() < 1) {
            throw new IntegrityException("Page number information is not valid");
        }

        if (!(page.getOffset() == 10 || page.getOffset() == 50 || page.getOffset() == 100)) {
            throw new IntegrityException("Offset information is not valid");
        }

        if (page.getStartIndex() < 0) {
            throw new IntegrityException("Start index is not valid");
        }

        if (page.getTotalPage() < 0) {
            throw new IntegrityException("Total page number is not valid");
        }
    }
}
