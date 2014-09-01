// `package package;`
//   => エラー: <identifier>がありません
// `package "hello";`
//   => エラー: <identifier>がありません
package ch01.ex01_02;

// `class public HelloWorld {`
//   => エラー: <identifier>がありません
// `HelloWorld {`
//   => エラー: class、interfaceまたはenumがありません
// `public private class HelloWorld {`
//   => エラー: 修飾子privateをここで使用することはできません
// `public public class HelloWorld {`
//   => エラー: 修飾子が繰り返されています
public class HelloWorld {
  // `public static void main(String[]) {`
  //   => エラー: <identifier>がありません
  // `main(String[] args) {`
  //   => エラー: 無効なメソッド宣言です。戻り値の型が必要です。
  // `AAAAA main(String[] args) {`
  //   => エラー: シンボルを見つけられません
  // `void public main(String[] args) {`
  //   => エラー: <identifier>がありません
  // `void public(String[] args) {`
  //   => エラー: <identifier>がありません
  public static void main(String[] args) {
    // `System.out.println "Hello, world";`
    //   => エラー: 文ではありません
    // `System.out. "Hello, world";`
    //   => エラー: <identifier>がありません
    // `System.out.println("Hello, world")`
    //   => エラー: ';'がありません
    // `System.out.println(int);`
    //   => エラー: '.class'がありません
    // `System.out.println(class);`
    //   => エラー: 式の開始が不正です
    // `System.out.println(new Hoge());`
    //   => エラー: シンボルを見つけられません
    // `System.out.println(System.out.println);`
    //   => エラー: シンボルを見つけられません
    // `System.out.println(this);`
    //   => エラー: staticでない変数 thisをstaticコンテキストから参照することはできません
    System.out.println("Hello, world");
  }
}
