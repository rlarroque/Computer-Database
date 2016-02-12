package com.excilys.computer_database.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.excilys.computer_database.dto.ComputerDTO;
import com.excilys.computer_database.model.Company;
import com.excilys.computer_database.model.Computer;

/**
 * Mapper used to convert a resultSet into a Computer object.
 * 
 * @author excilys
 *
 */
public interface ComputerMapper {

	public static Computer resultSetToComputer(ResultSet rs) throws SQLException {

		Computer computer = new Computer();

		computer.setId(rs.getInt("computer.id"));
		computer.setName(rs.getString("computer.name"));

		if (rs.getTimestamp("introduced") != null) {
			computer.setIntroduced(rs.getTimestamp("introduced").toLocalDateTime());
		}

		if (rs.getTimestamp("discontinued") != null) {
			computer.setDiscontinued(rs.getTimestamp("discontinued").toLocalDateTime());
		}

		Company company = new Company();
		company.setId(rs.getInt("company.id"));
		company.setName(rs.getString("company.name"));

		computer.setCompany(company);

		return computer;
	}

	public static Computer dtoToComputer(ComputerDTO dto) {

		LocalDate introducedLocalDate = null;
		LocalTime introducedLocaTime = null;
		LocalDate discontinuedLocaDate = null;
		LocalTime discontinuedLocaTime = null;

		Computer computer = new Computer();

		computer.setName(dto.getName());

		if (dto.getIntroducedDate() == "" || dto.getIntroducedDate() == null) {
			computer.setIntroduced(null);
		} else {
			introducedLocalDate = LocalDate.parse(dto.getIntroducedDate(), DateTimeFormatter.ISO_LOCAL_DATE);

			if (dto.getIntroducedTime() == "" || dto.getIntroducedTime() == null) {
				introducedLocaTime = LocalTime.of(0, 0, 0);
			} else {
				introducedLocaTime = LocalTime.parse(dto.getIntroducedTime(), DateTimeFormatter.ISO_LOCAL_TIME);
			}

			computer.setIntroduced(LocalDateTime.of(introducedLocalDate, introducedLocaTime));
		}

		if (dto.getDiscontinuedDate() == "" || dto.getDiscontinuedDate() == null) {
			computer.setIntroduced(null);
		} else {
			discontinuedLocaDate = LocalDate.parse(dto.getDiscontinuedDate(), DateTimeFormatter.ISO_LOCAL_DATE);

			if (dto.getDiscontinuedTime() == "" || dto.getDiscontinuedTime() == null) {
				discontinuedLocaTime = LocalTime.of(0, 0, 0);
			} else {
				discontinuedLocaTime = LocalTime.parse(dto.getDiscontinuedTime(), DateTimeFormatter.ISO_LOCAL_TIME);
			}

			computer.setDiscontinued(LocalDateTime.of(discontinuedLocaDate, discontinuedLocaTime));
		}

		if (dto.getCompany_name() == "" || dto.getCompany_name() == null) {
			computer.setCompany(null);
		} else {
			computer.setCompany(new Company(dto.getCompany_name()));
		}

		return computer;
	}

	public static ComputerDTO computerToDTO(Computer computer) {

		ComputerDTO dto = new ComputerDTO();

		dto.setName(computer.getName());

		if (computer.getIntroduced() == null) {
			dto.setIntroducedDate("");
			dto.setIntroducedTime("");
		} else {
			dto.setIntroducedDate(computer.getIntroduced().toLocalDate().toString());
			dto.setIntroducedTime(computer.getIntroduced().toLocalTime().toString());
		}

		if (computer.getDiscontinued() == null) {
			dto.setDiscontinuedDate("");
			dto.setDiscontinuedTime("");
		} else {
			dto.setDiscontinuedDate(computer.getDiscontinued().toLocalDate().toString());
			dto.setDiscontinuedTime(computer.getDiscontinued().toLocalTime().toString());
		}

		if (computer.getCompany() == null) {
			dto.setCompany_name("");
		} else {
			dto.setCompany_name(computer.getCompany().getName());
		}

		return dto;
	}

}
