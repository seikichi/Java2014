package ch01.ex01_11;

class StringsDemo {
  public static void main(String[] args) {
    String myName = "近藤誠一";
    String occupation = "ソフトウェアエンジニア見習い";
    myName += " ";
    myName += "(" + occupation + ")";
    System.out.println("Name = " + myName);
  }
}
