package org.arxing.treeadapter;

import java.security.Principal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class TreeNode<TChild extends TreeNode> {
    /**
     * 這個節點的識別碼
     */
    protected TreeNodeId treeNodeId;

    /**
     * 這個節點的父節點
     */
    private TreeNode parent;

    public TreeNode() {
        treeNodeId = new TreeNodeId();
        parent = null;
    }

    /**
     * 取得下層的節點
     */
    public abstract List<TChild> getNodes();

    public abstract String getTag();

    /**
     * 不遞迴搜尋取得下層的節點數量
     */
    public int getNodeCount() {
        return getNodes() != null ? getNodes().size() : 0;
    }

    /**
     * 是否為葉 表示底下沒有任何子節點
     */
    public boolean isLeaf() {
        return getNodeCount() == 0;
    }

    /**
     * 是否為根 表示沒有父節點
     */
    public boolean isRoot() {
        return parent == null;
    }

    /**
     * 遞迴搜尋底下所有的節點數量
     */
    public int getTotalChildNodeCount() {
        if (isLeaf())
            return 0;
        AtomicInteger count = new AtomicInteger();
        visitAllChildNodes((parent, node, level, index) -> count.getAndIncrement());
        //遞迴拜訪的子節點包括了自己 所以要扣掉自己
        return count.get() - 1;
    }

    /**
     * 遞迴搜尋拜訪底下所有的節點
     */
    public void visitAllChildNodes(TreeNodeVisitor visitor) {
        innerForeachAllChildNodes(parent, this, visitor, 0, 0);
    }

    private void innerForeachAllChildNodes(TreeNode parent,
                                           TreeNode<? extends TreeNode> node,
                                           TreeNodeVisitor visitor,
                                           int level,
                                           int index) {
        visitor.onVisit(parent, node, level, index);
        if (node.getNodes() != null) {
            for (TreeNode childNode : node.getNodes()) {
                int i = node.getNodes().indexOf(childNode);
                innerForeachAllChildNodes(node, childNode, visitor, level + 1, i);
            }
        }
    }

    /**
     * 新增一個子節點
     */
    public boolean addNode(TChild child) {
        if (getNodes() != null)
            if (getNodes().add(child)) {
                child.setParent(this);
                int id = getNodes().indexOf(child);
                child.treeNodeId.setLastId(id);
                return true;
            }
        return false;
    }

    /**
     * 移除一個子節點
     */
    public boolean removeNode(TChild child) {
        if (getNodes() != null)
            if (getNodes().remove(child)) {
                child.setParent(null);
                return true;
            }
        return false;
    }

    /**
     * 移除所有子節點
     */
    public void clearNodes() {
        if (getNodes() != null)
            getNodes().clear();
    }

    /**
     * 取得父節點
     */
    public TreeNode getParent() {
        return parent;
    }

    /**
     * 設置父節點
     */
    public void setParent(TreeNode parent) {
        this.parent = parent;
        if (parent == null)
            this.treeNodeId.reset();
        else {
            PrintUtil.testd("----------%s 設置父節點為 %s", getTag(), parent.getTag());
            ThreadUtil.sleep(1);
            visitAllChildNodes((p, node, level, index) -> {
                PrintUtil.testd("%s 重設父節點為 %s", node.getTag(), p.getTag());
                node.treeNodeId.resetFromParent(p.treeNodeId);
                ThreadUtil.sleep(1);
            });
        }

    }


    public TreeNodeId getId() {
        return treeNodeId;
    }
}
