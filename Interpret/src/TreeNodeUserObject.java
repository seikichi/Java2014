import java.util.List;

public interface TreeNodeUserObject {
  boolean isLeaf();
  List<TreeNodeUserObject> getChildren();
  boolean relatedTo(ExplorerDialogPresenter.TargetType target);
  boolean matchTo(ExplorerDialogPresenter.TargetType target);
  ExplorerResult getWrapperObject();
}
