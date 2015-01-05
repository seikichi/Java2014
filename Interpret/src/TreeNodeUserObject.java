import java.util.List;

public interface TreeNodeUserObject {
  boolean isLeaf();
  String toString();
  List<TreeNodeUserObject> getChildren();
}
