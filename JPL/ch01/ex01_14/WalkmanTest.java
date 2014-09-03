package ch01.ex01_14;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;
import java.util.Arrays;

public class WalkmanTest {
  private Walkman walkman;
  private DualTerminalWalkman dual;
  private ChattableWalkman chattable;

  @Before
  public void createWalkmans() {
    walkman = new Walkman();
    dual = new DualTerminalWalkman();
    chattable = new ChattableWalkman();
  }

  @Test
  public void walkmanCanPlaySound() {
    for (Walkman w: Arrays.asList(walkman, dual, chattable)) {
      ConnectionInterface conn = w.play();
      w.enqueueSound(new Sound("music~~~"));
      assertThat(conn.listen(), hasToString("music~~~"));
      conn.close();
    }
  }

  @Test
  public void dualTerminalWalkmanCanPlayMusicWithTwoPeople() {
    for (Walkman w: Arrays.asList(dual, chattable)) {
      ConnectionInterface conn1 = w.play();
      ConnectionInterface conn2 = w.play();
      w.enqueueSound(new Sound("music~~~"));
      assertThat(conn1.listen(), hasToString("music~~~"));
      assertThat(conn2.listen(), hasToString("music~~~"));
      conn1.close();
      conn2.close();
    }
  }

  @Test
  public void chattableWalkmanCanSendSoundToOthers() {
    ChattableConnectionInterface conn1 = chattable.play();
    ChattableConnectionInterface conn2 = chattable.play();
    conn1.speak(new Sound("Ya!"));
    assertThat(conn2.listen(), hasToString("Ya!"));
  }
}

