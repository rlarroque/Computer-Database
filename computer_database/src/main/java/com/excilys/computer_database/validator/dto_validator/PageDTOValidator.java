package com.excilys.computer_database.validator.dto_validator;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.webapp.dto.PageDTO;

/**
 * Interface with static methods used to validate the integrity of a PageDTO.
 * @author rlarroque
 */
public interface PageDTOValidator {

    /**
     * Validate the integrity of a pageDTO.
     * @param dto pageDTO to validate
     * @throws IntegrityException thrown if the integrity is not respected
     */
    static void validate(PageDTO dto) throws IntegrityException {

        if (dto == null) {
            throw new IntegrityException("The Page is null");
        }

        if (dto.getTotalComputer() < 0) {
            throw new IntegrityException("Total computer information is not valid");
        }

        if (dto.getCurrentPage() < 1) {
            throw new IntegrityException("Page number information is not valid");
        }

        if (!(dto.getOffset() == 10 || dto.getOffset() == 50 || dto.getOffset() == 100)) {
            throw new IntegrityException("Offset information is not valid");
        }

        if (dto.getTotalPage() < 0) {
            throw new IntegrityException("Total page count is not valid");
        }

        if (dto.getStartPage() < 1) {
            throw new IntegrityException("The start page is not valid");
        }

        if (dto.getEndPage() > dto.getTotalPage()) {
            throw new IntegrityException("The end page is not valid");
        }

        if ((dto.getEndPage() < dto.getStartPage()) && dto.getTotalComputer() != 0) {
            throw new IntegrityException("End page cannot be before start page");
        }
        
        throw new IntegrityException("This is a test");
    }
}