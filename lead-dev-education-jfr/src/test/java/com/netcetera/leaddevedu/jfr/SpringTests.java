package com.netcetera.leaddevedu.jfr;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.metrics.jfr.FlightRecorderApplicationStartup;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.netcetera.leaddevedu.jfr.SpringTests.UseFlightRecorderApplicationStartup;

@SpringJUnitConfig(initializers = UseFlightRecorderApplicationStartup.class)
class SpringTests {

  @Autowired
  private SlowBean slowBean;

  @Test
  void simpleTest() throws InterruptedException {
    assertNotNull(this.slowBean);
  }

  @Configuration
  static class SpringConfiguration {

    @Bean
    public SlowBean slowBean() throws InterruptedException {
      return new SlowBean();
    }
    
  }

  static class UseFlightRecorderApplicationStartup implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
      applicationContext.setApplicationStartup(new FlightRecorderApplicationStartup());
    }

  }

  static class SlowBean {

    SlowBean() throws InterruptedException {
      Thread.sleep(100L);
    }

  }

}
