package ch06.ex06_05;

import java.awt.Color;

public enum TrafficLightColor {
  RED { Color getColor() { return Color.RED; }},
  YELLOW { Color getColor() { return Color.YELLOW; }},
  BLUE { Color getColor() { return Color.BLUE; }};

  abstract Color getColor();
}
