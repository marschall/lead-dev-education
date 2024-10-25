package com.netcetera.leaddevedu.jfr.load;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

import jakarta.annotation.PreDestroy;

public final class LoadGenerator {
  
  private final RestOperations restOperations;
  
  private final ExecutorService executorService;

  public LoadGenerator(RestOperations restOperations) {
    this.restOperations = restOperations;
    this.executorService = Executors.newVirtualThreadPerTaskExecutor();
  }

  public void stat() {
    this.submitNext();
  }
  
  private void submitNext() {
    this.executorService.submit(() -> {
      this.findOwner();
      this.findVet();
      this.submitNext();
    });
  }
  
  @PreDestroy
  public void close() {
    this.executorService.shutdownNow();
  }
  
  private void findOwner() {
    ResponseEntity<Void> responseEntity = this.restOperations.getForEntity("http://127.0.0.1:8080/owners?lastName=", Void.class);
    verify(responseEntity);
  }

  private void findVet() {
    ResponseEntity<Void> responseEntity = this.restOperations.getForEntity("http://127.0.0.1:8080/vets.html", Void.class);
    verify(responseEntity);
  }
  
  private void verify(ResponseEntity<?> responseEntity) {
    HttpStatusCode statusCode = responseEntity.getStatusCode();
    if (!statusCode.is2xxSuccessful()) {
      throw new RuntimeException("request failed with code: " + statusCode);
    }
  }

}
