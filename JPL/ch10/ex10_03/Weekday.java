package ch10.ex10_03;

public final class Weekday {
  public static boolean isWeekday(DayOfWeek day) {
    if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
      return false;
    }
    return true;
  }
}
