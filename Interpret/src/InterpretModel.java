import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

public final class InterpretModel extends Observable {
  List<Class<?>> klassList = new ArrayList<>();
  Map<String, Object> localMap = new HashMap<>();

  InterpretModel() {
    this.klassList.add(Integer.class);
    this.klassList.add(ArrayList.class);
    this.klassList.add(System.class);

    this.localMap.put("x", new Integer(10));
    this.localMap.put("y", new ArrayList<Integer>());
    this.localMap.put("z", new int[5]);
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
    } catch (ClassNotFoundException ignore) { }
    if (klass == null || klassList.contains(klass)) { return; }
    klassList.add(klass);
    Collections.sort(klassList, (lhs, rhs) -> lhs.toString().compareTo(rhs.toString()));
    notifyObservers();
  }
}
