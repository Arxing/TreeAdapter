package org.arxing.treeadapter.model;

import org.arxing.treeadapter.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class RootBean extends TreeNode {
    public List<CategoryBean> nodes = new ArrayList<>();

    @Override public List<? extends TreeNode> getNodes() {
        return nodes;
    }

    @Override public String getTag() {
        return "root";
    }
}
