package com.example.helloworld.core;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class CarPoolDAO extends AbstractDAO<CarPool> {
  public CarPoolDAO(SessionFactory factory) {
    super(factory);
  }

  public CarPool findByDate(String dateTime) {
    return get(dateTime);
  }

  public String create(CarPool person) {
    System.out.println("Create : " + person);
    persist(person);
    return person.getDriverName();
  }

//  public List<CarPool> findAll() {
//    return list(namedQuery("com.example.helloworld.core.CarPool.findAll"));
//  }
}