package ch02.ex02_12;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LinkedListTest {
  private LinkedList linkedList;
  private LinkedList otherLinkedList;
  private LinkedList linkedListFromElements;
  private ByteArrayOutputStream outputStream;

  @Before
  public void prepareLinkedLists() {
    linkedList = new LinkedList(42);
    otherLinkedList = new LinkedList("Hello", linkedList);
    linkedListFromElements = LinkedList.fromElements(1, 2, 3);
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
    assertThat(linkedList.element, is(42));
  }

  @Test
  public void linkedListHasNext() {
    assertThat(linkedList.next, is(nullValue()));
  }

  @Test
  public void twoArgsConstructorSetCorrespondingFields() {
    assertThat(otherLinkedList.element, is("Hello"));
    assertThat(otherLinkedList.next, is(linkedList));
  }

  @Test
  public void linkedListToString() {
    assertThat(linkedList, hasToString(startsWith("LinkedList(")));
    assertThat(linkedList, hasToString(containsString("42")));
    assertThat(otherLinkedList, hasToString(containsString("Hello")));
  }

  @Test
  public void linkedListFromElements() {
    assertThat(linkedListFromElements.element, is(1));
    assertThat(linkedListFromElements.next.element, is(2));
    assertThat(linkedListFromElements.next.next.element, is(3));
    assertThat(linkedListFromElements.next.next.next, is(nullValue()));
  }

  @Test
  public void linkedListMainOutputsFormattedVehicles() {
    LinkedList.main(new String[0]);
    String[] outputLines = outputStream.toString()
      .split(System.getProperty("line.separator"));

    assertThat(outputLines.length, greaterThan(0));
    for (String line: outputLines) {
      assertThat(line, startsWith("LinkedList(element="));
    }
  }
}
