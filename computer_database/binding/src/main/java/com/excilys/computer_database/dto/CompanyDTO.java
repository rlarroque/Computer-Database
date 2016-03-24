package com.excilys.computer_database.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Data Transfer Object used to bring information from the server to the view. Only primitive types
 * are allowed here.
 * @author rlarroque
 *
 */
public class CompanyDTO {

    private long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    
    /**
     * Constructor.
     * @param id id of the dto
     * @param name name of the dto
     */
    public CompanyDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Default constructor.
     */
    public CompanyDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CompanyDTO [name=" + name + ", id=" + id + "]";
    }
}
