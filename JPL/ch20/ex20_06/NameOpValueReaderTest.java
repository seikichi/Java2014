package ch20.ex20_06;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.rules.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.*;
import org.junit.experimental.theories.*;

@RunWith(Theories.class)
public class NameOpValueReaderTest {
  static final class Data {
    public String input;
    public double x, y, z;
    Data(String[] input, double x, double y, double z) {
      this.input = "";
      for (String s : input) {
        this.input = this.input + String.format("%s%n", s);
      }
      this.x = x;
      this.y = y;
      this.z = z;
    }
  }

  @DataPoints
  public static Data[] data = {
    new Data(new String[]{}, 0, 0, 0),
    new Data(new String[]{"x = 1", "y = 2", "z = 3"}, 1, 2, 3),
    new Data(new String[]{"x = 10", "x + 10", "x - 5"}, 15, 0, 0),
    new Data(new String[]{"# I am comment!"}, 0, 0, 0),
    new Data(new String[]{"z = 10", "z + 5", "z = 0", "z - 5"}, 0, 0, -5),
    new Data(new String[]{"x + 1", "y + 1", "z + 1"}, 1, 1, 1),
  };

  @Theory
  public void readはname_op_value形式の入力を受け取り計算を行うこと(Data data) throws Exception {
    NameOpValueReader sut = new NameOpValueReader();
    Map<String, Double> values = sut.read(new StringReader(data.input), "x", "y", "z");

    assertThat(Math.abs(values.get("x") - data.x) < 1e-10, is(true));
    assertThat(Math.abs(values.get("y") - data.y) < 1e-10, is(true));
    assertThat(Math.abs(values.get("z") - data.z) < 1e-10, is(true));
  }
}
