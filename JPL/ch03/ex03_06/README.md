# 練習問題 3.6
`Vehicle` を変更して，コンストラクタで `Vehicle` と関連付けされる
`EnergySource` オブジェクトの参照を持つようにしなさい．
`EnergySource` クラスは `abstract` クラスでなければなりません．
なぜならば，`GasTank` オブジェクトの満タンの測定の方法は，
`Battery` オブジェクトとは異なるでしょう．
`EnergySource` に `abstract` の `empty` メソッドを入れて，
`GaskTank` と `Battery` クラスでそのメソッドを実装しなさい．
動力源が空でないことを保証する `start` メソッドを `Vehicle` に追加しなさい．
