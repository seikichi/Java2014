package ch03.ex03_09;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GarageTest {
  private static final String name1 = "hoge";
  private static final String name2 = "fuga";
  private static final String name3 = "poyo";

  private Vehicle v1, v2, v3;
  private Garage garage;

  @Before
  public void setupVehicles() {
    v1 = new Vehicle(name1);
    v2 = new Vehicle(name2);
    v3 = new Vehicle(name3);
  }

  @Before
  public void setupGarage() {
    garage = new Garage(3);
    garage.set(0, v1);
    garage.set(1, v2);
    garage.set(2, v3);
  }

  @Test
  public void cloneReturnsDeepClonedObject() {
    Garage cloned = garage.clone();
    assertThat(cloned.get(0).getOwnerName(), is(name1));
    assertThat(cloned.get(1).getOwnerName(), is(name2));
    assertThat(cloned.get(2).getOwnerName(), is(name3));

    assertThat(cloned.get(0).getID(), is(not(v1.getID())));
    assertThat(cloned.get(1).getID(), is(not(v2.getID())));
    assertThat(cloned.get(2).getID(), is(not(v3.getID())));
  }
}
