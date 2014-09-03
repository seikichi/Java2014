package ch01.ex01_11;

class StringsDemo {
  public static void main(String[] args) {
    String myName = "Seiichi KONDO";
    String company = "Ricoh";
    myName += " ";
    myName += "(" + company + ")";
    System.out.println("Name = " + myName);
  }
}
