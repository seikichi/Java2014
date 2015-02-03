package ch16.ex16_11;

import java.util.ArrayList;

public class Game {
  public static void main(String[] args) {
    String name;
    while ((name = getNextPlayer()) != null) {
      try {
        PlayerLoader loader = new PlayerLoader();
        Class<? extends Player> classOf = loader.loadClass(name).asSubclass(Player.class);
        Player player = classOf.newInstance();
        Game game = new Game();
        player.play(game);
        game.reportScore(name);
      } catch (Exception e) {
        reportException(name, e);
      }
    }
  }

  public static void reportException(String name, Exception e) {
    e.printStackTrace();
  }

  private static ArrayList<String> playerNameList = new ArrayList<String>() {{
    add("RandomPlayer");
  }};

  public static String getNextPlayer() {
    if (playerNameList.size() == 0) { return null; }
    String name = playerNameList.get(0);
    playerNameList.remove(0);
    return name;
  }

  public void reportScore(String name) {
    System.out.println("reportScore: " + name);
  }
}
