package ch01.ex01_15;

import java.util.ArrayList;

interface Lookup {
  Object find(String name);
  Object add(String name, Object object);
  Object remove(String name);
}

class SimpleLookupPair {
  public final String name;
  public final Object value;
  SimpleLookupPair(String name, Object value) {
    this.name = name;
    this.value = value;
  }
}

class SimpleLookup implements Lookup {
  private ArrayList<SimpleLookupPair> pairs;

  SimpleLookup() {
    this.pairs = new ArrayList<SimpleLookupPair>();
  }

  public Object find(String name) {
    for (int i = 0; i < pairs.size(); ++i) {
      if (pairs.get(i).name.equals(name)) {
        return pairs.get(i).value;
      }
    }
    return null;
  }

  public Object remove(String name) {
    for (int i = 0; i < pairs.size(); ++i) {
      if (pairs.get(i).name.equals(name)) {
        SimpleLookupPair oldPair = pairs.get(i);
        pairs.remove(i);
        return oldPair.value;
      }
    }
    return null;
  }

  public Object add(String name, Object object) {
    for (int i = 0; i < pairs.size(); ++i) {
      if (pairs.get(i).name.equals(name)) {
        SimpleLookupPair oldPair = pairs.get(i);
        pairs.set(i, new SimpleLookupPair(oldPair.name, object));
        return oldPair.value;
      }
    }
    pairs.add(new SimpleLookupPair(name, object));
    return null;
  }
}
