package com.excilys.computer_database.mapper;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computer_database.dto.ComputerDTO;
import com.excilys.computer_database.model.Company;
import com.excilys.computer_database.model.Computer;

@ContextConfiguration(locations = {"classpath:/test-binding-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestComputerMapper {
    
    @Autowired
    private ComputerMapper computerMapper;

    /**
     * Test.
     */
    @Test
    public void testComputerToDTO() {

        Company company = new Company(1l, "Dummy Company");
        Computer computer = new Computer.ComputerBuilder("Dummy Computer").build();
        computer.setIntroduced(LocalDate.of(2000, 1, 1));
        computer.setDiscontinued(LocalDate.of(2001, 1, 1));
        computer.setCompany(company);

        ComputerDTO dto = computerMapper.toDTO(computer);

        assertEquals(computer.getName(), dto.getName());
        assertEquals("01-01-2000", dto.getIntroducedDate());
        assertEquals("01-01-2001", dto.getDiscontinuedDate());
        assertEquals(computer.getCompany().getName(), dto.getCompanyName());
    }

    /**
     * Test.
     */
    @Test
    public void testComputerToDTOWithNull() {

        Computer computer = new Computer.ComputerBuilder("Dummy Computer").build();
        ComputerDTO dto = computerMapper.toDTO(computer);

        assertEquals(computer.getName(), dto.getName());
        assertEquals("", dto.getIntroducedDate());
        assertEquals("", dto.getDiscontinuedDate());
        assertEquals("", dto.getCompanyName());
    }

    /**
     * Test.
     */
    @Test
    public void testDtoToComputer() {

        ComputerDTO dto = createDummyDTO();
        Computer computer = computerMapper.toComputer(dto);

        assertEquals(dto.getName(), computer.getName());
        assertEquals(dto.getCompanyName(), computer.getCompany().getName());
    }

    /**
     * Test.
     */
    @Test
    public void testDtoToComputerWithNullDate() {
        ComputerDTO dto = createDummyDTO();
        dto.setIntroducedDate("");
        dto.setDiscontinuedDate("");
        dto.setCompanyName("");
        dto.setCompanyId(0);

        Computer computer = computerMapper.toComputer(dto);

        assertEquals(dto.getName(), computer.getName());
        assertEquals(null, computer.getIntroduced());
        assertEquals(null, computer.getDiscontinued());
        assertEquals(null, computer.getCompany());
        assertEquals(null, computer.getCompany());
    }

    /**
     * Creation of dummy computerDTO
     * @return the dummy DTO
     */
    public ComputerDTO createDummyDTO() {

        ComputerDTO dto = new ComputerDTO();
        dto.setName("Dummy Computer");
        dto.setIntroducedDate("01-01-2000");
        dto.setDiscontinuedDate("01-01-2001");
        dto.setCompanyId(1);
        dto.setCompanyName("Dummy Company");

        return dto;
    }
}
