package org.arxing.treeadapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("UnnecessaryLocalVariable")
public class DataTree<T extends TreeNode> extends TreeNode<T> {
    private List<T> nodes;

    private DataTree(List<T> nodes) {
        this.nodes = new ArrayList<>();
        for (T node : nodes) {
            addNode(node);
        }
    }

    public static <T extends TreeNode> DataTree<T> of(List<T> nodes) {
        DataTree<T> dataTree = new DataTree<>(nodes);
        return dataTree;
    }

    public static <T extends TreeNode> DataTree<T> of(T... nodes) {
        return of(new ArrayList<>(Arrays.asList(nodes)));
    }

    @Override public List<T> getNodes() {
        return nodes;
    }

    @Override public String getTag() {
        return null;
    }

    private void buildAllNodeId() {
        AtomicInteger preLevel = new AtomicInteger();
        AtomicInteger preIndex = new AtomicInteger();
        visitAllChildNodes((parent, node, level, index) -> {
            if (level > preLevel.get()) {
                //往下一階

            } else if (level < preLevel.get()) {
                //往上一階

            } else {
                //同一階

            }

            preLevel.set(level);
            preIndex.set(index);
        });
    }
}
