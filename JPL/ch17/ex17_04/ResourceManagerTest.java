package ch17.ex17_04;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.rules.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.*;
import org.junit.experimental.theories.*;

public class ResourceManagerTest {
  @Test
  public void shutdownによってすべてのリソースが解放されること() throws Exception {
    ResourceManager rm = new ResourceManager();
    Object key1 = new Object();
    Object key2 = new Object();
    Object key3 = new Object();

    Resource r1 = rm.getResource(key1);
    Resource r2 = rm.getResource(key2);
    Resource r3 = rm.getResource(key3);

    key1 = null;
    System.gc();

    rm.shutdown();

    try { Thread.sleep(1); } catch (InterruptedException e) {}

    assertThat(r1.isReleased(), is(true));
    assertThat(r2.isReleased(), is(true));
    assertThat(r3.isReleased(), is(true));
  }
}
