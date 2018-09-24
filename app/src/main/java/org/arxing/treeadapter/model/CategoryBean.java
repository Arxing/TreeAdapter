package org.arxing.treeadapter.model;

import org.arxing.treeadapter.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class CategoryBean extends TreeNode {

    public String name;
    public List<PropertyBean> areas = new ArrayList<>();

    public CategoryBean(String name) {
        this.name = name;
    }

    @Override public List<? extends TreeNode> getNodes() {
        return areas;
    }

    @Override public String getTag() {
        return name;
    }
}
