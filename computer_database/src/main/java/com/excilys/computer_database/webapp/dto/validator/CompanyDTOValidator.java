package com.excilys.computer_database.webapp.dto.validator;

import java.util.List;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.webapp.dto.CompanyDTO;

/**
 * Interface with static methods used to validate the integrity of a companyDTO.
 * @author rlarroque
 */
public interface CompanyDTOValidator {

    /**
     * Validate the integrity of a companyDTO.
     * @param dto companyDTO to validate
     * @throws IntegrityException thrown if the integrity is not respected
     */
    static void validate(CompanyDTO dto) throws IntegrityException {

        if (dto == null) {
            throw new IntegrityException("The company is null.");
        }

        if (dto.getId() < 1) {
            throw new IntegrityException("The company's id is not valid.");
        }
        
        if (dto.getName() == null || "".equals(dto.getName())) {
            throw new IntegrityException("The company's name is not valid.");
        }
    }

    /**
     * Validate the integrity of a list of companyDTOs.
     * @param list list to validate
     * @throws IntegrityException thrown if the integrity is not respected
     */
    static void validate(List<CompanyDTO> list) throws IntegrityException {
        for (CompanyDTO dto : list) {
            validate(dto);
        }
    }
}
