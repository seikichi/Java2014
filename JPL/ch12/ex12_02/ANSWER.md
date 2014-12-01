# `PassengerVehicle` オブジェクトの定員を負の値に設定しようとした
`IllegalArgumentException`

# オブジェクトがその初期値を設定するのに使用する設定ファイルに，文法エラーが見つかった
`IllegalStateException`

# プログラマが指定した単語を文字列の配列から検索するメソッドが，その単語を発見できない
`NotFoundException` のような例外を自作 or 例外を通知せず「0件発見」といった結果を返すようにする．

# `open` メソッドへ指定されたファイルが存在しない
`java.io.FileNotFoundException`

# `open` メソッドへ指定されたファイルは存在するが，セキュリティにより使用できない
`java.nio.file.AccessDeniedException`

# リモートのサーバプロセスにネットワークコネクションを確立しようとするが，リモートマシンと接続できない
`java.net.ConnectException` または `java.net.NoRouteToHostException` ？

# リモートのサーバプロセスとのやり取りの最中に，ネットワークコネクションが切れる
`java.net.SocketException`
