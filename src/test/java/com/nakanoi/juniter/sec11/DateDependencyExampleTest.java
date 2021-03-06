package com.nakanoi.juniter.sec11;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import org.junit.jupiter.api.Test;

/** Test Mock with DateDependencyExample. */
public class DateDependencyExampleTest {
  @Test
  void testDoSomethingSetsDateCurrentDate() throws Exception {
    final Date current = new Date();
    DateDependencyExample sut = new DateDependencyExample();
    // Do with method
    final MethodType type = MethodType.METHOD;
    // Do with object
    // final MethodType type = MethodType.OBJECT;

    switch (type) {
      case METHOD:
        sut =
            new DateDependencyExample() {
              @Override
              public Date newDate() {
                return current;
              }
            };
        break;
      case OBJECT:
        sut.factory =
            new DateFactory() {
              @Override
              public Date newDate() {
                return current;
              }
            };
        break;
      default:
        sut = new DateDependencyExample();
    }
    sut.doSomething(type);
    assertThat(sut.date).isEqualTo(current);
  }
}
