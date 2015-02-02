package ch17.ex17_05;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.rules.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.*;
import org.junit.experimental.theories.*;

public class ResourceManagerTest {
  @Test
  public void releaseによってリソースの解放が行われること() throws Exception {
    ResourceManager rm = new ResourceManager();
    Object key1 = new Object();
    Object key2 = new Object();
    Object key3 = new Object();

    Resource r1 = rm.getResource(key1);
    Resource r2 = rm.getResource(key2);
    Resource r3 = rm.getResource(key3);

    key1 = null;
    System.gc();
    try { Thread.sleep(1); } catch (InterruptedException e) {}
    rm.release();

    assertThat(r1.isReleased(), is(true));
    assertThat(r2.isReleased(), is(false));
    assertThat(r3.isReleased(), is(false));
  }
}
