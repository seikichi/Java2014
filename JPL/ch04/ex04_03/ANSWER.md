- `LinkedList` クラスはインタフェースであるべきではない。
- 単方向連結リスト (`LinkedList`) が複数の実装を持つことや、実装を切り替える必要があるとは考えにくい。
- それよりも、より一般的な `List` をインタフェースにするのが良いと思う。
