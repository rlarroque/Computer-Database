package com.excilys.computer_database.persistence.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.persistence.model.Company;
import com.excilys.computer_database.persistence.model.Computer;
import com.excilys.computer_database.webapp.dto.ComputerDTO;

/**
 * Mapper used to convert a resultSet or DTO into a Computer object.
 * @author rlarroque
 *
 */
@Component
public class ComputerMapper implements RowMapper<Computer> {
    
    @Autowired
    private DateMapper dateMapper;

    @Override
    public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Computer computer = new Computer();

        computer.setId(rs.getInt("id"));
        computer.setName(rs.getString("computer.name"));

        if (rs.getTimestamp("introduced") != null) {
            computer.setIntroduced(rs.getTimestamp("introduced").toLocalDateTime().toLocalDate());
        }

        if (rs.getTimestamp("discontinued") != null) {
            computer.setDiscontinued(rs.getTimestamp("discontinued").toLocalDateTime().toLocalDate());
        }

        computer.setCompany(new Company(rs.getLong("company.id"), rs.getString("company.name")));

        return computer;
    }

    /**
     * Take a resultSet as parameter and return a Computer.
     * @param rs resultSet that is returned from a SQL query
     * @return a Computer corresponding to the resultSet
     * @throws SQLException if a SQl exception occurred while reading the resultSet
     * @throws IntegrityException thrown if the integrity is not respected
     */
    public Computer toComputer(ResultSet rs) throws SQLException, IntegrityException {

        Computer computer = new Computer();

        computer.setId(rs.getInt("computer.id"));
        computer.setName(rs.getString("computer.name"));

        if (rs.getTimestamp("introduced") != null) {
            computer.setIntroduced(rs.getTimestamp("introduced").toLocalDateTime().toLocalDate());
        }

        if (rs.getTimestamp("discontinued") != null) {
            computer.setDiscontinued(rs.getTimestamp("discontinued").toLocalDateTime().toLocalDate());
        }

        Company company = new Company();
        company.setId(rs.getLong("company.id"));
        company.setName(rs.getString("company.name"));
        computer.setCompany(company);

        return computer;
    }

    /**
     * Used to map a ComputerDTO into a Computer.
     * @param dto dto to map
     * @return the Computer mapped
     * @throws IntegrityException thrown if the integrity is not respected
     */
    public Computer toComputer(ComputerDTO dto) throws IntegrityException {

        Computer computer = new Computer();

        computer.setId(dto.getId());
        computer.setName(dto.getName());

        if ("".equals(dto.getIntroducedDate())) {
            computer.setIntroduced(null);
        } else {
            dto.setIntroducedDate(dto.getIntroducedDate().replace('/', '-'));
            computer.setIntroduced(dateMapper.toLocalDate(dto.getIntroducedDate()));
        }

        if ("".equals(dto.getDiscontinuedDate())) {
            computer.setDiscontinued(null);
        } else {
            dto.setDiscontinuedDate(dto.getDiscontinuedDate().replace('/', '-'));
            computer.setDiscontinued(dateMapper.toLocalDate((dto.getDiscontinuedDate())));
        }

        if (dto.getCompanyId() != 0) {
            computer.setCompany(new Company(dto.getCompanyId(), dto.getCompanyName()));
        } else {
            computer.setCompany(null);
        }

        return computer;
    }

    /**
     * Used to map a list of ComputerDTO into a list of Computers.
     * @param dtoList list to map
     * @return the list of Computers mapped
     */
    public List<Computer> toComputer(List<ComputerDTO> dtoList) {
        List<Computer> computerList = new ArrayList<>();

        if (dtoList != null) {
            for (ComputerDTO dto : dtoList) {
                computerList.add(toComputer(dto));
            }
        }

        return computerList;
    }

    /**
     * Used to map a Computer into a ComputerDTO.
     * @param computer to map
     * @return the dto mapped
     * @throws IntegrityException thrown if the integrity is not respected
     */
    public ComputerDTO toDTO(Computer computer) throws IntegrityException {

        ComputerDTO dto = new ComputerDTO();

        dto.setId(computer.getId());

        dto.setName(computer.getName());
        if (computer.getIntroduced() == null) {
            dto.setIntroducedDate("");
        } else {
            dto.setIntroducedDate(dateMapper.toString(computer.getIntroduced()));
        }

        if (computer.getDiscontinued() == null) {
            dto.setDiscontinuedDate("");
        } else {
            dto.setDiscontinuedDate(dateMapper.toString(computer.getDiscontinued()));
        }

        if (computer.getCompany() == null) {
            dto.setCompanyId(0);
            dto.setCompanyName("");
        } else {
            dto.setCompanyId(computer.getCompany().getId());

            if (computer.getCompany().getName() == null || "".equals(computer.getCompany().getName())) {
                dto.setCompanyName("");
            } else {
                dto.setCompanyName(computer.getCompany().getName());
            }
        }

        return dto;
    }

    /**
     * Used to map a request into a dto.
     * @param request request received
     * @return the mapped dto
     */
    public ComputerDTO toDTO(HttpServletRequest request) {

        ComputerDTO dto = new ComputerDTO();

        dto.setName(request.getParameter("computerName"));
        dto.setIntroducedDate(request.getParameter("introduced"));
        dto.setDiscontinuedDate(request.getParameter("discontinued"));
        dto.setCompanyId(Long.parseLong(request.getParameter("companyId")));

        return dto;
    }

    /**
     * Used to map a list of Computers into a list of ComputerDTO.
     * @param computerList to map
     * @return the list of dto mapped
     */
    public List<ComputerDTO> toDTO(List<Computer> computerList) {
        List<ComputerDTO> computerDTOList = new ArrayList<>();

        for (Computer computer : computerList) {
            computerDTOList.add(toDTO(computer));
        }

        return computerDTOList;
    }

}
