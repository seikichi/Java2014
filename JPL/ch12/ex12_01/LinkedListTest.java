package ch12.ex12_01;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;

public class LinkedListTest {
  class TestData {
    public int value;
    TestData(int value) { this.value = value ;}
  }

  private LinkedList<Integer> linkedList;
  private LinkedList<Integer> linkedListFromElements;
  private LinkedList<TestData> linkedListWithTestData;

  private TestData data1, data2, data3;

  @Before
  public void setup() {
    linkedList = new LinkedList<>(42);
    linkedListFromElements = LinkedList.fromElements(1, 2, 3);

    data1 = new TestData(1);
    data2 = new TestData(2);
    data3 = new TestData(3);
    linkedListWithTestData = LinkedList.fromElements(data1, data2, data3);
  }

  @Test
  public void cloneReturnsShallowCopiedList() {
    final LinkedList cloned = linkedListWithTestData.clone();

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
  public void linkedListToString() {
    assertThat(linkedList.toString(), startsWith("LinkedList("));
    assertThat(linkedList.toString(), containsString("42"));
  }

  @Test
  public void linkedListFromElements() {
    assertThat(linkedListFromElements.getElement(), is(1));
    assertThat(linkedListFromElements.getNext().getElement(), is(2));
    assertThat(linkedListFromElements.getNext().getNext().getElement(), is(3));
    assertThat(linkedListFromElements.getNext().getNext().getNext(), is(nullValue()));
  }

  @Test
  public void countElements() {
    assertThat(linkedListFromElements.countElements(), is(3));
  }

  @Test(expected = ObjectNotFoundException.class)
  public void findThrowsExceptionWhenNotFound() throws Exception {
    linkedList.find(0);
  }

  @Test
  public void findReturnsListThatHasGivenElement() throws Exception {
    LinkedList<Integer> found = linkedListFromElements.find(2);
    assertThat(found.countElements(), is(2));
  }
}
