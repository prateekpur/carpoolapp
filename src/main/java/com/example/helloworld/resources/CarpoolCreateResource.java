package com.example.helloworld.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.helloworld.core.CarPool;
import com.example.helloworld.core.CarPoolDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/create")
@Produces(MediaType.APPLICATION_JSON)
public class CarpoolCreateResource {
  private final CarPoolDAO dao;

  public CarpoolCreateResource(CarPoolDAO dao)  {
    this.dao = dao;
  }

  @GET
  @Timed
  @UnitOfWork
  public String createCarPool(@QueryParam("date") String date, @QueryParam("name") String driver) {
    System.out.println("Name : " + date + " : Driver : " + driver);
    dao.create(new CarPool(date, driver));
    return driver;
  }
}
