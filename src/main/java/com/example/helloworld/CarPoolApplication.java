package com.example.helloworld;

import com.example.helloworld.core.CarPoolDAO;
import com.example.helloworld.core.CarPool;
import com.example.helloworld.resources.CarPoolResource;
import com.example.helloworld.resources.CarpoolCreateResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.example.helloworld.health.TemplateHealthCheck;

public class CarPoolApplication extends Application<CarPoolConfiguration> {
  private final HibernateBundle<CarPoolConfiguration> hibernate = new HibernateBundle<CarPoolConfiguration>(CarPool.class) {
    @Override
    public DataSourceFactory getDataSourceFactory(CarPoolConfiguration configuration) {
      return configuration.getDataSourceFactory();
    }
  };

  public static void main(String[] args) throws Exception {
        new CarPoolApplication().run(args);
    }

    @Override
    public String getName() {
        return "carpool-app";
    }

    @Override
    public void initialize(Bootstrap<CarPoolConfiguration> bootstrap) {
      bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(CarPoolConfiguration configuration,
                    Environment environment) {
      final CarPoolDAO dao = new CarPoolDAO(hibernate.getSessionFactory());
      final CarPoolResource resource = new CarPoolResource(
                configuration.getTemplate(),
                configuration.getDefaultName(),
                dao);
      final CarpoolCreateResource createCarpoolResource = new CarpoolCreateResource(dao);
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
      environment.jersey().register(resource);
      environment.jersey().register(createCarpoolResource);
    }
}
