package org.arxing.treeadapter.model;

import org.arxing.treeadapter.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PropertyBean extends TreeNode {
    public String name;
    public List<TargetBean> games = new ArrayList<>();

    public PropertyBean(String name) {
        this.name = name;
    }

    @Override public List<? extends TreeNode> getNodes() {
        return games;
    }

    @Override public String getTag() {
        return name;
    }
}
