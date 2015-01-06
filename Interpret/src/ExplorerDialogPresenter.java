import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.Dialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.Package;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeModel;

public class ExplorerDialogPresenter {
  private JDialog dialog;
  private InterpretModel model;
  private ExplorerListener listener;
  private TargetType targetType;

  private static class SelectedItem extends Observable {
    private TreeNodeUserObject lastSelectedItem = null;
    public void notifyObservers() {
      setChanged();
      notifyObservers(this);
    }
    public void set(TreeNodeUserObject userObject) {
      lastSelectedItem = userObject;
      notifyObservers();
    }
    private TreeNodeUserObject get() {
      return lastSelectedItem;
    }
  }
  private SelectedItem selectedItem = new SelectedItem();

  public enum TargetType {
    CONSTRUCTOR,
    METHOD,
    FIELD,
    FIELD_READONLY,
    ALL,
  }

  public static interface ExplorerListener {
    public void onSelected(ExplorerResult result);
  }

  public ExplorerDialogPresenter(Window owner,
                                 InterpretModel model,
                                 ExplorerListener listener) {
    this(owner, model, TargetType.ALL, listener);
  }

  public ExplorerDialogPresenter(Window owner,
                                 InterpretModel model,
                                 TargetType targetType,
                                 ExplorerListener listener) {
    this.model = model;
    this.listener = listener;
    this.targetType = targetType;

    dialog = new JDialog(owner, "Explorer", Dialog.ModalityType.APPLICATION_MODAL);
    JTabbedPane tabbedPane = new JTabbedPane();
    JPanel staticPanel = setupStaticPanel();
    JPanel localPanel = setupLocalPanel();

    tabbedPane.addTab("static", staticPanel);
    tabbedPane.addTab("local", localPanel);

    JButton select = new JButton("Select");
    select.setEnabled(false);
    this.selectedItem.addObserver((ob, arg) -> {
      if (selectedItem.get() != null) {
        select.setEnabled(selectedItem.get().matchTo(targetType));
      }
    });
    JButton cancel = new JButton("Cancel");
    select.addActionListener(e -> {
      this.dialog.dispose();
      if (selectedItem.get().getWrapperObject() != null) {
        listener.onSelected(selectedItem.get().getWrapperObject());
      }
    });
    cancel.addActionListener(e -> { this.dialog.dispose(); });
    JPanel footer = new JPanel();
    footer.setLayout(new GridLayout(1, 2));
    footer.add(select);
    footer.add(cancel);
    dialog.getContentPane().add(footer, BorderLayout.PAGE_END);

    dialog.getContentPane().add(tabbedPane, BorderLayout.CENTER);
    dialog.setSize(512, 512);
    dialog.setVisible(true);
  }

  private JPanel setupStaticPanel() {
    JPanel staticPanel = new JPanel();
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("static");
    root.setAllowsChildren(true);
    DefaultTreeModel treeModel = new DefaultTreeModel(root, true);

    JTree staticTree = new JTree(treeModel);
    staticTree.setRootVisible(false);
    staticTree.setShowsRootHandles(false);

    for (Class<?> klass : this.model.classList()) {
      TreeNodeUserObject userObject = TreeNodeUserObjects.fromClass(klass);
      DefaultMutableTreeNode node = new DefaultMutableTreeNode(userObject, !userObject.isLeaf());
      root.add(node);
    }
    treeModel.reload(root);

    ExplorerDialogPresenter.TargetType targetType = this.targetType;
    staticTree.addTreeWillExpandListener(new TreeWillExpandListener() {
        @Override public void treeWillCollapse(TreeExpansionEvent event) {
          DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
          node.removeAllChildren();
          treeModel.reload(node);
        }
        @Override public void treeWillExpand(TreeExpansionEvent event) {
          DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
          TreeNodeUserObject userObject = (TreeNodeUserObject) node.getUserObject();
          for (TreeNodeUserObject child : userObject.getChildren()) {
            if (!child.relatedTo(targetType)) { continue; }
            node.add(new DefaultMutableTreeNode(child, !child.isLeaf()));
          }
          treeModel.reload(node);
        }
    });

    staticTree.addTreeSelectionListener(e -> {
      DefaultMutableTreeNode node = (DefaultMutableTreeNode)staticTree.getLastSelectedPathComponent();
      if (node == null) { return; }
      TreeNodeUserObject userObject = (TreeNodeUserObject) node.getUserObject();
      selectedItem.set(userObject);
    });

    this.model.addObserver((ob, arg) -> {
        @SuppressWarnings("unchecked")
        List<DefaultMutableTreeNode> children = Collections.list(root.children());
        root.removeAllChildren();
        for (Class<?> klass : this.model.classList()) {
          TreeNodeUserObject child = TreeNodeUserObjects.fromClass(klass);
          if (!child.relatedTo(targetType)) { continue; }
          DefaultMutableTreeNode node = new DefaultMutableTreeNode(child, !child.isLeaf());
          root.add(node);
        }
        treeModel.reload(root);
    });

    JTextField field = new JTextField();
    JButton button = new JButton("Add Class");
    JPanel header = new JPanel();

    header.setLayout(new GridLayout(1, 2));
    header.add(field);
    header.add(button);
    button.addActionListener(e -> {
      this.model.addClass(field.getText());
      field.setText("");
    });

    JScrollPane staticScrollPane = new JScrollPane();
    staticScrollPane.getViewport().setView(staticTree);

    staticPanel.setLayout(new BorderLayout());
    staticPanel.add(header, BorderLayout.PAGE_START);
    staticPanel.add(staticScrollPane, BorderLayout.CENTER);

    return staticPanel;
  }

  private JPanel setupLocalPanel() {
    JPanel localPanel = new JPanel();
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("local");
    root.setAllowsChildren(true);
    DefaultTreeModel treeModel = new DefaultTreeModel(root, true);

    JTree localTree = new JTree(treeModel);
    localTree.setRootVisible(false);
    localTree.setShowsRootHandles(false);

    for (Map.Entry<String, Object> entry : this.model.localVariableMap().entrySet()) {
      TreeNodeUserObject userObject = TreeNodeUserObjects.fromInstance(entry.getKey(), entry.getValue());
      DefaultMutableTreeNode node = new DefaultMutableTreeNode(userObject, !userObject.isLeaf());
      root.add(node);
    }
    treeModel.reload(root);

    ExplorerDialogPresenter.TargetType targetType = this.targetType;
    localTree.addTreeWillExpandListener(new TreeWillExpandListener() {
        @Override public void treeWillCollapse(TreeExpansionEvent event) {
          DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
          node.removeAllChildren();
          treeModel.reload(node);
        }
        @Override public void treeWillExpand(TreeExpansionEvent event) {
          DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
          TreeNodeUserObject userObject = (TreeNodeUserObject) node.getUserObject();
          for (TreeNodeUserObject child : userObject.getChildren()) {
            if (!child.relatedTo(targetType)) { continue; }
            node.add(new DefaultMutableTreeNode(child, !child.isLeaf()));
          }
          treeModel.reload(node);
        }
    });

    localTree.addTreeSelectionListener(e -> {
      DefaultMutableTreeNode node = (DefaultMutableTreeNode)localTree.getLastSelectedPathComponent();
      TreeNodeUserObject userObject = (TreeNodeUserObject) node.getUserObject();
      selectedItem.set(userObject);
    });

    JScrollPane localScrollPane = new JScrollPane();
    localScrollPane.getViewport().setView(localTree);
    localPanel.setLayout(new BorderLayout());
    localPanel.add(localScrollPane, BorderLayout.CENTER);

    return localPanel;
  }
}
