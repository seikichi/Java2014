package ch19.ex19_01;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.*;

public class LinkedListTest {
  private LinkedList linkedList;
  private LinkedList otherLinkedList;
  private LinkedList linkedListFromElements;

  @Before
  public void prepareLinkedLists() {
    linkedList = new LinkedList(42);
    otherLinkedList = new LinkedList("Hello", linkedList);
    linkedListFromElements = LinkedList.fromElements(1, 2, 3);
  }

  @Test
  public void linkedListHasElement() {
    assertThat(linkedList.getElement(), is(42));
  }

  @Test
  public void linkedListHasNext() {
    assertThat(linkedList.getNext(), is(nullValue()));
  }

  @Test
  public void twoArgsConstructorSetCorrespondingFields() {
    assertThat(otherLinkedList.getElement(), is("Hello"));
    assertThat(otherLinkedList.getNext(), is(linkedList));
  }

  @Test
  public void linkedListToString() {
    assertThat(linkedList, hasToString(startsWith("LinkedList(")));
    assertThat(linkedList, hasToString(containsString("42")));
    assertThat(otherLinkedList, hasToString(containsString("Hello")));
  }

  @Test
  public void linkedListFromElements() {
    assertThat(linkedListFromElements.getElement(), is(1));
    assertThat(linkedListFromElements.getNext().getElement(), is(2));
    assertThat(linkedListFromElements.getNext().getNext().getElement(),
               is(3));
    assertThat(linkedListFromElements.getNext().getNext().getNext(),
               is(nullValue()));
  }

  @Test
  public void countElements() {
    assertThat(linkedListFromElements.countElements(), is(3));
  }
}
