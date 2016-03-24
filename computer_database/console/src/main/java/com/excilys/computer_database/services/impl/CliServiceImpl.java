package com.excilys.computer_database.services.impl;

import com.excilys.computer_database.dto.model.CompanyDTO;
import com.excilys.computer_database.dto.model.ComputerDTO;
import com.excilys.computer_database.exception.CliException;
import com.excilys.computer_database.mapper.CompanyMapper;
import com.excilys.computer_database.mapper.ComputerMapper;
import com.excilys.computer_database.model.Company;
import com.excilys.computer_database.model.Computer;
import com.excilys.computer_database.services.CliService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author rlarroque
 */
@Service
@SuppressWarnings("unchecked")
public class CliServiceImpl implements CliService {

    private static final String REST_URL = "http://localhost:8181/api/rest";
    private static final String COMPUTER = "/computer";
    private static final String COMPANY = "/company";
    private static final Gson gson = new GsonBuilder().create();

    @Override
    public List<ComputerDTO> getAllComputers() {

        ClientConfig cfg = new DefaultClientConfig();
        cfg.getClasses().add(JacksonJsonProvider.class);

        ClientResponse response = Client.create(cfg)
                                        .resource(REST_URL + COMPUTER)
                                        .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new CliException("Failed : HTTP error code : " + response.getStatus());
        }

        return response.getEntity(new GenericType<List<ComputerDTO>>() {});
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {

        ClientConfig cfg = new DefaultClientConfig();
        cfg.getClasses().add(JacksonJsonProvider.class);

        ClientResponse response = Client.create(cfg)
                                        .resource(REST_URL + COMPANY)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new CliException("Failed : HTTP error code : " + response.getStatus());
        }

        return response.getEntity(new GenericType<List<CompanyDTO>>() {});
    }

    @Override
    public ComputerDTO get(long id) {

        ClientResponse response = Client.create()
                                        .resource(REST_URL + COMPUTER + "/" + id)
                                        .type(MediaType.APPLICATION_JSON)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new CliException("Failed : HTTP error code : " + response.getStatus());
        }

        return response.getEntity(new GenericType<ComputerDTO>() {});

    }

    @Override
    public long create(ComputerDTO computer) {

        ClientResponse response = Client.create()
                                        .resource(REST_URL + COMPUTER)
                                        .type(MediaType.APPLICATION_JSON)
                                        .post(ClientResponse.class, gson.toJson(computer));

        if (response.getStatus() != 201) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }

        return response.getEntity(Computer.class).getId();
    }

    @Override
    public void update(ComputerDTO computer) {

        ClientResponse response = Client.create()
                                        .resource(REST_URL + COMPUTER)
                                        .type(MediaType.APPLICATION_JSON)
                                        .put(ClientResponse.class, gson.toJson(computer));

        if (response.getStatus() != 204) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
    }

    @Override
    public void delete(long id) {
        ClientResponse response = Client.create()
                                        .resource(REST_URL + COMPUTER + "/" + id)
                                        .type(MediaType.APPLICATION_JSON)
                                        .delete(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
    }

    @Override
    public void deleteByCompany(long id) {
        ClientResponse response = Client.create()
                                        .resource(REST_URL + COMPANY + "/" + id)
                                        .type(MediaType.APPLICATION_JSON)
                                        .delete(ClientResponse.class);

        if (response.getStatus() != 204) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
    }
}
