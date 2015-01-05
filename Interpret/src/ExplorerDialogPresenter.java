import java.util.Collections;

import java.awt.GridLayout;

import java.awt.FlowLayout;
import java.util.List;
import java.util.Enumeration;

import java.awt.BorderLayout;
import java.lang.Package;
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

  private JDialog dialog;

  private JTabbedPane tabbedPane;
  private JPanel staticPanel;
  private JPanel localPanel;
  private JPanel logPanel;

  private JScrollPane staticScrollPane;
  private JTree staticTree;

  public ExplorerDialogPresenter(JFrame owner, InterpretModel model) {
    this.model = model;
    dialog = new JDialog(owner, "Explorer", true);
    tabbedPane = new JTabbedPane();
    staticPanel = new JPanel();
    localPanel = new JPanel();
    logPanel = new JPanel();

    setupStaticPanel();

    tabbedPane.addTab("static", staticPanel);
    tabbedPane.addTab("local", localPanel);
    tabbedPane.addTab("log", logPanel);

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
          TreeNodeUserObject userObject = TreeNodeUserObjects.fromClass(klass);
          DefaultMutableTreeNode node = new DefaultMutableTreeNode(userObject, !userObject.isLeaf());
          root.add(node);
        }
        treeModel.reload(root);
    });

    JTextField field = new JTextField();
    JButton button = new JButton("Add Class");
    JButton cancel = new JButton("Cancel");
    JPanel header = new JPanel();
    JPanel footer = new JPanel();
    header.setLayout(new GridLayout(1, 2));
    header.add(field);
    header.add(button);
    button.addActionListener(e -> {
      this.model.addClass(field.getText());
      field.setText("");
    });
    footer.setLayout(new GridLayout(1, 1));
    footer.add(cancel);

    staticScrollPane = new JScrollPane();
    staticScrollPane.getViewport().setView(staticTree);

    staticPanel.setLayout(new BorderLayout());
    staticPanel.add(header, BorderLayout.PAGE_START);
    staticPanel.add(footer, BorderLayout.PAGE_END);
    staticPanel.add(staticScrollPane, BorderLayout.CENTER);
  }
}
