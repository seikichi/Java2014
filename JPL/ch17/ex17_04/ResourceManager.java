package ch17.ex17_04;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

public final class ResourceManager {
  final ReferenceQueue<Object> queue;
  final Map<Reference<?>, Resource> refs;
  final Thread reaper;
  boolean shutdown = false;

  public ResourceManager() {
    queue = new ReferenceQueue<Object>();
    refs = new HashMap<Reference<?>, Resource>();
    reaper = new ReaperThread();
    reaper.start();
  }

  public synchronized void shutdown() {
    if (!shutdown) {
      shutdown = true;
      reaper.interrupt();
    }
  }

  public synchronized Resource getResource(Object key) {
    if (shutdown) {
      throw new IllegalStateException();
    }
    Resource res = new ResourceImpl(key);
    Reference<?> ref = new PhantomReference<Object>(key, queue);
    refs.put(ref, res);
    return res;
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

  class ReaperThread extends Thread {
    public void run() {
      while (true) {
        try {
          Reference<?> ref = queue.remove();
          Resource res = null;
          synchronized(ResourceManager.this) {
            res = refs.get(ref);
            refs.remove(ref);
          }
          res.release();
          ref.clear();
        } catch (InterruptedException ex) {
          for (Map.Entry<Reference<?>, Resource> e : refs.entrySet()) { e.getValue().release(); }
          break;
        }
      }
    }
  }
}
