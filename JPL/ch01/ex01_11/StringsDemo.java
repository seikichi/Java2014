package ch01.ex01_11;

class StringsDemo {
  public static void main(String[] args) {
    String myName = "近藤誠一";
    String department = "WS開本 C開発室 開発2G";
    myName += " ";
    myName += "(" + department + ")";
    System.out.println("Name = " + myName);
  }
}
