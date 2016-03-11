package com.excilys.computer_database.persistence.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This class describe a company with all its parameters. id, and name. This is a simple POJO.
 * @author rlarroque
 *
 */
@Entity(name = "company")
public class Company implements Serializable {

    private static final long serialVersionUID = -692385214874548903L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
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

    /**
     * Instantiates a new company.
     * @param id the id
     * @param name the name
     */
    public Company(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Instantiates a new company.
     * @param name the name
     */
    public Company(String name) {
        this.name = name;
    }

    /**
     * Instantiates a new company.
     */
    public Company() {
    }

    @Override
    public String toString() {

        return "Company [id=" + id + ", name=" + name + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Company other = (Company) obj;

        if (id != other.id) {
            return false;
        }

        if (name == null) {

            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }

        return true;
    }

}