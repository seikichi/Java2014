// import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
// import org.fest.swing.edt.GuiActionRunner;
// import org.fest.swing.edt.GuiQuery;
// import org.fest.swing.fixture.FrameFixture;
// import org.junit.After;
// import org.junit.Test;
// import org.junit.Before;
// import org.junit.BeforeClass;

// import javax.swing.JFrame;

// public class InterpretTest {
//   private FrameFixture fixture;

//   @BeforeClass
//   public static void beforeClass() {
//     FailOnThreadViolationRepaintManager.install();
//   }

//   @Before
//   public void before() {
//     JFrame frame = GuiActionRunner.execute(new GuiQuery<JFrame>() {
//       @Override protected JFrame executeInEDT() throws Throwable {
//         return new InterpretPresenter().frame;
//       }
//     });
//     fixture = new FrameFixture(frame);
//     fixture.show();
//   }

//   @After
//   public void after() {
//     fixture.cleanUp();
//   }

//   @Test
//   public void ラベルがHelloであること() {
//     fixture.label("label").requireText("Hello");
//   }
// }
