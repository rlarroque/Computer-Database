package com.excilys.computer_database.dto;

/**
 * Data Transfer Object used to bring information from the server to the view. Only primitive types
 * are allowed here.
 * @author rlarroque
 *
 */
public class ComputerDTO {

    public int id;
    public String name;
    public String introducedDate;
    public String discontinuedDate;
    public String companyName;
    public int companyId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
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
