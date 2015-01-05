import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Collections;
import java.util.Observable;

public final class InterpretModel extends Observable {
  List<Class<?>> klassList = new ArrayList<>();

  InterpretModel() {
    this.klassList.add(Integer.class);
    this.klassList.add(ArrayList.class);
  }

  public void notifyObservers() {
    setChanged();
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
