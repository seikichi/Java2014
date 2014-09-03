// package ch01.ex01_14;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Walkman との接続をモデル化したインタフェース
 */
interface ConnectionInterface {
  void close();
  Sound listen();
}

/**
 * 音声を受信するだけでなく送信可能な接続をモデル化したインタフェース
 */
interface ChattableConnectionInterface extends ConnectionInterface {
  void speak(Sound sound);
}

/**
 * Walkman から Connection に送られる音声をモデル化したクラス
 */
class Sound {
  private final String content;
  @Override public String toString() { return content; }
  Sound(final String content) { this.content = content; }
}

/**
 * ConnectionInterface の実装
 */
class SimpleConnection implements ConnectionInterface  {
  public void close() { walkman.close(this); }
  public Sound listen() { return walkman.dequeueSound(this); }

  SimpleConnection(final Walkman walkman) { this.walkman = walkman; }
  private final Walkman walkman;
}

/**
 * ChattableConnectionInterface の実装
 */
class ChattableConnection implements ChattableConnectionInterface {
  public void close() { conn.close(); }
  public Sound listen() { return conn.listen(); }
  public void speak(Sound sound) { this.walkman.enqueueSound(sound);  }

  ChattableConnection(final Walkman walkman, final ConnectionInterface conn) {
    this.walkman = walkman;
    this.conn = conn;
  }
  private final Walkman walkman;
  private final ConnectionInterface conn;
}

/**
 * 1端子の Walkman をモデル化したクラス
 */
class Walkman {
  /**
   * 音楽の再生を開始する (同時に1コネクションしか存在できない)
   */
  public ConnectionInterface play() {
    if (currentConnection != null) { return null; }
    currentConnection = new SimpleConnection(this);
    return currentConnection;
  }

  /**
   * 再生する音楽を追加する
   */
  public void enqueueSound(Sound sound) {
    soundQueue.offer(sound);
  }

  void close(ConnectionInterface connection) {
    if (currentConnection == connection) {
      currentConnection = null;
      soundQueue.clear();
    }
  }

  Sound dequeueSound(ConnectionInterface connection) {
    return soundQueue.poll();
  }

  private ConnectionInterface currentConnection = null;
  private Queue<Sound> soundQueue = new LinkedList();

  public static void main(String[] args) {
    // Walkman walkman = new Walkman();
    // Connection conn = walkman.play();
    // walkman.enqueueSound(new Sound("Hello, world!"));
    // System.out.println(conn.listen());

    // Walkman walkman = new DualTerminalWalkman();
    // Connection conn1 = walkman.play();
    // Connection conn2 = walkman.play();
    // walkman.enqueueSound(new Sound("Hello, world!"));
    // System.out.println(conn1.listen());
    // System.out.println(conn2.listen());

    ChattableWalkman walkman = new ChattableWalkman();
    ConnectionInterface conn1 = walkman.play();
    ChattableConnectionInterface conn2 = walkman.play();
    walkman.enqueueSound(new Sound("Hello, world!"));
    System.out.println(conn1.listen());
    System.out.println(conn2.listen());

    conn2.speak(new Sound("Ya!"));
    System.out.println(conn1.listen());
  }
}

/**
 * 2端子の Walkman をモデル化したクラス
 */
class DualTerminalWalkman extends Walkman {
  /**
   * 音楽の再生を開始する (同時に2コネクション存在できる)
   */
  @Override public ConnectionInterface play() {
    ConnectionInterface conn = super.play();
    if (conn != null) { return conn; }
    if (additionalConnection != null) { return null; }
    additionalConnection = new SimpleConnection(this);
    return additionalConnection;
  }

  @Override public void enqueueSound(Sound sound) {
    super.enqueueSound(sound);
    if (additionalConnection != null) { additionalSoundQueue.offer(sound); }
  }

  @Override void close(ConnectionInterface connection) {
    if (additionalConnection == connection) { additionalConnection = null; }
    super.close(connection);
  }

  @Override Sound dequeueSound(ConnectionInterface connection) {
    if (additionalConnection == connection) { return additionalSoundQueue.poll(); }
    return super.dequeueSound(connection);
  }

  private ConnectionInterface additionalConnection = null;
  private Queue<Sound> additionalSoundQueue = new LinkedList();
}

/**
 * 2端子かつ双方向コミュニケーション可能な Walkman をモデル化したクラスa
 */
class ChattableWalkman extends DualTerminalWalkman {
  @Override public ChattableConnectionInterface play() {
    ConnectionInterface conn = super.play();
    if (conn == null) { return null; }
    return new ChattableConnection(this, conn);
  }
}
