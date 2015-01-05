import java.util.List;

public interface TreeNodeUserObject {
  boolean isLeaf();
  String toString();
  List<TreeNodeUserObject> getChildren();
  boolean relatedTo(ExplorerDialogPresenter.TargetType target);
}
