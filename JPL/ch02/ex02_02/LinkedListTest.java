package ch02.ex02_02;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class LinkedListTest {
  private LinkedList linkedList;

  @Before
  public void prepareLinkedList() {
    linkedList = new LinkedList();
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
}
