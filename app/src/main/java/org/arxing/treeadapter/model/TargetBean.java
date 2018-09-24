package org.arxing.treeadapter.model;

import org.arxing.treeadapter.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TargetBean extends TreeNode {
    public String name;
    public List<TargetBean> children = new ArrayList<>();

    public TargetBean(String name) {
        this.name = name;
    }

    @Override public List<? extends TreeNode> getNodes() {
        return children;
    }

    @Override public String getTag() {
        return name;
    }

}
