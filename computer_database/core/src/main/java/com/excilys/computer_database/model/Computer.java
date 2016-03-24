package com.excilys.computer_database.model;

import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * This class describe a computer with all its parameters. id, name, introduced date, discontinued date, and company id. This is a simple POJO.
 * @author rlarroque
 *
 */
@Entity(name = "computer")
public class Computer implements Serializable{
    
    private static final long serialVersionUID = 3529447147619528271L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String name;
    private LocalDate introduced;
    private LocalDate discontinued;
    
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

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

    public LocalDate getIntroduced() {
        return introduced;
    }

    public void setIntroduced(LocalDate introduced) {
        this.introduced = introduced;
    }

    public LocalDate getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(LocalDate discontinued) {
        this.discontinued = discontinued;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    // Pattern builder
    public Computer(ComputerBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.introduced = builder.introduced;
        this.discontinued = builder.discontinued;
        this.company = builder.company;
    }

    public static class ComputerBuilder {
        private int id;
        private String name;
        private LocalDate introduced;
        private LocalDate discontinued;
        private Company company;

        // Name is mandatory
        public ComputerBuilder (String name) {
            this.name = name;
        }

        public ComputerBuilder id(int id) {
            this.id = id;
            return this;
        }

        public ComputerBuilder introduced(LocalDate introduced) {
            this.introduced = introduced;
            return this;
        }

        public ComputerBuilder discontinued(LocalDate discontinued) {
            this.discontinued = discontinued;
            return this;
        }

        public ComputerBuilder company(Company company) {
            this.company = company;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }

    public Computer() {

    }

    @Override
    public String toString() {

        return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced
                + ", discontinued=" + discontinued + ", company=" + company + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
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

        Computer other = (Computer) obj;

        if (company == null) {

            if (other.company != null) {
                return false;
            }
        } else if (!company.equals(other.company)) {
            return false;
        }

        if (discontinued == null) {

            if (other.discontinued != null) {
                return false;
            }
        } else if (!discontinued.equals(other.discontinued)) {
            return false;
        }

        if (id != other.id) {
            return false;
        }

        if (introduced == null) {

            if (other.introduced != null) {
                return false;
            }
        } else if (!introduced.equals(other.introduced)) {
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