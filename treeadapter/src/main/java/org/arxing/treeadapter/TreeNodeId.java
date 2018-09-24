package org.arxing.treeadapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 標示出一棵樹中的某個節點的唯一值
 * 例:0, 0-0, 0-0-0, 0-1-0
 */
public class TreeNodeId {
    private List<Integer> idList;

    public TreeNodeId() {
        idList = new ArrayList<>();
        reset();
    }

    public TreeNodeId(TreeNodeId parentId) {
        this();
        resetFromParent(parentId);
    }

    private int lastIndex() {
        return idList.size() - 1;
    }

    private int lastId() {
        return idList.get(lastIndex());
    }

    public void setLastId(int id) {
        idList.set(lastIndex(), id);
    }

    public void pushId(int id) {
        idList.add(id);
    }

    public void resetFromParent(TreeNodeId parentId) {
        int lastId = lastId();
        idList.clear();
        idList.addAll(parentId.idList);
        pushId(lastId);
    }

    public void reset() {
        idList.clear();
        pushId(0);
    }

    public String getId() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < idList.size(); i++) {
            boolean isLast = i == lastIndex();
            Integer id = idList.get(i);
            sb.append(id).append(isLast ? "" : "-");
        }
        return sb.toString();
    }

    @Override public String toString() {
        return getId();
    }
}
