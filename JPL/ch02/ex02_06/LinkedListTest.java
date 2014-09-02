package ch02.ex02_06;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LinkedListTest {
  private LinkedList linkedList;
  private ByteArrayOutputStream outputStream;

  @Before
  public void prepareLinkedList() {
    linkedList = new LinkedList();
  }

  @Before
  public void setUpStreams() {
    outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
  }

  @After
  public void cleanUpStreams() {
    System.setOut(null);
  }

  @Test
  public void linkedListHasElement() {
    linkedList.element = 42;
    assertThat(linkedList.element, is(42));
  }

  @Test
  public void linkedListHasNext() {
    assertThat(linkedList.next, is(nullValue()));
  }

  @Test
  public void linkedListMainOutputsFormattedVehicles() {
    LinkedList.main(new String[0]);
    String[] outputLines = outputStream.toString()
      .split(System.getProperty("line.separator"));

    assertThat(outputLines.length, greaterThan(1));
    for (String line: outputLines) {
      assertThat(line, startsWith("Vehicle(id="));
    }
  }
}
