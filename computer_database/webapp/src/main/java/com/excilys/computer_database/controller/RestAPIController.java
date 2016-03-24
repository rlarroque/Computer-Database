package com.excilys.computer_database.controller;

import com.excilys.computer_database.dto.ComputerDTO;
import com.excilys.computer_database.mapper.ComputerMapper;
import com.excilys.computer_database.services.ComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Controller in charge of exposing a full rest api of the application.
 * @author rlarroque
 */
@RestController
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestAPIController extends ApplicationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestAPIController.class);

    @Autowired
    private ComputerService computerService;

    @Autowired
    private ComputerMapper computerMapper;

    @RequestMapping(method = RequestMethod.GET, value = REST_API + COMPUTER)
    public List<ComputerDTO> getComputer() {

        LOGGER.info("[GET rest] all computers");

        return computerMapper.toDTO(computerService.getAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = REST_API + COMPUTER + ID)
    public ComputerDTO getByIdComputer(@PathVariable("id") long id) {

        LOGGER.info("[GET rest] computer by id");

        return computerMapper.toDTO(computerService.get(id));
    }

    @RequestMapping(method = RequestMethod.POST, value = REST_API + COMPUTER)
    public ComputerDTO postComputer(@RequestBody ComputerDTO computer) {

        LOGGER.info("[POST rest] computer: " + computer);
        computerService.create(computerMapper.toComputer(computer));

        return computer;
    }

    @RequestMapping(method = RequestMethod.PUT, value = REST_API + COMPUTER)
    public ComputerDTO putComputer(@RequestBody ComputerDTO computer) {

        LOGGER.info("[PUT rest] computer");
        computerService.update(computerMapper.toComputer(computer));

        return computer;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = REST_API + COMPUTER  + ID)
    public void deleteComputer(@PathVariable("id") long id) {

        LOGGER.info("[DELETE rest] computer");
        computerService.delete(id);
    }
}
