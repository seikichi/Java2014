package ch21.ex21_03;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.*;

public class WeakValueMap<K, V> implements Map<K, V> {
    
    private HashMap<K, ValueReference> hashMap = new HashMap<>();
    private ReferenceQueue<V> queue = new ReferenceQueue<>();

    private void expungeStaleEntries() {
        if (this.hashMap.size() == 0) {
            return;
        }
        for (;;) {
            ValueReference ref = (ValueReference) queue.poll();
            if (ref == null) {
                break;
            }
            hashMap.remove(ref.key);
        }
    }

    private class ValueReference extends WeakReference<V> {
        final K key;

        ValueReference(K key, V value, ReferenceQueue<V> queue) {
            super(value, queue);
            this.key = key;
        }
    }

    @Override
    public int size() {
        if (hashMap.size() == 0) { return 0; }
        expungeStaleEntries();
        return hashMap.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return hashMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (ValueReference ref : hashMap.values()) {
            if (ref.get() == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        ValueReference ref = hashMap.get(key);
        return ref == null ? null : ref.get();
    }

    @Override
    public V put(K key, V value) {
        expungeStaleEntries();
        ValueReference old = hashMap.put(key, new ValueReference(key, value, queue));
        return old == null ? null : old.get();
    }

    @Override
    public V remove(Object key) {
        expungeStaleEntries();
        WeakReference<V> old = hashMap.remove(key);
        return old == null ? null : old.get();
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        hashMap.clear();
    }

    @Override
    public Set<K> keySet() {
        return hashMap.keySet();
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> result = new ArrayList<>();
        for (ValueReference ref : hashMap.values()) {
            V value = ref.get();
            if (value != null) {
                result.add(value);
            }
        }
        return result;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        HashSet<Map.Entry<K, V>> result = new HashSet<>();
        for (Map.Entry<K, ValueReference> entry : hashMap.entrySet()) {
            V value = entry.getValue().get();
            if (value != null) {
                result.add(new AbstractMap.SimpleEntry<>(entry.getKey(), value));
            }
        }
        return result;
    }
}
