package ch01.ex01_16;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

public class MyUtilitiesTest {
  private final String invalidSetName = "HELLO!!!";

  @Test(expected=BadDataSetException.class)
  public void getDataSetWithInvalidSetNameThrowsException()
    throws BadDataSetException {
    new MyUtilities().getDataSet(invalidSetName);
  }

  @Test
  public void badDataSetExceptionsHaveDetails() {
    BadDataSetException exception = null;
    try {
      new MyUtilities().getDataSet(invalidSetName);
    } catch (BadDataSetException e) {
      exception = e;
    } finally {
      assertThat(exception, is(instanceOf(BadDataSetException.class)));
      assertThat(exception.getFileName(),
                 is(invalidSetName + ".dset"));
      assertThat(exception.getIOException(),
                 is(instanceOf(IOException.class)));
      assertThat(exception.getIOException().getMessage(),
                 not(isEmptyString()));
    }
  }
}
