package com.nakanoi.juniter.sec9;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/** Test external resources. */
public class ExternalResourceExampleExtension implements BeforeAllCallback, AfterAllCallback {
  Server server;

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    server = new Server();
    server.start();
  }

  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    server.shutdown();
    assertFalse(server.getStatus());
  }

  /**
   * Get server.
   *
   * @return A server instance which is instanced in beforeAll.
   */
  public Server getServer() {
    return server;
  }

  /**
   * Return ServerResourceExtension builder. This comes from
   * https://github.com/junit-team/junit5/blob/main/documentation/src/main/java/example/registration/WebServerExtension.java
   *
   * @return Builder
   */
  public static Builder builder() {
    return new Builder();
  }

  /** ServerResourceExtension builder. This comes from same as builder method. */
  public static class Builder {
    public ExternalResourceExampleExtension build() {
      return new ExternalResourceExampleExtension();
    }
  }
}
