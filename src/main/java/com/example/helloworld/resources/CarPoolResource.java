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
import java.util.concurrent.atomic.AtomicLong;

@Path("/viewDrivers")
@Produces(MediaType.APPLICATION_JSON)
public class CarPoolResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;
  private final CarPoolDAO dao;

    public CarPoolResource(String template, String defaultName, CarPoolDAO dao) {
      this.template = template;
      this.defaultName = defaultName;
      this.dao = dao;
      this.counter = new AtomicLong();
    }

    @GET
    @Timed
    @UnitOfWork
    public CarPool displayName(@QueryParam("date") String date) {
      System.out.println(" =================== Name : " + date);
      CarPool p = dao.findByDate(date);
      return p;
    }
}