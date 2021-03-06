package com.nakanoi.juniter.sec9;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

/** Test Server Resource implemented ExternalResource. */
public class ExternalResourceExampleTest {
  @RegisterExtension
  static ExternalResourceExampleExtension serverResource =
      ExternalResourceExampleExtension.builder().build();

  /** Test server returns string which is equal to the request path. */
  @Test
  void testServerConnection() throws Exception {
    Server server = serverResource.getServer();
    String path = "hello";
    // Simulated get request to the server.
    String actual = server.get(path);

    assertThat(actual).isEqualTo(path);
  }
}
