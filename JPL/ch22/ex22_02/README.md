# 練習問題 22.2

`WhichChars` クラスはUnicode範囲の上位の文字を記録するのに問題を抱えています．
それは，上位の文字は，下位の範囲に関して多くの使用されないビットを残してしまうことです．
出現した文字ごとに `Character` オブジェクトを `HashSet` に保存することで，この問題を解決しなさい．