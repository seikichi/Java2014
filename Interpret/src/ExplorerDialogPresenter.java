import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.lang.Package;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
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
  private InterpretModel model;
  private TargetType targetType;
  private JDialog dialog;

  private JTabbedPane tabbedPane;
  private JPanel staticPanel;
  private JPanel localPanel;
  private JPanel logPanel;

  private JScrollPane staticScrollPane;
  private JTree staticTree;

  public enum TargetType {
    CONSTRUCTOR,
    METHOD,
    FIELD,
    ALL,
    INSTANT_METHOD,
    INSTANT_FIELD,
  }

  public ExplorerDialogPresenter(JFrame owner,
                                 InterpretModel model) {
    this(owner, model, TargetType.ALL);
  }

  public ExplorerDialogPresenter(JFrame owner,
                                 InterpretModel model,
                                 TargetType targetType) {
    this.model = model;
    this.targetType = targetType;

    dialog = new JDialog(owner, "Explorer", true);
    tabbedPane = new JTabbedPane();
    staticPanel = new JPanel();
    localPanel = new JPanel();
    logPanel = new JPanel();

    setupStaticPanel();
    setupLocalPanel();

    tabbedPane.addTab("static", staticPanel);
    tabbedPane.addTab("local", localPanel);
    // tabbedPane.addTab("log", logPanel);

    JButton select = new JButton("Select");
    JButton cancel = new JButton("Cancel");
    select.addActionListener(e -> { this.dialog.dispose(); });
    cancel.addActionListener(e -> { this.dialog.dispose(); });
    JPanel footer = new JPanel();
    footer.setLayout(new GridLayout(1, 2));
    footer.add(select);
    footer.add(cancel);
    dialog.getContentPane().add(footer, BorderLayout.PAGE_END);

    dialog.getContentPane().add(tabbedPane, BorderLayout.CENTER);
    dialog.setSize(256, 256);
    dialog.setVisible(true);
  }

  private void setupStaticPanel() {
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("static");
    root.setAllowsChildren(true);
    DefaultTreeModel treeModel = new DefaultTreeModel(root, true);

    staticTree = new JTree(treeModel);
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
    // JPanel footer = new JPanel();
    header.setLayout(new GridLayout(1, 2));
    header.add(field);
    header.add(button);
    button.addActionListener(e -> {
      this.model.addClass(field.getText());
      field.setText("");
    });
    // footer.setLayout(new GridLayout(1, 1));
    // footer.add(cancel);

    staticScrollPane = new JScrollPane();
    staticScrollPane.getViewport().setView(staticTree);

    staticPanel.setLayout(new BorderLayout());
    staticPanel.add(header, BorderLayout.PAGE_START);
    // staticPanel.add(footer, BorderLayout.PAGE_END);
    staticPanel.add(staticScrollPane, BorderLayout.CENTER);
  }

  private void setupLocalPanel() {
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

    JScrollPane localScrollPane = new JScrollPane();
    localScrollPane.getViewport().setView(localTree);
    localPanel.setLayout(new BorderLayout());
    localPanel.add(localScrollPane, BorderLayout.CENTER);
  }
}
