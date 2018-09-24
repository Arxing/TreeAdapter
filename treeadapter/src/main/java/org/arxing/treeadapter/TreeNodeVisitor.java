package org.arxing.treeadapter;

public interface TreeNodeVisitor {

    void onVisit(TreeNode parent, TreeNode node, int level, int index);
}
