package org.arxing.treeadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.arxing.treeadapter.model.CategoryBean;
import org.arxing.treeadapter.model.PropertyBean;
import org.arxing.treeadapter.model.RootBean;
import org.arxing.treeadapter.model.TargetBean;
import org.arxing.treeadapter.util.PrintUtil;
import org.arxing.treeadapter.util.ThreadUtil;

public class MainActivity extends AppCompatActivity {
    private DataTree dataTree;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataTree = buildDataTree();

        //        dataTree.visitAllChildNodes((node, level, index) -> {
        //            PrintUtil.pd("TEST", "[%d]%s %s %s", level, buildTab(level), node.getTag(), node.getId());
        //            ThreadUtil.sleep(1);
        //        });
    }

    private DataTree<CategoryBean> buildDataTree() {
        RootBean root = new RootBean();


        CategoryBean c1 = new CategoryBean("英雄");

        PropertyBean p1 = new PropertyBean("火屬性");
        PropertyBean p2 = new PropertyBean("雷屬性");
        PropertyBean p3 = new PropertyBean("水屬性");

        TargetBean t1 = new TargetBean("布蘭德");
        TargetBean t2 = new TargetBean("安妮");
        TargetBean t3 = new TargetBean("雷茲");
        TargetBean t4 = new TargetBean("娜米");
        TargetBean t5 = new TargetBean("齊勒斯");

        CategoryBean c2 = new CategoryBean("道具");

        PropertyBean p10 = new PropertyBean("防具");
        PropertyBean p11 = new PropertyBean("武器");

        TargetBean t10 = new TargetBean("無盡之刃");
        TargetBean t11 = new TargetBean("多蘭之盾");

        c1.addNode(p1);
        c1.addNode(p2);
        c1.addNode(p3);

        p1.addNode(t1);
        p1.addNode(t2);
        p2.addNode(t3);
        p2.addNode(t5);
        p3.addNode(t4);

        c2.addNode(p10);
        c2.addNode(p11);

        p10.addNode(t11);
        p11.addNode(t10);


        root.addNode(c1);
        root.addNode(c2);

        root.removeNode(c2);
        c1.addNode(c2);

        ThreadUtil.sleep(5000);

        root.visitAllChildNodes((parent, node, level, index) -> {
            PrintUtil.pd("TEST", "[%d]%s %s %s", level, buildTab(level), node.getTag(), node.getId());
            ThreadUtil.sleep(1);
        });

        PrintUtil.pd("Test", "所有子節點=%d", c1.getTotalChildNodeCount());
        return DataTree.of(c1, c2);
    }

    private String buildTab(int level) {
        String t = "    ";
        String r = "";
        for (int i = 0; i < level; i++) {
            r = r.concat(t);
        }
        return r;
    }
}
