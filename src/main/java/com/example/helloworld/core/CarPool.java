package com.example.helloworld.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARPOOL")
public class CarPool implements java.io.Serializable {

  private String date;
  private String driverName;

  public CarPool() {
  }

  public CarPool(String date, String patientName) {
      this.driverName = patientName;
      this.date = date;
  }

  //@Column(name = "DATE", length = 20, unique = true, nullable = false)
  @Id
  @Column(name = "DATE", length = 20)
  public String getDate() {
    return this.date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  @Column(name = "DRIVER", length = 20)
  public String getDriverName() {
    return this.driverName;
  }

  public void setDriverName(String driverName) {
    this.driverName = driverName;
  }

  public String toString()  {
    StringBuilder bld = new StringBuilder("DATE : ");
    bld.append(date);
    bld.append("  NAME  : ");
    bld.append(driverName);
    return bld.toString();
  }
}