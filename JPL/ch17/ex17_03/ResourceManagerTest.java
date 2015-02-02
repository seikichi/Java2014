package ch17.ex17_03;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.rules.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.*;
import org.junit.experimental.theories.*;

public class ResourceManagerTest {
  @Test
  public void キーへの参照が無くなるとリソースが解放されること() throws Exception {
    ResourceManager rm = new ResourceManager();
    Object k = new Object();
    Resource r = rm.getResource(k);

    k = null;
    System.gc();
    try { Thread.sleep(1); } catch (InterruptedException e) {}

    assertThat(r.isReleased(), is(true));

    rm.shutdown();
  }
}
