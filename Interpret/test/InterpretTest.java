import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;

public class InterpretTest {
  private FrameFixture fixture;

  @BeforeClass
  public static void beforeClass() {
    FailOnThreadViolationRepaintManager.install();
  }

  @Before
  public void before() {
    Interpret window = GuiActionRunner.execute(new GuiQuery<Interpret>() {
      @Override protected Interpret executeInEDT() throws Throwable {
        return new Interpret();
      }
    });
    fixture = new FrameFixture(window);
    fixture.show();
  }

  @After
  public void after() {
    fixture.cleanUp();
  }

  @Test
  public void ラベルがHelloであること() {
    fixture.label("label").requireText("Hello");
  }
}
