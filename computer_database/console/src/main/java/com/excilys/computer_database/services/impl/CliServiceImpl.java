package com.excilys.computer_database.services.impl;

import com.excilys.computer_database.dto.CompanyDTO;
import com.excilys.computer_database.dto.ComputerDTO;
import com.excilys.computer_database.exception.CliException;
import com.excilys.computer_database.services.CliService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
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

    private static ClientConfig cfg = new DefaultClientConfig();
    static {
        // Jackson's MessageBodyReader seems better than the Jersey'ss one.
        cfg.getClasses().add(JacksonJsonProvider.class);
    }

    @Override
    public List<ComputerDTO> getAllComputers() {

        ClientResponse response = Client.create(cfg)
                                        .resource(REST_URL + COMPUTER)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new CliException("Failed : HTTP error code : " + response.getStatus());
        }

        return response.getEntity(new GenericType<List<ComputerDTO>>() {});
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {

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

        ClientResponse response = Client.create(cfg)
                                        .resource(REST_URL + COMPUTER + "/" + id)
                                        .type(MediaType.APPLICATION_JSON)
                                        .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new CliException("Failed : HTTP error code : " + response.getStatus());
        }

        return response.getEntity(new GenericType<ComputerDTO>() {});
    }

    @Override
    public long create(ComputerDTO computer) {

        ClientResponse response = Client.create(cfg)
                                        .resource(REST_URL + COMPUTER)
                                        .type(MediaType.APPLICATION_JSON_TYPE)
                                        .post(ClientResponse.class, gson.toJson(computer));

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }

        return response.getEntity(ComputerDTO.class).getId();
    }

    @Override
    public void update(ComputerDTO computer) {

        ClientResponse response = Client.create(cfg)
                                        .resource(REST_URL + COMPUTER)
                                        .type(MediaType.APPLICATION_JSON_TYPE)
                                        .put(ClientResponse.class, gson.toJson(computer));

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
    }

    @Override
    public void delete(long id) {
        ClientResponse response = Client.create(cfg)
                                        .resource(REST_URL + COMPUTER + "/" + id)
                                        .type(MediaType.APPLICATION_JSON)
                                        .delete(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
    }

    @Override
    public void deleteByCompany(long id) {
        ClientResponse response = Client.create(cfg)
                                        .resource(REST_URL + COMPANY + "/" + id)
                                        .type(MediaType.APPLICATION_JSON)
                                        .delete(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
    }
}
