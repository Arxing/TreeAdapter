package org.arxing.treeadapter;

import java.util.ArrayList;
import java.util.List;

public class Section extends TreeNode<Section> {
    /**
     * 這個Section的子Section
     */
    private List<Section> childSections;
    /**
     * 這個Section投影成一維陣列後的位置
     */
    private int position;
    /**
     * 這個Section所在的層級
     */
    private int level;


    public Section() {
        childSections = new ArrayList<>();
    }

    @Override public List<Section> getNodes() {
        return childSections;
    }

    @Override public String getTag() {
        return null;
    }
}
