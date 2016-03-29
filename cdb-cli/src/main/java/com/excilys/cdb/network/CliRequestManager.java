package com.excilys.cdb.network;

import com.excilys.cdb.dtos.CompanyDto;
import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.dtos.ComputerPageDto;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class CliRequestManager {
  
  // Credentials
  private static final String USERNAME = "admin";
  private static final String PASSWORD = "admin";

  // Constants
  private static final String BASE_URL = "http://localhost:8080/computer-database/json";
  private static final String LOCALDATEFORMAT_PATH = "/localDateFormat";
  private static final String COMPUTER_PATH = "/computers/%d";
  private static final String COMPUTERS_PATH = "/computers";
  private static final String COMPUTERS_DELETE_PATH = "/computers/delete";
  private static final String COMPUTERS_CREATE_PATH = "/computers/new";
  private static final String COMPUTERS_UPDATE_PATH = "/computers/edit";
  private static final String COMPANIES_PATH = "/companies";
  private static final String COMPANIES_DELETE_PATH = "/companies/delete";

  public static ComputerPageDto getComputerPageDto(int page) {

    Client c = ClientBuilder.newClient().register(new Authenticator(USERNAME, PASSWORD));
    WebTarget baseUrlWt = c.target(BASE_URL);

    try {
      Invocation.Builder bldr = baseUrlWt.path(COMPUTERS_PATH).queryParam("page", page).request();
      return bldr.get(ComputerPageDto.class);
    } finally {
      c.close();
    }
  }
  
  public static String getLocalDate() {

    Client c = ClientBuilder.newClient().register(new Authenticator(USERNAME, PASSWORD));
    WebTarget baseUrlWt = c.target(BASE_URL);

    try {
      Invocation.Builder bldr = baseUrlWt.path(LOCALDATEFORMAT_PATH).request();
      return bldr.get(String.class);
    } finally {
      c.close();
    }
  }

  public static ComputerDto getComputerById(long id) {
    Client c = ClientBuilder.newClient();
    WebTarget baseUrlWt = c.target(BASE_URL);
    
    try{
    Invocation.Builder bldr = baseUrlWt.path(String.format(COMPUTER_PATH, id)).request();
    return bldr.get(ComputerDto.class);
    } finally {
      c.close();
    }
  }

  public static List<CompanyDto> getCompanies() {
    Client c = ClientBuilder.newClient();
    WebTarget baseUrlWt = c.target(BASE_URL);

    try {

      Invocation.Builder bldr = baseUrlWt.path(COMPANIES_PATH).request();
      return bldr.get(new GenericType<List<CompanyDto>>() {
      });

    } finally {
      c.close();
    }
  }

  public static void createComputer(ComputerDto computerDto) {
    Client c = ClientBuilder.newClient();
    WebTarget baseUrlWt = c.target(BASE_URL);

    try {

      Invocation.Builder bldr =
          baseUrlWt.path(COMPUTERS_CREATE_PATH).request(MediaType.APPLICATION_JSON_TYPE);
      bldr.post(Entity.json(computerDto));
    } finally {
      c.close();
    }
  }

  public static void updateComputer(ComputerDto computerDto) {
    Client c = ClientBuilder.newClient();
    WebTarget baseUrlWt = c.target(BASE_URL);

    try {

      Invocation.Builder bldr =
          baseUrlWt.path(COMPUTERS_UPDATE_PATH).request(MediaType.APPLICATION_JSON_TYPE);
      bldr.post(Entity.json(computerDto));
    } finally {
      c.close();
    }
  }

  public static void deleteComputer(long id) {
    Client c = ClientBuilder.newClient();
    WebTarget baseUrlWt = c.target(BASE_URL);

    try {
      Invocation.Builder bldr =
          baseUrlWt.path(COMPUTERS_DELETE_PATH).request(MediaType.APPLICATION_JSON_TYPE);
      Form form = new Form().param("id", Long.toString(id));
      Entity<Form> entity = Entity.form(form);
      bldr.post(entity);
    } finally {
      c.close();
    }
  }

  public static void deleteCompany(long id) {
    Client c = ClientBuilder.newClient();
    WebTarget baseUrlWt = c.target(BASE_URL);

    try {

      Invocation.Builder bldr =
          baseUrlWt.path(COMPANIES_DELETE_PATH).request(MediaType.APPLICATION_JSON_TYPE);
      Form form = new Form().param("id", Long.toString(id));
      Entity<Form> entity = Entity.form(form);
      bldr.post(entity);
    } finally {
      c.close();
    }
  }
}
