# 環境

```
> uname -a
Darwin leticia 13.4.0 Darwin Kernel Version 13.4.0: Sun Aug 17 19:50:11 PDT 2014; root:xnu-2422.115.4~1/RELEASE_X86_64 x86_64 i386 MacBookPro11,1 Darwin
> javac -version
javac 1.8.0
> java -version
java version "1.8.0"
Java(TM) SE Runtime Environment (build 1.8.0-b132)
Java HotSpot(TM) 64-Bit Server VM (build 25.0-b70, mixed mode)
```

実験は以下のスクリプトで行った．

```
#!/bin/bash

CLASS=$1
TRIAL=$2
DEADLOCK=0

for i in $(seq 1 $TRIAL); do
    timeout 3 \
            java -classpath JPL/build/classes/main \
            ch14.ex14_08.$1 > /dev/null \
        || ((++DEADLOCK))
done

printf "deadlock: %d / %d\n" $DEADLOCK $TRIAL
```

# 結果

## 変更無し

```
> ./JPL/ch14/ex14_08/test.sh Friendly 200
deadlock: 13 / 200
```

## `yield` 追加
`FriendlyWithYield.hug` 内で `partner.hugBack();` の直前の行に
`Thread.yield();` を挿入した．

```
> ./JPL/ch14/ex14_08/test.sh FriendlyWithYield 200
deadlock: 42 / 200
```

`hugBack()` の直前でスレッドの切り替えが行われると，
デッドロックになる可能性が増加する．

## リソース順序付けによる deadlock 排除
`Friendly.name` でロックの順序付けを行った．

```
> ./JPL/ch14/ex14_08/test.sh FriendlyWithOrderedLock 200
deadlock: 0 / 200
```
