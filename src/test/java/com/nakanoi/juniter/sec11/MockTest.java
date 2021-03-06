package com.nakanoi.juniter.sec11;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/** Mock test. */
public class MockTest {
  @Test
  void checkMockApis() throws Exception {
    List<String> mock = mock(ArrayList.class);
    assertThat(mock.get(0)).isNull();

    when(mock.get(0)).thenReturn("Hello");
    doThrow(new RuntimeException()).when(mock).clear();

    assertThat(mock.get(0)).isEqualTo("Hello");
    assertThrows(RuntimeException.class, mock::clear);

    when(mock.get(anyInt())).thenReturn("String");
    assertThat(mock.get(100)).isEqualTo("String");

    mock.add("Hello");
    mock.add("Hello");

    verify(mock, times(2)).add("Hello");
    verify(mock, never()).add("String");
  }

  @Test
  void testMockWithPartialMock() throws Exception {
    List<Integer> list = new ArrayList<>();
    List<Integer> spy = spy(list);
    when(spy.size()).thenReturn(100);
    spy.add(4);

    assertThat(spy.get(0)).isEqualTo(4);
    assertThat(spy.size()).isEqualTo(100);
  }

  @Test
  void testMockWithSpy() throws Exception {
    List<String> list = new ArrayList<>();
    List<String> spy = spy(list);

    spy.add("Hello");
    spy.add("World");
    spy.add("Hello");

    verify(spy, times(2)).add("Hello");
    verify(spy).add("World");
    assertThat(spy.get(0)).isEqualTo("Hello");
  }
}
