package com.excilys.computer_database.webapp.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.excilys.computer_database.validator.dto_validator.Computer;
import com.excilys.computer_database.validator.dto_validator.Date;

/**
 * Data Transfer Object used to bring information from the server to the view. Only primitive types
 * are allowed here.
 * @author rlarroque
 *
 */
@Computer
public class ComputerDTO {

    public long id;
    @NotBlank(message = "Name is mandatory")
    public String name;
    @Date
    public String introducedDate;
    @Date
    public String discontinuedDate;
    public String companyName;
    public long companyId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroducedDate() {
        return introducedDate;
    }

    public void setIntroducedDate(String introducedDate) {
        this.introducedDate = introducedDate;
    }

    public String getDiscontinuedDate() {
        return discontinuedDate;
    }

    public void setDiscontinuedDate(String discontinuedDate) {
        this.discontinuedDate = discontinuedDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    /**
     * Default constructor.
     */
    public ComputerDTO() {

    }

    /**
     * Constructor.
     * @param name name of the dto
     */
    public ComputerDTO(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ComputerDTO [id=" + id + ", name=" + name + ", introducedDate=" + introducedDate
                + ", discontinuedDate=" + discontinuedDate + ", companyId=" + companyId + "]";
    }
}
