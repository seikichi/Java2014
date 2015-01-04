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

実験は以下のコマンドで行った．

```
> java -classpath JPL/build/classes/main ch14.ex14_07.Babble true 2 Did DidNot
```

# 結果
常に同じ結果にはならない．以下に結果を示す．

```
> for i in $(seq 1 100); do echo $(java -classpath JPL/build/classes/main ch14.ex14_07.Babble true 2 Did DidNot | tr '\n' ' '); done | sort | uniq
     53 Did Did DidNot DidNot
     10 Did DidNot Did DidNot
     20 Did DidNot DidNot Did
      6 DidNot Did Did DidNot
      2 DidNot Did DidNot Did
      9 DidNot DidNot Did Did
```
