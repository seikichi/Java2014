package ch03.ex03_10;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;

public class LinkedListTest {
  class TestData {
    public int value;
    TestData(int value) { this.value = value ;}
  }

  private LinkedList linkedList;
  private LinkedList otherLinkedList;
  private LinkedList linkedListFromElements;
  private LinkedList linkedListWidthTestData;

  private TestData data1, data2, data3;

  @Before
  public void prepareLinkedLists() {
    linkedList = new LinkedList(42);
    otherLinkedList = new LinkedList("Hello", linkedList);
    linkedListFromElements = LinkedList.fromElements(1, 2, 3);

    data1 = new TestData(1);
    data2 = new TestData(2);
    data3 = new TestData(3);
    linkedListWidthTestData = LinkedList.fromElements(data1, data2, data3);
  }

  @Test
  public void cloneReturnsShallowCopiedList() {
    final LinkedList cloned = linkedListWidthTestData.clone();

    assertThat(cloned.getElement(), is(data1));
    assertThat(cloned.getNext().getElement(), is(data2));
    assertThat(cloned.getNext().getNext().getElement(), is(data3));
    assertThat(cloned.getNext().getNext().getNext(), is(nullValue()));
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
