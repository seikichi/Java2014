`Attr` をジェネリックにするのは良い考え．
不要な実行時キャストが減り，プログラムの信頼性が向上する．

ただし `Attributed` インタフェースは `Attributed<E>` に変更する必要がある．
また1つの `Attributed` に `Attr<Integer>` と `Attr<String>` のような
複数の `Attr` を入れることを考えると，
インタフェース内のメソッドにワイルドカードを入れる必要が出てきて，
若干複雑になるというデメリットがある (Attributed.java 参照)．
