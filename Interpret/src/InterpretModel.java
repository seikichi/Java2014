import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import javax.swing.JOptionPane;

public final class InterpretModel extends Observable {
  List<Class<?>> klassList = new ArrayList<>();
  Map<String, Object> localMap = new HashMap<>();

  InterpretModel() {
    this.klassList.add(Interpret.class);
    this.klassList.add(TestData.class);
    this.klassList.add(Integer.class);
    this.klassList.add(System.class);
    this.klassList.add(ArrayList.class);

    this.localMap.put("new Integer(10)", new Integer(10));
    this.localMap.put("new int[5]", new int[5]);
    this.localMap.put("new TestData()", new TestData());
    this.localMap.put("new TestData[4]", new TestData[4]);
  }

  public String getFreshVariableName() {
    return String.format("$%d", this.localMap.size());
  }

  public void notifyObservers() {
    setChanged();
    notifyObservers(this);
  }

  public Map<String, Object> localVariableMap() {
    return localMap;
  }

  public void addLocalVariable(String name, Object value) {
    this.localMap.put(name, value);
    notifyObservers(this);
  }

  public List<Class<?>> classList() {
    return this.klassList;
  }

  public void addClass(String name) {
    Class<?> klass = null;
    try {
      klass = Class.forName(name);
    } catch (ClassNotFoundException e) {
      JOptionPane.showMessageDialog(null,
                                    e.toString(),
                                    "Exception",
                                    JOptionPane.ERROR_MESSAGE);
    }
    if (klass == null || klassList.contains(klass)) { return; }
    klassList.add(klass);
    Collections.sort(klassList, (lhs, rhs) -> lhs.toString().compareTo(rhs.toString()));
    notifyObservers();
  }
}
