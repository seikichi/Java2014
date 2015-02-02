package ch17.ex17_05;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

public final class ResourceManager {
  final ReferenceQueue<Object> queue;
  final Map<Reference<?>, Resource> refs;

  public ResourceManager() {
    queue = new ReferenceQueue<Object>();
    refs = new HashMap<Reference<?>, Resource>();
  }

  public synchronized Resource getResource(Object key) {
    Resource res = new ResourceImpl(key);
    Reference<?> ref = new PhantomReference<Object>(key, queue);
    refs.put(ref, res);
    return res;
  }

  public synchronized void release() {
    for (;;) {
      Reference<?> ref = queue.poll();
      if (ref == null) { break; }

      Resource res = null;
      synchronized(ResourceManager.this) {
        res = refs.get(ref);
        refs.remove(ref);
      }
      res.release();
      ref.clear();
    }
  }

  private static class ResourceImpl implements Resource {
    int keyHash;
    boolean needsRelease = false;

    ResourceImpl(Object key) {
      keyHash = System.identityHashCode(key);
      // 外部リソースの設定
      needsRelease = true;
    }

    public void use(Object key, Object... args) {
      if (System.identityHashCode(key) != keyHash) {
        throw new IllegalArgumentException("wrong key");
      }

      // リソースの使用
      // System.out.println("use Resource: key = " + key + ", keyHash = " + keyHash);
    }

    public synchronized void release() {
      if (needsRelease) {
        needsRelease = false;
        // リソースの解放
        // System.out.println("release Resource: keyHash = " + keyHash);
      }
    }

    public boolean isReleased() {
      return !needsRelease;
    }
  }
}
