package com.netcetera.leaddevedu.jfr;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.metrics.jfr.FlightRecorderApplicationStartup;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.netcetera.leaddevedu.jfr.SpringTests.UseFlightRecorderApplicationStartup;

@SpringJUnitConfig(initializers = UseFlightRecorderApplicationStartup.class)
class SpringTests {

  @Test
  void simpleTest() {

  }

  @Configuration
  static class SpringConfiguration {

  }

  static class UseFlightRecorderApplicationStartup implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
      applicationContext.setApplicationStartup(new FlightRecorderApplicationStartup());
    }

  }

}
