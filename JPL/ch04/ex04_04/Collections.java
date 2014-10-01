package ch04.ex04_04;

/*
  - 問題の意図がよくわかりませんでした
  - java.util.Stack<E> が java.util.Vector<E> を継承していることに代表されるように
  - 既存のコレクション階層が所々おかしいので、自分で再定義してみようという話でしょうか
 */

interface Iterable<T> {}
interface Collection<T> extends Iterable<T> {}

interface List<T> extends Collection<T> {}
interface Stack<T> extends Collection<T> {}
interface Queue<T> extends Collection<T> {}
interface Deque<T> extends Collection<T> {}

interface Set<T> extends Collection<T> {}
interface SortedSet<T> extends Set<T> {}
interface HashSet<T> extends Set<T> {}

interface Map<K, V> {}
interface SortedMap<K, V> extends Map<K, V> {}
interface HashMap<K, V> extends Map<K, V> {}
